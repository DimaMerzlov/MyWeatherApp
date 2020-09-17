package dima_merzlov.com.myweatherapp.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("location_type")
    @Expose
    var locationType: String? = null

    @SerializedName("woeid")
    @Expose
    var woeid: Int? = null

    @SerializedName("latt_long")
    @Expose
    var lattLong: String? = null
}