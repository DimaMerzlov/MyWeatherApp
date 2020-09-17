package dima_merzlov.com.myweatherapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dima_merzlov.com.myweatherapp.R
import dima_merzlov.com.myweatherapp.network.model.Location
import dima_merzlov.com.myweatherapp.view.DetailActivity
import kotlinx.android.synthetic.main.rv_location_child.view.*
import kotlin.math.log

class LocationAdapter(private val context: Context) :
    RecyclerView.Adapter<LocationAdapter.MyViewHolder>() {
    private var list: List<Location> = ArrayList()

    fun setLocationList(list: List<Location>){
        this.list=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_location_child,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].title
        holder.latLng.text = list[position].lattLong
        holder.rootView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("location", list[position].woeid)
            intent.putExtra("name", list[position].title)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name = v.tv_location_name!!
        var latLng = v.tv_lat_lng!!
        var rootView = v.child_root!!


    }
}