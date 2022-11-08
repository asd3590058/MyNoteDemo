package com.example.utils

/**
 *@package com.example.utils
 *@author https://github.com/asd3590058
 *@fileName AppConfig
 *@date 2022/11/8 11:37
 *@description
 */
object AppConfig {
     var mLoginEntity : LoginEntity ?=null
}

data class LoginEntity(
    val name:String ?=null,
    val age :Int =0
)