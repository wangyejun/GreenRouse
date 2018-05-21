package com.example.niit.greenrouse.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niit.greenrouse.R;
import com.example.niit.greenrouse.util.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    EditText RegistName;
    EditText RegistPwd;
    EditText RegistPwd2;
    Button Regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //控件初始化
        initView();
    }

    private void initView(){
        RegistName = (EditText)findViewById(R.id.et_RegistName);
        RegistPwd = (EditText)findViewById(R.id.et_LoginPwd);
        RegistPwd2 = (EditText)findViewById(R.id.et_RegistPwd2);
        Regist = (Button)findViewById(R.id.btn_Regist);



        Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = RegistName.getText().toString();
                String pwd = RegistPwd.getText().toString();

                if (name.length()==0 || pwd.length()==0){
                    Toast.makeText(RegisterActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
