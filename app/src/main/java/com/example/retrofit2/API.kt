package com.example.retrofit2

import com.example.retrofit2.entity.Bloodsugar
import com.example.retrofit2.entity.Pulse
import com.example.retrofit2.entity.Temperature
import com.example.retrofit2.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.*

public interface API {
    //login
    @POST("/api/v1/join")
    fun getJoinResponse(
        @Body user: User
    ) : Call<String>

    @POST("/login")
    fun getLoginResponse()

    //Body_data
    @POST("/pulse-data")
    fun getPusleResponse(@Body pulse: Pulse) : Call<String>

    //Body_data
    @POST("/temperature-data")
    fun getTemperatureResponse(@Body temperature: Temperature) : Call<String>

    //Body_data
    @POST("/bloodsugar-data")
    fun getBloodsugarResponse(@Body bloodsugar: Bloodsugar) : Call<String>

}