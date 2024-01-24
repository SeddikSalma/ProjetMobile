package com.example.project.api

import com.example.project.dataclasses.BaseResponse
import com.example.project.dataclasses.Post
import com.example.project.dataclasses.create_post.CreatePostBody
import com.example.project.dataclasses.create_post.CreatePostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostRoute {
    @POST("post/")
    suspend fun createPost(@Body body: CreatePostBody): Response<BaseResponse<CreatePostResponse>>

    @GET("post/")
    suspend fun getPosts(): Response<BaseResponse<List<Post>>>
}