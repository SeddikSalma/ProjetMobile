package com.example.project.utils

import com.example.project.dataclasses.UniqueEmailValidationResponse
import com.example.project.dataclasses.ValidateEmailBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {
    @POST("user/validate-unique-email")
    fun validateEmailAddress(@Body body:ValidateEmailBody): Response<UniqueEmailValidationResponse>
}