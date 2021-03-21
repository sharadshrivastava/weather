package com.test.app.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = ["city_name", "country_name"], unique = true)))
data class City(
    @ColumnInfo(name = "city_name") val cityName: String?,
    @ColumnInfo(name = "country_name") val CountryName: String?
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}