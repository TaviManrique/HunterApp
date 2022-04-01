package com.manriquetavi.hunterapp.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.manriquetavi.hunterapp.data.local.HxHDatabase
import com.manriquetavi.hunterapp.data.remote.HxHApi
import com.manriquetavi.hunterapp.data.repository.RemoteDataSourceImpl
import com.manriquetavi.hunterapp.domain.repository.RemoteDataSource
import com.manriquetavi.hunterapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideHxHApi(retrofit: Retrofit): HxHApi {
        return retrofit.create(HxHApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(
        hxHApi: HxHApi,
        hxHDatabase: HxHDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            hxHApi = hxHApi,
            hxHDatabase = hxHDatabase
        )
    }
}