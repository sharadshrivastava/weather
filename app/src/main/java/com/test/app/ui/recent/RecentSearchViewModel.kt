package com.test.app.ui.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.app.data.AppRepository
import com.test.app.data.db.entity.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentSearchViewModel  @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    val cityList: LiveData<List<City>>? = appRepository.getAllCity()

    fun delete(id: Int) = viewModelScope.launch { appRepository.delete(id) }

    fun deleteAll() = viewModelScope.launch { appRepository.deleteAll() }
}