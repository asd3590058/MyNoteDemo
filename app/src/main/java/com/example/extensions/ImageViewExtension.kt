package com.example.extensions

import android.graphics.Typeface
import android.text.InputType
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.DrawableCompat
import com.example.mydemo.R

/**
 *@package com.example.extensions
 *@author https://github.com/asd3590058
 *@fileName ImageView扩展函数
 *@date 2022/9/7 14:46
 *@description
 */

fun AppCompatImageView.setBackTint(color: Int) {
    drawable?.let {
        DrawableCompat.setTint(it, color)
        setImageDrawable(it)
    }
}
fun AppCompatImageView.hideOrShowPwd(input: AppCompatEditText, isShowOrHide: Boolean = false) {
    if (isShowOrHide) {
        setImageResource(R.mipmap.ic_launcher)
        input.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        input.typeface = Typeface.DEFAULT
    } else {
        setImageResource(R.mipmap.ic_launcher)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        input.typeface = Typeface.DEFAULT
    }
}