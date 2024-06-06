package com.areeb.boxoffice.di

import com.areeb.boxoffice.data.remote.api.BoxOfficeApi
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteApiModule = module {
    single { createTMDBService(get()) }
}

fun createTMDBService(retrofit: Retrofit) : BoxOfficeApi {
    return  retrofit.create(BoxOfficeApi::class.java)
}