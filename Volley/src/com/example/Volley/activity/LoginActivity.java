package com.example.Volley.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.Volley.MyVolley;
import com.example.Volley.R;
import com.example.Volley.dialogfragment.LoadingDialogFragment;
import com.example.Volley.model.Result;
import com.example.Volley.util.CroutonUtil;
import com.example.Volley.util.GsonUtil;
import com.example.Volley.util.SharedPreferencesUtil;
import com.example.Volley.util.StringUtil;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LoadingDialogFragment loadingDialogFragment;

    private EditText etUsername;

    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
    }

    @Override
    public void onClick(View v) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            CroutonUtil.showInfoMessage(this, "用户名和密码不能为空。");
            return;
        }

        login(username, password);
    }

    private void initView() {

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        String username = SharedPreferencesUtil.getString("username", "");
        String password = SharedPreferencesUtil.getString("password", "");
        etUsername.setText(username);
        etPassword.setText(password);

        findViewById(R.id.llLogin).setOnClickListener(this);
    }

    private void login(String username, String password) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);

        showLoadingDialog();
        MyVolley.sendRequest("/login", params, createResponseListener(), createErrorListener());
    }

    private Response.Listener<String> createResponseListener() {

        return new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                hideLoadingDialog();
                Result result = GsonUtil.getInstance().fromJson(response, Result.class);
                boolean success = result.isSuccess();

                if (success) {
                    startMainActivity();
                    saveUserInfo();
                } else {
                    CroutonUtil.showErrorMessage(LoginActivity.this, "用户名或密码错误！");
                }
            }
        };
    }

    private void saveUserInfo() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        SharedPreferencesUtil.putString("username", username);
        SharedPreferencesUtil.putString("password", password);
    }

    private void startMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showLoadingDialog() {

        if (loadingDialogFragment == null) {
            loadingDialogFragment = new LoadingDialogFragment("登陆中...");
        }

        loadingDialogFragment.show(getSupportFragmentManager(), LoadingDialogFragment.class.getName());
    }

    private void hideLoadingDialog() {

        loadingDialogFragment.dismiss();
    }

    @Override
    protected void copeVolleyError(VolleyError volleyError) {

        hideLoadingDialog();
        super.copeVolleyError(volleyError);
    }

}
