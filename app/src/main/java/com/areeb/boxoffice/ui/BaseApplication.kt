package com.areeb.boxoffice.ui

import android.app.Application
import com.areeb.boxoffice.di.daoModule
import com.areeb.boxoffice.di.productionModule
import com.areeb.boxoffice.di.remoteApiModule
import com.areeb.boxoffice.di.repoModule
import com.areeb.boxoffice.di.viewModelModules
import com.google.firebase.FirebaseApp

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        initKoin()

    }


    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(productionModule, remoteApiModule, daoModule, repoModule, viewModelModules))

        }
    }

}