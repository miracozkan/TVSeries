package com.miracozkan.tvseries.utils


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 05/07/19 - 14:10            │
//└─────────────────────────────┘

sealed class Output<out T : Any> {
    data class Success<out T : Any>(val output: T) : Output<T>()
    data class Error(val exception: Exception) : Output<Nothing>()
}
//
//sealed class Resource<out T> {
//    class Loading<out T> : Resource<T>()
//    data class Success<out T>(val data: T?) : Resource<T>()
//    data class Failure<out T>(val cause: String?) : Resource<T>()
//}