package com.example.allen.frameworkexample.volley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Allen Lin 2016/02
 */
public class MyImageCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    public MyImageCache() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }

}