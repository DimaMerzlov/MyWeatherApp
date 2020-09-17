package dima_merzlov.com.myweatherapp.repository

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dima_merzlov.com.myweatherapp.network.BASE_URL
import dima_merzlov.com.myweatherapp.network.WeatherNetwork
import dima_merzlov.com.myweatherapp.network.model.Location
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(var application: Application?) {
    var showProgress=MutableLiveData<Boolean>()
    var handler=Handler(Looper.getMainLooper())
    val locationList=MutableLiveData<List<Location>>()

    fun changeState(){
        if (showProgress.value !=null && showProgress.value!!){
            showProgress.value=false
        }else{
            showProgress.value=true
        }
    }

    fun searchLocation(searchString: String) {
        showProgress.value=true
        //
        var retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(WeatherNetwork::class.java)
        var thread:Thread=Thread(object: Runnable{
            override fun run() {
                service.getLocation(searchString).enqueue(object :Callback<List<Location>>{
                    override fun onResponse(
                        call: Call<List<Location>>,
                        response: Response<List<Location>>
                    ) {
                        handler.post(Runnable {
                            showProgress.value=false
                            Log.d("SearchRepository","Response : ${Gson().toJson(response.body())}")
                            locationList.value=response.body()
                        })
                    }

                    override fun onFailure(call: Call<List<Location>>, t: Throwable) {

                        handler.post(Runnable {
                            Toast.makeText(application,"Error while accessing API",Toast.LENGTH_LONG).show()
                            showProgress.value=false
                        })

                    }
                })
            }
        })
        thread.start()
    }
}