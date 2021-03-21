package com.test.app.data

import com.test.app.data.db.AppDao
import com.test.app.data.db.entity.City
import com.test.app.data.network.WeatherApi
import com.test.app.data.network.model.Response
import com.test.app.data.network.wrapper.Resource
import com.test.app.data.network.wrapper.ResponseHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    var weatherApi: WeatherApi, var appDao: AppDao?
) {

    //As of now retrofit doesn't support error handling with coroutines so errors need to be handled using try catch.
    //In coming releases they will introduce onSuccess and onError standard methods.

    suspend fun weatherByCity(query: String): Resource<Response> =
        try {
            ResponseHandler.handleSuccess(weatherApi.weatherByCity(query))
        } catch (e: Exception) {
            ResponseHandler.handleException(e)
        }

    suspend fun weatherByZipCode(zipCode: String): Resource<Response> =
        try {
            ResponseHandler.handleSuccess(weatherApi.weatherByZipCode(zipCode))
        } catch (e: Exception) {
            ResponseHandler.handleException(e)
        }

    fun getAllCity() = appDao?.getAll()

    suspend fun saveCity(city: String?, country: String?) = appDao?.insert(City(city, country))

    suspend fun delete(id: Int) = appDao?.delete(id)

    suspend fun deleteAll() = appDao?.deleteAll()

    suspend fun getCityById(id: Int) = appDao?.getCityById(id)

    suspend fun isDBEmpty() = appDao?.isDBEmpty()
}