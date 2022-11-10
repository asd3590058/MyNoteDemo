package com.example.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams

/**
 *@package com.example.extensions
 *@author https://github.com/asd3590058
 *@fileName ViewExtensions
 *@date 2022/1/12 15:10 view扩展函数
 *@description
 */

inline fun View.setDebounceClickListener(time: Long = 200, crossinline runnable: () -> Unit) {
    setOnClickListener {
        isEnabled = false
        runnable()
        postDelayed({ isEnabled = true }, time)
    }
}

fun View.setStatusMargin() {
    val lp: ViewGroup.LayoutParams = this.layoutParams
    if (lp is MarginLayoutParams) {
        lp.topMargin += (statusBarHeight - 10) //增高
    }
    this.layoutParams = lp
}

fun View.setPaddingSmart() = this.setPadding(this.paddingLeft, this.paddingTop + statusBarHeight, this.paddingRight, this.paddingBottom + 10)

val statusBarHeight: Int
    get() = Resources.getSystem().let {
        it.getDimensionPixelOffset(it.getIdentifier("status_bar_height", "dimen", "android"))
    }

val navigationBarHeight: Int
    get() = Resources.getSystem().let {
        it.getDimensionPixelOffset(it.getIdentifier("navigation_bar_height", "dimen", "android"))
    }


fun View.setCornerBackground(corner: Float, color: Int = resources.getColor(Color.WHITE, null), stoke: Int = 0, stokeColor: Int = 0) {
    this.background = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = corner
        if (stoke > 0) this.setStroke(stoke, stokeColor)
        setColor(color)
    }
}

fun View.setCornerGradientBackground(corner: Float, startColor: Int, endColor: Int) {
    val drawable = GradientDrawable().also {
        it.shape = GradientDrawable.RECTANGLE
        it.cornerRadius = corner
        it.colors = intArrayOf(startColor, endColor)
    }
    this.background = drawable
}


val screenDensity = Resources.getSystem().displayMetrics.density

fun Float.dp2px() = screenDensity * this + 0.5f

fun Float.sp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

fun Float.px2dp() = this / screenDensity + 0.5f

fun Float.px2sp() = this / Resources.getSystem().displayMetrics.scaledDensity

val Context.screenWidth: Int get() = resources.displayMetrics.widthPixels

val Context.screenHeight: Int get() = resources.displayMetrics.heightPixels

fun getColorWithAlpha(alpha: Float, baseColor: Int): Int {
    val a = 255.coerceAtMost(0.coerceAtLeast((alpha * 255).toInt())) shl 24
    val rgb = 0x00ffffff and baseColor
    return a + rgb
}
