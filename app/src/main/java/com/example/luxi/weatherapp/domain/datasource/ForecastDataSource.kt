package com.example.luxi.weatherapp.domain.datasource

import com.example.luxi.weatherapp.domain.commands.model.Forecast
import com.example.luxi.weatherapp.domain.commands.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}