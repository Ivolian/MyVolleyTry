package com.example.Volley.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.Volley.R;
import com.example.Volley.util.CroutonUtil;


public class BeforeLoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.before_login_activity);

        initView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLogin:
                startLoginActivity();
                break;
            case R.id.btnRegister:
                CroutonUtil.showInfoMessage(this, "暂时无法注册，登陆试试。");
                break;
        }
    }

    private void initView() {

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }

    private void startLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
