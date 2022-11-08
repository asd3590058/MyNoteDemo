package com.example.network

data class AppException(
    val errCode: Int = 0,//错误码
    val errorMsg: String? = "",
    val params: Any? = null//错误消息
)