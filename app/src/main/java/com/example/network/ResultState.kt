package com.example.network


/**
 *@package com.example.network
 *@author https://github.com/asd3590058
 *@fileName BaseResultState
 *@date 2022/9/13 15:05
 *@description
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onLoading(isShow: Boolean): ResultState<T> = Loading(isShow)
        fun <T> onSuccess(data: T?): ResultState<T> = Success(data)
        fun <T> onFailure(message: String?): ResultState<T> = Failure(message)
        fun <T> onError(error: AppException): ResultState<T> = Error(error)
    }

    object Init : ResultState<Nothing>()
    data class Loading(val isShow: Boolean) : ResultState<Nothing>()
    data class Success<out T>(val data: T?) : ResultState<T>()
    data class Failure<out T>(val message: String?) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}

