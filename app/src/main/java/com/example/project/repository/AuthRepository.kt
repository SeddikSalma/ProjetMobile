package com.example.project.repository

import com.example.project.dataclasses.RegisterBody
import com.example.project.dataclasses.UniqueEmailValidationResponse
import com.example.project.dataclasses.ValidateEmailBody
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.utils.APIConsumer
import com.example.project.utils.RequestStatus
import com.example.project.utils.SimplifiedMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository(private val consumer :APIConsumer) {
    fun registerUser (body: RegisterBody) = flow {
        emit(RequestStatus.Waiting)
        val response = consumer.registerUser(body)
        if (response.isSuccessful) {
            emit((RequestStatus.Success(response.body()!!)))
        } else {
            emit(
                RequestStatus.Error(
                    SimplifiedMessage.get(
                        response.errorBody()!!.byteStream().reader().readText()
                    )
                )
            )
        }
    }

    fun loginUser(body: LoginRequestBody) = flow {
        emit(RequestStatus.Waiting)
        val response = consumer.loginUser(body)
        if(response.isSuccessful) {
            emit(RequestStatus.Success(response.body()!!))
        } else {
            response.errorBody()
            emit(
                RequestStatus.Error(
                    SimplifiedMessage.get(
                        response.errorBody()!!.byteStream().reader().readText()
                    )
                )
            )
        }
    }
}