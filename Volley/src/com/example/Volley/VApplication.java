package com.example.Volley;

import android.app.Application;


public class VApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        init();
    }

    private void init() {

        TheVolley.init(this);
    }

}
