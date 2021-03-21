package com.test.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.app.data.db.entity.City

@Database(entities = [City::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun appDao(): AppDao
}