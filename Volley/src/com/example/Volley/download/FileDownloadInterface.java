package com.example.Volley.download;

import java.io.Serializable;


public interface FileDownloadInterface extends Serializable{

    public void onDownloading(final int percentage);

    public void onDownloadFail(String errorMessage);

    public void onDownloadFinish();

}
