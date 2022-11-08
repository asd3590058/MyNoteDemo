package com.example.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat


/**
 *@package com.sinosig.khapp.common.extensions
 *@author https://github.com/asd3590058
 *@fileName 页面点击跳转工具 刨除一些原生逻辑，主要做原生跳转H5拦截
 *@date 2022/2/18 15:44
 *@description
 */
fun AppCompatActivity.startActivity(
    mClass: Class<*>,
    mCurrentFinish: Boolean? = false,
    mBundle: Bundle.() -> Unit
) {
    val bundle = Bundle().apply(mBundle)
    val intent = Intent(this, mClass).apply {
        putExtras(bundle)
    }
    startActivity(intent)
    if (mCurrentFinish!!) {
        this.finish()
    }
}


fun AppCompatActivity.hideSoftInput() {
    (this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}
fun WindowInsetsControllerCompat.hideSoftInput(){
    hide(WindowInsetsCompat.Type.ime())
}
fun WindowInsetsControllerCompat.showSoftInput(){
    show(WindowInsetsCompat.Type.ime())
}

fun Context.loadJsonFromAsserts(file: String): List<String>? =
    try {
        fromJsonByList(resources.assets.open(file).bufferedReader().use { it.readText() })
    } catch (e: Exception) {
        null
    }



