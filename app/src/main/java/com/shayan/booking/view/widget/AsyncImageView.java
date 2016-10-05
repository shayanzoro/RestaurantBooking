package com.shayan.booking.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.shayan.booking.util.imageloader.ImageLoader;

/**
 * Created by Shayan on 10/5/2016.
 */
public class AsyncImageView extends ImageView {

    public AsyncImageView(Context context) {
        super(context);
    }

    public AsyncImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void loadImage(String url) {
        setImageBitmap(null);

        if (!TextUtils.isEmpty(url)) {
            ImageLoader.getInstance().load(this, url);
        }
    }
}
