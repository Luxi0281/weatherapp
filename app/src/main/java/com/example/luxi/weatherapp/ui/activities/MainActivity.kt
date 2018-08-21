package com.example.luxi.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ForecastListAdapter
import com.example.luxi.weatherapp.R
import com.example.luxi.weatherapp.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(94080).execute()
            uiThread {
                val adapter = ForecastListAdapter(result){
                    startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
            }
            title = "Weather in ${result.city} (${result.country})"
        }
    }
}