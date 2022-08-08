package com.wparja.buildingandroid.Rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircularActivityIndicator extends View {
    private static final int DEFAULT_FG_COLOR = 0xffff0000;
    private static final int DEFAULT_BG_COLOR = 0xffa0a0a0;
    private Paint foregroundPaint;
    private Paint backgroundPaint;
    private int selectedAngle;
    private Path clipPath;
    public CircularActivityIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        foregroundPaint = new Paint();
        foregroundPaint.setColor(DEFAULT_FG_COLOR);
        foregroundPaint.setStyle(Paint.Style.FILL);
        selectedAngle = 280;

        backgroundPaint = new Paint();
        backgroundPaint.setColor(DEFAULT_BG_COLOR);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int circleSize = Math.min(getWidth(), getHeight());
        int horMargin = (getWidth() - circleSize) / 2;
        int verMargin = (getHeight() - circleSize) / 2;

        if (clipPath == null) {
            int clipWidth = (int) (circleSize * 0.75);

            int clipX = (getWidth() - clipWidth) / 2;
            int clipY = (getHeight() - clipWidth) / 2;
            clipPath = new Path();
            clipPath.addArc(clipX, clipY, clipX + clipWidth, clipY + clipWidth, 0, 360);
        }

        canvas.clipRect(0, 0, getWidth(), getHeight());
        canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

        canvas.save();
        canvas.rotate(-90, getWidth() / 2, getHeight() / 2);

        canvas.drawArc(
                horMargin,
                verMargin,
                horMargin + circleSize,
                verMargin + circleSize,
                0, 360, true, backgroundPaint);
        canvas.drawArc(horMargin,verMargin, horMargin + circleSize, verMargin + circleSize, 0, selectedAngle, true, foregroundPaint);

        canvas.restore();
    }
}
