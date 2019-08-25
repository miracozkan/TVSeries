package com.miracozkan.tvseries.utils


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 25.08.2019 - 10:08            │
//└─────────────────────────────┘

sealed class Resource<out T> {
    data class Loading<out T>(var loading: Boolean) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): Resource<T> = Loading(isLoading)
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> failure(e: Throwable): Resource<T> = Failure(e)
    }
}