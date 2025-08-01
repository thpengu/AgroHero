package dev.pengui.di

import android.app.Application
import dev.pengui.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)

            androidContext(this@MyApp)

            modules(
                appModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                remoteDataSourceModule,
                domainModule,
                dataModule
            )
        }
    }
}