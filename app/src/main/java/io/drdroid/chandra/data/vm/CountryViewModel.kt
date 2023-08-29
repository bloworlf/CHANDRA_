package io.drdroid.chandra.data.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.drdroid.chandra.data.model.country.CountryModel
import io.drdroid.chandra.data.repository.Repository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CountryViewModel(
    private val repository: Repository
) : ViewModel(), KoinComponent {

    val countries: MutableLiveData<List<CountryModel>> by lazy {
        MutableLiveData<List<CountryModel>>()
    }

    fun getCountries(onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val value = repository.getCountries()
                if (value.isSuccessful) {
                    countries.postValue(value.body())
                } else {
                    countries.postValue(listOf())
                    value.errorBody()?.string()?.let { onError(it) }
                }
            } catch (e: Exception) {
                countries.postValue(listOf())
                e.message?.let { onError(it) }
            }

        }
    }
}