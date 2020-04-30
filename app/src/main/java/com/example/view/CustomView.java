package com.example.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.DimenUtil;
import com.example.mydemo.R;

import androidx.core.content.ContextCompat;

/**
 * 作者：Created by 武泓吉 on 2019/11/8.
 */

public class CustomView extends View {
	private Paint mPaint;
	private int outsideColor;//进度颜色
	private float outsideRadius;//外圆半径
	private int insideColor;//内圆半径
	private int progressTextColor;//文字颜色
	private float progressTextSize;//文字大小
	private float progressWidth;//圆环宽度
	private int maxProgress;//最大进度
	private float progress;//当前进度
	private int direction;    //进度从哪里开始(设置了4个值,上左下右)
	private String progressText;     //圆环内文字
	private Rect rect;
	private ValueAnimator animator;

	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context,  AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomCircleProgressBar);
		outsideColor=typedArray.getColor(R.styleable.CustomCircleProgressBar_outside_color,
				ContextCompat.getColor(getContext(), R.color.colorPrimary));
		outsideRadius= typedArray.getDimension(R.styleable.CustomCircleProgressBar_outside_radius,
				DimenUtil.dp2px(getContext(),60f));
		insideColor = typedArray.getColor(R.styleable.CustomCircleProgressBar_inside_color, ContextCompat.getColor(getContext(), R.color.inside_color));
		progressTextColor = typedArray.getColor(R.styleable.CustomCircleProgressBar_progress_text_color, ContextCompat.getColor(getContext(), R.color.colorPrimary));
		progressTextSize = typedArray.getDimension(R.styleable.CustomCircleProgressBar_progress_text_size, DimenUtil.dp2px(getContext(), 14.0f));
		progressWidth = typedArray.getDimension(R.styleable.CustomCircleProgressBar_progress_width, DimenUtil.dp2px(getContext(), 10.0f));
		progress = typedArray.getFloat(R.styleable.CustomCircleProgressBar_progress, 50.0f);
		maxProgress = typedArray.getInt(R.styleable.CustomCircleProgressBar_max_progress, 100);
		typedArray.recycle();

	}



	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int with=0;
		int height=0;
		int measureWith= MeasureSpec.getSize(widthMeasureSpec);
		int measureHeight=MeasureSpec.getSize(heightMeasureSpec);
		int measureWithMode = MeasureSpec.getMode(widthMeasureSpec);
		int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

		if (measureHeightMode==MeasureSpec.EXACTLY) {
			height=measureHeight;
		}else{
			height= (int) ((2*outsideRadius)+progressWidth);
		}

		if (measureWithMode==MeasureSpec.EXACTLY) {
			with=measureWith;
		}else {
			with= (int) ((2*outsideRadius)+progressWidth);
		}
		setMeasuredDimension(with,height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint=new Paint();
		int center=getWidth()/2;
		int center2=getHeight()/2;
		//第一步画内层圆
        mPaint.setColor(insideColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressWidth);
		mPaint.setAntiAlias(true);
		canvas.drawCircle(center,center,outsideRadius,mPaint);
		//第二部 画进度
		mPaint.setColor(outsideColor);
		RectF oval=new RectF(center-outsideRadius,center2-outsideRadius,center+outsideRadius,center2+outsideRadius);
		canvas.drawArc(oval,270,360*progress/maxProgress,false,mPaint);

		//第三步:画圆环内百分比文字
		rect=new Rect();
		mPaint.setColor(progressTextColor);
		mPaint.setTextSize(progressTextSize);
		mPaint.setStrokeWidth(0);
		progressText=getProgressText();
		mPaint.getTextBounds(progressText,0,progressText.length(),rect);
		Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
		int baseLine=(getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
		canvas.drawText(progressText, getMeasuredWidth() / 2 - rect.width() / 2, baseLine, mPaint);
	}
	//中间的进度百分比
	private String getProgressText() {
		return (int) ((progress / maxProgress) * 100) + "%";
	}
	//加锁保证线程安全,能在线程中使用
	public synchronized void setProgress(int progress) {
		if (progress < 0) {
			throw new IllegalArgumentException("progress should not be less than 0");
		}
		if (progress > maxProgress) {
			progress = maxProgress;
		}
		if(progress <= maxProgress){
			this.progress = progress;
			postInvalidate();
		}

	}
}
