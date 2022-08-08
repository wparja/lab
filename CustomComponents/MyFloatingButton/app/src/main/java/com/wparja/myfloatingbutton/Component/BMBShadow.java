package com.wparja.myfloatingbutton.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wparja.myfloatingbutton.R;

public class BMBShadow extends FrameLayout {

    private boolean shadowEffect = true;
    private int shadowOffSetX;
    private int shadowOffSetY;
    private int shadowRadius;
    private int shadowCornerRadius;
    private int shadowColor;

    public BMBShadow(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public BMBShadow(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BMBShadow(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BoomMenuButton, 0,0);

        shadowEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_shadowEffect, R.bool.default_bmb_shadow_effect);
        shadowRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shadowRadius, R.dimen.default_bmb_shadow_radius);
        shadowOffSetX = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetX, R.dimen.default_bmb_shadow_offset_x);
        shadowOffSetY = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetY, R.dimen.default_bmb_shadow_offset_y);
        shadowColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shadowColor, R.color.default_bmb_shadow_color);
    }

    private void initPadding() {
        int xPadding = shadowRadius + Math.abs(shadowOffSetX);
        int yPadding = shadowRadius + Math.abs(shadowOffSetY);
        setPadding(xPadding, yPadding, xPadding, yPadding);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            createShadow();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        createShadow();
    }

    private void createShadow() {
        if (shadowEffect) {
            Bitmap shadowBitmap = createShadowBitmap();
            if (shadowBitmap == null) return;

            BitmapDrawable shadowDrawable = new BitmapDrawable(getResources(), shadowBitmap);
            setBackground(shadowDrawable);
        } else {
            clearShadow();
        }
    }

    private Bitmap createShadowBitmap() {

        Bitmap shadowBitmap;

        if (getWidth() > 0 && getHeight() > 0) {
            shadowBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ALPHA_8);
        } else {
            return null;
        }

        Canvas canvas = new Canvas(shadowBitmap);
        RectF shadowRect = new RectF(
                shadowRadius + Math.abs(shadowOffSetX),
                shadowRadius + Math.abs(shadowOffSetY),
                getWidth() - shadowRadius - Math.abs(shadowOffSetX),
                getHeight() + shadowRadius - Math.abs(shadowOffSetY));

        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(Color.TRANSPARENT);
        shadowPaint.setStyle(Paint.Style.FILL);

        if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, shadowOffSetX, shadowOffSetY, shadowColor);
        }

        canvas.drawRoundRect(shadowRect, shadowCornerRadius, shadowCornerRadius, shadowPaint);
        return shadowBitmap;
    }

    public void setShadowEffect(boolean shadowEffect) {
        this.shadowEffect = shadowEffect;
    }

    public void setShadowOffSetX(int shadowOffSetX) {
        this.shadowOffSetX = shadowOffSetX;
    }

    public void setShadowOffSetY(int shadowOffSetY) {
        this.shadowOffSetY = shadowOffSetY;
    }

    public void setShadowRadius(int shadowRadius) {
        this.shadowRadius = shadowRadius;
    }

    public void setShadowCornerRadius(int shadowCornerRadius) {
        this.shadowCornerRadius = shadowCornerRadius;
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
    }

    public void clearShadow() {
        Util.setDrawable(this, null);
    }
}
