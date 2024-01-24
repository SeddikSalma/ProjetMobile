package com.example.project.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.dataclasses.register.RegisterBody
import com.example.project.repository.AuthRepository
import com.example.project.utils.RequestStatus
import kotlinx.coroutines.launch

sealed class RegisterState {
    data object Idle: RegisterState()
    data object Loading: RegisterState()
    data class Error(val error: String): RegisterState()
    data class Success(val tokens: String): RegisterState()
}

class SignupActivityViewModel(private val authRepository: AuthRepository):ViewModel() {
    private val registerResult: MutableLiveData<RegisterState> = MutableLiveData<RegisterState>().apply {
        value = RegisterState.Idle
    }
    fun getRegisterResult(): LiveData<RegisterState> = registerResult

    fun  registerUser(body: RegisterBody) {
        viewModelScope.launch {
            authRepository.registerUser(body).collect{
                when(it){
                    is RequestStatus.Waiting -> {
                        registerResult.value = RegisterState.Loading
                    }
                    is RequestStatus.Success -> {
                        registerResult.value = RegisterState.Success("")
                    }
                    is RequestStatus.Error -> {
                        registerResult.value = RegisterState.Error("")
                    }
                }
            }
        }
    }
}