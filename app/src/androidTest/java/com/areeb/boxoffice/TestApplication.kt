package com.areeb.boxoffice

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.areeb.boxoffice.data.di.daoModule
import com.areeb.boxoffice.data.di.remoteApiModule
import com.areeb.boxoffice.di.testModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        startKoin {
            androidContext(context)
            modules(listOf(testModule, daoModule, remoteApiModule))
        }
    }

}