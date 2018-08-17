package com.example.luxi.weatherapp.domain.commands

import com.example.luxi.weatherapp.data.server.ForecastRequest
import com.example.luxi.weatherapp.domain.commands.mappers.ForecastDataMapper
import com.example.luxi.weatherapp.domain.commands.model.ForecastList

class RequestForecastCommand (private val zipCode: Long, private val countryCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode, countryCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}