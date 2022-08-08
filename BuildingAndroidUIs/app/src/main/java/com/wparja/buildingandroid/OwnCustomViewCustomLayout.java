package com.wparja.buildingandroid;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OwnCustomViewCustomLayout extends View {
    private static final String TAG = OwnCustomViewCustomLayout.class.getName();
    private Paint backgroundPaint;
    private static final int DEFAULT_SIZE = 2000;
    private static final int DEFAULT_FILL_COLOR = 0xff00ff00;

    private int topLeftColor = DEFAULT_FILL_COLOR;
    private int bottomLeftColor = DEFAULT_FILL_COLOR;
    private int topRightColor = DEFAULT_FILL_COLOR;
    private int bottomRightColor = DEFAULT_FILL_COLOR;
    private boolean firstDraw = false;
    private int[] colorArray;

    public OwnCustomViewCustomLayout(@NonNull Context context) {
        super(context);
        init(DEFAULT_FILL_COLOR);
    }

    public OwnCustomViewCustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int fillColor;
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OwnCustomView, 0, 0);
        try {
            fillColor = ta.getColor(R.styleable.OwnCustomView_fillColor, DEFAULT_FILL_COLOR);
        } finally {
            ta.recycle();
        }
        init(fillColor);
    }


    private void init(int fillColor) {
        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        setFillColor(fillColor);
    }

    public void setFillColor(int fillColor) {
        backgroundPaint.setColor(fillColor);
    }

    private static int getMeasurementSize(int measureSpec, int defaultSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);


        switch (mode) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                return Math.min(defaultSize, size);
            case MeasureSpec.UNSPECIFIED:
            default:
                return defaultSize;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasurementSize(widthMeasureSpec, DEFAULT_SIZE);
        int height = getMeasurementSize(heightMeasureSpec, DEFAULT_SIZE);
        setMeasuredDimension(width, height);
    }

    public final int dpToPixel(int dp) {
        return (int)(dp * getResources().getDisplayMetrics().density + 0.5);
    }

    public final int pixelsToDp(int px) {
        return (int)(px / getResources().getDisplayMetrics().density + 0.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int leftX = getPaddingLeft();
        int rightX = getWidth() - getPaddingLeft() - getPaddingRight();

        int topY = getPaddingTop();
        int bottomY = getHeight() - getPaddingTop() - getPaddingBottom();

        canvas.drawRect(leftX, topY, rightX, bottomY, backgroundPaint);

        super.onDraw(canvas);
    }


}
