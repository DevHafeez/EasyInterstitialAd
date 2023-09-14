package com.devHafeez.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.devhafeez.easyinterstitialad.EasyInterstitialAd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      if you want to control show hide from server later, just update this variable
//      EasyInterstitialAd.shouldShowInterAd = true;

//        initialize with context, and ad id
//        note: these needs to be called only once, for loading more, just set the parameter true in show() method
        EasyInterstitialAd.init(this, "");

//        for showing interstital ad later
        EasyInterstitialAd.show(this, true, new EasyInterstitialAd.MyInterAdListener() {
            @Override
            public void onAdClosed(boolean isSuccess) {
                EasyInterstitialAd.MyInterAdListener.super.onAdClosed(isSuccess);
            }
        });
    }
}