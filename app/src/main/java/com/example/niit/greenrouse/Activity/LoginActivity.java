package com.example.niit.greenrouse.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    private void  initView(){
        LoginName = (EditText)findViewById(R.id.et_LoginName);
        LoginPwd = (EditText)findViewById(R.id.et_LoginPwd);
        Login = (Button)findViewById(R.id.btn_Regist);
        Regist = (TextView)findViewById(R.id.tv_regist);
     }
}
