package com.example.rssreader.model.api.entity.extension

import com.example.rssreader.model.api.entity.presentation.NetworkError
import retrofit2.Response

fun <T> Response<T>.toNetworkError(): NetworkError {
    return NetworkError(responseCode = this.code())
}