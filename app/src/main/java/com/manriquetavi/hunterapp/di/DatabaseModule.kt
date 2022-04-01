package com.manriquetavi.hunterapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manriquetavi.hunterapp.data.local.HxHDatabase
import com.manriquetavi.hunterapp.data.repository.LocalDataSourceImpl
import com.manriquetavi.hunterapp.domain.repository.LocalDataSource
import com.manriquetavi.hunterapp.util.Constants.HUNTERXHUNTER_DATABASE
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
    fun provideDatabase(@ApplicationContext context: Context): HxHDatabase = Room.databaseBuilder(
        context,
        HxHDatabase::class.java,
        HUNTERXHUNTER_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: HxHDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            hxHDatabase = database
        )
    }
}