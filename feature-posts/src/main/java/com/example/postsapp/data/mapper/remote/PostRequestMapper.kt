package com.example.postsapp.data.mapper.remote

import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.data.request.PostsRequestItem
import com.example.postsapp.mappers.BaseMapper
import javax.inject.Inject

class PostRequestMapper @Inject constructor() : BaseMapper<PostRequestModel, PostsRequestItem> {

    override fun map(model: PostRequestModel): PostsRequestItem {
        return PostsRequestItem(
            userId = model.userID, id = model.id, title = model.title, body = model.body
        )
    }
}