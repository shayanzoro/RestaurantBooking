package com.shayan.booking.util;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.github.florent37.viewanimator.AnimationBuilder;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * Created by Shayan on 7/10/2016.
 * ************************* Attention *****************************
 * All of the methods only return the animation, they don't start it
 * *****************************************************************
 */
public class AnimUtils {

    public static AnimationBuilder showMaximize(View view) {
        return ViewAnimator.animate(view)
                .scale(0, 1)
                .alpha(0.5f, 1)
                .duration(200)
                .interpolator(new AccelerateDecelerateInterpolator())
                .onStart(() -> view.setVisibility(View.VISIBLE));
    }

    public static AnimationBuilder hideMinimize(View view) {
        return ViewAnimator.animate(view)
                .scale(1, 0)
                .alpha(1, 0.5f)
                .duration(200)
                .interpolator(new AccelerateDecelerateInterpolator())
                .onStop(() -> view.setVisibility(View.INVISIBLE));
    }

    public static AnimationBuilder fadeOut(View view) {
        return ViewAnimator.animate(view)
                .fadeOut()
                .duration(500)
                .interpolator(new AccelerateDecelerateInterpolator())
                .onStop(() -> view.setVisibility(View.INVISIBLE));
    }

    public static AnimationBuilder fadeIn(View view) {
        return ViewAnimator.animate(view)
                .fadeIn()
                .duration(500)
                .interpolator(new AccelerateDecelerateInterpolator())
                .onStart(() -> view.setVisibility(View.VISIBLE));
    }

    public static AnimationBuilder slideUpOut(View view) {
        return ViewAnimator.animate(view)
                .translationY(0, -300)
                .alpha(1, 0)
                .duration(200)
                .interpolator(new AccelerateInterpolator())
                .onStop(() -> view.setVisibility(View.INVISIBLE));
    }

    public static AnimationBuilder slideDownIn(View view) {
        return ViewAnimator.animate(view)
                .translationY(-300, 0)
                .alpha(0, 1)
                .duration(200)
                .interpolator(new DecelerateInterpolator())
                .onStart(() -> view.setVisibility(View.VISIBLE));
    }
}
