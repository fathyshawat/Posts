package com.example.postsapp.data.mapper.remote

import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.data.response.PostsResponseItem
import com.example.postsapp.mappers.BaseMapper
import javax.inject.Inject

class PostItemMapper @Inject constructor() : BaseMapper<PostsResponseItem, PostsModel> {
    override fun map(model: PostsResponseItem): PostsModel {
        return PostsModel(
            success = model.id != 0,
            id = model.id ?: 0,
            title = model.title,
            body = model.body,
            userID = model.userId
        )
    }
}