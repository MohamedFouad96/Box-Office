package com.areeb.boxoffice.data.util

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val throwable: Throwable? = null
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?,message: String? = null): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                message
            )
        }

        fun <T> error(msg: String?= null, data: T? = null, throwable: Throwable? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(msg: String? = null, data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                msg
            )
        }
    }
}