package com.example.niit.greenrouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    private RxDialogChooseImage mDialog;
    private Uri mUri;
    private File mFile;

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
        radioGroup = findViewById(R.id.radiogroup);
        iv_head_show = findViewById(R.id.iv_head_show);
        tv_head_show = findViewById(R.id.tv_head_show);
        btn_donation = findViewById(R.id.btn_donation);
        btn_my_exchange = findViewById(R.id.btn_my_exchange);
        btn_my_work = findViewById(R.id.btn_my_work);
        tv_versionName = findViewById(R.id.tv_versionName);
        slidingPane= findViewById(R.id.slidingPane);

        iv_head_show.setOnClickListener(this);
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
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head_show:
                chooseImage();
                break;
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
    private void chooseImage() {
        if (mDialog==null){
            mDialog=new RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE);
        }
        if (!mDialog.isShowing()){
            mDialog.show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //选择相册之后的处理
            case RxPhotoTool.GET_IMAGE_FROM_PHONE:
                if (resultCode == RESULT_OK) {
                    initUCrop( data.getData());
                }
                break;
            //选择照相机之后的处理
            case RxPhotoTool.GET_IMAGE_BY_CAMERA:
                if (resultCode == RESULT_OK) {
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }
                break;
            //普通裁剪后的处理
            case RxPhotoTool.CROP_IMAGE:
                GlideApp.with(this)
                        .load(RxPhotoTool.cropImageUri)
                        .placeholder(R.mipmap.show)
                        .error(R.mipmap.show)
                        .error(R.mipmap.ic_launcher_round)
                        .into(iv_head_show);
                break;
            //UCrop裁剪之后的处理
            case UCrop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    mUri = UCrop.getOutput(data);
                    mFile = roadImageView(mUri);
                }
                break;
            //UCrop裁剪错误之后的处理
            case UCrop.RESULT_ERROR:
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }

    }
    private void initUCrop( Uri data) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(this.getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));
        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
        options.setCircleDimmedLayer(true);
        //设置是否展示矩形裁剪框
        options.setShowCropFrame(false);

        //设置竖线的数量
        options.setCropGridColumnCount(0);
        //设置横线的数量
        options.setCropGridRowCount(0);

        UCrop.of(data, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }

    /**
     * 从Uri中加载图片 并将其转化成File文件返回
     *
     * @param mUri
     * @return
     */
    private File roadImageView(Uri mUri) {
        GlideApp.with(this)
                .load(mUri)
                .placeholder(R.mipmap.show)
                .error(R.mipmap.show)
                .into(iv_head_show);
        return new File(RxPhotoTool.getImageAbsolutePath(this, mUri));
    }

}
