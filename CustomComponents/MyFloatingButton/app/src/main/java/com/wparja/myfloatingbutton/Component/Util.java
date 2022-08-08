package com.wparja.myfloatingbutton.Component;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.view.View;
import android.widget.FrameLayout;

import com.wparja.myfloatingbutton.R;

public class Util {

    public static void setDrawable(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static boolean getBoolean(TypedArray typedArray, int id, int defaultId) {
        return typedArray.getBoolean(id, typedArray.getResources().getBoolean(defaultId));
    }

    public static int getDimenSize(TypedArray typedArray, int id, int defaultId) {
        return typedArray.getDimensionPixelSize(id, typedArray.getResources().getDimensionPixelSize(defaultId));
    }
    public static int getDimenOffset(TypedArray typedArray, int id, int defaultId) {
        return typedArray.getDimensionPixelOffset(id, typedArray.getResources().getDimensionPixelOffset(defaultId));
    }

    public static int getColor(TypedArray typedArray, int id, int defaultId) {
        return typedArray.getColor(id, Util.getColor(typedArray, defaultId));
    }
    public static int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.9f;
        return Color.HSVToColor(hsv);
    }

    public static int getLighterColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 1.1f;
        return Color.HSVToColor(hsv);
    }

    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    public static int getColor(Context context, int id, int color) {
        if (id == 0) return color;
        else return getColor(context, id);
    }

    public static int getColor(View view, int id, Resources.Theme theme) {
        return view.getResources().getColor(id);
    }

    public static int getColor(TypedArray typedArray, int id, Resources.Theme theme) {
            return typedArray.getResources().getColor(id);
    }

    public static int getColor(View view, int id) {
        return getColor(view, id, null);
    }

    public static int getColor(TypedArray typedArray, int id) {
        return getColor(typedArray, id, null);
    }

    public static int getInt(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getInt(id, typedArray.getResources().getInteger(defaultId));
    }

    public static int dp2px(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.density/160f);
        return Math.round(px);
    }

    public static Drawable getOvalDrawable(View view, int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) getDrawable(view, R.drawable.shape_oval_normal);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    public static StateListDrawable getOvalStateListBitmapDrawable(View view, int radius, int normalColor, int highlightedColor, int unableColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                getOvalBitmapDrawable(view, radius, highlightedColor));
        stateListDrawable.addState(
                new int[]{-android.R.attr.state_enabled},
                getOvalBitmapDrawable(view, radius, unableColor));
        stateListDrawable.addState(
                StateSet.WILD_CARD,
                getOvalBitmapDrawable(view, radius, normalColor));
        return stateListDrawable;
    }

    private static Drawable getOvalBitmapDrawable(View view, int radius, int color) {

        if (radius <= 0) return null;

        Bitmap bitmap = Bitmap.createBitmap(2 * radius, 2 * radius, Bitmap.Config.ARGB_8888);
        Canvas canvasPressed = new Canvas(bitmap);
        Paint paintPressed = new Paint();
        paintPressed.setAntiAlias(true);
        paintPressed.setColor(color);
        canvasPressed.drawCircle(radius, radius, radius, paintPressed);
        return new BitmapDrawable(view.getResources(), bitmap);
    }

    public static Drawable getSystemDrawable(Context context, int id) {
        int[] attrs = new int[] { id };
        TypedArray ta = context.obtainStyledAttributes(attrs);
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        return drawable;
    }

    public static Drawable getDrawable(View view, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return view.getResources().getDrawable(id, null);
        } else {
            //noinspection deprecation
            return view.getResources().getDrawable(id);
        }
    }

    public static Drawable getDrawable(View view, int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return view.getResources().getDrawable(id, theme);
        } else {
            //noinspection deprecation
            return view.getResources().getDrawable(id);
        }
    }
}
