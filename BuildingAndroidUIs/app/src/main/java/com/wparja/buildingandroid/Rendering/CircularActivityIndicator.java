package com.wparja.buildingandroid.Rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;

public class CircularActivityIndicator extends View {
    private static final String TAG = CircularActivityIndicator.class.getName();

    private static final int BLACK_COLOR = 0xff0000ff;
    private static final int DEFAULT_FG_COLOR = 0xffff0000;
    private static final int PRESSED_FG_COLOR = 0xff0000ff;
    private static final int DEFAULT_BG_COLOR = 0xffa0a0a0;
    private static final int BORDER_SIZE = 20;
    private static final Paint.Cap[] CAPS = new Paint.Cap[] {
            Paint.Cap.BUTT,
            Paint.Cap.ROUND,
            Paint.Cap.SQUARE
    };

    private Paint backgroundPaint;
    private Paint foregroundPaint;
    private Paint indicatorBorderPaint;
    private int selectedAngle;
    private Path clipPath;
    private boolean pressed;
    private int circleSize;
    private GestureDetector gestureListener;
    private Scroller angleScroller;

    private Paint paint;
    private ArrayList<Float> rects;
    private ArrayList<Integer> colors;

    public CircularActivityIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);


        rects = new ArrayList<>();
        colors = new ArrayList<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(DEFAULT_BG_COLOR);
        backgroundPaint.setStyle(Paint.Style.FILL);

        foregroundPaint = new Paint();
        foregroundPaint.setColor(DEFAULT_BG_COLOR);
        foregroundPaint.setStyle(Paint.Style.FILL);

        indicatorBorderPaint = new Paint();
        indicatorBorderPaint.setAntiAlias(false);
        indicatorBorderPaint.setColor(BLACK_COLOR);
        indicatorBorderPaint.setStyle(Paint.Style.STROKE);
        indicatorBorderPaint.setStrokeWidth(BORDER_SIZE);
        indicatorBorderPaint.setStrokeCap(Paint.Cap.BUTT);

        selectedAngle = 220;
        pressed = false;

        angleScroller = new Scroller(context, null, true);
        angleScroller.setFinalX(selectedAngle);

        gestureListener = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            boolean processed;

            @Override
            public boolean onDown(MotionEvent event) {
                processed = computeAndSetAngle(event.getX(), event.getY());
                if (processed) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    changePressedState(true);
                    postInvalidate();
                }
                return processed;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                endGesture();
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                computeAndSetAngle(e2.getX(), e2.getY());
                postInvalidate();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                endGesture();
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }
    boolean b = false;
    float x, y;

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
        b = true;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        // callPaintExample(canvas);
        // callDrawRect(canvas);
        //callDrawPoints(canvas);
        if (b) {
            paint.setColor(0xffa0a0a0);
            paint.setStrokeWidth(4.f);
            paint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawRect(0, x, 100, y, paint);
        }

    }

    private void callDrawPoints(Canvas canvas) {
        int POINT = 50;
        float[] points = new float[POINT * 2];
        for (int i = 0; i < POINT; i++) {
            points[i*2] = (float) Math.random() * getWidth();
            points[i*2 + 1] = (float)Math.random() * getHeight();
        }

        paint.setColor(0xffa0a0a0);
        paint.setStrokeWidth(4.f);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLines(points, paint);
        paint.setColor(0xffffffff);
        paint.setStrokeWidth(10.f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoints(points, paint);
    }

    private void callDrawRect(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < 2; i++) {
            rects.add((float) Math.random() * width);
            rects.add((float) Math.random() * height);
        }
        colors.add(0xff000000 | (int) (0xffffff * Math.random()));
        for (int i = 0; i < rects.size() / 4; i++) {
            paint.setColor(colors.get(i));
            canvas.drawRoundRect(
                    rects.get(i * 4 ),
                    rects.get(i * 4 + 1),
                    rects.get(i * 4 + 2),
                    rects.get(i * 4 + 3),
                    40, 40, paint);
        }
        if (rects.size() < 400) postInvalidateDelayed(20);
    }

    private void callPaintExample(Canvas canvas) {
        int xPos = (getWidth() - 100) / 2;
        int yPos = getHeight() / 2 - BORDER_SIZE * CAPS.length / 2;
        for (Paint.Cap cap : CAPS) {
            indicatorBorderPaint.setStrokeCap(cap);
            canvas.drawLine(xPos, yPos, xPos + 100, yPos, indicatorBorderPaint);
            yPos += BORDER_SIZE * 2;
        }
        indicatorBorderPaint.setStrokeCap(Paint.Cap.BUTT);

        boolean notFinished = angleScroller.computeScrollOffset();
        selectedAngle = angleScroller.getCurrX();

        if (pressed) {
            foregroundPaint.setColor(PRESSED_FG_COLOR);
        } else {
            foregroundPaint.setColor(DEFAULT_FG_COLOR);
        }

        circleSize = getWidth();
        if (getHeight() < circleSize) circleSize = getHeight();

        int horMargin = (getWidth() - circleSize) / 2;
        int verMargin = (getHeight() - circleSize) / 2;
        int clipWidth = (int) (circleSize * 0.75);
        int clipX = (getWidth() - clipWidth) / 2;
        int clipY = (getHeight() - clipWidth) / 2;

        // create a clipPath the first time
        if(clipPath == null) {
            clipPath = new Path();
            clipPath.addArc(
                    clipX,
                    clipY,
                    clipX + clipWidth,
                    clipY + clipWidth,
                    0, 360);
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

        canvas.drawArc(
                horMargin,
                verMargin,
                horMargin + circleSize,
                verMargin + circleSize,
                0, selectedAngle, true, foregroundPaint);

        canvas.drawArc(
                horMargin + BORDER_SIZE / 4,
                verMargin + BORDER_SIZE / 4,
                horMargin + circleSize - BORDER_SIZE /2,
                verMargin + circleSize - BORDER_SIZE /2,
                0, selectedAngle, true, indicatorBorderPaint);

        //draw inner border by drawing an arc the size of the clipping area
        canvas.drawArc(
                clipX - BORDER_SIZE / 4,
                clipY - BORDER_SIZE / 4,
                clipX + clipWidth + BORDER_SIZE /2,
                clipY + clipWidth + BORDER_SIZE /2,
                0, selectedAngle, true, indicatorBorderPaint);

        canvas.restore();

        if (notFinished) invalidate();
    }

    private void endGesture() {
        getParent().requestDisallowInterceptTouchEvent(false);
        changePressedState(false);
        postInvalidate();
    }

    private void changePressedState(boolean pressed) {
        this.pressed = pressed;
    }

    private boolean computeAndSetAngle(float x, float y) {
        x -= getWidth() / 2;
        y -= getHeight() / 2;

        double radius = Math.sqrt(x * x + y * y);
        if(radius > circleSize/2) return false;

        int angle = (int) (180.0 * Math.atan2(y, x) / Math.PI) + 90;
        angle = ((angle > 0) ? angle : 360 + angle);

        if(angleScroller.computeScrollOffset()) {
            angleScroller.forceFinished(true);
        }

        angleScroller.startScroll(angleScroller.getCurrX(), 0, angle - angleScroller.getCurrX(), 0);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureListener.onTouchEvent(event)) {
            return true;
        } else {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                endGesture();
                return true;
            }
            return false;
        }
    }
}