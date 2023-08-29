package io.drdroid.chandra.data.network.callback

import io.drdroid.chandra.data.network.response.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallback<T : Any> : Callback<ApiResponse<T>> {

    abstract fun onSuccess(response: ApiResponse<T>)

    abstract fun onFailure(response: ApiResponse<T>)

    override fun onResponse(call: Call<ApiResponse<T>>, response: Response<ApiResponse<T>>) {
        if (response.isSuccessful && response.body() != null && response.code() == 200) {
            onSuccess(response.body()!!)
        } else {
            // handle 4xx & 5xx error codes here
            val resp = ApiResponse<T>()
            resp.status = false
            resp.message = response.message()
            onFailure(resp)
        }
    }

    override fun onFailure(call: Call<ApiResponse<T>>, t: Throwable) {
        val response = ApiResponse<T>()
        response.status = false
        response.message = t.message.toString()
        onFailure(response)
    }
}