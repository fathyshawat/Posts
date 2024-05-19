package com.example.postsapp.data.mapper.local

import com.example.postsapp.data.model.PostRequestModel
import com.example.postsapp.entity.Posts
import com.example.postsapp.mappers.BaseMapper
import javax.inject.Inject

class PostLocalMapper @Inject constructor() : BaseMapper<PostRequestModel, Posts> {


    override fun map(model: PostRequestModel): Posts {

        return Posts(
            id = model.id ?: 1,
            title = model.title,
            body = model.body,
            userID = model.userID
        )

    }

}