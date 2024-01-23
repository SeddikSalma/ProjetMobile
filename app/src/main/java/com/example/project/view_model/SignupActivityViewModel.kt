package com.example.project.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.repository.AuthRepository

class SignupActivityViewModel(val authRepository: AuthRepository, val application: Application):ViewModel() {
 private var isLoading : MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value=false }
 private var errorMessage: MutableLiveData<HashMap<String, String>> = MutableLiveData()

 fun getIsLoading():LiveData<Boolean> = isLoading
 fun getErrorMessage():LiveData<HashMap<String, String>> = errorMessage


}