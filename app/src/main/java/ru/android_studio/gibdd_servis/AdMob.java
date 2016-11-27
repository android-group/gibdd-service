package ru.android_studio.gibdd_servis;

import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by yuryandreev on 27/11/2016.
 */

public class AdMob {

    private static final String APP_ID = "ca-app-pub-7157276789419592~2989801067";

    public static void addMobBanner(AppCompatActivity appCompatActivity, String unitId) {
        MobileAds.initialize(appCompatActivity, APP_ID);

        LinearLayout relativeLayout = (LinearLayout) appCompatActivity.findViewById(R.id.body_layout);
        AdView adView = new AdView(appCompatActivity);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("63EC398B918CC0428A2236B4BCC113C3").build();
        if (unitId != null) {
            adView.setAdUnitId(unitId);
            adView.setAdSize(AdSize.BANNER);
        }
        relativeLayout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public static void addMobBanner(AppCompatActivity appCompatActivity) {
        MobileAds.initialize(appCompatActivity, APP_ID);

        AdView adView = (AdView) appCompatActivity.findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("63EC398B918CC0428A2236B4BCC113C3").build();
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
