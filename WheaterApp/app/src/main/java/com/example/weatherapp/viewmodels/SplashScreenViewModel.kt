package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.City
import com.example.domain.usecases.CreateCityListUseCase
import com.example.weatherapp.contracts.SplashScreenContract
import com.example.weatherapp.utils.Data
import com.example.weatherapp.utils.Event
import com.example.weatherapp.utils.Status.CHARGED_JSON
import com.example.weatherapp.utils.Status.INIT
import com.example.weatherapp.viewmodels.CityMainViewModel.Companion.listOfCities
import com.example.weatherapp.viewmodels.CityMainViewModel.Companion.listOfCity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

class SplashScreenViewModel(
        private val createCityListUseCase: CreateCityListUseCase
) : ViewModel(), SplashScreenContract.ViewModel {
    override val mutableMainState: MutableLiveData<Event<Data<City>>> = MutableLiveData()

    override val mainState: LiveData<Event<Data<City>>>
        get() {
            return mutableMainState
        }

    override fun initAutoCompleteTextViewState() {
        mutableMainState.value = Event(Data(status = INIT))
    }

    override fun createCityList(JSON: String) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val jsonArrayString = JSONArray(JSON)
            listOfCities = createCityListUseCase(listOfCity, jsonArrayString)
            if (listOfCities.isNotEmpty()) {
                mutableMainState.postValue(Event(Data(status = CHARGED_JSON)))
            }
        }
    }
}

