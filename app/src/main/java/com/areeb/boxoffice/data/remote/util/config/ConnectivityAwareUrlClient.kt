package com.areeb.boxoffice.data.remote.util.config

import android.content.Context
import android.net.ConnectivityManager
import java.io.IOException

class ConnectivityAwareUrlClient(private val context: Context) : okhttp3.Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {
        if (!isOnline()) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}