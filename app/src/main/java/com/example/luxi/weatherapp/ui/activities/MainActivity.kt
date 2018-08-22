package com.example.luxi.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ForecastListAdapter
import android.support.v7.widget.Toolbar
import com.example.luxi.weatherapp.R
import com.example.luxi.weatherapp.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)

        doAsync {
            val result = RequestForecastCommand(94080).execute()
            uiThread { _ ->
                val adapter = ForecastListAdapter(result){
                    startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                toolbarTitle = "Weather in ${result.city} (${result.country})"
            }
        }
    }
}