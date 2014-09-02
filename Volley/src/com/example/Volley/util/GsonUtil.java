package com.example.Volley.util;

import com.google.gson.Gson;


public class GsonUtil {

    private static Gson instance = new Gson();

    public static String toJson(Object src) {

        return instance.toJson(src);
    }

    public static Gson getInstance() {

        return instance;
    }

}
