package com.example.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
/**
*@package
*@author https://github.com/asd3590058
*@date 2022/4/7 14:04
*@description 返回数据基类
*/
@Parcelize
@JsonClass(generateAdapter = true)
data class BaseResponseBody<T>(
    var code: Int = 0,
    var message: String = "",
    @Json(name = "data")
    var data: @RawValue T? = null
) : Parcelable
