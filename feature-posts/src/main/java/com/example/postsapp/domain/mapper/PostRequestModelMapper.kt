package com.example.postsapp.domain.mapper

import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.mappers.BaseMapper
import javax.inject.Inject

class PostRequestModelMapper @Inject constructor() : BaseMapper<PostsModel, PostRequestModel?> {

    override fun map(model: PostsModel): PostRequestModel {
        return PostRequestModel(
            id = model.id,
            title = model.title,
            body = model.body,
            userID = model.userID
        )
    }
}