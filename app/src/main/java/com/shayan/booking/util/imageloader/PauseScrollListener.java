package com.shayan.booking.util.imageloader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.shayan.booking.R;


/**
 * Created by Shayan on 9/12/16.
 */
public class PauseScrollListener extends RecyclerView.OnScrollListener {

    private int scrollThreshold;

    public PauseScrollListener(Context context) {
        scrollThreshold = context.getResources().getDimensionPixelSize(R.dimen.scroll_velocity_threshold);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                ImageLoader.getInstance().resume();
                break;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (Math.abs(dy) >= scrollThreshold) {
            ImageLoader.getInstance().pause();
        } else {
            ImageLoader.getInstance().resume();
        }
    }
}
