package com.example.niit.greenrouse.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.niit.greenrouse.MainActivity;
import com.example.niit.greenrouse.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_login_name;
    private EditText et_login_pwd;
    private Button btn_register;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //沉浸式下滑栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int opiton = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(opiton);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击登录按钮后对输入的用户名和密码进行比对正确则跳转到主界面错误则提示
            case R.id.btn_login:
                login();
                break;
            //点击注册按钮后跳转到注册界面
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }

    private void login() {
        String name = et_login_name.getText().toString();
        String pwd1 = et_login_pwd.getText().toString();
        //对输入框进行判断，isEmpty是判断输入框是否有字符的存在
        if (name.isEmpty() && pwd1.isEmpty()) {
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (pwd1.isEmpty()) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }

        //使用Bmob云运行登录
        BmobUser user = new BmobUser();
        user.setUsername(name);
        user.setPassword(pwd1);
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    //提示登录成功并跳转到主界面
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initView() {
        et_login_name = (EditText) findViewById(R.id.et_login_name);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }
}