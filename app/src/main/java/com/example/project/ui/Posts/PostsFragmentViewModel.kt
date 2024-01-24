package com.example.project.ui.Posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.dataclasses.Post
import com.example.project.dataclasses.create_post.CreatePostBody
import com.example.project.dataclasses.login.LoginRequestBody
import com.example.project.dataclasses.login.LoginResponse
import com.example.project.repository.PostRepository
import com.example.project.utils.RequestStatus
import com.example.project.view_model.LoginState
import kotlinx.coroutines.launch

sealed class CreatePostState {
    data object Idle: CreatePostState()
    data object Loading: CreatePostState()
    data class Error(val error: String): CreatePostState()
    data class Success(val newPost: Post): CreatePostState()
}

class PostsFragmentViewModel(private val postRepository: PostRepository) : ViewModel() {
    private val loginResult: MutableLiveData<CreatePostState> = MutableLiveData<CreatePostState>().apply {
        value = CreatePostState.Idle
    }
    fun getNewPostResult(): LiveData<CreatePostState> = loginResult

    fun createPost(body: CreatePostBody){
        viewModelScope.launch {
            postRepository.createPost(body).collect {
                when(it) {
                    is RequestStatus.Waiting -> {
                        loginResult.value = CreatePostState.Loading
                    }
                    is RequestStatus.Success -> {
                        loginResult.value = CreatePostState.Success(it.data.data!!.post)
                    }
                    is RequestStatus.Error -> {
                        loginResult.value = CreatePostState.Error(it.message["message"]!!)
                    }
                }
            }
        }
    }

    fun getPosts(){

    }
}