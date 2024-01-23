package com.example.project.repository

import com.example.project.dataclasses.UniqueEmailValidationResponse
import com.example.project.dataclasses.ValidateEmailBody
import com.example.project.utils.APIConsumer
import com.example.project.utils.RequestStatus
import com.example.project.utils.SimplifiedMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.Body

class AuthRepository(private val consumer :APIConsumer) {
   fun validateEmailAddress(body: ValidateEmailBody) : Flow<RequestStatus<UniqueEmailValidationResponse>> = flow{
       emit(RequestStatus.Waiting)
       val response = consumer.validateEmailAddress(body)
       if(response.isSuccessful){
           emit((RequestStatus.Success(response.body()!!)))
       }else{
           emit(RequestStatus.Error(SimplifiedMessage.get(response.errorBody()!!.byteStream().reader().readText())))
       }
   }
}