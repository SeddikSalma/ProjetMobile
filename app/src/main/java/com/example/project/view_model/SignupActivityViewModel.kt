package com.example.project.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.dataclasses.User
import com.example.project.dataclasses.ValidateEmailBody
import com.example.project.repository.AuthRepository
import com.example.project.utils.RequestStatus
import kotlinx.coroutines.launch

class SignupActivityViewModel(val authRepository: AuthRepository, val application: Application):ViewModel() {
 private var isLoading : MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value=false }
 private var errorMessage: MutableLiveData<HashMap<String, String>> = MutableLiveData()
 private var isUniqueEmail : MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value=false }
 private var user : MutableLiveData<User> = MutableLiveData()
 fun getIsLoading():LiveData<Boolean> = isLoading
 fun getErrorMessage():LiveData<HashMap<String, String>> = errorMessage
 fun getIsUnique():LiveData<Boolean> = isUniqueEmail
 fun getUser(): LiveData<User> = user
 fun validateEmailAddress(body:ValidateEmailBody){
  viewModelScope.launch {
   authRepository.validateEmailAddress(body).collect{
    when(it){
     is RequestStatus.Waiting->{
        isLoading.value=true
     }
     is RequestStatus.Success->{
        isLoading.value=false
        isUniqueEmail.value=true
     }
     is RequestStatus.Error->{
        isLoading.value=false
        errorMessage.value=it.message
     }

    }
   }
  }
 }

}