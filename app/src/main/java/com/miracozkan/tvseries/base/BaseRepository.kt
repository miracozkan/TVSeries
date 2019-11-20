package com.miracozkan.tvseries.base

import android.util.Log
import com.miracozkan.tvseries.utils.Result
import retrofit2.Response


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 10/05/19 - 19:21            │
//└─────────────────────────────┘


abstract class BaseRepository {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return Result.success(body)!!
            }
            return error(response.message(), response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), e.hashCode())
        }
    }

    private fun <T> error(message: String, errorCode: Int): Result<T> {
        Log.e("BaseRepo_message -->", message)
        Log.e("BaseRepo_cod -->", errorCode.toString())
        return Result.error("Network call has failed for a following reason: $message")
    }
}