package com.dalmazo.helena.webservicejsonxml.service.xml

import com.dalmazo.helena.webservicejsonxml.model.xml.response.TemperatureCelsiusToFahrenheitResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TemperatureCalculatorService {

    @POST("/xml/tempconvert.asmx")
    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: \"https://www.w3schools.com/xml/CelsiusToFahrenheit\""
    )
    fun calculateCelsiusToFahrenheit(@Body requestBody: RequestBody): Call<TemperatureCelsiusToFahrenheitResponse>

    companion object {
        val BASE_URL = "https://www.w3schools.com/"
    }
}