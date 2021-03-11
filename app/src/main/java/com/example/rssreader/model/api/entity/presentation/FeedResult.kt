package com.example.rssreader.model.api.entity.presentation

// kotlin.Result のインターフェイスと揃えている
sealed class FeedResult<T> {
    data class Success<T>(val data: T) : FeedResult<T>()
    data class Failure<T>(val throwable: Throwable) : FeedResult<T>()

    companion object {
        fun <T> success(data: T): FeedResult<T> = Success(data)
        fun <T> failure(throwable: Throwable): FeedResult<T> = Failure(throwable)
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

inline fun <T> FeedResult<T>.onFailure(action: (exception: Throwable) -> Unit): FeedResult<T> {
    exceptionOrNull()?.let {
        action(it)
    }
    return this
}

inline fun <T> FeedResult<T>.onSuccess(action: (value: T) -> Unit): FeedResult<T> {
    if (this is FeedResult.Success) {
        action(this.data)
    }
    return this
}

class NetworkError(val responseCode: Int) : Throwable()
