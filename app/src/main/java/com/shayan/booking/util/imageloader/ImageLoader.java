package com.shayan.booking.util.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.TintContextWrapper;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;

/**
 * Created by Shayan on 9/11/16.
 */
public class ImageLoader {

    @Getter
    private static ImageLoader instance = new ImageLoader();
    private ExecutorService downloadExecutor, taskExecutor;
    private MemoryCache memoryCache;

    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<>());

    private AtomicBoolean paused = new AtomicBoolean(false);
    private Object pauseLock = new Object();

    public ImageLoader() {
        downloadExecutor = Executors.newFixedThreadPool(5);
        taskExecutor = Executors.newFixedThreadPool(10);
        memoryCache = new MemoryCache();
    }

    public void load(ImageView imageView, String url) {
//        imageView.setImageBitmap(null);

        taskExecutor.submit(() -> {

            if (waitIfPaused()) return;

            imageViews.put(imageView, url);
            Log.d("load", "loadin");
            Bitmap cachedBitmap = memoryCache.get(url);
            if (cachedBitmap != null)
                displayBitmap(imageView, cachedBitmap, url);
            else
                queuePhoto(imageView, url);
        });
    }

    private void queuePhoto(ImageView imageView, String urlString) {
        downloadExecutor.submit(() -> {
            try {
                if (isImageReused(imageView, urlString)) return;

                URL url = new URL(urlString);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                Log.d("loader", " download completed");
                memoryCache.put(urlString, bitmap);
                displayBitmap(imageView, bitmap, urlString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void displayBitmap(ImageView imageView, Bitmap bitmap, String url) {
        Activity activity;
        if (imageView.getContext() instanceof TintContextWrapper) {
            activity = (Activity) ((TintContextWrapper) imageView.getContext()).getBaseContext();
        } else {
            activity = (Activity) imageView.getContext();
        }

        activity.runOnUiThread(() -> {
            if (isImageReused(imageView, url)) return;
            imageView.setImageBitmap(bitmap);
        });
    }

    private boolean waitIfPaused() {
        if (paused.get()) {
            synchronized (pauseLock) {
                if (paused.get()) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void pause() {
        paused.set(true);
    }

    public void resume() {
        paused.set(false);
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    private boolean isImageReused(ImageView imageView, String url) {
        String tag = imageViews.get(imageView);
        boolean isReused = tag == null || !tag.equals(url);
        Log.d("reused", ""+isReused);
        return isReused;
    }

    public void clear() {
        memoryCache.clear();
    }
}