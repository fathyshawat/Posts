package com.example.postsapp.data.mapper.remote

import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.data.response.PostsResponseItem
import com.example.postsapp.mappers.BaseMapper
import javax.inject.Inject

class PostsResponseMapper @Inject constructor() :
    BaseMapper<List<PostsResponseItem?>?, List<PostsModel?>?> {

    override fun map(model: List<PostsResponseItem?>?): List<PostsModel?>? {
        return model?.map {
            PostsModel(
                success = model.isNotEmpty(),
                id = it?.id ?: 0,
                title = it?.title,
                body = it?.body,
                userID = it?.userId,
            )
        }
    }
}