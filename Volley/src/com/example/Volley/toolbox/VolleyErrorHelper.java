package com.example.Volley.toolbox;

import com.android.volley.*;


public class VolleyErrorHelper {

    // 手机未连接到网络
    public static final int NETWORK_UNREACHABLE = 0;

    private static final String NETWORK_UNREACHABLE_STRING = "Network is unreachable";

    // 连接被拒（服务器未开启）
    public static final int CONNECTION_REFUSED = 1;

    private static final String CONNECTION_REFUSED_STRING = "Connection refused";

    // 连接超时（windows 防火墙未关闭，或者服务器来不及处理请求时）
    public static final int CONNECTION_TIMEOUT = 2;

    // 比如 404错误，500错误，才疏学浅只知道这两个，已测试
    public static final int SERVER_ERROR = 3;

    public static int getErrorType(VolleyError volleyError) {

        if (isNetworkProblem(volleyError)) {
            String errorMessage = volleyError.getMessage();
            if (errorMessage.contains(NETWORK_UNREACHABLE_STRING)) {
                return NETWORK_UNREACHABLE;
            }
            if (errorMessage.contains(CONNECTION_REFUSED_STRING)) {
                return CONNECTION_REFUSED;
            }
        }

        if (isTimeoutProblem(volleyError)) {
            return CONNECTION_TIMEOUT;
        }

        if (isServerProblem(volleyError)) {
            return SERVER_ERROR;
        }

        return -1;
    }

    // 当 errorType 为 ServerError 时，进一步判断 statusCode
    public static int getServerErrorStatusCode(ServerError serverError) {

        return  serverError.networkResponse.statusCode;
    }

    private static boolean isNetworkProblem(VolleyError volleyError) {

        return (volleyError instanceof NetworkError);
    }

    private static boolean isTimeoutProblem(VolleyError volleyError) {

        return (volleyError instanceof TimeoutError);
    }

    private static boolean isServerProblem(VolleyError volleyError) {

        return (volleyError instanceof ServerError);
    }

}