package com.example.retrofit2

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api: API

    var gson = GsonBuilder()
        .setLenient()
        .create()

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl("http://서버ip:8080/connection/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        api = retrofit.create(API ::class.java)
    }
}