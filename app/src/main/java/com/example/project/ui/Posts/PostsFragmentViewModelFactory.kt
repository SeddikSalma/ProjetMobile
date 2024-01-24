package com.example.project.ui.Posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.api.APIService
import com.example.project.repository.PostRepository
import com.example.project.view_model.LoginActivityViewModel
import java.security.InvalidParameterException

class PostsFragmentViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsFragmentViewModel::class.java)){
            return PostsFragmentViewModel(PostRepository(APIService.getPostService())) as T
        }
        throw InvalidParameterException("Unable to create PostsViewModel")
    }
}