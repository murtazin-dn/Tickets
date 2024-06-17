package com.example.network.di

import com.example.network.TicketsNetworkDataSource
import com.example.network.retrofit.TicketsRetrofit
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module
internal class NetworkModule {

    @Provides
    @NetworkScope
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @NetworkScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        val client = OkHttpClient.Builder().apply {
            networkInterceptors().add(httpLoggingInterceptor)
        }
        return client.build()
    }

    @Provides
    @NetworkScope
    fun provideJsonConverterFactory(): Json{
        return Json
    }

    @Provides
    @NetworkScope
    fun provideNetworkDataSource(
        json: Json,
        okHttpClient: OkHttpClient
    ): TicketsNetworkDataSource {
        return TicketsRetrofit(
            json = json,
            okHttpClient = okHttpClient
        )
    }
}