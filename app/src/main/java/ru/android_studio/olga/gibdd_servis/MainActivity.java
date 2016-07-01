package ru.android_studio.olga.gibdd_servis;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.IOException;

import ru.android_studio.olga.gibdd_servis.service.OCRService;
import ru.android_studio.olga.gibdd_servis.service.imp.TesseractOCRServiceImp;

public class MainActivity extends ActivityWithMenu {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.main_logo);

        setMenuConfig();

        PrepareOCRAsyncTask ocrAsyncTask = new PrepareOCRAsyncTask();
        try {
            ocrAsyncTask.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    protected void onDestroy() {
        try {
            TesseractOCRServiceImp.getInstance().close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Error by Tesseract OCR service closing", e);
        }
        super.onDestroy();
    }


    @Override
    int getCurrentMenuId() {
        return 0;
    }

    private class PrepareOCRAsyncTask extends AsyncTask<Context, Void, OCRService> {

        @Override
        protected OCRService doInBackground(Context... params) {
            OCRService service = TesseractOCRServiceImp.getInstance(params[0]);
            service.prepare();
            return service;
        }

    }

}