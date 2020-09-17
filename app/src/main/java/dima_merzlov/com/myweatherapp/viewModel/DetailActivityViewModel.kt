package dima_merzlov.com.myweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dima_merzlov.com.myweatherapp.network.model.WeatherResponse
import dima_merzlov.com.myweatherapp.repository.DetailActivityRepository

class DetailActivityViewModel : ViewModel() {
    var application: Application?=null
    private var repository = DetailActivityRepository(application)
    val showProgress:MutableLiveData<Boolean>
    val response : MutableLiveData<WeatherResponse>
    init {
        showProgress=repository.showProgress
        response=repository.myResponse
    }
    fun getWeather(woeid: Int){
        repository.getWeather(woeid)
    }
}