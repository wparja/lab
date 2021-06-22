package com.wparja.myfloatingbutton.Component;

import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.wparja.myfloatingbutton.Component.Animation.AnimationManager;

public class BackgroundView extends FrameLayout {

    private int dimColor;

    public BackgroundView(@NonNull Context context, final BoomMenuButton bmb) {
        super(context);

        dimColor = bmb.getDimColor();

        ViewGroup rootView = bmb.getParentView();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(rootView.getWidth(), rootView.getHeight());
        setLayoutParams(params);
        setBackgroundColor(Color.TRANSPARENT);
        setOnClickListener(v -> bmb.onBackgroundClicked());
        setMotionEventSplittingEnabled(false);
        rootView.addView(this);
    }

    protected void reLayout(final BoomMenuButton bmb) {
        ViewGroup rootView = bmb.getParentView();
        FrameLayout.LayoutParams params = (LayoutParams) getLayoutParams();
        params.width = rootView.getWidth();
        params.height = rootView.getHeight();
        setLayoutParams(params);
    }

    protected void dim(long duration, AnimatorListenerAdapter completeListener) {
        setVisibility(VISIBLE);
        AnimationManager.animate(this, "backgroundColor", 0, duration, new ArgbEvaluator(), completeListener,  Color.TRANSPARENT, dimColor);
    }

    protected void light(long duration, AnimatorListenerAdapter completeListener) {
        AnimationManager.animate(this, "backgroundColor", 0, duration, new ArgbEvaluator(), completeListener, dimColor, Color.TRANSPARENT);
    }
}
