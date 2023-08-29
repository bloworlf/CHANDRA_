package io.drdroid.chandra.data.di

import android.content.Context
import io.drdroid.chandra.data.impl.RepositoryImpl
import io.drdroid.chandra.data.network.CountryCall
import io.drdroid.chandra.data.network.interceptor.NetworkConnectionInterceptor
import io.drdroid.chandra.data.repository.Repository
import io.drdroid.chandra.utils.Common
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppModule {

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Common.API.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    fun providesCountryCall(retrofit: Retrofit): CountryCall {
        return retrofit.create(CountryCall::class.java)
    }

    fun providesRepository(countryCall: CountryCall): Repository {
        return RepositoryImpl(
            countryCall = countryCall
        )
    }
}