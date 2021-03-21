package com.test.app.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.data.db.AppDB
import com.test.app.data.db.AppDao
import com.test.app.data.db.entity.City
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DBTest {

    lateinit var appDao: AppDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDao = Room.inMemoryDatabaseBuilder(
            context, AppDB::class.java
        ).build().appDao()
    }

    @Test
    fun testInsert() {
        runBlocking {
            appDao.insert(City("Sydney", "AU"))
            val cities = appDao.getAll()
            Assert.assertEquals("Sydney", cities[0].cityName)
        }
    }

    @Test
    fun testDBEmpty() {
        runBlocking {
            appDao.deleteAll()
            val count = appDao.isDBEmpty()
            Assert.assertTrue(count==0)
        }
    }

    @Test
    fun testCityByID() {
        runBlocking {
            appDao.insert(City("New York", "US").apply { id = 1 })
            val city = appDao.getCityById(1)
            Assert.assertEquals("New York",city?.cityName )
        }
    }
}