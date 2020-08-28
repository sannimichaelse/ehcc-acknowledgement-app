package com.katana.koin

import android.app.Application
import com.katana.koin.di.mvvmModule
import com.utils.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

/**
 * Created by tomiwa on 10:28 2018-12-19
 */
class MvvmKoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.init(true)
        startKoin {
            androidContext(this@MvvmKoinApplication)
            modules(mvvmModule)
            logger(EmptyLogger())
        }
    }
}