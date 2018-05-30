package com.example.niit.greenrouse.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.niit.greenrouse.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class WelcomeActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对Bmob进行初始化，需要在setContentview方法之前
        Bmob.initialize(this,"f42ca8d297f049f8d9a1ae9d790d8757");
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        //获取当前用户信息，登录过一次后下次无需登录操作
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser!=null){
            handler.postDelayed(runnable1,2000);
        }else {
            handler.postDelayed(runnable,2000);
        }

//       initwelcome();
    }
//    private void initwelcome(){
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },2000);
//    }
}
