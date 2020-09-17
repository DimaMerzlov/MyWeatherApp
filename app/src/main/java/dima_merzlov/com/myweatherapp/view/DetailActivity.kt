package dima_merzlov.com.myweatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dima_merzlov.com.myweatherapp.R
import dima_merzlov.com.myweatherapp.viewModel.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel =
            ViewModelProvider.NewInstanceFactory().create(DetailActivityViewModel::class.java)
        viewModel.application=this.application
        if (intent.hasExtra("name")) {
            tv_location.text = intent.getStringExtra("name")
        }
        if (intent.hasExtra("location")) {
            val location = intent.getIntExtra("location",0)
            if (location > 0)

                viewModel.getWeather(location)
        }

        viewModel.showProgress.observe(this, Observer {
            if (it) {
                detail_progress.visibility = VISIBLE
            } else {
                detail_progress.visibility = GONE
            }
        })
        viewModel.response.observe(this, Observer {
            if (it != null) {
                tv_temp.text = it.consolidatedWeather!![0].theTemp.toString()
            }
        })
    }
}