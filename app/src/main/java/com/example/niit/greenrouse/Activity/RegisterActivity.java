package com.example.niit.greenrouse.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niit.greenrouse.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    /*声明控件对象*/


    private Button btn_register;
    private EditText et_register_name;
    private EditText et_register_pwd1;
    private EditText et_register_pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }


    private void initView() {
        btn_register = (Button) findViewById(R.id.btn_register2);
        et_register_name = (EditText) findViewById(R.id.et_register_name);
        et_register_pwd1 = (EditText) findViewById(R.id.et_register_pwd1);
        et_register_pwd2 = (EditText) findViewById(R.id.et_register_pwd2);

        //控件点击监听
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击注册后调用submit中所写的内容同
            case R.id.btn_register2:
                submit();
                break;
        }

    }


    private void submit() {
        final String name = et_register_name.getText().toString();
        final String pwd1 = et_register_pwd1.getText().toString();
        final String pwd2 = et_register_pwd2.getText().toString();

        //对输入框进行判断，isEmpty是判断输入框是否有字符的存在
        if (name.isEmpty()&& pwd1.isEmpty()) {
            Toast.makeText(this, "密码或账号不能为空", Toast.LENGTH_SHORT).show();
        }else if (name.isEmpty()){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if (pwd1.isEmpty()){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else if (pwd2.isEmpty()){
            Toast.makeText(this,"请确认密码",Toast.LENGTH_SHORT).show();
        }

        //使用BmobUser类进行注册
        BmobUser user = new BmobUser();
        user.setUsername(name);
        user.setPassword(pwd1);

        user.signUp(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null||pwd1 == pwd2){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });

    }
}
