package com.wparja.myfloatingbutton.Component.Animation;

import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;

import java.util.ArrayList;

public class AnimationManager {
    public static ObjectAnimator animate(Object target, String property, int delay, long duration, ArgbEvaluator evaluator, AnimatorListenerAdapter listenerAdapter, int... values) {

        ObjectAnimator animator = ObjectAnimator.ofInt(target, property, values);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.setEvaluator(evaluator);

        if (listenerAdapter != null)
            animator.addListener(listenerAdapter);

        animator.start();
        return animator;
    }

    public static ArrayList<Integer> getOrderIndex(OrderEnum aDefault, int size) {
        return null;
    }
}
