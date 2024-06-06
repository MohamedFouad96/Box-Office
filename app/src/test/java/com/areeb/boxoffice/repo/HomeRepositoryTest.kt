package com.areeb.boxoffice.repo

import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.data.remote.FakeBoxOfficeApi
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.data.util.CacheState
import com.areeb.boxoffice.data.util.DataSourceConstants.PhotosConfig.MOVIES_PAGINATION_PAGE_SIZE
import com.areeb.boxoffice.data.util.NetworkState
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.di.DependencyContainer
import com.areeb.boxoffice.util.MainCoroutineRule
import com.areeb.boxoffice.util.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.lastOrNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    //Setup
    private lateinit var repo: IHomeRepository
    private lateinit var movieDao: MoviesDao
    private lateinit var remoteKeysDao: RemoteKeysDao
    private lateinit var api: BoxOfficeApi

    private val dependencyContainer = DependencyContainer()


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @BeforeEach
    fun setup(){
        dependencyContainer.build()
        movieDao = dependencyContainer.fakeMoviesDao
        remoteKeysDao = dependencyContainer.fakeRemoteKeys
        api = dependencyContainer.fakeBoxOfficeApi
        repo = dependencyContainer.homeRepo
    }



    @Test
    fun getMoviesByTypeWithFirstPageAndPopularTypeSuccessNetworkResponseThenShouldReturnDataFromLocal() = mainCoroutineRule.runTest {


        val response = repo.getMoviesByType(true, MovieType.Popular).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(
            response?.data?.size,
            `is`(MOVIES_PAGINATION_PAGE_SIZE)
        )


        val movie = response?.data?.first()
        MatcherAssert.assertThat(movie?.id, Matchers.`is`(1))


    }

    @Test
    fun getMoviesByTypeWithThirdPageAndPopularTypeSuccessNetworkResponseThenShouldReturnAllPagesFromLocal() = mainCoroutineRule.runTest {

        repo.getMoviesByType(true,MovieType.Popular).lastOrNull()
        repo.getMoviesByType(false,MovieType.Popular).lastOrNull()
        val response = repo.getMoviesByType(false,MovieType.Popular).lastOrNull()


        val moviesSize = MOVIES_PAGINATION_PAGE_SIZE*3


        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.size, `is`(moviesSize))
        val movie = response?.data?.last()
        MatcherAssert.assertThat(movie?.id,
            Matchers.`is`(MOVIES_PAGINATION_PAGE_SIZE * 3)
        )


    }

    @Test
    fun getMoviesByTypeWithAllPagesThenShouldReturnAllPagesFromLocal() = mainCoroutineRule.runTest {

        repo.getMoviesByType(true,MovieType.Popular).lastOrNull()
        var response: Resource<List<Movie>>? = null
        val totalPages = (api as FakeBoxOfficeApi).totalPages
        for (i in 2..totalPages) {
            response =  repo.getMoviesByType(false,MovieType.Popular).lastOrNull()

        }
        val moviesSize = MOVIES_PAGINATION_PAGE_SIZE*totalPages


        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.size, `is`(moviesSize))
        val movie = response?.data?.last()

        MatcherAssert.assertThat(
            movie?.id,
            Matchers.`is`(MOVIES_PAGINATION_PAGE_SIZE * totalPages)
        )


    }


    @Test
    fun getMoviesByTypeWithMoreThanPagesSizeThenShouldReturnOnlyCurrentPages() = mainCoroutineRule.runTest {

        repo.getMoviesByType(true,MovieType.Popular).lastOrNull()
        var response: Resource<List<Movie>>? = null
        val totalPages = (api as FakeBoxOfficeApi).totalPages
        for (i in 2..totalPages+10) {
            response = repo.getMoviesByType(false,MovieType.Popular).lastOrNull()

        }

        val moviesSize = MOVIES_PAGINATION_PAGE_SIZE*totalPages


        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.size, `is`(moviesSize))
        val movie = response?.data?.last()

        MatcherAssert.assertThat(
            movie?.id,
            Matchers.`is`(MOVIES_PAGINATION_PAGE_SIZE * totalPages)
        )


    }



    @Test
    fun getMoviesByTypeWithNetworkErrorAndCachedDataThenShouldReturnCachedData() = mainCoroutineRule.runTest {

        dependencyContainer.setWebServiceStateTo(NetworkState.BackendError)
        dependencyContainer.setCacheStateTo(CacheState.WithFullData)

        var response: Resource<List<Movie>>? = null

        repo.getMoviesByType(true,MovieType.Popular).collect {
            if(it.status == Resource.Status.LOADING && it.data != null) {
                response = it
            }
        }

        val moviesSize = MOVIES_PAGINATION_PAGE_SIZE*5


        MatcherAssert.assertThat(response?.data?.size, `is`(moviesSize))

    }

    @Test
    fun getMoviesByTypeWithNetworkErrorWithoutCachedDataThenShouldReturnEmpty() = mainCoroutineRule.runTest {


        dependencyContainer.setWebServiceStateTo(NetworkState.BackendError)

        var response: Resource<List<Movie>>? = null

        repo.getMoviesByType(true,MovieType.Popular).collect {
            if(it.status == Resource.Status.LOADING && it.data != null) {
                response = it
            }
        }

        MatcherAssert.assertThat(response?.data?.isEmpty(), Matchers.`is`(true))


    }



    @Test
    fun getTopRatedMoviesWithSuccessNetworkResponseThenShouldReturnDataFromLocal() = mainCoroutineRule.runTest {


        val response = repo.getTopRatedMovies().lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(
            response?.data?.size,
            `is`(5)
        )

        val movie = response?.data?.first()
        MatcherAssert.assertThat(movie?.id, Matchers.`is`(20))

    }

    @Test
    fun getTopRatedMoviesWithNetworkErrorAndCachedDataThenShouldReturnCachedData() = mainCoroutineRule.runTest {

        dependencyContainer.setWebServiceStateTo(NetworkState.BackendError)
        dependencyContainer.setCacheStateTo(CacheState.WithFullData)

        var response: Resource<List<Movie>>? = null

        repo.getTopRatedMovies().collect {
            if(it.status == Resource.Status.LOADING && it.data != null) {
                response = it
            }
        }


        MatcherAssert.assertThat(response?.data?.size, `is`(5))

    }

    @Test
    fun getTopRatedMoviesWithNetworkErrorWithoutCachedDataThenShouldReturnEmpty() = mainCoroutineRule.runTest {


        dependencyContainer.setWebServiceStateTo(NetworkState.BackendError)

        var response: Resource<List<Movie>>? = null

        repo.getTopRatedMovies().collect {
            if(it.status == Resource.Status.LOADING && it.data != null) {
                response = it
            }
        }

        MatcherAssert.assertThat(response?.data?.isEmpty(), Matchers.`is`(true))


    }


    @Test
    fun movieTypeFromValueThenShouldReturnMovieType() = mainCoroutineRule.runTest {



        MatcherAssert.assertThat(MovieType.fromValue("now_playing"), Matchers.`is`(MovieType.NowPlaying))


    }




}