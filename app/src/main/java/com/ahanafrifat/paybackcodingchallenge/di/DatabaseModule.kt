package com.ahanafrifat.paybackcodingchallenge.di

import android.content.Context
import androidx.room.Room
import com.ahanafrifat.paybackcodingchallenge.data.local.PayBackDatabase
import com.ahanafrifat.paybackcodingchallenge.data.repository.LocalDataSourceImpl
import com.ahanafrifat.paybackcodingchallenge.domain.repository.LocalDataSource
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.PAYBACK_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PayBackDatabase {
        return Room.databaseBuilder(
            context,
            PayBackDatabase::class.java,
            PAYBACK_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: PayBackDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            hitDatabase = database
        )
    }
}