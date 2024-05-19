package com.example.postsapp.network.adapter

import com.example.postsapp.model.GeneralNetworkModel
import com.example.postsapp.resProvider.IResourceProvider
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

class ApiCallAdapterFactory(
  private val resourceProvider: IResourceProvider
) : CallAdapter.Factory() {

  override fun get(
    returnType: Type,
    annotations: Array<out Annotation>,
    retrofit: Retrofit
  ):
    CallAdapter<*, *>? {
    /***
     * suspend functions wrap the response type in `Call`
     * ***/
    if (getRawType(returnType) != Call::class.java) {
      return null
    }

    check(returnType is ParameterizedType) {
    }

    val responseType = getParameterUpperBound(0, returnType)

    val errorBodyConverter = retrofit.responseBodyConverter<GeneralNetworkModel>(
      GeneralNetworkModel::class.java,
      annotations
    )

    return ApiResponseAdapter<Any>(responseType, resourceProvider, errorBodyConverter)
  }
}