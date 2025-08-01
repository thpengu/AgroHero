package dev.pengui.di

import PlantAnalyzerApi
import PlantIdApi
import android.content.Context
import dev.pengui.app.data.permission.PermissionManager
import dev.pengui.app.data.repository.MenuRepository
import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.data.repository.impl.MenuRepoImpl
import dev.pengui.app.data.repository.impl.WeatherRepoImpl
import dev.pengui.app.domain.usecase.GetMenuItemUseCase
import dev.pengui.app.domain.usecase.GetWeatherUseCase
import dev.pengui.app.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
//    single { createOkHttpClient() }
//    single { createRetrofit(get()) }
//    single { createPlantIdApi(get()) }
}

val repositoryModule = module {
    //single<PlantAnalysisRepository> { PlantAnalysisRepositoryImpl(get(), get()) }
}

val domainModule = module {
    factory { GetMenuItemUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
}

val dataModule = module {
    // Repositories
    single<MenuRepository> { MenuRepoImpl() }
    single<WeatherRepository> { WeatherRepoImpl() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
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