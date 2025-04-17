package com.example.smartflooddashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        RetrofitClient.instance.getData().enqueue(object : Callback<Map<String, List<SensorData>>> {
            override fun onResponse(
                call: Call<Map<String, List<SensorData>>>,
                response: Response<Map<String, List<SensorData>>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.get("data")
                    textView.text = data?.joinToString("\n") {
                        "Timestamp: ${it.timestamp}, Water level: ${it.water_level}, Temperature: ${it.temperature}, Humidity: ${it.humidity}"
                    } ?: "Aucune donnée trouvée."
                } else {
                    textView.text = "Erreur : code ${response.code()}"
                }
            }

            override fun onFailure(
                call: Call<Map<String, List<SensorData>>>,
                t: Throwable
            ) {
                textView.text = "Erreur réseau : ${t.message}"
            }
        })
    }
}