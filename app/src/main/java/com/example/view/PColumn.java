package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author ：张 奎
 * @date ：2020-04-30 14：26
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
public class PColumn extends View {


    private Context context = null;
    private AttributeSet attributeSet = null;
    /**
     * 自定义view实现柱状图
     * 首先定义一个类实现View
     */

    //定义画笔
    private Paint mLinePaint;
    private Paint mGreenPaint;
    private Paint mTextPaint;
    private Paint mGreenTwoPaint;
    //定义上下文
    private Context mContext;
    //定义宽高
    private float width;
    private float height;
    private float mScale;
    //这个数组是高度的值
    private String[] y_title = {"100", "80", "60", "40", "20", "0"};
    //分别为定义数据与数据源名称的集合
    private static List<Long> mData;
    private static List<Long> mDataTwo;
    private static List<String> mNames;

    public PColumn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attributeSet = attrs;
        //给定义的画笔进行加工
        mContext = context;
        mLinePaint = new Paint();
        mGreenPaint = new Paint();
        mGreenTwoPaint = new Paint();
        mTextPaint = new Paint();


        mLinePaint.setARGB(255, 223, 233, 231);
        mGreenPaint.setARGB(255, 0, 200, 149);
        mGreenTwoPaint.setARGB(255, 255, 128, 1);
        mTextPaint.setARGB(255, 153, 153, 153);

        mGreenPaint.setStyle(Paint.Style.FILL);
        mGreenTwoPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setAntiAlias(true);
        mGreenPaint.setAntiAlias(true);
        mLinePaint.setAntiAlias(true);

        mScale = context.getResources().getDisplayMetrics().density;
    }

    //尺寸发生改变的时候调用
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = 0.8F * w;
        height = 0.80F * h;
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float min_height = height / 5;
        for (int i = 5; i >= 0; i--) {
            if (i == 5) {
                mLinePaint.setARGB(255, 131, 148, 144);
            } else {
                mLinePaint.setARGB(255, 223, 233, 231);
            }
            canvas.drawLine(70 * mScale, 30 * mScale + min_height * i, 70 * mScale + width, 30 * mScale + min_height * i, mLinePaint);
            mTextPaint.setTextAlign(Paint.Align.RIGHT);
            mTextPaint.setTextSize(10 * mScale);
            canvas.drawText(y_title[i], 60 * mScale, 32 * mScale + min_height * i, mTextPaint);
        }
        float min_width = (width - 70 * mScale) / (mData.size());
        mTextPaint.setTextSize(12 * mScale);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < mData.size(); i++) {
            // 第一条柱形
            int leftR = (int) (70 * mScale + i * min_width + min_width / 2);
            int rightR = leftR + (int) (min_width / 4);
            int bottomR = (int) (30 * mScale + min_height * 5);
            int topR = bottomR - (int) (height / 100 * mData.get(i));
            canvas.drawRect(new RectF(leftR, topR, rightR, bottomR), mGreenPaint);

            // 第二条柱形
            int mLeftTwoR = rightR + (int) (min_width / 4);
            int mRightTwoR = mLeftTwoR + (int) (min_width / 4);
            int mBottomTwoR = (int) (30 * mScale + min_height * 5);
            int mTopTwoR = mBottomTwoR - (int) (height / 100 * mDataTwo.get(i));
            canvas.drawRect(new RectF(mLeftTwoR, mTopTwoR, mRightTwoR, mBottomTwoR), mGreenTwoPaint);

            mTextPaint.setARGB(255, 153, 153, 153);
            canvas.drawText(mNames.get(i), leftR + 3 * min_width / 8, bottomR + 20 * mScale, mTextPaint);
            mTextPaint.setARGB(255, 51, 51, 51);
            canvas.drawText(mData.get(i) + "", leftR+(int) (min_width / 8) , topR - 10 * mScale, mTextPaint);
            canvas.drawText(mDataTwo.get(i) + "", mLeftTwoR+(int) (min_width / 8), mTopTwoR - 10 * mScale, mTextPaint);
        }
    }


    public void setData(List<Long> data, List<Long> dataTwo,List<String> name) {
        mData = data;
        mNames = name;
        mDataTwo = dataTwo;
        invalidate();
    }
    //传入数据并进行绘制
    //    public PColumn(List<Long> data, List<String> name) {
    //        super(PColumn.context,PColumn.attributeSet);
    //        mData = data;
    //        mNames = name;
    //        invalidate();
    //    }

}
