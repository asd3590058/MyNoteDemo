package com.example.extensions

import com.example.network.AppException
import com.example.network.BaseResponseBody
import com.example.network.ResultState
import com.hjq.toast.ToastUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

inline fun CoroutineScope.repeatLaunch(
    interval: Long, repeatCount: Int = Int.MAX_VALUE, delayTime: Long = 0L, context: CoroutineContext = EmptyCoroutineContext,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    check(interval > 0) { "timeDelta must be large than 0" }
    check(repeatCount > 0) { "repeatCount must be large than 0" }
    return launch(context) {
        if (delayTime > 0) delay(delayTime)
        repeat(repeatCount) {
            block()
            delay(interval)
        }
    }
}


/**
 * 跟后台交互的统一返回实体
 * @receiver FlowCollector<BaseResultState<BaseResponseBody<T>>>
 * @param resultState BaseResponseBody<T>
 */
suspend fun <T> FlowCollector<ResultState<BaseResponseBody<T>>>.parseResultData(resultState: BaseResponseBody<T>) {
    emit(
        when (resultState.code) {//业务层和后台约定返回code
            //请求成功 200
            200 -> ResultState.onSuccess(resultState)
            //请求失败 500
            500 -> ResultState.onFailure(resultState.message)
            //请求失败 其他code
            else -> ResultState.onError(AppException(resultState.code, resultState.message))
        }
    )
}

//防止一些其他异常问题 导致闪退 使用try catch
fun Throwable.catchException() {
    try {
        when (this) {
            is HttpException -> {
                val data = this.response()?.errorBody()?.source()?.buffer?.snapshot()?.utf8()
                val result = data?.run {
                    fromJsonByBaseResponseBody<Any>(this)
                }
                when (result?.code) {
                    401 -> {ToastUtils.show(result.message)}
                    403 -> { ToastUtils.show(result.message) }
                    412 -> { ToastUtils.show(result.message) }
                    else -> { ToastUtils.show(result?.message) }
                }

            }
            is ConnectException -> {
                ToastUtils.show("")

            }
            is SocketTimeoutException -> {
                ToastUtils.show("")
            }
            else -> {
                this.printStackTrace()

            }
        }
    }catch (e:Exception){
        e.printStackTrace()
    }

}