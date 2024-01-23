package com.example.project.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.repository.AuthRepository
import java.security.InvalidParameterException

class SignupActivityViewModelFactory(private val authRepository: AuthRepository, private val application: Application):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupActivityViewModel::class.java)){
            return SignupActivityViewModel(authRepository,application) as T
            }
        throw InvalidParameterException("unable")
    }
}