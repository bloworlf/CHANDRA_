package io.drdroid.chandra.data.network.response

class ApiResponse<T : Any> {
    var status: Boolean = true
    var message: String = ""
    var data: T? = null
}