package com.example.extensions

import android.os.Build
import android.text.Html
import androidx.core.text.HtmlCompat
import org.apache.commons.lang3.StringUtils
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException

/**
 *@package com.example.extensions
 *@author https://github.com/asd3590058
 *@fileName StringExtrnsions
 *@date 2022/4/13 14:01
 *@description
 */
fun String.getValue(key: String): String {
    return JSONObject(this).also {
        it.optString(key)
    }.toString()
}

fun String.parseParams(params: String?): String {
    try {
        params?.let {
            val jsonObject = JSONObject(it)
            var index = 0
            val sb = with(StringBuilder()) {
                for (key in jsonObject.keys()) {
                    when (index) {
                        0 -> this@with.append(key).append("=").append(jsonObject.optString(key))
                        else -> this@with.append("&").append(key).append("=").append(jsonObject.optString(key))
                    }
                    index++
                }
                this
            }
            if (sb.isNotEmpty()) {
                return if (this.contains("?")) {
                    "$this&$sb"
                } else {
                    "$this?$sb"
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this
}

fun String.thousandths(): String {
    val nf = NumberFormat.getInstance()
    try {
        val df = DecimalFormat("#,###")
        return df.format(nf.parse(this))
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

fun String.desensitize(): String =
    if (this.isBlank() || this.length < 8) this else this.substring(0, 3) + String(CharArray(this.length - 6)).replace(
        "\u0000",
        "*"
    ) + this.substring(this.length - 3)


fun String.phoneDesensitize(): String =
    if (this.isBlank() || this.length != 11) this else this.replace(Regex("(\\d{3})\\d{4}(\\d{4})"), "$1****$2")

fun String.nameDesensitize(): String = StringUtils.rightPad(StringUtils.left(this, 1), StringUtils.length(this), "*")

fun String.birthDateDesensitize(): String = StringUtils.rightPad(StringUtils.left(this, 4), StringUtils.length(this), "*")

fun String.idNoDesensitize(): String = if (this.isBlank() || this.length < 8) this else this.replace(Regex("(?<=\\w{3})\\w(?=\\w{4})"), "*")

fun String.renderHtml(): String = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
    Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(this).toString()
}
