package com.example.project.repository

import com.example.project.utils.APIConsumer
import com.example.project.utils.RequestStatus
import kotlinx.coroutines.flow.flow
import retrofit2.http.Body
import java.util.concurrent.Flow

class AuthRepository(private val consumer :APIConsumer) {
   fun validateEmailAddress(body: ValidateEmailBody):Flow<RequestStatus<UniqueEmailValidationResponse>> = flow{
       emit(RequestStatus.Waiting)
       val response = consumer.validateEmailAddress(body)
       if(response.isSuccessful){
           emit((RequestStatus.Success(response.body())))
       }else{
           emit(RequestStatus.Error())
       }
   }
}