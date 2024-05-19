package com.example.postsapp.data.request

data class PostsRequestItem(
    val id: Int? = 1,
    val title: String? = null,
    val body: String? = null,
    val userId: Int? = 1
)
