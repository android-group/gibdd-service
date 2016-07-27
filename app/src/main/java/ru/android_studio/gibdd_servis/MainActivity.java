package ru.android_studio.gibdd_servis;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.service.OCRService;
import ru.android_studio.gibdd_servis.service.imp.TesseractOCRServiceImp;

public class MainActivity extends ActivityWithMenu {

    public static TesseractOCRServiceImp ocrService;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setLogo(R.mipmap.main_logo);
        setSupportActionBar(toolbar);

        setMenuConfig();

        if (ocrService == null) {
            PrepareOCRAsyncTask ocrAsyncTask = new PrepareOCRAsyncTask();
            try {
                ocrAsyncTask.execute(this);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (ocrService != null) {
            try {
                ocrService.close();
            } catch (Throwable e) {
                Log.e(TAG, "Error by Tesseract OCR service closing", e);
            }
        }
        super.onDestroy();
    }


    @Override
    protected int getCurrentMenuId() {
        return 0;
    }

    private class PrepareOCRAsyncTask extends AsyncTask<Context, Void, OCRService> {

        @Override
        protected OCRService doInBackground(Context... params) {
            ocrService = TesseractOCRServiceImp.getInstance(params[0]);
            ocrService.prepare();
            return ocrService;
        }

    }

}