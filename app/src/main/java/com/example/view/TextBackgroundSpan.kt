package com.example.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt

/**
 *@package com.sinosig.khapp.widget
 *@author https://github.com/asd3590058
 *@fileName ImageSpan
 *@date 2022/10/14 13:57
 *@description
 */
class TextBackgroundSpan(private val backgroundColor: Int, private val textColor: Int, private val radius: Float, private val padding: Float) : ReplacementSpan() {
    private var mSize: Int = 0
    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        mSize = (paint.measureText(text, start, end) + 2 * padding).roundToInt()
        return mSize
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        //设置背景矩形，x为文字左边缘的x值，y为文字的baseline的y值。paint.ascent()获得baseline到文字上边缘的值，paint.descent()获得baseline到文字下边缘
        paint.color = backgroundColor
        val rectF = RectF(x, top.toFloat(), x + mSize, bottom.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        paint.color = textColor
        canvas.drawText(text.toString(), start, end, x+padding, y.toFloat(), paint)
    }
}
