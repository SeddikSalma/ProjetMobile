package com.example.project.ui.Posts

import com.example.project.dataclasses.Post

sealed class CreatePostState {
    data object Idle: CreatePostState()
    data object Loading: CreatePostState()
    data class Error(val error: String): CreatePostState()
    data class Success(val newPost: Post): CreatePostState()
}