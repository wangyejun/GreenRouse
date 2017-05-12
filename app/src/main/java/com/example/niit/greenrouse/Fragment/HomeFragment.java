package com.example.niit.greenrouse.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.niit.greenrouse.MainActivity;
import com.example.niit.greenrouse.R;
import com.example.niit.greenrouse.zxing.android.CaptureActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView imageView;
    private ImageView iv_personage;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";


    private static final int REQUEST_CODE_SCAN = 0x0000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        if (toolbar!=null){
            toolbar.setTitle("");
            //Fragment用ToolBar需要强转
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }

        final SlidingPaneLayout layout=((MainActivity)getActivity()).slidingPane;
        iv_personage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.isOpen()){
                    layout.closePane();
                }else {
                    layout.openPane();
                }
            }
        });
    }
    private void initView() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        imageView = (ImageView) getActivity().findViewById(R.id.iv_scan);
        iv_personage= (ImageView) getActivity().findViewById(R.id.iv_personage);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_scan:
                Intent intent = new Intent(getActivity(),CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SCAN);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && requestCode == REQUEST_CODE_SCAN){
            if (data!=null){
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

            }
        }
    }
}
