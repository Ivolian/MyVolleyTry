package com.example.Volley.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.Volley.R;
import com.example.Volley.TheVolley;
import com.example.Volley.adapter.ListViewAdapter;
import com.example.Volley.model.DownloadInfo;
import com.example.Volley.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final String URL = TheVolley.getBaseUrl() + "/Login";

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.button).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListViewAdapter(this));
    }

    @Override
    public void onClick(View v) {

        sendRequest();
    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        List<DownloadInfo> downloadInfoList = GsonUtil.getInstance().fromJson(response, new TypeToken<List<DownloadInfo>>() {

                                }.getType()
                        );
                        ((ListViewAdapter) listView.getAdapter()).addDownloadInfoList(downloadInfoList);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {



                    }
                }
        );

        TheVolley.getRequestQueue().add(stringRequest);
    }

}


