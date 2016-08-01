package ru.android_studio.olga.gibdd_servis;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.Closeable;
import java.io.IOException;

import ru.android_studio.olga.gibdd_servis.service.OCRService;
import ru.android_studio.olga.gibdd_servis.service.imp.TesseractOCRServiceImp;

/*
* При загрузке устанавливаем в Picasso кэш OkHttpDownloader
* */
public class BootApplication extends Application {

    private static final String TAG = BootApplication.class.getSimpleName();

    private static OCRService ocrService;

    @Override
    public void onCreate() {
        super.onCreate();

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

        PrepareOCRAsyncTask ocrAsyncTask = new PrepareOCRAsyncTask();
        try {
            ocrAsyncTask.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (ocrService instanceof Closeable)
            try {
                ((Closeable) ocrService).close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Error by OCR service closing", e);
            }
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