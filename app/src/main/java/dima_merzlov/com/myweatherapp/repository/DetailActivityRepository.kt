package dima_merzlov.com.myweatherapp.repository

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import dima_merzlov.com.myweatherapp.network.BASE_URL
import dima_merzlov.com.myweatherapp.network.WeatherNetwork
import dima_merzlov.com.myweatherapp.network.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivityRepository(var application: Application?) {
    val showProgress = MutableLiveData<Boolean>()
    var myResponse = MutableLiveData<WeatherResponse>()
    val handler:Handler= Handler(Looper.getMainLooper())

    fun getWeather(woeid: Int){
        showProgress.value=true

        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        val service=retrofit.create(WeatherNetwork::class.java)
        val thread=Thread(Runnable {
            service.getWeather(woeid).enqueue(object :Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    handler.post(Runnable {
                        showProgress.value=false
                        myResponse.value=response.body()

                    })
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    handler.post(Runnable {
                        showProgress.value=false
                        Log.d("TEG","Error while accessing API"+t.toString())
                        //Toast.makeText(application,"Error while accessing API", Toast.LENGTH_LONG).show()
                    })
                }

            })
        })
        thread.start()
    }
}