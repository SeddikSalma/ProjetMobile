package com.example.project.utils

import com.example.project.dataclasses.BaseResponse
import com.example.project.dataclasses.RegisterBody
import com.example.project.dataclasses.RegisterResponse
import com.example.project.dataclasses.UniqueEmailValidationResponse
import com.example.project.dataclasses.ValidateEmailBody
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.dataclasses.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {
    @POST("auth/register")
    suspend fun registerUser(@Body body: RegisterBody) :Response<BaseResponse<RegisterResponse>>

    @POST("auth/login")
    suspend fun loginUser(@Body body: LoginRequestBody) :Response<BaseResponse<LoginResponse>>
}