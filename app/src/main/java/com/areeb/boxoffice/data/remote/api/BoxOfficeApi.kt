package com.areeb.boxoffice.data.remote.api

import com.areeb.boxoffice.data.remote.util.ApiResponse
import com.areeb.boxoffice.data.remote.model.CreditsResponse
import com.areeb.boxoffice.data.remote.model.MovieDetailsResponse
import com.areeb.boxoffice.data.remote.model.MovieReviewsResponse
import com.areeb.boxoffice.data.remote.model.MoviesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoxOfficeApi {

    @GET(GET_MOVIES)
    fun getMovies(
        @Path("type") type: String,
        @Query("page") page: Int
    ): Flow<ApiResponse<MoviesResponse>>


    @GET(GET_SEARCH_MOVIES)
    fun searchForMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Flow<ApiResponse<MoviesResponse>>


    @GET(GET_MOVIE_DETAILS)
    fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Flow<ApiResponse<MovieDetailsResponse>>


    @GET(GET_MOVIE_CREDITS)
    fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): Flow<ApiResponse<CreditsResponse>>

    @GET(GET_MOVIE_REVIEWS)
    fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): Flow<ApiResponse<MovieReviewsResponse>>



    companion object {
        const val GET_MOVIES = "/3/movie/{type}?language=en-US"
        const val GET_SEARCH_MOVIES = "/3/search/movie?language=en-US"
        const val GET_MOVIE_DETAILS = "/3/movie/{movie_id}?language=en-US"
        const val GET_MOVIE_CREDITS = "/3/movie/{movie_id}/credits?language=en-US"
        const val GET_MOVIE_REVIEWS = "/3/movie/{movie_id}/reviews?language=en-US"
    }
}