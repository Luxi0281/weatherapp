package com.example.luxi.weatherapp.data.server

import com.example.luxi.weatherapp.data.db.ForecastDb
import com.example.luxi.weatherapp.domain.commands.model.Forecast
import com.example.luxi.weatherapp.domain.commands.model.ForecastList
import com.example.luxi.weatherapp.domain.datasource.ForecastDataSource

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()


    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}