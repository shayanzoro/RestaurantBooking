package com.shayan.booking.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.shayan.booking.loader.BitmapLoader;

/**
 * Created by Shayan on 10/2/2016.
 */
public class BitmapLoaderViewModel implements ViewModel , LoaderManager.LoaderCallbacks<Bitmap> {

    private static final int LOADER_ID = 23;

    private Context context;
    private DataListener listener;

    public BitmapLoaderViewModel( Context context, LoaderManager loaderManager,DataListener dataListener) {
        this.context = context;
        listener = dataListener;
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onDestroy() {
        context = null;
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int loaderId, Bundle args) {
        return new BitmapLoader(context, "http://www.expathousingnetwork.nl/wp-content/uploads/2016/02/Airbnb-55-mln-amsterdam.jpg");
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap bitmap) {
        Log.d("BitmapLoaderViewModel", "onLoadFinished().");
        listener.showImage(bitmap);
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {
        Log.d("BitmapLoaderViewModel", "onLoaderReset()");
    }

    public interface DataListener{
        void showImage(Bitmap bitmap);
    }
}
