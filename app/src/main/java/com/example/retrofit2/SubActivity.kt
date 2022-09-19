package com.example.retrofit2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit2.entity.Bloodsugar
import com.example.retrofit2.entity.Pulse
import com.example.retrofit2.entity.Temperature
import com.example.retrofit2.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubActivity : AppCompatActivity() {

    var pulseData: String = ""
    var temperatureData: String = ""
    var bloodsugarData: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        Body_data()
    }

    fun Body_data() {

        var btn_retun_data = findViewById<Button>(R.id.btn_return_login)
        var btn_body_data = findViewById<Button>(R.id.btn_body_data)
        var sub_pulse_data = findViewById<EditText>(R.id.sub_pulse_data)
        var sub_temperature_data = findViewById<EditText>(R.id.sub_temperature_data)
        var sub_blood_sugar_data = findViewById<EditText>(R.id.sub_blood_sugar_data)

        btn_body_data.setOnClickListener {

            pulseData = sub_pulse_data.text.toString()
            temperatureData = sub_temperature_data.text.toString()
            bloodsugarData = sub_blood_sugar_data.text.toString()

            val pulse = Pulse()
            val temperature = Temperature()
            val bloodsugar = Bloodsugar()

            pulse.pulseData = sub_pulse_data.text.toString()
            temperature.temperatureData = sub_temperature_data.text.toString()
            bloodsugar.bloodsugarData = sub_blood_sugar_data.text.toString()

            Log.d("BUTTON CLICKED", "pulseData: " + pulse.pulseData +
                    "temperData: " + temperature.temperatureData +
                    "bloodsugarData: " + bloodsugar.bloodsugarData)

            pulseData(pulse)
            temperatureData(temperature)
            bloodsugarData(bloodsugar)
        }

        btn_retun_data.setOnClickListener() {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }

    }

    fun pulseData(pulse: Pulse){
        val call = RetrofitBuilder.api.getPusleResponse(pulse)
        call.enqueue(object : Callback<String> {
            // 통신에 성곻
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())
                }   else { // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    fun temperatureData(temperature: Temperature){
        val call = RetrofitBuilder.api.getTemperatureResponse(temperature)
        call.enqueue(object : Callback<String> {
            // 통신에 성곻
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())
                }   else { // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

    fun bloodsugarData(bloodsugar: Bloodsugar){
        val call = RetrofitBuilder.api.getBloodsugarResponse(bloodsugar)
        call.enqueue(object : Callback<String> {
            // 통신에 성곻
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful()) { // 응답 잘 받은 경우
                    Log.d("RESPONSE: ", response.body().toString())
                }   else { // 통신 성공 but 응답 실패
                    Log.d("RESPONSE", "FAILURE")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 통신에 실패한 경우
                Log.d("CONNECTION FAILURE: ", t.localizedMessage)
            }
        })
    }

}