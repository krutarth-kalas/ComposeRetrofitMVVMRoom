package com.example.composeretrofitmvvmroomcode.network

data class Resource<out T>(val status: APIStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T, message: String = ""): Resource<T> =
            Resource(status = APIStatus.SUCCESS, data = data, message = message)

        fun <T> error(data: T?, message: String = ""): Resource<T> =
            Resource(status = APIStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?, message: String = ""): Resource<T> =
            Resource(status = APIStatus.LOADING, data = data, message = message)

        fun <T> exception(/*data: T?,*/ message: String = ""): Resource<T> =
            Resource(status = APIStatus.EXCEPTION, data = null, message = message)

        fun <T> default(/*data: T?,*/ message: String = ""): Resource<T> =
            Resource(status = APIStatus.DEFAULT, data = null, message = message)
    }
}