package com.example.postsapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.postsapp.entity.Posts

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<Posts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Posts)

    @Update
    suspend fun updatePost(post: Posts)

    @Query("DELETE FROM posts WHERE id = :id")
    suspend fun deletePost(id: Int)


    @Query("DELETE FROM posts")
    fun deleteTable()
}