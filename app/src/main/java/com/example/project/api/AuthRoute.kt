package com.example.project.api

import com.example.project.dataclasses.BaseResponse
import com.example.project.dataclasses.register.RegisterBody
import com.example.project.dataclasses.register.RegisterResponse
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.dataclasses.login.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthRoute {
    @POST("auth/register")
    suspend fun registerUser(@Body body: RegisterBody) :Response<BaseResponse<RegisterResponse>>

    @POST("auth/login")
    suspend fun loginUser(@Body body: LoginRequestBody) :Response<BaseResponse<LoginResponse>>

    @POST("auth/refresh")
    fun refreshToken(@Header("Authorization") refresh: String): Call<BaseResponse<LoginResponse>>
}