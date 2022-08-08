package com.wparja.myfloatingbutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wparja.myfloatingbutton.Component.BMBShadow;


public class MyButton extends FrameLayout {

    int offsetX = 20;
    int offsetY = 20;
    int radius = 50;

    private BMBShadow shadow;
    private FrameLayout button;

    public MyButton(@NonNull Context context) {
        super(context);
        init();
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.bmb, this, true);
        initShadow();
        initButton();
    }

    private void initButton() {
        if (button == null) button = (FrameLayout) findViewById(R.id.button);
        setButtonSize();
        setBackgroundButton();
    }

    private void setBackgroundButton() {
        // Ripple drawable
//        GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.shape_oval_normal);
//        drawable.setColor(getContext().getResources().getColor(R.color.colorAccent));
//        ColorStateList colorStateList = new ColorStateList(new int[][]{}, new int[] {getContext().getResources().getColor(R.color.colorPrimaryDark)});
//        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, drawable, null);
//        button.setBackground(rippleDrawable);

        // Old version
//        int[] attrs = new int[] { android.R.attr.selectableItemBackground };
//        TypedArray ta = getContext().obtainStyledAttributes(attrs);
//        Drawable drawable1 = ta.getDrawable(0);
//        ta.recycle();


        StateListDrawable stateListDrawable = new StateListDrawable();
        Bitmap bitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(getContext().getResources().getColor(R.color.colorAccent));
        canvas.drawCircle(radius, radius, radius, paint);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(R.drawable.shape_oval_normal_pressed));
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, getResources().getDrawable(R.drawable.shape_oval_normal));

        button.setBackground(stateListDrawable);
    }

    private void initShadow() {
        if (shadow == null) shadow = (BMBShadow) findViewById(R.id.shadow);
        shadow.setShadowEffect(true);
        shadow.setShadowOffSetX(offsetX);
        shadow.setShadowOffSetY(offsetY);
        shadow.setShadowColor(getContext().getResources().getColor(android.R.color.background_dark));
        shadow.setShadowRadius(radius);
        shadow.setShadowCornerRadius(radius + radius);
    }

    private void setButtonSize() {
        LayoutParams params = (LayoutParams) button.getLayoutParams();
        params.width = radius * 2;
        params.height = radius * 2;
        button.setLayoutParams(params);
    }
}
