package com.areeb.boxoffice.data.remote


import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import com.areeb.boxoffice.data.remote.model.AuthorDetails
import com.areeb.boxoffice.data.remote.model.Cast
import com.areeb.boxoffice.data.remote.model.CreditsResponse
import com.areeb.boxoffice.data.remote.model.MovieDetailsResponse
import com.areeb.boxoffice.data.remote.model.MovieResult
import com.areeb.boxoffice.data.remote.model.MovieReviewResult
import com.areeb.boxoffice.data.remote.model.MovieReviewsResponse
import com.areeb.boxoffice.data.remote.model.MoviesResponse
import com.areeb.boxoffice.data.remote.util.ApiResponse
import com.areeb.boxoffice.data.util.DataSourceConstants
import com.areeb.boxoffice.data.util.DataSourceConstants.PhotosConfig.MOVIES_PAGINATION_PAGE_SIZE
import com.areeb.boxoffice.data.util.NetworkState
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.concurrent.atomic.AtomicInteger

class FakeBoxOfficeApi(var state: NetworkState = NetworkState.Normal): BoxOfficeApi {


    private val movieList = generateFakeMovieList()

    var totalPages = 0



    override fun getMovies(type: String, page: Int) = flow<ApiResponse<MoviesResponse>> {
        when(state) {
            NetworkState.Normal -> {
                val pageSize = MOVIES_PAGINATION_PAGE_SIZE
                val startIndex = (page - 1) * pageSize
                val endIndex = startIndex + pageSize
                totalPages = movieList.results.size / pageSize + if (movieList.results.size % pageSize != 0) 1 else 0

                val sublist = movieList.results.subList(startIndex.coerceAtLeast(0), endIndex.coerceAtMost(movieList.results.size))

                val movies = MoviesResponse(
                    page = page,
                    totalPages = totalPages,
                    results = sublist,
                )



                emit(ApiResponse.Companion.create(Response.success(movies)))
            }
            NetworkState.BackendError -> {
                val mediaType = "application/json".toMediaTypeOrNull()
                val response = ResponseBody.create(mediaType, error_response)
                emit(ApiResponse.Companion.create(Response.error(400,response)))
            }

            NetworkState.EmptyData -> {

                val movies = MoviesResponse(
                    page = 0,
                    totalPages = 0,
                    results = listOf(),
                )


                emit(ApiResponse.Companion.create(Response.success(movies)))

            }
        }
    }

    override fun searchForMovies(query: String, page: Int) = flow<ApiResponse<MoviesResponse>> {
        when(state) {
            NetworkState.Normal -> {
                emit(ApiResponse.Companion.create(Response.success(movieList)))
            }
            NetworkState.BackendError -> {
                val mediaType = "application/json".toMediaTypeOrNull()
                val response = ResponseBody.create(mediaType, error_response)
                emit(ApiResponse.Companion.create(Response.error(400,response)))
            }

            NetworkState.EmptyData -> {

                val movies = MoviesResponse(
                    page = 0,
                    totalPages = 0,
                    results = listOf(),
                )


                emit(ApiResponse.Companion.create(Response.success(movies)))

            }
        }
    }

    override fun getMovieDetails(movieId: Int) = flow<ApiResponse<MovieDetailsResponse>> {
        when(state) {
            NetworkState.Normal -> {
                val movieDetailsResponse = MovieDetailsResponse("path", 1, "title", "overview", "poster", "2020-10-03", 12, 3.0, listOf())
                emit(ApiResponse.Companion.create(Response.success(movieDetailsResponse)))
            }
            NetworkState.BackendError -> {
                val mediaType = "application/json".toMediaTypeOrNull()
                val response = ResponseBody.create(mediaType, error_response)
                emit(ApiResponse.Companion.create(Response.error(400,response)))
            }

            NetworkState.EmptyData -> {
                emit(ApiResponse.Companion.create(Response.success(null)))
            }
        }
    }

    override fun getMovieCredits(movieId: Int) = flow<ApiResponse<CreditsResponse>> {
        when(state) {
            NetworkState.Normal -> {
                val movieCreditsResponse = CreditsResponse(listOf(Cast("originalName", "profilePath")))
                emit(ApiResponse.Companion.create(Response.success(movieCreditsResponse)))
            }
            NetworkState.BackendError -> {
                val mediaType = "application/json".toMediaTypeOrNull()
                val response = ResponseBody.create(mediaType, error_response)
                emit(ApiResponse.Companion.create(Response.error(400,response)))
            }

            NetworkState.EmptyData -> {
                emit(ApiResponse.Companion.create(Response.success(CreditsResponse(listOf()))))
            }
        }
    }

    override fun getMovieReviews(movieId: Int, page: Int) = flow<ApiResponse<MovieReviewsResponse>> {
        when(state) {
            NetworkState.Normal -> {
                val movieReviewsResponse = MovieReviewsResponse(1, listOf(
                    MovieReviewResult(
                    AuthorDetails("avatarPath","username", 10),"content"
                )
                ),totalPages)
                emit(ApiResponse.Companion.create(Response.success(movieReviewsResponse)))
            }
            NetworkState.BackendError -> {
                val mediaType = "application/json".toMediaTypeOrNull()
                val response = ResponseBody.create(mediaType, error_response)
                emit(ApiResponse.Companion.create(Response.error(400,response)))
            }

            NetworkState.EmptyData -> {
                emit(ApiResponse.Companion.create(Response.success(MovieReviewsResponse(1,listOf(),totalPages))))
            }
        }
    }





    companion object {
        const val error_response = "error_happened"
        const val backend_error_response = "backend_error_happened"

    }

}


fun generateFakeMovieList(): MoviesResponse {

    val fakeMoviesList = mutableListOf<MovieResult>()
    val idCounter = AtomicInteger(1)
    for (i in 1..100) {
        val fakeMovie = MovieResult(
            "backdropPath$i",
            idCounter.getAndIncrement(),
            "orginalTitle$i",
            "overview$i",
            "posterPath$i",
            "2020-10-15",
            i*0.1,
        )
        fakeMoviesList.add(fakeMovie)
    }
    return MoviesResponse(1,fakeMoviesList,fakeMoviesList.size / DataSourceConstants.PhotosConfig.MOVIES_PAGINATION_PAGE_SIZE + if (fakeMoviesList.size % DataSourceConstants.PhotosConfig.MOVIES_PAGINATION_PAGE_SIZE != 0) 1 else 0)
}