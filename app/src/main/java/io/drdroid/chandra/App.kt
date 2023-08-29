package io.drdroid.chandra

import android.app.Application
import android.content.Context
import io.drdroid.chandra.data.di.AppModule.provideOkHttpClient
import io.drdroid.chandra.data.di.AppModule.provideRetrofit
import io.drdroid.chandra.data.di.AppModule.providesCountryCall
import io.drdroid.chandra.data.di.AppModule.providesRepository
import io.drdroid.chandra.data.impl.RepositoryImpl
import io.drdroid.chandra.data.network.CountryCall
import io.drdroid.chandra.data.network.interceptor.NetworkConnectionInterceptor
import io.drdroid.chandra.data.repository.Repository
import io.drdroid.chandra.data.vm.CountryViewModel
import io.drdroid.chandra.utils.Common
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

    private val appModule = module {
        single { RepositoryImpl(get()) }
        single { provideRetrofit(get()) }
        single { providesCountryCall(get()) }
        single { providesRepository(get()) }
        single { provideOkHttpClient(applicationContext) }
        viewModel { CountryViewModel(get()) }
    }
}