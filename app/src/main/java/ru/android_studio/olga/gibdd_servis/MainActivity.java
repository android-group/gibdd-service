package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends ActivityWithMenu {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.main_logo);

        setMenuConfig();
    }


    @Override
    int getCurrentMenuId() {
        return 0;
    }
}