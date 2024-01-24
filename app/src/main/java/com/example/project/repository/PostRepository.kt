package com.example.project.repository

import com.example.project.api.PostRoute
import com.example.project.dataclasses.create_post.CreatePostBody
import com.example.project.api.RequestStatus
import com.example.project.api.SimplifiedMessage
import kotlinx.coroutines.flow.flow

class PostRepository(private val consumer : PostRoute) {
    fun createPost(body: CreatePostBody) = flow {
        emit(RequestStatus.Waiting)
        val response = consumer.createPost(body)
        if (response.isSuccessful) {
            emit((RequestStatus.Success(response.body()!!)))
        } else {
            emit(
                RequestStatus.Error(
                    SimplifiedMessage.get(
                        response.errorBody()!!.byteStream().reader().readText()
                    )
                )
            )
        }
    }

    fun getPosts() = flow {
        emit(RequestStatus.Waiting)
        val response = consumer.getPosts()
        if (response.isSuccessful) {
            emit((RequestStatus.Success(response.body()!!)))
        } else {
            emit(
                RequestStatus.Error(
                    SimplifiedMessage.get(
                        response.errorBody()!!.byteStream().reader().readText()
                    )
                )
            )
        }
    }
}