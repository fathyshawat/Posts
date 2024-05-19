package com.example.postsapp.data.repository

import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel
import okhttp3.ResponseBody
import retrofit2.Response

interface RemoteRepository {

    suspend fun getPosts(): List<PostsModel?>?

    suspend fun addPost(post: PostRequestModel): PostsModel

    suspend fun updatePost(id: Int, post: PostRequestModel): PostsModel

    suspend fun deletePost(id: Int): Response<ResponseBody>
}