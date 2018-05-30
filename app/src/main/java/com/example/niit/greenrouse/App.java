package com.example.niit.greenrouse;

import android.app.Application;

import com.vondear.rxtools.RxTool;

/**
 * Created by wangyejun on 2018/5/22.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
    }
}
