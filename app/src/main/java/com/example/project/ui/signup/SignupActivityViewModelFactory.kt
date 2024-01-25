package com.example.project.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.repository.AuthRepository
import com.example.project.api.APIService
import java.security.InvalidParameterException

class SignupActivityViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupActivityViewModel::class.java)){
            return SignupActivityViewModel(AuthRepository(APIService.getAuthService())) as T
        }
        throw InvalidParameterException("unable")
    }
}