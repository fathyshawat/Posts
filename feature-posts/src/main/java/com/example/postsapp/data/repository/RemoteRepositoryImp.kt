package com.example.postsapp.data.repository

import com.example.postsapp.data.mapper.remote.PostItemMapper
import com.example.postsapp.data.mapper.remote.PostRequestMapper
import com.example.postsapp.data.mapper.remote.PostsResponseMapper
import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.data.remote.PostsServiceApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val postServiceApi: PostsServiceApi,
    private val postRequestMapper: PostRequestMapper,
    private val postsResponseMapper: PostsResponseMapper,
    private val postItemMapper: PostItemMapper
) : RemoteRepository {
    override suspend fun getPosts(): List<PostsModel?>? =
        postsResponseMapper.map(postServiceApi.getPosts())

    override suspend fun addPost(post: PostRequestModel): PostsModel =
        postItemMapper.map(postServiceApi.addPost(postRequestMapper.map(post)))

    override suspend fun updatePost(id: Int, post: PostRequestModel): PostsModel =
        postItemMapper.map(postServiceApi.updatePost(id, postRequestMapper.map(post)))

    override suspend fun deletePost(id: Int): Response<ResponseBody> =
        postServiceApi.deletePost(id)

}