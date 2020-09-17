package dima_merzlov.com.myweatherapp.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dima_merzlov.com.myweatherapp.R
import dima_merzlov.com.myweatherapp.adapter.LocationAdapter
import dima_merzlov.com.myweatherapp.viewModel.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchActivityViewModel
    private lateinit var adapter:LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProvider.NewInstanceFactory().create(SearchActivityViewModel::class.java)
        //viewModel = ViewModelProvider.NewInstanceFactory().create(SearchActivityViewModel::class.java)
        iv_search.setOnClickListener {
            viewModel.application=this.application
            if (et_search.text!!.isNotEmpty())
            viewModel.searchLocation(et_search.text.toString())
        }
        viewModel.showProgress.observe(this, Observer {
            if (it){
                search_progress.visibility=VISIBLE
            }else{
                search_progress.visibility=GONE
            }
        })
        viewModel.locationList.observe(this, Observer{

            adapter.setLocationList(it)
        })
        adapter= LocationAdapter(this)
        rv_search.adapter=adapter
    }
}