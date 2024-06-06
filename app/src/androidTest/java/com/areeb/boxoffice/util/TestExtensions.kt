package com.areeb.boxoffice.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int, token: String = "") {
    javaClass.classLoader?.let {
        val inputStream = it.getResource("api-responses/$fileName").readText()


        enqueue(
            if (token.isEmpty()) {
                MockResponse()
                    .setResponseCode(code)
                    .setBody(inputStream)
            } else if (token == "xxxx.test") {
                MockResponse()
                    .setResponseCode(code)
                    .setBody(inputStream)
            } else {
                MockResponse()
                    .setResponseCode(code)
            }

        )
    }

}


object Files {

    const val MOVIES_FILE = "movies/movies_response.json"
    const val MOVIES_REVIEWS_FILE = "movies/reviews_response.json"
    const val MOVIES_CREDITS_FILE = "movies/credits_response.json"
    const val MOVIE_DETAILS_FILE = "movies/movie_details_response.json"

    const val EMPTY_FILE = "empty.json"
    const val CREDITS_EMPTY_FILE = "movie_credits_empty.json"

    const val NULL_FILE = "null.json"


}



