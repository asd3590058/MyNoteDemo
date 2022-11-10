package com.example.extensions

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.text.Selection
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.text.scale
import com.example.BaseApplication
import com.example.view.TextBackgroundSpan

/**
 *@package com.example.extensions
 *@author https://github.com/asd3590058
 *@fileName TextView扩展函数
 *@date 2022/9/7 14:28
 *@description
 */
fun AppCompatTextView.setSpanWithBrowser(
    textHead: CharSequence,
    textPolicyArray: Array<String>,
    textFoot: CharSequence,
    color: Int
) {
    val buildSpannedString = buildSpannedString {
        for (index in textPolicyArray.indices) {
            inSpans(BrowserSpan(index, color)) {
                append(textPolicyArray[index])
            }

            if (index < textPolicyArray.size - 1) {
                append(",")
            }
        }
    }
    this.text = buildSpannedString {
        append(textHead)
        append(buildSpannedString)
        append(textFoot)
    }
}

class BrowserSpan(val index: Int, val color: Int) : ClickableSpan() {
    override fun onClick(widget: View) {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val contentUrl = Uri.parse("https://www.baidu.com")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.data = contentUrl
        BaseApplication.instance.startActivity(intent)
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = color
        ds.isUnderlineText = false
    }
}

fun AppCompatTextView.setCountdownSpan(
    countdownHint: String?,
    countdownTvColor: String?,
    timerBg: String?,
    timerColor: String?,
    radius: Float,
    padding: Float,
    day: String?,
    hour: String?,
    minute: String?,
    second: String?,
    colonColor: String?
) {
    text = buildSpannedString {
        inSpans(ForegroundColorSpan(Color.parseColor(countdownTvColor))) {
            append(countdownHint)
        }
        scale(1.1f) {
            inSpans(TextBackgroundSpan(Color.parseColor(timerBg), Color.parseColor(timerColor), radius, padding)) {
                append(day)
            }
        }
        inSpans(ForegroundColorSpan(Color.parseColor(colonColor))) {
            append("天")
        }
        scale(1.1f) {
            inSpans(TextBackgroundSpan(Color.parseColor(timerBg), Color.parseColor(timerColor), radius, padding)) {
                append(hour)
            }
        }
        inSpans(ForegroundColorSpan(Color.parseColor(colonColor))) {
            append(":")
        }
        scale(1.1f) {
            inSpans(TextBackgroundSpan(Color.parseColor(timerBg), Color.parseColor(timerColor), radius, padding)) {
                append(minute)
            }
        }
        inSpans(ForegroundColorSpan(Color.parseColor(colonColor))) {
            append(":")
        }
        scale(1.1f) {
            inSpans(TextBackgroundSpan(Color.parseColor(timerBg), Color.parseColor(timerColor), radius, padding)) {
                append(second)
            }
        }
    }
}

fun AppCompatTextView.setDrawableColor(@ColorRes color: Int) {
    compoundDrawablesRelative.filterNotNull().forEach {
        it.mutate()
        it.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
    }
}


fun AppCompatEditText.setEditSelection() = Selection.setSelection(text, text.toString().length)