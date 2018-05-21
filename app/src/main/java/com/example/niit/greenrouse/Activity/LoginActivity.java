package com.example.niit.greenrouse.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niit.greenrouse.MainActivity;
import com.example.niit.greenrouse.R;

import cn.bmob.v3.Bmob;

public class LoginActivity extends AppCompatActivity {

    EditText LoginName;
    EditText LoginPwd;
    Button Login;
    TextView Regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"8cb6a5617d6829c540f1515ffccd79e1");
        setContentView(R.layout.activity_login);

        //沉浸式下滑栏
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            int opiton = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(opiton);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
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
//                String name = LoginName.getText().toString();
//                String pwd = LoginPwd .getText().toString();
//                if (name.length()<=0&&pwd.length()<=0){
//                    Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
//                }
//                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intent);
            }
        });
    }
}