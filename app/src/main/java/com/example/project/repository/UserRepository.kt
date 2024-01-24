package com.example.project.repository

import com.example.project.dataclasses.register.RegisterBody
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.api.AuthRoute
import com.example.project.api.RequestStatus
import com.example.project.api.SimplifiedMessage
import com.example.project.api.UserRoute
import kotlinx.coroutines.flow.flow

class UserRepository(private val consumer : UserRoute) {
    fun getCurrentUser() = flow {
        emit(RequestStatus.Waiting)
        val response = consumer.getCurrentUser()
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
}