package com.example.niit.greenrouse;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niit.greenrouse.Fragment.BenefitFragment;
import com.example.niit.greenrouse.Fragment.HomeFragment;
import com.example.niit.greenrouse.Fragment.KnapsackFragment;
import com.example.niit.greenrouse.Fragment.TradeFragment;
import com.example.niit.greenrouse.Fragment.WalkFragment;
import com.example.niit.greenrouse.util.AppUtil;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup radioGroup;

    private ImageView iv_head_show;//侧滑菜单头像-头像
    private TextView tv_head_show;//侧滑菜单-昵称
    private Button btn_donation;//侧滑菜单-公益捐款
    private Button btn_my_exchange;//侧滑菜单-我的兑换
    private Button btn_my_work;//侧滑菜单-我的行走
    private TextView tv_versionName;//侧滑菜单-版本号

    public SlidingPaneLayout slidingPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        InitEvent();
    }

    //单点back键提示在按一次
    //退出程序，双击back键时退出程序
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis()-exitTime>2000){
                Toast.makeText(this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);


        iv_head_show = (ImageView) findViewById(R.id.iv_head_show);
        tv_head_show = (TextView) findViewById(R.id.tv_head_show);
        btn_donation = (Button) findViewById(R.id.btn_donation);
        btn_my_exchange = (Button) findViewById(R.id.btn_my_exchange);
        btn_my_work = (Button) findViewById(R.id.btn_my_work);
        tv_versionName = (TextView) findViewById(R.id.tv_versionName);

        slidingPane= (SlidingPaneLayout) findViewById(R.id.slidingPane);

        btn_donation.setOnClickListener(this);
        btn_my_exchange.setOnClickListener(this);
        btn_my_work.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new HomeFragment()).commit();
    }

    public void InitEvent() {
        //显示APP版本号
        tv_versionName.setText(getString(R.string.app_name)+":"+AppUtil.getVersionName(MainActivity.this));
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int i) {
        switch (i) {
            case R.id.rb_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new HomeFragment()).commit();
                break;
            case R.id.rb_benefit:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new BenefitFragment()).commit();
                break;
            case R.id.rb_trade:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new TradeFragment()).commit();
                break;
            case R.id.rb_walk:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new WalkFragment()).commit();
                break;
            case R.id.rb_knapsack:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new KnapsackFragment()).commit();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_donation:
                Toast.makeText(this, "公益捐款", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_my_exchange:
                Toast.makeText(this, "我的兑换", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_my_work:
                Toast.makeText(this, "我的行走", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
