package com.example.project.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.repository.AuthRepository
import com.example.project.utils.APIService
import com.example.project.view_model.LoginActivityViewModel
import java.security.InvalidParameterException

class LoginActivityViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)){
            return LoginActivityViewModel(AuthRepository(APIService.getService())) as T
        }
        throw InvalidParameterException("Unable to create LoginActivityViewModel")
    }
}