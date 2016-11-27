package ru.android_studio.gibdd_servis;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by yuryandreev on 27/11/2016.
 */

public class AdMob {

    private static final String APP_ID = "ca-app-pub-7157276789419592~2989801067";

    public static void addMobBanner(AppCompatActivity appCompatActivity) {
    MobileAds.initialize(appCompatActivity, APP_ID);

    AdView mAdView = (AdView) appCompatActivity.findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().addTestDevice("63EC398B918CC0428A2236B4BCC113C3").build();
    //AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);
}
}
