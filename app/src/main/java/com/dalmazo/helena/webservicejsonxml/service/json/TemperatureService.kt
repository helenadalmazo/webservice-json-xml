package com.dalmazo.helena.webservicejsonxml.service.json

import com.dalmazo.helena.webservicejsonxml.model.json.Temperature
import retrofit2.Call
import retrofit2.http.GET

interface TemperatureService {

    @GET("/temperatures")
    fun list(): Call<List<Temperature>>

    companion object {
        val BASE_URL = "https://helenadalmazo.free.beeceptor.com"
    }
}