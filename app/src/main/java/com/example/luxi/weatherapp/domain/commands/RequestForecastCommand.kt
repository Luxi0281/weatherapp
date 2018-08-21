package com.example.luxi.weatherapp.domain.commands

import com.example.luxi.weatherapp.data.server.ForecastByZipCodeRequest
import com.example.luxi.weatherapp.data.server.ServerDataMapper
import com.example.luxi.weatherapp.domain.commands.model.ForecastList
import com.example.luxi.weatherapp.domain.datasource.ForecastProvider
import java.text.DateFormat

class RequestForecastCommand(private val zipCode: Long, private val  forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastList>{
    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}