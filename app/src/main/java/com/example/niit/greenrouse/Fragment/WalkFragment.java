package com.example.niit.greenrouse.Fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.niit.greenrouse.R;
import com.example.niit.greenrouse.config.Constant;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class WalkFragment extends Fragment implements Handler.Callback {
    private Toolbar toolbar;
    private ImageView imageView;
    //循环取当前时刻的步数中间的时间间隔
    private long TIME_INTERVAL = 500;
    //控件
    private TextView text_step;    //显示走的步数

    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Handler delayHandler;

    //以bind形式开启service，故有ServiceConnection接收回调
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                //更新步数
                text_step.setText(msg.getData().getInt("step") + "");
                delayHandler.sendEmptyMessageDelayed(Constant.REQUEST_SERVER, TIME_INTERVAL);
                break;
            case Constant.REQUEST_SERVER:
                try {
                    Message msgl = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                    msgl.replyTo = mGetReplyMessenger;
                    messenger.send(msgl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_walk,container,false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initView();

        if (toolbar!=null){
            toolbar.setTitle("");
            //Fragment用ToolBar需要强转
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }
    }
    private void initView(){
        toolbar = getActivity().findViewById(R.id.toolbar4);
        imageView = getActivity().findViewById(R.id.sharewalk);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnekeyShare oks = new OnekeyShare();
                oks.setTitle("分享");
                oks.setTitle("dddddddddddd");
                oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");
                oks.setTitleUrl("http://wwww.baidu.com");
                oks.show(getActivity());
            }
        });
    }

}
