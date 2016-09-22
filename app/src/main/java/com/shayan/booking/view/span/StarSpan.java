package com.shayan.booking.view.span;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import com.shayan.booking.R;

/**
 * Created by Shayan on 9/21/2016.
 */
public class StarSpan extends TypefaceSpan {

    private Typeface typeface;
    private int color;
    private int textSize;

    public StarSpan(Context context) {
        super("");
        typeface = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.font_fontawesome));
        color = ContextCompat.getColor(context, R.color.gold);
        textSize = context.getResources().getDimensionPixelSize(R.dimen.textsize_smaller);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyTypeFace(ds);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyTypeFace(paint);
    }

    protected void applyTypeFace(Paint paint) {
        int oldStyle;

        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~typeface.getStyle();

        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(typeface);
        paint.setColor(color);
        paint.setTextSize(textSize);
    }
}
