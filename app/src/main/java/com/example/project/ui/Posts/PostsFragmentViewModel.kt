package com.example.project.ui.Posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.dataclasses.Post
import com.example.project.dataclasses.create_post.CreatePostBody
import com.example.project.repository.PostRepository
import com.example.project.api.RequestStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsFragmentViewModel(private val postRepository: PostRepository) : ViewModel() {
    private val loginResult: MutableLiveData<CreatePostState> = MutableLiveData<CreatePostState>().apply {
        value = CreatePostState.Idle
    }

    fun getNewPostResult(): LiveData<CreatePostState> = loginResult
    private var _posts: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    val posts = _posts.asStateFlow()

    fun createPost(body: CreatePostBody){
        viewModelScope.launch {
            postRepository.createPost(body).collect {
                when(it) {
                    is RequestStatus.Waiting -> {
                        loginResult.value = CreatePostState.Loading
                    }
                    is RequestStatus.Success -> {
                        val post = it.data.data!!.post
                        loginResult.value = CreatePostState.Success(post)

                        _posts.value = listOf(post) + posts.value;
                    }
                    is RequestStatus.Error -> {
                        loginResult.value = CreatePostState.Error(it.message["message"]!!)
                    }
                }
            }
        }
    }

    fun getPosts(){
        viewModelScope.launch {
            postRepository.getPosts().collect {
                when(it) {
                    is RequestStatus.Waiting -> {
                        Log.d("TESSSSSST", "loading")
//                        posts.value = GetPostsState.Loading
                    }
                    is RequestStatus.Success -> {
                        Log.d("TESSSSSST", "Success")
                        _posts.value = it.data.data!! // GetPostsState.Success(it.data.data!!)
                    }
                    is RequestStatus.Error -> {
                        Log.d("TESSSSSST", "err" + it.message["message"]!!)
//                        posts.value = GetPostsState.Error(it.message["message"]!!)
                    }
                }
            }
        }
    }
}