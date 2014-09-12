package com.example.Volley.download;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class FileDownloadHandler extends Handler {

    // ============== 常量静态属性 ==============

    private static final int ON_DOWNLOAD_FAIL = 0;

    private static final int ON_DOWNLOADING = 1;

    private static final int ON_DOWNLOAD_FINISH = 2;

    private static final String FILE_DOWNLOAD_INTERFACE_TAG = "fileDownloadInterface";

    private static final String PERCENTAGE_TAG = "percentage";

    private static final String ERROR_MESSAGE_TAG = "errorMessage";

    // ============== 事件处理 ==============

    @Override
    public void handleMessage(Message message) {

        Bundle data = message.getData();
        FileDownloadInterface fileDownloadInterface = (FileDownloadInterface) data.getSerializable(FILE_DOWNLOAD_INTERFACE_TAG);
        switch (message.what) {

            case ON_DOWNLOAD_FAIL:
                String errorMessage = data.getString(ERROR_MESSAGE_TAG);
                fileDownloadInterface.onDownloadFail(errorMessage);
                break;

            case ON_DOWNLOADING:
                int percentage = data.getInt("percentage");
                fileDownloadInterface.onDownloading(percentage);
                break;

            case ON_DOWNLOAD_FINISH:
                fileDownloadInterface.onDownloadFinish();
                break;
        }

    }

    // ============== public method ==============

    public void sendDownloadingMessage(FileDownloadInterface fileDownloadInterface, final int percentage) {

        Message message = obtainMessage();
        message.what = ON_DOWNLOADING;
        message.getData().putSerializable(FILE_DOWNLOAD_INTERFACE_TAG, fileDownloadInterface);
        message.getData().putInt(PERCENTAGE_TAG, percentage);
        sendMessage(message);
    }

    public void sendDownloadFailMessage(FileDownloadInterface fileDownloadInterface, String errorMessage) {

        Message message = obtainMessage();
        message.what = ON_DOWNLOAD_FAIL;
        message.getData().putSerializable(FILE_DOWNLOAD_INTERFACE_TAG, fileDownloadInterface);
        message.getData().putString(ERROR_MESSAGE_TAG, errorMessage);
        sendMessage(message);
    }

    public void sendDownloadFinishMessage(FileDownloadInterface fileDownloadInterface) {

        Message message = obtainMessage();
        message.what = ON_DOWNLOAD_FINISH;
        message.getData().putSerializable(FILE_DOWNLOAD_INTERFACE_TAG, fileDownloadInterface);
        sendMessage(message);
    }

}
