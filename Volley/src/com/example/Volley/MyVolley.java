package com.example.Volley;

import android.app.ActivityManager;
import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Volley.toolbox.BitmapLruCache;

import java.util.Map;


public class MyVolley {

    private static RequestQueue mRequestQueue;

    private static ImageLoader mImageLoader;

    // 每日改 IP
    private static final String BASE_URL = "http://192.168.7.115:8080";

    static void init(Context context) {

        mRequestQueue = Volley.newRequestQueue(context);

        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        int cacheSize = 1024 * 1024 * memClass / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
    }

    public static RequestQueue getRequestQueue() {

        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
     * that no memory caching is used. This is useful for images that you know that will be show
     * only once.
     *
     * @return
     */
    public static ImageLoader getImageLoader() {

        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }

    public static String getBaseUrl() {

        return BASE_URL;
    }

    public static void sendRequest(String relativePath, final Map<String, String> params,
                            Response.Listener<String> listener, Response.ErrorListener errorListener) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                BASE_URL + relativePath,
                listener,
                errorListener
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };

        mRequestQueue.add(stringRequest);
    }

}
