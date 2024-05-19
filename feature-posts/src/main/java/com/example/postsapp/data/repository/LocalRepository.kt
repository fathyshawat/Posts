package com.example.postsapp.data.repository

import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel

interface LocalRepository {

    suspend fun getPosts(): List<PostsModel?>?

    suspend fun addPost(post: PostRequestModel)

    suspend fun updatePost(post: PostRequestModel)

    suspend fun deletePost(id: Int)

    suspend fun deleteAll()
}