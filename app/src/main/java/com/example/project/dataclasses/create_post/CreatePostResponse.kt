package com.example.project.dataclasses.create_post;

import com.example.project.dataclasses.Post
import com.google.gson.annotations.SerializedName

data class CreatePostResponse (
    @SerializedName("post")
    val post: Post
)
