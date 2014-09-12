package com.example.Volley;

import android.app.Application;
import com.example.Volley.download.FileDownloadUtil;


public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {

        super.onCreate();

        init();
    }

    private void init() {

        instance = this;
        MyVolley.init(this);
        FileDownloadUtil.init();
    }

    public static MyApplication getInstance() {

        return instance;
    }

}
