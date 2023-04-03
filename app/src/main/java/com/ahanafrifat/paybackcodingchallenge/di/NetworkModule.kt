package com.ahanafrifat.paybackcodingchallenge.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.ahanafrifat.paybackcodingchallenge.data.local.PayBackDatabase
import com.ahanafrifat.paybackcodingchallenge.data.remote.PaybackApi
import com.ahanafrifat.paybackcodingchallenge.data.repository.RemoteDataSourceImpl
import com.ahanafrifat.paybackcodingchallenge.domain.repository.RemoteDataSource
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.networkInterceptors().add(httpLoggingInterceptor)
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.connectTimeout(15, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePaybackApi(retrofit: Retrofit): PaybackApi {
        return retrofit.create(PaybackApi::class.java)
    }

    @Provides
    @Singleton
    fun providePaybackRepository(paybackApi: PaybackApi, payBackDatabase: PayBackDatabase, @ApplicationContext context: Context): RemoteDataSource {
        return RemoteDataSourceImpl(paybackApi = paybackApi, paybackDatabase = payBackDatabase, context = context)
    }

}