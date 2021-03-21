package com.test.app.di

import android.app.Application
import androidx.room.Room
import com.test.app.data.db.AppDB
import com.test.app.data.db.AppDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun dao(app: Application): AppDao = Room.databaseBuilder(
        app, AppDB::class.java, "app-db"
    ).build().appDao()
}