package com.example.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 *@package com.sinosig.khapp.common.extensions
 *@author https://github.com/asd3590058
 *@fileName DataExtensions
 *@date 2022/2/21 17:34
 *@description
 */
fun Date.toDateString(format: String = "yyyy-MM-dd"): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return try {
        formatter.format(this)
    } catch (e: Exception) {
        ""
    }
}

/**
 * 通过指定格式，将字符串转换为Date
 */
fun String.toDate(pattern: String): Date? {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return try {
        formatter.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.toYear(format: String = "yyyy-MM-dd"): Int {
    val mCalendar = Calendar.getInstance()
    toDate(format)?.apply {
        mCalendar.time = this
    }
    return mCalendar[Calendar.YEAR]
}

fun String.toMonth(format: String = "yyyy-MM-dd"): Int {
    val mCalendar = Calendar.getInstance()
    toDate(format)?.apply {
        mCalendar.time = this
    }
    return mCalendar[Calendar.MONTH] + 1
}

fun String.toDay(format: String = "yyyy-MM-dd"): Int {
    val mCalendar = Calendar.getInstance()
    toDate(format)?.apply {
        mCalendar.time = this
    }
    return mCalendar[Calendar.DAY_OF_MONTH]
}

fun String.toTime(format: String): Long? {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return try {
        formatter.parse(this)?.time
    } catch (e: Exception) {
        0
    }
}


