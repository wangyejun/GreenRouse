package com.example.niit.greenrouse.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niit.greenrouse.R;

public class HomeFragment extends Fragment {
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        initView();

        if (toolbar!=null){
            toolbar.setTitle("");
            //Fragment用ToolBar需要强转
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }
    }
    private void initView() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }
}
