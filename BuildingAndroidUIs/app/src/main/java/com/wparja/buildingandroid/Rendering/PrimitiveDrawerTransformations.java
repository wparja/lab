package com.wparja.buildingandroid.Rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PrimitiveDrawerTransformations extends View {

    Paint paint;
    float rectSize;
    long timeStart;

    public PrimitiveDrawerTransformations(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(0xffffffff);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int smallerDimension = (right - left);
        if (bottom - top < smallerDimension) smallerDimension = bottom - top;

        rectSize = smallerDimension / 10;
        timeStart = System.currentTimeMillis();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float angle = (System.currentTimeMillis() - timeStart) / 100.f;
        canvas.drawColor(Color.BLACK);

        canvas.save();
        canvas.translate(canvas.getWidth() /2, canvas.getHeight() / 2);

        for ( int i = 0; i < 15; i++) {
            canvas.rotate(angle, rectSize / 2, rectSize / 2);
            canvas.drawRect(0, 0, rectSize, rectSize, paint);
            canvas.scale(1.2f, 1.2f, rectSize / 2, rectSize / 2);
        }

        canvas.restore();
        invalidate();

    }
}
