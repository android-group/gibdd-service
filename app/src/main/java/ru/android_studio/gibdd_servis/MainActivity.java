package ru.android_studio.gibdd_servis;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ocr.OCRService;
import ru.android_studio.gibdd_servis.ocr.imp.TesseractOCRServiceImp;

public class MainActivity extends ActivityWithMenu {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setLogo(R.mipmap.ic_main);
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