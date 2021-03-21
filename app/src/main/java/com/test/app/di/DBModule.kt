package com.test.app.di

import android.app.Application
import androidx.room.Room
import com.test.app.data.db.AppDB
import com.test.app.data.db.AppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun dao(app: Application): AppDao = Room.databaseBuilder(
        app, AppDB::class.java, "app-db"
    ).build().appDao()
}