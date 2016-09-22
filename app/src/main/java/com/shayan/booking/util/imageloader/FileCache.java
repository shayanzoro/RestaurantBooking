package com.shayan.booking.util.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Shayan on 9/22/2016.
 */
public class FileCache {

    private File cacheDir;

    public FileCache(Context context) {
        cacheDir = context.getCacheDir();
    }

    public void put(String url, Bitmap bitmap) {
        try {
            String fileName = hash(url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(new File(cacheDir, fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap get(String url) {
        try {
            String fileName = hash(url);
            File file = new File(cacheDir, fileName);
            if (!file.exists()) return null;

            return BitmapFactory.decodeFile(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String hash(String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(url.getBytes("UTF-8"));
        return String.format("%032x", new BigInteger(1, md.digest()));
    }

}