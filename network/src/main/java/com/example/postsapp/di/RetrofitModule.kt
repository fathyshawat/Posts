package com.example.postsapp.di

import com.example.postsapp.di.Annotations.CoreNetwork
import com.example.postsapp.network.adapter.ApiCallAdapterFactory
import com.example.postsapp.resProvider.IResourceProvider
import com.example.postsapp.utils.Headers
import com.example.postsapp.utils.Headers.Keys.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideHeadersInterceptor(): Interceptor =
        Interceptor { chain ->
            val newRequest = chain
                .request()
                .newBuilder()
                .addHeader(Headers.Keys.ACCEPT, Headers.Values.ACCEPT_VALUE)
                .addHeader(Headers.Keys.CONTENT_TYPE, Headers.Values.ACCEPT_VALUE)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @CoreNetwork
    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient
        .Builder()
        .applyCommonConfiguration(
            headersInterceptor = headersInterceptor,
            loggingInterceptor = loggingInterceptor,
        )
        .retryOnConnectionFailure(false)
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @CoreNetwork
    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        apiCallAdapterFactory: ApiCallAdapterFactory,
        @CoreNetwork okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .applyCommonConfiguration(apiCallAdapterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiCallFactory(iResourceProvider: IResourceProvider) = ApiCallAdapterFactory(iResourceProvider)
}