package com.example.project.ui.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.api.APIService
import com.example.project.repository.UserRepository
import java.security.InvalidParameterException

class ProfileFragmentViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileFragmentViewModel::class.java)){
            return ProfileFragmentViewModel(UserRepository(APIService.getUserService())) as T
        }
        throw InvalidParameterException("Unable to create PostsViewModel")
    }
}