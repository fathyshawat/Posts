package com.example.postsapp.network

import com.example.postsapp.model.GeneralNetworkModel
import com.example.postsapp.resProvider.IResourceProvider
import com.example.postsapp.utils.ApiException


fun createApiException(
    resourceProvider: IResourceProvider,
    errorResponse: GeneralNetworkModel?
): ApiException.ApiError {
    return ApiException.ApiError(
        message = errorResponse?.error?.info
            ?: (resourceProvider.getText(R.string.general_network_error)),
        statusCode = errorResponse?.error?.code.toString()
    )
}



