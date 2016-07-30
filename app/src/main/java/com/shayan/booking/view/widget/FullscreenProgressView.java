package com.shayan.booking.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.rey.material.widget.ProgressView;
import com.shayan.booking.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shayan on 7/30/2016.
 */
public class FullscreenProgressView extends LinearLayout {

    @Bind(R.id.progressView)
    ProgressView progressView;

    public FullscreenProgressView(Context context) {
        super(context);
        init(context);
    }

    public FullscreenProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FullscreenProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fullscreen_progress, this);

        if (isInEditMode())
            return;

        ButterKnife.bind(this, view);
    }

    public void start() {
        setVisibility(VISIBLE);
        progressView.start();
    }

    public void stop() {
        setVisibility(GONE);
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        super.onDetachedFromWindow();
    }
}
