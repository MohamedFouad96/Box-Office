package com.areeb.boxoffice.data.remote.util.config

import android.annotation.SuppressLint
import com.areeb.boxoffice.data.util.DataSourceConstants.ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException



class AuthenticationInterceptor() : Interceptor {

    private val TAG = "AuthenticationInterceptor"

    @SuppressLint("HardwareIds")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        return chain.proceed(
            chain.request().newBuilder()
                .header(
                    "Authorization", "Bearer $ACCESS_TOKEN",
                )
                .method(original.method, original.body)
                .build()
        )

    }

}