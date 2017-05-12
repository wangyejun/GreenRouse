package com.example.niit.greenrouse.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.niit.greenrouse.R;
import com.example.niit.greenrouse.zxing.android.CaptureActivity;

public class BenefitFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;


    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";


    private static final int REQUEST_CODE_SCAN = 0x0000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benefit,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }
    private void initView(){
        imageView = (ImageView) getActivity().findViewById(R.id.ivbenefit_scan);

        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivbenefit_scan:

                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SCAN);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && requestCode == REQUEST_CODE_SCAN);
        if (data!=null){
            String content = data.getStringExtra(DECODED_CONTENT_KEY);
            Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
        }
    }
}
