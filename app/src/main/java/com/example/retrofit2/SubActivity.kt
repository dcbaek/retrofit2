package com.example.retrofit2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubActivity : AppCompatActivity() {

    var pulse_data: String = ""
    var temperature_data: String = ""
    var blood_sugar_data: String = ""

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

            pulse_data = sub_pulse_data.text.toString()
            temperature_data = sub_temperature_data.text.toString()
            blood_sugar_data = sub_blood_sugar_data.text.toString()
            val user = User()
            user.pulse_data = sub_pulse_data.text.toString()
            user.temperature_data = sub_temperature_data.text.toString()
            user.blood_sugar_data = sub_blood_sugar_data.text.toString()

            Log.d("BUTTON CLICKED", "pulse_data: " + user.pulse_data +
                    ", temperature_data: " + user.temperature_data +
                    ", blood_sugar_data: " + user.blood_sugar_data)

            Login(user)
        }

        btn_retun_data.setOnClickListener() {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }

    }

    fun Login(user: User){
        val call = RetrofitBuilder.api.BodyResponse(user)
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