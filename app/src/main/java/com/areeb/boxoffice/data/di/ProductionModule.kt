package com.areeb.boxoffice.data.di

import android.content.Context
import androidx.room.Room
import com.areeb.boxoffice.data.cache.database.BoxOfficeDatabase
import com.areeb.boxoffice.data.cache.database.BoxOfficeDatabase.Companion.DATABASE_NAME
import com.areeb.boxoffice.data.remote.util.calladapter.FlowCallAdapterFactory
import com.areeb.boxoffice.data.remote.util.config.AuthenticationInterceptor
import com.areeb.boxoffice.data.remote.util.config.ConnectivityAwareUrlClient
import com.areeb.boxoffice.data.util.DataSourceConstants.NETWORK_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val productionModule = module {
    single { createDatabase(androidContext()) }
    single { createOkHttpClient(androidContext()) }
    single { createRetrofit(get()) }
}

fun createDatabase(context: Context): BoxOfficeDatabase {
    return Room.databaseBuilder(
        context,
        BoxOfficeDatabase::class.java, DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}


fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(okHttpClient)
        .baseUrl(NETWORK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .build()
}


fun createOkHttpClient( context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .callTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(AuthenticationInterceptor())
        .addInterceptor(ConnectivityAwareUrlClient(context))
        .addInterceptor(httpLoggingInterceptor)
        .build()
}