package com.example.postsapp.data.repository

import com.example.postsapp.PostDao
import com.example.postsapp.data.mapper.local.PostLocalMapper
import com.example.postsapp.data.mapper.local.PostsListLocalMapper
import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(
    private val dao: PostDao,
    private val postLocalMapper: PostLocalMapper,
    private val postsListLocalMapper: PostsListLocalMapper
) : LocalRepository {


    override suspend fun getPosts(): List<PostsModel?>? =
        postsListLocalMapper.map(dao.getAllPosts())

    override suspend fun addPost(post: PostRequestModel) = dao.insertPost(postLocalMapper.map(post))

    override suspend fun updatePost(post: PostRequestModel) =
        dao.updatePost(postLocalMapper.map(post))

    override suspend fun deletePost(id: Int) = dao.deletePost(id)

    override suspend fun deleteAll() = dao.deleteTable()
}