package com.shayan.booking.view.span;

import android.content.Context;
import android.graphics.Paint;

import com.shayan.booking.R;

/**
 * Created by Shayan on 9/22/2016.
 */
public class ThumbSpan extends StarSpan {

    private int textSize;

    public ThumbSpan(Context context) {
        super(context);
        textSize = context.getResources().getDimensionPixelSize(R.dimen.textsize_small);
    }

    @Override
    protected void applyTypeFace(Paint paint) {
        super.applyTypeFace(paint);
        paint.setTextSize(textSize);
    }
}
