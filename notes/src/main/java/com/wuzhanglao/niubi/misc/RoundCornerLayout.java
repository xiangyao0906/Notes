package com.wuzhanglao.niubi.misc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.wuzhanglao.niubi.R;

/**
 * Created by ming.wu@shanbay.com on 2017/6/26.
 */

public class RoundCornerLayout extends FrameLayout {
	private static final int DEFAULT_COLOR = Color.LTGRAY;

	private RectF mRectF = new RectF();
	private Paint mPaint = new Paint();

	private int mCornerColor;

	public RoundCornerLayout(@NonNull Context context) {
		this(context, null);
	}

	public RoundCornerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		TypedArray ta = getResources().obtainAttributes(attrs, R.styleable.cview_RoundCornerLayout);
		mCornerColor = ta.getColor(R.styleable.cview_RoundCornerLayout_cview_roundCornerColor, DEFAULT_COLOR);
		ta.recycle();

		mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(0);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(mCornerColor);

		setWillNotDraw(false);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mRectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
	}

	public void setCornerColor(int color) {
		mCornerColor = color;
		mPaint.setColor(mCornerColor);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		float radius = mRectF.height() / 2.0f;
		canvas.drawRoundRect(mRectF, radius, radius, mPaint);
		super.onDraw(canvas);
	}
}