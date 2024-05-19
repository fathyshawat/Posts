package com.example.postsapp.di

import com.example.postsapp.network.BuildConfig
import com.example.postsapp.network.adapter.ApiCallAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val REQUEST_TIME_OUT: Long = 60
fun OkHttpClient.Builder.applyCommonConfiguration(
  headersInterceptor: Interceptor,
  loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient.Builder {
  this
    .readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
    .connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
    .addInterceptor(headersInterceptor)
  if (BuildConfig.DEBUG)
    this.addNetworkInterceptor(loggingInterceptor)

  return this
}

fun Retrofit.Builder.applyCommonConfiguration(apiCallAdapterFactory: ApiCallAdapterFactory): Retrofit.Builder {
  addCallAdapterFactory(apiCallAdapterFactory)
  return this
}