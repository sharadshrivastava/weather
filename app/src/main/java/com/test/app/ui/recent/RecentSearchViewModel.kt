package com.test.app.ui.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.app.WeatherApp
import com.test.app.data.AppRepository
import com.test.app.data.db.entity.City
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecentSearchViewModel : ViewModel() {

    @Inject
    lateinit var appRepository: AppRepository

    val cityList: LiveData<List<City>>?

    init {
        WeatherApp.get()?.component?.inject(this)
        cityList = appRepository.getAllCity()
    }

    fun delete(id: Int) = viewModelScope.launch { appRepository.delete(id) }

    fun deleteAll() = viewModelScope.launch { appRepository.deleteAll() }
}