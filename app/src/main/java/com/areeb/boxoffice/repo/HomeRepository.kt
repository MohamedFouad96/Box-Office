package com.areeb.boxoffice.repo



import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.entity.MovieEntity
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.cache.entity.RemoteKeys
import com.areeb.boxoffice.data.cache.entity.toDomain
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.data.remote.model.MoviesResponse
import com.areeb.boxoffice.data.remote.model.toEntity
import com.areeb.boxoffice.data.remote.util.ApiSuccessResponse
import com.areeb.boxoffice.data.util.DataSourceConstants.PhotosConfig.STARTING_PAGE_INDEX
import com.areeb.boxoffice.data.util.NetworkBoundResource
import com.areeb.boxoffice.data.util.NetworkBoundResourcePagination
import com.areeb.boxoffice.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface IHomeRepository {

    fun getMoviesByType(firstPage: Boolean,type: MovieType) : Flow<Resource<List<Movie>>>
    fun getTopRatedMovies() : Flow<Resource<List<Movie>>>

}
class HomeRepository(
    private val webService: BoxOfficeApi,
    private val moviesDao: MoviesDao,
    private val remoteDao: RemoteKeysDao,
): IHomeRepository {

    private val TAG = "HomeRepository"

    override fun getMoviesByType(firstPage: Boolean, type: MovieType) =
        object : NetworkBoundResourcePagination<List<MovieEntity>, MoviesResponse, RemoteKeys>() {
            override suspend fun fetchFromLocal(remoteKey: RemoteKeys): List<MovieEntity>? {
                return moviesDao
                    .getMoviesByType(type)

            }

            override suspend fun fetchFromRemote(remoteKey: RemoteKeys) =
                webService.getMovies(
                    type.value,
                    if (firstPage) STARTING_PAGE_INDEX else remoteKey.nextKey ?: STARTING_PAGE_INDEX
                )


            override suspend fun processRemoteResponse(
                response: ApiSuccessResponse<MoviesResponse>
            ): Boolean {
                response.body?.let {
                    return processRemoteResponse(
                        it,
                        firstPage,
                        type
                    )
                }

                return false

            }

            override suspend fun fetchRemoteKey() =
                remoteDao.remoteKeysId(type.value) ?: RemoteKeys(
                    TAG,
                    STARTING_PAGE_INDEX
                )


            override suspend fun saveRemoteData(remote: MoviesResponse) {
                if (remote.results.isNotEmpty())
                    moviesDao.insertCategoryMovies(type,remote.toEntity())
            }

            override suspend fun shouldFetchFromRemote(
                local: List<MovieEntity>?,
                remoteKey: RemoteKeys?
            ) = !(remoteKey?.isEndReached ?: true)

        }.getResult().map { it.run { Resource(status, data?.toDomain(), message, throwable) } }


    private suspend fun processRemoteResponse(
        response: MoviesResponse,
        firstPage: Boolean,
        type: MovieType
    ): Boolean {

        val existPhoto = moviesDao.getMoviesByType(type)

        if (response.results.isNotEmpty()) {

            val remotePhotos = response.results

            if (firstPage && (existPhoto.isEmpty() || (existPhoto.first().originalTitle != remotePhotos.first().originalTitle))) {
                clearCache(type)
            }

        }

        if (!firstPage || (existPhoto.isEmpty() && firstPage)) {
            RemoteKeys(
                type.value,
                nextKey = response.page + 1,
                isEndReached = response.totalPages == response.page,
                prevKey = response.page,
            ).apply {
                remoteDao.insertKey(this)
            }
        }

        return existPhoto.isEmpty() || !firstPage
    }

    private suspend fun clearCache(type: MovieType) {
        moviesDao.clearMoviesByType(type)
        remoteDao.clearRemoteKey(type.value)
    }


    override fun getTopRatedMovies() = object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {
        override suspend fun fetchFromLocal() = moviesDao.getTopRatedMovies()
        override suspend fun fetchFromRemote() = webService.getMovies(MovieType.TopRated.value,1)

        override suspend fun saveRemoteData(remote: MoviesResponse) {
            moviesDao.insertCategoryMovies(MovieType.TopRated,remote.toEntity())
        }

        override suspend fun shouldFetchFromRemote(local: List<MovieEntity>?) = true

        override suspend fun processRemoteResponse(response: ApiSuccessResponse<MoviesResponse>): Boolean {
            val localCache = moviesDao.getTopRatedMovies()
            val remoteResponse = response.body?.results


            val updateData = localCache.isNotEmpty() && !remoteResponse.isNullOrEmpty() &&  localCache.first().id != remoteResponse.first().id

            if (updateData) {
                moviesDao.clearMoviesByType(MovieType.TopRated)
            }

            return  updateData || localCache.isEmpty()
        }


    }.getResult().map { it.run { Resource(status, data?.toDomain(), message, throwable) } }

}