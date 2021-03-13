package com.example.rssreader.model.api.entity.presentation

// kotlin.Result のインターフェイスと揃えている
sealed class RequestResult<T> {
    data class Success<T>(val data: T) : RequestResult<T>()
    data class Failure<T>(val throwable: Throwable) : RequestResult<T>()

    companion object {
        fun <T> success(data: T): RequestResult<T> = Success(data)
        fun <T> failure(throwable: Throwable): RequestResult<T> = Failure(throwable)
    }

    fun isSuccess(): Boolean {
        return this !is Failure
    }

    fun isFailure(): Boolean {
        return this is Failure
    }

    fun exceptionOrNull(): Throwable? {
        return if (this is Failure) {
            this.throwable
        } else {
            null
        }
    }

    fun getOrNull(): T? {
        return if (this is Success) {
            this.data
        } else {
            null
        }
    }
}

inline fun <T> RequestResult<T>.onFailure(action: (exception: Throwable) -> Unit): RequestResult<T> {
    exceptionOrNull()?.let {
        action(it)
    }
    return this
}

inline fun <T> RequestResult<T>.onSuccess(action: (value: T) -> Unit): RequestResult<T> {
    if (this is RequestResult.Success) {
        action(this.data)
    }
    return this
}

class NetworkError(val responseCode: Int) : Throwable()
