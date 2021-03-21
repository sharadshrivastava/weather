package com.test.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.app.data.db.entity.City

@Dao
interface AppDao {

    @Query("SELECT * FROM City")
    fun getAll(): LiveData<List<City>>

    @Query("SELECT * FROM City WHERE id=:id")
    suspend fun getCityById(id: Int): City?

    @Query("SELECT count(*) FROM City")
    suspend fun isDBEmpty(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Query("DELETE FROM City WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM City")
    suspend fun deleteAll()
}