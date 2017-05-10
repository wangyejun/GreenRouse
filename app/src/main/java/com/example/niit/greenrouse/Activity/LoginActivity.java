package com.example.niit.greenrouse.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.niit.greenrouse.MainActivity;
import com.example.niit.greenrouse.R;

public class LoginActivity extends AppCompatActivity {

    EditText LoginName;
    EditText LoginPwd;
    Button Login;
    TextView Regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //控件初始化
        initView();
        initEvent();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        LoginName = (EditText) findViewById(R.id.et_LoginName);
        LoginPwd = (EditText) findViewById(R.id.et_LoginPwd);
        Login = (Button) findViewById(R.id.btn_Login);
        Regist = (TextView) findViewById(R.id.tv_regist);
    }
    private void initEvent(){
        Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}