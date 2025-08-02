package dev.pengui.di

import com.google.android.gms.location.LocationServices
import dev.pengui.BuildConfig
import dev.pengui.app.data.datasource.remote.WeatherApi
import dev.pengui.app.data.permission.PermissionManager
import dev.pengui.app.data.repository.DefaultLocationClient
import dev.pengui.app.data.repository.LocationClient
import dev.pengui.app.data.repository.MenuRepository
import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.data.repository.impl.MenuRepoImpl
import dev.pengui.app.data.repository.impl.WeatherRepoImpl
import dev.pengui.app.domain.usecase.GetMenuItemUseCase
import dev.pengui.app.domain.usecase.GetWeatherUseCase
import dev.pengui.app.presentation.viewmodel.HomeViewModel
import dev.pengui.app.presentation.viewmodel.MainViewModel
import dev.pengui.app.presentation.viewmodel.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}

val domainModule = module {
    factory { GetMenuItemUseCase(get()) }
    factory { GetWeatherUseCase(get()) }
}

val dataModule = module {
    single<MenuRepository> { MenuRepoImpl() }
    single<WeatherRepository> { WeatherRepoImpl(get(), get(named("API_KEY"))) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(getMenuItems = get(), getWeather = get(), locationClient = get()) }
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { MainViewModel() }
}

val appModule = module {
    single { PermissionManager(get()) }
    single<LocationClient> {
        DefaultLocationClient(
            context = androidContext(),
            client = LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }
}

val configModule = module {
    single(named("API_KEY")) { BuildConfig.WEATHER_API_KEY }
}