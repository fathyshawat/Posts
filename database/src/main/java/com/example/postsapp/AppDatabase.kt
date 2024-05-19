package com.example.postsapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postsapp.entity.Posts


@Database(entities = [Posts::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostDao
}