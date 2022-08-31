package com.example.retrofit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var id: String = ""
    var pw: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        User_data()
    }

    fun User_data() {

        var login_button = findViewById<Button>(R.id.login_button)
        var userId = findViewById<EditText>(R.id.userId)
        var userPassword = findViewById<EditText>(R.id.userPassword)

        login_button.setOnClickListener{
            id = userId.text.toString()
            pw = userPassword.text.toString()
            val user = User()
            user.id = userId.text.toString()
            user.password = userPassword.text.toString()

            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)

            Log.d("BUTTON CLICKED", "id: " + user.id + ", pw: " + user.password)

            Login(user)
        }

    }

    fun Login(user: User){
        val call = RetrofitBuilder.api.getLoginResponse(user)
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