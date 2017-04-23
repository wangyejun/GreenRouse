package com.example.niit.greenrouse.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.niit.greenrouse.R;

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
    }
}
