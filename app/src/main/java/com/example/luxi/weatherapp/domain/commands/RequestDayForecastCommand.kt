package com.example.luxi.weatherapp.domain.commands

import com.example.luxi.weatherapp.domain.commands.model.Forecast
import com.example.luxi.weatherapp.domain.datasource.ForecastProvider

class RequestDayForecastCommand(val id: Long, private val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<Forecast>{
    override fun execute() = forecastProvider.requestForecast(id)
}