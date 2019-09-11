package com.miracozkan.tvseries.base

import android.util.Log
import com.miracozkan.tvseries.utils.Output
import retrofit2.Response
import java.io.IOException


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 10/05/19 - 19:21            │
//└─────────────────────────────┘


abstract class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): T? {
        val result = newsApiOutput(call, error)
        var output: T? = null
        when (result) {
            is Output.Success ->
                output = result.output
            is Output.Error ->
                Log.e("Error", "The $error and the ${result.exception}")
        }
        return output

    }

    private suspend fun <T : Any> newsApiOutput(call: suspend () -> Response<T>, error: String): Output<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else {
            Output.Error(IOException("OOps .. Something went wrong due to  ${response.message()} and ${response.errorBody()} and ${response.code()}"))
        }
    }
}