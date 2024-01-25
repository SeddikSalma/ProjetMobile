package com.example.project.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.repository.AuthRepository
import com.example.project.api.APIService
import java.security.InvalidParameterException

class LoginActivityViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)){
            return LoginActivityViewModel(AuthRepository(APIService.getAuthService())) as T
        }
        throw InvalidParameterException("Unable to create LoginActivityViewModel")
    }
}