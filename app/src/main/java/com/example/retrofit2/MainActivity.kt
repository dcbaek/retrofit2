package com.example.retrofit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.retrofit2.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var userName: String = ""
    var userPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        User_data()
    }

    fun User_data() {

        var login_button = findViewById<Button>(R.id.login_button)
        var userid = findViewById<EditText>(R.id.userid)
        var userpw = findViewById<EditText>(R.id.userpw)

        login_button.setOnClickListener{

            userName = userid.text.toString()
            userPassword = userpw.text.toString()

            val user = User()

            user.userName = userid.text.toString()
            user.userPassword = userpw.text.toString()

            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)

            Log.d("BUTTON CLICKED", "id: " + user.userName + ", pw: " + user.userPassword)

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