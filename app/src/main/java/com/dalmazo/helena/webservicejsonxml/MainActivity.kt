package com.dalmazo.helena.webservicejsonxml

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.dalmazo.helena.webservicejsonxml.model.json.Temperature
import com.dalmazo.helena.webservicejsonxml.model.xml.request.TemperatureCelsiusToFahrenheitRequest
import com.dalmazo.helena.webservicejsonxml.model.xml.response.TemperatureCelsiusToFahrenheitResponse
import com.dalmazo.helena.webservicejsonxml.service.json.TemperatureService
import com.dalmazo.helena.webservicejsonxml.service.xml.TemperatureCalculatorService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import org.simpleframework.xml.core.Persister
import java.io.StringWriter

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCalculateTemperatureButton()
        initTemperatureList()
    }

    private fun initCalculateTemperatureButton() {
        textView = findViewById(R.id.temperature_calculated)
        editText = findViewById(R.id.temperature_edit_text)

        val button = findViewById<Button>(R.id.calculate_temperature_button)

        button.setOnClickListener {
            doAsync(::calculateTemperature, ::showTemperatureCalculated).execute()
        }
    }

    private fun calculateTemperature(): TemperatureCelsiusToFahrenheitResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl(TemperatureCalculatorService.BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        val temperatureCelsiusToFahrenheitRequest = TemperatureCelsiusToFahrenheitRequest()
        temperatureCelsiusToFahrenheitRequest.request.celsius = editText.text.toString()

        val persister = Persister()
        val stringWriter = StringWriter()
        persister.write(temperatureCelsiusToFahrenheitRequest, stringWriter)

        val service: TemperatureCalculatorService = retrofit.create(TemperatureCalculatorService::class.java)
        val call = service.calculateCelsiusToFahrenheit(RequestBody.create(MediaType.parse("text/xml"), stringWriter.toString()))

        return call.execute().body()
    }

    private fun showTemperatureCalculated(temperatureCalculated: Any?) {
        if (temperatureCalculated == null) return

        if (editText.length() < 1) {
            editText.error = "Campo obrigatório"
            return
        }

        var result = (temperatureCalculated as TemperatureCelsiusToFahrenheitResponse).response.result
        result = "Fahrenheit: ${result}"
        textView.text = result
    }

    private fun initTemperatureList() {
        val listView = findViewById<ListView>(R.id.temperature_list)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        doAsync(::listTemperatures, ::addTemperatureIListItems).execute()
    }

    private fun listTemperatures(): List<Temperature> {
        val retrofit = Retrofit.Builder()
            .baseUrl(TemperatureService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: TemperatureService = retrofit.create(
            TemperatureService::class.java)

        val call = service.list()

        return call.execute().body() as List<Temperature>
    }

    private fun addTemperatureIListItems(temperatureList: Any?) {
        if (temperatureList == null) return

        val temperatureNameList = (temperatureList as List<Temperature>).map {
            "${it.name} (${it.symbol}) \nPonto de congelamento da água: ${it.freezingPointOfWater} \nPonto de ebulição da água: ${it.boilingPointOfWater}"
        }
        adapter.addAll(temperatureNameList)
        adapter.notifyDataSetChanged()
    }

    class doAsync(val handler: () -> Any?, val callback: (any: Any?) -> Unit) : AsyncTask<Void, Void, Any?>() {
        override fun doInBackground(vararg params: Void?): Any? {
            return handler()
        }

        override fun onPostExecute(result: Any?) {
            super.onPreExecute()

            callback(result)
        }
    }
}
