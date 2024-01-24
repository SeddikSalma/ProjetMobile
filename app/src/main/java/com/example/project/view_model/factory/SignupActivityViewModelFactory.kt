package com.example.project.view_model.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.repository.AuthRepository
import com.example.project.utils.APIService
import com.example.project.view_model.SignupActivityViewModel
import java.security.InvalidParameterException

class SignupActivityViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupActivityViewModel::class.java)){
            return SignupActivityViewModel(AuthRepository(APIService.getService())) as T
            }
        throw InvalidParameterException("unable")
    }
}