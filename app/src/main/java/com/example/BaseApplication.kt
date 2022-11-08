package com.example

import android.app.Application
import kotlin.properties.Delegates

/**
 *@package com.example
 *@author https://github.com/asd3590058
 *@fileName BaseApplication
 *@date 2022/11/8 13:44
 *@description
 */
class BaseApplication :Application(){
    companion object {
        var instance: BaseApplication by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
    }
}