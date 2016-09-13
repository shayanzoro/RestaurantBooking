package com.shayan.booking.util.imageloader;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

/**
 * Created by Shayan on 9/11/16.
 */
@NoArgsConstructor
public class MemoryCache {

    private Map<String, Bitmap> cache = Collections.synchronizedMap(new HashMap());

    public Bitmap get(String key) {
        if(!cache.containsKey(key))
            return null;
        else
            return cache.get(key);
    }

    public void put(String key, Bitmap bitmap) {
        cache.put(key, bitmap);
        Log.d("cache", "cached items: " + cache.size());
    }

    public void clear() {
        cache.clear();
    }
}