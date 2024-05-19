package com.example.postsapp.data.mapper.local

import com.example.postsapp.entity.Posts
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.mappers.BaseMapper
import javax.inject.Inject

class PostsListLocalMapper @Inject constructor() :
    BaseMapper<List<Posts>, List<PostsModel?>?> {

    override fun map(model: List<Posts>): List<PostsModel?>? {
        return model.map {
            PostsModel(
                success = model.isNotEmpty(),
                id = it.id,
                title = it.title,
                body = it.body,
                userID = it.userID,
            )
        }
    }
}