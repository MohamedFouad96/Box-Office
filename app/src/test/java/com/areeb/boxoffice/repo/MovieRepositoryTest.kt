package com.areeb.boxoffice.repo


import com.areeb.boxoffice.data.cache.dao.MoviesDao
import com.areeb.boxoffice.data.cache.dao.RemoteKeysDao
import com.areeb.boxoffice.data.cache.entity.MovieEntity
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.data.util.NetworkState
import com.areeb.boxoffice.data.util.Resource
import com.areeb.boxoffice.di.DependencyContainer
import com.areeb.boxoffice.util.MainCoroutineRule
import com.areeb.boxoffice.util.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.lastOrNull
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryTest {

    //Setup
    private lateinit var repo: IMovieRepository
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
        repo = dependencyContainer.movieRepo
    }




    @Test
    fun getWatchListMoviesWithNoBookmarkedMoviesThenShouldReturnEmptyList() = mainCoroutineRule.runTest {

        val response =  repo.getWatchListMovies().lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.isEmpty(), `is`(true))

    }



    @Test
    fun getWatchListMoviesWithBookmarkedMoviesThenShouldReturnBookmarkedMovieList() = mainCoroutineRule.runTest {

        movieDao.insertMovies(listOf(MovieEntity(1, isBookmarked = true)))
        val response =  repo.getWatchListMovies().lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.isNotEmpty(), `is`(true))

    }



    @Test
    fun searchForMoviesThenShouldListOfMovies() = mainCoroutineRule.runTest {


        val response =  repo.searchForMovies("spider").lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.size, `is`(100))
        MatcherAssert.assertThat(
            response?.data?.first()?.id,
            CoreMatchers.`is`(1)
        )
    }


    @Test
    fun searchForMoviesWithInvalidDataThenReturnBackendErrorResponse() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.BackendError)


        val response =  repo.searchForMovies("spider").lastOrNull()

        print("Response: ${response?.message}")

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.ERROR))
        MatcherAssert.assertThat(response?.data, CoreMatchers.nullValue())

    }


    @Test
    fun searchForMoviesWithEmptyResponseThenReturnSuccessWithNullValues() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.EmptyData)

        val response =  repo.searchForMovies("spider").lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.message, CoreMatchers.nullValue())
        MatcherAssert.assertThat(response?.data?.isEmpty(), `is`(true))

    }


    @Test
    fun getMovieDetailsThenShouldReturnMovieDetails() = mainCoroutineRule.runTest {


        val response =  repo.getMovieDetails(1).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.overview, `is`("overview"))

    }


    @Test
    fun getMovieDetailsWithInvalidDataThenReturnBackendErrorResponse() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.BackendError)


        val response =  repo.getMovieDetails(1).lastOrNull()


        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.ERROR))
        MatcherAssert.assertThat(response?.data, CoreMatchers.nullValue())

    }


    @Test
    fun getMovieDetailsWithEmptyResponseThenReturnSuccessWithNullValues() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.EmptyData)

        val response =  repo.getMovieDetails(1).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.message, CoreMatchers.nullValue())
        MatcherAssert.assertThat(response?.data, CoreMatchers.nullValue())

    }




    @Test
    fun getMovieReviewsThenShouldReturnListOfReviews() = mainCoroutineRule.runTest {


        val response =  repo.getMovieReviews(1).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.size, `is`(1))
        MatcherAssert.assertThat(
            response?.data?.first()?.content,
            `is`("content")
        )
    }


    @Test
    fun getMovieReviewsWithInvalidDataThenReturnBackendErrorResponse() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.BackendError)


        val response =  repo.getMovieReviews(1).lastOrNull()


        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.ERROR))
        MatcherAssert.assertThat(response?.data, CoreMatchers.nullValue())

    }



    @Test
    fun getMovieReviewsWithEmptyResponseThenReturnSuccessWithNullValues() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.EmptyData)

        val response =  repo.getMovieReviews(1).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.message, CoreMatchers.nullValue())
        MatcherAssert.assertThat(response?.data?.isEmpty(), `is`(true))

    }




    @Test
    fun getMovieCastThenShouldReturnListOfCast() = mainCoroutineRule.runTest {


        val response =  repo.getMovieCast(1).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.data?.size, `is`(1))
        MatcherAssert.assertThat(
            response?.data?.first()?.originalName,
            CoreMatchers.`is`("originalName")
        )
    }


    @Test
    fun getMovieCastWithInvalidDataThenReturnBackendErrorResponse() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.BackendError)


        val response =  repo.getMovieCast(1).lastOrNull()



        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.ERROR))
        MatcherAssert.assertThat(response?.data, CoreMatchers.nullValue())

    }


    @Test
    fun getMovieCastWithEmptyResponseThenReturnSuccessWithNullValues() = mainCoroutineRule.runTest {
        dependencyContainer.setWebServiceStateTo(state = NetworkState.EmptyData)

        val response =  repo.getMovieCast(1).lastOrNull()

        MatcherAssert.assertThat(response?.status, `is`(Resource.Status.SUCCESS))
        MatcherAssert.assertThat(response?.message, CoreMatchers.nullValue())
        MatcherAssert.assertThat(response?.data?.isEmpty(), `is`(true))

    }


}