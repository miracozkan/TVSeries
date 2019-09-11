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
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure<out T>(val cause: String?) : Resource<T>()
}