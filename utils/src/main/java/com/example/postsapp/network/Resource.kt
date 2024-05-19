package com.example.postsapp.network

sealed class Resource<out T> {

  object Empty : Resource<Nothing>()

  object Loading : Resource<Nothing>()

  data class Success<out T>(val value: T) : Resource<T>()

  class Failure(val exception: Throwable) : Resource<Nothing>()

  companion object {

    fun <T> empty(): Resource<T> = Empty

    fun <T> success(data: T): Resource<T> = Success(data)
  }
}