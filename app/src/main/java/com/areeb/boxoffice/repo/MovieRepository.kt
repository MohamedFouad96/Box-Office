package com.areeb.boxoffice.repo


import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.entity.toDomain
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.model.MovieCast
import com.areeb.boxoffice.data.model.MovieDetails
import com.areeb.boxoffice.data.model.MovieReview
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.data.remote.model.toDomain
import com.areeb.boxoffice.data.remote.util.ApiEmptyResponse
import com.areeb.boxoffice.data.remote.util.ApiErrorResponse
import com.areeb.boxoffice.data.remote.util.ApiSuccessResponse
import com.areeb.boxoffice.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface IMovieRepository {

    fun getWatchListMovies() :  Flow<Resource<List<Movie>>>
    fun searchForMovies(query: String) : Flow<Resource<List<Movie>>>
    suspend fun changeMovieBookmark(movieId: Int,isBookmarked: Boolean)

    fun getMovieDetails(movieId: Int) : Flow<Resource<MovieDetails>>

    fun getMovieReviews(movieId: Int) : Flow<Resource<List<MovieReview>>>
    fun getMovieCast(movieId: Int) : Flow<Resource<List<MovieCast>>>

    fun isMovieBookmarked(movieId: Int) : Flow<Resource<Boolean>>

}
class MovieRepository(
    private val webService: BoxOfficeApi,
    private val moviesDao: MoviesDao
): IMovieRepository {

    private val TAG = "MovieRepository"

    override fun getWatchListMovies() =  flow<Resource<List<Movie>>>  {
        emit(Resource.loading())
        emit(Resource.success(moviesDao.getBookmarkedMovies().toDomain()))

    }

    override fun searchForMovies(query: String) = flow<Resource<List<Movie>>> {
        emit(Resource.loading())

        webService.searchForMovies(query,1).collect() { apiResponse ->
            emit(
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        Resource.success(
                            data = apiResponse.body?.toDomain(),
                        )
                    }
                    is ApiErrorResponse -> {
                        Resource.error(apiResponse.errorMessage)
                    }
                    is ApiEmptyResponse -> {
                        Resource.success(data = null)
                    }
                }
            )
        }

    }

    override suspend fun changeMovieBookmark(movieId: Int,isBookmarked: Boolean) {
        moviesDao.changeMovieBookmark(movieId,isBookmarked)
    }

    override fun getMovieDetails(movieId: Int) = flow<Resource<MovieDetails>> {
        emit(Resource.loading())

        webService.getMovieDetails(movieId).collect() { apiResponse ->
            emit(
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        Resource.success(
                            data = apiResponse.body?.toDomain(),
                        )
                    }
                    is ApiErrorResponse -> {
                        Resource.error(apiResponse.errorMessage)
                    }
                    is ApiEmptyResponse -> {
                        Resource.success(data = null)
                    }
                }
            )
        }
    }

    override fun getMovieReviews(movieId: Int) = flow<Resource<List<MovieReview>>> {
        emit(Resource.loading())

        webService.getMovieReviews(movieId,1).collect() { apiResponse ->
            emit(
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        Resource.success(
                            data = apiResponse.body?.toDomain(),
                        )
                    }
                    is ApiErrorResponse -> {
                        Resource.error(apiResponse.errorMessage)
                    }
                    is ApiEmptyResponse -> {
                        Resource.success(data = null)
                    }
                }
            )
        }
    }

    override fun getMovieCast(movieId: Int) = flow<Resource<List<MovieCast>>> {
        emit(Resource.loading())

        webService.getMovieCredits(movieId).collect() { apiResponse ->
            emit(
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        Resource.success(
                            data = apiResponse.body?.toDomain(),
                        )
                    }
                    is ApiErrorResponse -> {
                        Resource.error(apiResponse.errorMessage)
                    }
                    is ApiEmptyResponse -> {
                        Resource.success(data = null)
                    }
                }
            )
        }
    }

    override fun isMovieBookmarked(movieId: Int) = flow<Resource<Boolean>> {
        emit(Resource.loading())
        emit(Resource.success(moviesDao.getMoviesById(movieId)?.isBookmarked))
    }

}