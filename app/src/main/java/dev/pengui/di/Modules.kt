package dev.pengui.di

import PlantAnalyzerApi
import PlantIdApi
import android.content.Context
import dev.pengui.app.data.permission.PermissionManager
import dev.pengui.app.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Network module
val networkModule = module {
//    single { createOkHttpClient() }
//    single { createRetrofit(get()) }
//    single { createPlantIdApi(get()) }
}

// Repository module
val repositoryModule = module {
    //single<PlantAnalysisRepository> { PlantAnalysisRepositoryImpl(get(), get()) }
}

// ViewModel module
val viewModelModule = module {
    viewModel { HomeViewModel() }
    //viewModel { PlantA }
    //viewModel { P }
}

// App module (general dependencies)
val appModule = module {
    single { PermissionManager(get()) }
    //single { ImageService(get(), get()) }
    single<Context> { androidContext() }
}
val remoteDataSourceModule = module {
    //single<PlantAnalyzerApi> { PlantIdApi(get()) }
}