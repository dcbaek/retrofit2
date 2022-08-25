package com.example.retrofit2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.*

public interface API {
    //login
    @POST("/android")
    fun getLoginResponse(@Body user: User) : Call<String>
}