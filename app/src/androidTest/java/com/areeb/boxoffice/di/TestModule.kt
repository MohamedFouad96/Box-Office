package com.areeb.boxoffice.di

import android.content.Context
import androidx.room.Room
import com.areeb.boxoffice.data.cache.database.BoxOfficeDatabase
import com.areeb.boxoffice.data.remote.util.calladapter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val testModule = module {
    single { createDatabase(androidContext()) }
    single { createMockWebServer() }
    single { createOkHttpClient() }
    single { createFakeRetrofit(get(),get()) }
}

fun createDatabase(context: Context): BoxOfficeDatabase {
    return Room.inMemoryDatabaseBuilder(
        context,
        BoxOfficeDatabase::class.java
    )
        .fallbackToDestructiveMigration()
        .build()
}

fun createMockWebServer(): MockWebServer {
    return MockWebServer()
}


fun createFakeRetrofit(mockWebServer: MockWebServer,okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(okHttpClient)
        .baseUrl(mockWebServer.url("").toUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .build()

}


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .callTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}