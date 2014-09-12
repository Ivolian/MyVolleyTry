package com.example.Volley.download;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FileDownloadUtil {

    static final int TIME_OUT = 3 * 1000;

    static File MyDownload;

    // ============== 公有函数 ==============

    public static void init() {

        File SDPath = Environment.getExternalStorageDirectory();
        MyDownload = new File(SDPath.getAbsolutePath() + "/MyDownload");
        if (!MyDownload.exists()) {
            boolean flag = MyDownload.mkdir();
            if (flag) {
                Log.e("result", "create MyDownload successfully.");
            } else {
                Log.e("result", "create MyDownload fail.");
            }
        }else{
            Log.e("result", "MyDownload exists.");
        }
    }

    public static String getDownloadPath() {

        if (MyDownload == null) {
            throw new RuntimeException("FileDownloadUtil hasn't been inited");
        }

        return MyDownload.getAbsolutePath();
    }

    public static void startDownloadFile(final String downloadFileUrl, final String downloadFilePath,
                                         final FileDownloadHandler fileDownloadHandler,
                                         final FileDownloadInterface fileDownloadInterface) {

        new Thread() {

            @Override
            public void run() {

                downloadFile(downloadFileUrl, downloadFilePath, fileDownloadHandler, fileDownloadInterface);
            }
        }.start();
    }

    // ============== 私有函数 ==============

    private static void downloadFile(String downloadFileUrl, String downloadFilePath,
                                     FileDownloadHandler fileDownloadHandler,
                                     FileDownloadInterface fileDownloadInterface) {

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            URL url = new URL(downloadFileUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(TIME_OUT);

            int contentLength = httpURLConnection.getContentLength();
            int len, sum = 0;
            byte[] buffer = new byte[1024];
            inputStream = httpURLConnection.getInputStream();
            fileOutputStream = new FileOutputStream(downloadFilePath);

            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
                sum += len;
                int percentage = sum * 100 / contentLength;
                fileDownloadHandler.sendDownloadingMessage(fileDownloadInterface, percentage);
            }
            fileDownloadHandler.sendDownloadFinishMessage(fileDownloadInterface);
        } catch (Exception e) {
            // todo cope all kinds of exception, such as FileNotFoundException
            String errorMessage = e.getClass() + "\r\n" + e.getMessage();
            fileDownloadHandler.sendDownloadFailMessage(fileDownloadInterface, errorMessage);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                // it will not happen
            }
        }
    }

    public static boolean isNetWorkConnected(Context context) {

        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

}
