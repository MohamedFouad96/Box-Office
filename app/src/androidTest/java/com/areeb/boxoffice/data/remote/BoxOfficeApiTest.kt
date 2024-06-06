package com.areeb.boxoffice.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.data.remote.util.ApiErrorResponse
import com.areeb.boxoffice.data.remote.util.ApiSuccessResponse
import com.areeb.boxoffice.util.Files.CREDITS_EMPTY_FILE
import com.areeb.boxoffice.util.Files.EMPTY_FILE
import com.areeb.boxoffice.util.Files.MOVIES_CREDITS_FILE
import com.areeb.boxoffice.util.Files.MOVIES_FILE
import com.areeb.boxoffice.util.Files.MOVIES_REVIEWS_FILE
import com.areeb.boxoffice.util.Files.MOVIE_DETAILS_FILE
import com.areeb.boxoffice.util.Files.NULL_FILE
import com.areeb.boxoffice.util.enqueueResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class BoxOfficeApiTest: KoinTest {

    private val api: BoxOfficeApi by inject()
    private val mockServer: MockWebServer by inject()



    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun getMoviesWithSuccessCodeThenReturnMovies() = runTest {
        mockServer.enqueueResponse(MOVIES_FILE, 200)


        val response =  api.getMovies(MovieType.NowPlaying.value, 1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body , notNullValue())
        assertThat(response.body?.results?.firstOrNull() , notNullValue())
        assertThat(response.body?.results?.firstOrNull()?.id ,  `is`(278))

    }



    @Test
    fun getMoviesWith413To500NetworkCodeThenReturnErrorMsg() = runTest {

        mockServer.enqueueResponse(MOVIES_FILE, 413)


        val response =  api.getMovies(MovieType.NowPlaying.value, 1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }





    @Test
    fun getMoviesWithNullResponseThenReturnErrorMsg() = runTest {
        mockServer.enqueueResponse(NULL_FILE, 200)

        val response =  api.getMovies(MovieType.NowPlaying.value, 1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }

    @Test
    fun getMoviesWithEmptyResponseThenReturnApiSuccessResponseWithNullValues() = runTest {
        mockServer.enqueueResponse(EMPTY_FILE, 200)


        val response =  api.getMovies(MovieType.NowPlaying.value, 1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body?.results?.isEmpty() , `is`(true))

    }




    @Test
    fun searchForMoviesWithSuccessCodeThenReturnMovies() = runTest {
        mockServer.enqueueResponse(MOVIES_FILE, 200)


        val response =  api.searchForMovies("spiderman", 1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body , notNullValue())
        assertThat(response.body?.results?.firstOrNull() , notNullValue())
        assertThat(response.body?.results?.firstOrNull()?.id ,  `is`(278))

    }



    @Test
    fun searchForMoviesWith413To500NetworkCodeThenReturnErrorMsg() = runTest {

        mockServer.enqueueResponse(MOVIES_FILE, 413)


        val response =  api.searchForMovies("spiderman", 1).lastOrNull()  as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }





    @Test
    fun searchForMoviesWithNullResponseThenReturnErrorMsg() = runTest {
        mockServer.enqueueResponse(NULL_FILE, 200)

        val response =  api.searchForMovies("spiderman", 1).lastOrNull()  as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }

    @Test
    fun searchForMoviesWithEmptyResponseThenReturnApiSuccessResponseWithNullValues() = runTest {
        mockServer.enqueueResponse(EMPTY_FILE, 200)


        val response =  api.searchForMovies("spiderman", 1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body?.results?.isEmpty() , `is`(true))

    }




    @Test
    fun getMovieReviewsWithSuccessCodeThenReturnReviews() = runTest {
        mockServer.enqueueResponse(MOVIES_REVIEWS_FILE, 200)


        val response =  api.getMovieReviews(1, 1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body , notNullValue())
        assertThat(response.body?.results?.firstOrNull() , notNullValue())
        assertThat(response.body?.results?.firstOrNull()?.authorDetails?.username ,  `is`("OrlyForly"))

    }



    @Test
    fun getMovieReviewsWith413To500NetworkCodeThenReturnErrorMsg() = runTest {

        mockServer.enqueueResponse(MOVIES_REVIEWS_FILE, 413)


        val response =  api.getMovieReviews(1, 1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }





    @Test
    fun getMovieReviewsWithNullResponseThenReturnErrorMsg() = runTest {
        mockServer.enqueueResponse(NULL_FILE, 200)

        val response =  api.getMovieReviews(1, 1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }

    @Test
    fun getMovieReviewsWithEmptyResponseThenReturnApiSuccessResponseWithNullValues() = runTest {
        mockServer.enqueueResponse(EMPTY_FILE, 200)


        val response =  api.getMovieReviews(1, 1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body?.results?.isEmpty() , `is`(true))

    }



    @Test
    fun getMovieCreditsWithSuccessCodeThenReturnCast() = runTest {
        mockServer.enqueueResponse(MOVIES_CREDITS_FILE, 200)


        val response =  api.getMovieCredits(1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body , notNullValue())
        assertThat(response.body?.cast?.firstOrNull() , notNullValue())
        assertThat(response.body?.cast?.firstOrNull()?.originalName ,  `is`("Sophie McIntosh"))

    }



    @Test
    fun getMovieCreditsWith413To500NetworkCodeThenReturnErrorMsg() = runTest {

        mockServer.enqueueResponse(MOVIES_CREDITS_FILE, 413)


        val response =  api.getMovieCredits(1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }





    @Test
    fun getMovieCreditsWithNullResponseThenReturnErrorMsg() = runTest {
        mockServer.enqueueResponse(NULL_FILE, 200)

        val response =  api.getMovieCredits(1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }

    @Test
    fun getMovieCreditsWithEmptyResponseThenReturnApiSuccessResponseWithNullValues() = runTest {
        mockServer.enqueueResponse(CREDITS_EMPTY_FILE, 200)


        val response =  api.getMovieCredits(1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body?.cast?.isEmpty() , `is`(true))

    }




    @Test
    fun getMovieDetailsWithSuccessCodeThenReturnMovieDetails() = runTest {
        mockServer.enqueueResponse(MOVIE_DETAILS_FILE, 200)


        val response =  api.getMovieDetails(1).lastOrNull() as ApiSuccessResponse


        assertThat(response.body , notNullValue())
        assertThat(response.body , notNullValue())
        assertThat(response.body?.originalTitle ,  `is`("No Way Up"))

    }



    @Test
    fun getMovieDetailsWith413To500NetworkCodeThenReturnErrorMsg() = runTest {

        mockServer.enqueueResponse(MOVIE_DETAILS_FILE, 413)


        val response =  api.getMovieDetails(1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }





    @Test
    fun getMovieDetailsWithNullResponseThenReturnErrorMsg() = runTest {
        mockServer.enqueueResponse(NULL_FILE, 200)

        val response =  api.getMovieDetails(1).lastOrNull() as ApiErrorResponse


        assertThat(response.errorMessage.isNotEmpty() , `is`(true))

    }



}