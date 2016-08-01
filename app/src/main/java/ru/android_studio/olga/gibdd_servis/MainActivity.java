package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends ActivityWithMenu {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setLogo(R.mipmap.main_logo);
        setSupportActionBar(toolbar);

        setMenuConfig();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getCurrentMenuId() {
        return 0;
    }

}