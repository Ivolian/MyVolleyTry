package com.example.Volley.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import com.example.Volley.MyVolley;
import com.example.Volley.R;
import com.example.Volley.download.FileDownloadHandler;
import com.example.Volley.download.FileDownloadInterface;
import com.example.Volley.download.FileDownloadUtil;
import com.example.Volley.util.CroutonUtil;


public class DownloadFragment extends Fragment implements View.OnClickListener {

    private ProgressBar progressBar, progressBar2;

    private Button button, button2;

    private FileDownloadHandler fileDownloadHandler = new FileDownloadHandler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.download_fragment, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        progressBar2 = (ProgressBar) rootView.findViewById(R.id.progress2);
        button2 = (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        boolean flag = FileDownloadUtil.isNetWorkConnected(getActivity());
        if (!flag) {
            CroutonUtil.showErrorMessage(getActivity(), "手机未连接到网络！");
            return;
        }

        if (v.getId() == button2.getId()) {

            // 防止连续下载
            button2.setOnClickListener(null);

            FileDownloadUtil.startDownloadFile(
                    MyVolley.getBaseUrl() + "/bilibili.rar",
                    FileDownloadUtil.getDownloadPath() + "/hehe2.txt",
                    fileDownloadHandler, new FileDownloadInterface() {

                        @Override
                        public void onDownloadFail(String errorMessage) {

                            CroutonUtil.showErrorMessage(getActivity(), errorMessage);
                        }

                        @Override
                        public void onDownloadFinish() {

                            button2.setText("重新下载");
                            button2.setOnClickListener(DownloadFragment.this);
                        }

                        @Override
                        public void onDownloading(final int percentage) {

                            progressBar2.setProgress(percentage);
                            button2.setText(percentage + "%");
                        }
                    }
            );

            return;
        }


        button.setOnClickListener(null);

        FileDownloadUtil.startDownloadFile(
                MyVolley.getBaseUrl() + "/bilibili.rar",
                FileDownloadUtil.getDownloadPath() + "/hehe.txt",
                fileDownloadHandler, new FileDownloadInterface() {

                    @Override
                    public void onDownloadFail(String errorMessage) {

                        CroutonUtil.showErrorMessage(getActivity(), errorMessage);
                    }

                    @Override
                    public void onDownloadFinish() {

                        button.setText("重新下载");
                        button.setOnClickListener(DownloadFragment.this);
                    }

                    @Override
                    public void onDownloading(int percentage) {

                        progressBar.setProgress(percentage);
                        button.setText(percentage + "%");
                    }
                }
        );

    }

}
