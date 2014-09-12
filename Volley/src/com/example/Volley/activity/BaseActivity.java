package com.example.Volley.activity;

import android.support.v4.app.FragmentActivity;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.Volley.toolbox.VolleyErrorHelper;
import com.example.Volley.util.CroutonUtil;


public class BaseActivity extends FragmentActivity {

    @Override
    protected void onDestroy() {

        CroutonUtil.clear();
        super.onDestroy();
    }

    protected final Response.ErrorListener createErrorListener() {

        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

                copeVolleyError(volleyError);
            }
        };
    }

    protected void copeVolleyError(VolleyError volleyError) {

        String errorMessage = VolleyErrorHelper.getErrorMessage(volleyError);
        CroutonUtil.showErrorMessage(this, errorMessage);
    }

}
