package dima_merzlov.com.myweatherapp.network

import dima_merzlov.com.myweatherapp.network.model.Location
import dima_merzlov.com.myweatherapp.network.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://www.metaweather.com/api/location/"
//https://www.metaweather.com/api/location/search/?query=ban

interface WeatherNetwork {

    @GET("search/")
    fun getLocation(@Query("query") searchString: String): Call<List<Location>>

    @GET("{woeid}")
    fun getWeather(@Path("woeid") woeid: Int): Call<WeatherResponse>
}