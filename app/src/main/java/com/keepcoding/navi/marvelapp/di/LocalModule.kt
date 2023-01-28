package com.keepcoding.navi.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.keepcoding.navi.marvelapp.data.local.CharacterDAO
import com.keepcoding.navi.marvelapp.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java, "marvel-database"
        ).build()
    }

    @Provides
    fun provideDao(database: MarvelDatabase): CharacterDAO {
        return database.getDao()
    }
}