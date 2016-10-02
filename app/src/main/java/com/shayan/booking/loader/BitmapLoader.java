package com.shayan.booking.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Shayan on 10/2/2016.
 */
public class BitmapLoader extends AsyncTaskLoader<Bitmap> {

    private Bitmap mData;
    private String mUrl;

    public BitmapLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public Bitmap loadInBackground() {
        Log.d("BitmapLoader", "loadInBackground()");

        Bitmap bitmap;
        try {
            URL url = new URL(mUrl);
            bitmap = BitmapFactory.decodeStream(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Log.d("BitmapLoader", "Items downloaded");
        return bitmap;
    }

    @Override
    public void deliverResult(Bitmap data) {
        Log.d("BitmapLoader", "deliverResult()");

        if (isReset()) {
            return;
        }

        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        Log.d("BitmapLoader", "onStartLoading()");
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        Log.d("BitmapLoader", "onStopLoading()");
        cancelLoad();
    }

    @Override
    protected void onReset() {
        Log.d("BitmapLoader", "onReset()");
        onStopLoading();
        mData = null;
    }
}