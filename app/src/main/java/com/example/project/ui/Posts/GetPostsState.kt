package com.example.project.ui.Posts

import com.example.project.dataclasses.Post

sealed class GetPostsState {
    data object Idle: GetPostsState()
    data object Loading: GetPostsState()
    data class Error(val error: String): GetPostsState()
    data class Success(val posts: List<Post>): GetPostsState()
}