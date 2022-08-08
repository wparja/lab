package com.wparja.buildingandroid;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OwnCustomView extends androidx.appcompat.widget.AppCompatTextView {
    private static final String TAG = OwnCustomView.class.getName();
    private Paint backgroundPaint;
    private static final int DEFAULT_SIZE = 2000;
    private static final int DEFAULT_FILL_COLOR = 0xff00ff00;

    private int topLeftColor = DEFAULT_FILL_COLOR;
    private int bottomLeftColor = DEFAULT_FILL_COLOR;
    private int topRightColor = DEFAULT_FILL_COLOR;
    private int bottomRightColor = DEFAULT_FILL_COLOR;
    private boolean firstDraw = false;
    private int[] colorArray;

    public OwnCustomView(@NonNull Context context) {
        super(context);
        init(DEFAULT_FILL_COLOR);
    }

    public OwnCustomView(Context context, @Nullable AttributeSet attrs) {
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


    private OwnCustomView(Builder builder) {
        super(builder.context);

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);

        colorArray = new int[] {
                builder.topLeftColor,
                builder.topRightColor,
                builder.bottomRightColor,
                builder.bottomLeftColor
        };
        firstDraw = true;
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

        if (firstDraw) {
            LinearGradient lg = new LinearGradient(0, 0, getWidth(), getHeight(), colorArray, null, Shader.TileMode.CLAMP);

            backgroundPaint.setShader(lg);
            firstDraw = false;
        }

        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
        super.onDraw(canvas);
    }


    public static class Builder {
        private Context context;
        private int topLeftColor = DEFAULT_FILL_COLOR;
        private int topRightColor = DEFAULT_FILL_COLOR;
        private int bottomLeftColor = DEFAULT_FILL_COLOR;
        private int bottomRightColor = DEFAULT_FILL_COLOR;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder topLeftColor(int topLeftColor) {
            this.topLeftColor = topLeftColor;
            return this;
        }

        public Builder topRightColor(int topRightColor) {
            this.topRightColor = topRightColor;
            return this;
        }

        public Builder bottomLeftColor(int bottomLeftColor) {
            this.bottomLeftColor = bottomLeftColor;
            return this;
        }
        public Builder bottomRightColor(int bottomRightColor) {
            this.bottomRightColor = bottomRightColor;
            return this;
        }
        public OwnCustomView build() {
            return new OwnCustomView(this);
        }
    }

}
