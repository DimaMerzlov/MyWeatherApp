package dima_merzlov.com.myweatherapp.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dima_merzlov.com.myweatherapp.network.model.Location
import dima_merzlov.com.myweatherapp.repository.SearchActivityRepository

class SearchActivityViewModel() : ViewModel() {

    var application:Application?=null
    var repository = SearchActivityRepository(application)
    var showProgress: LiveData<Boolean>
    val locationList:LiveData<List<Location>>

    init {
        this.showProgress = repository.showProgress
        this.locationList=repository.locationList
    }

    fun changeState() {
        repository.changeState()
    }

    fun searchLocation(searchString:String){
        repository.searchLocation(searchString)
    }
}