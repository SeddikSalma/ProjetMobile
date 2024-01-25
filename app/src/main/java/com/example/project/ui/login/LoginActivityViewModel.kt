package com.example.project.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.dataclasses.login.LoginResponse
import com.example.project.repository.AuthRepository
import com.example.project.api.RequestStatus
import kotlinx.coroutines.launch

sealed class LoginState {
    data object Idle: LoginState()
    data object Loading: LoginState()
    data class Error(val error: String): LoginState()
    data class Success(val tokens: LoginResponse): LoginState()
}

class LoginActivityViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val loginResult: MutableLiveData<LoginState> = MutableLiveData<LoginState>().apply {
        value = LoginState.Idle
    }
    fun getLoginResult(): LiveData<LoginState> = loginResult

    fun loginUser(body: LoginRequestBody){
        viewModelScope.launch {
            authRepository.loginUser(body).collect {
                when(it) {
                    is RequestStatus.Waiting -> {
                        loginResult.value = LoginState.Loading
                    }
                    is RequestStatus.Success -> {
                        loginResult.value = LoginState.Success(it.data.data!!)
                    }
                    is RequestStatus.Error -> {
                        loginResult.value = LoginState.Error(it.message["message"]!!)
                    }
                }
            }
        }
    }
}