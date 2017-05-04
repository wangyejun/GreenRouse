package com.example.niit.greenrouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.RadioGroup;

import com.example.niit.greenrouse.Fragment.BenefitFragment;
import com.example.niit.greenrouse.Fragment.HomeFragment;
import com.example.niit.greenrouse.Fragment.KnapsackFragment;
import com.example.niit.greenrouse.Fragment.TradeFragment;
import com.example.niit.greenrouse.Fragment.WalkFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    HomeFragment homeFragment = new HomeFragment();
    BenefitFragment benefitFragment = new BenefitFragment();
    TradeFragment tradeframent = new TradeFragment();
    KnapsackFragment knapsackfranment = new KnapsackFragment();
    WalkFragment walkFragment = new WalkFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
        InitEvent();
    }


    private void InitView(){
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.check(R.id.rb_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,homeFragment).commit();
    }
    public void InitEvent(){
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int i) {
        switch (i){
            case R.id.rb_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,homeFragment).commit();
                break;
            case R.id.rb_benefit:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,benefitFragment).commit();
                break;
            case R.id.rb_trade:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,tradeframent).commit();
                break;
            case R.id.rb_walk:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,walkFragment).commit();
                break;
            case R.id.rb_knapsack:
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,knapsackfranment).commit();
        }

    }
}
