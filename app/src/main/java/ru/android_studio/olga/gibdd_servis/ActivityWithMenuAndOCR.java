package ru.android_studio.olga.gibdd_servis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import ru.android_studio.olga.gibdd_servis.service.OCRService;
import ru.android_studio.olga.gibdd_servis.service.imp.TesseractOCRServiceImp;

/**
 * Created by Ruslan Suleymanov on 23.06.16.
 *
 * Активити с Нижним меню и Тесерактом.
 * Тесеракт сейчас будет отдельным сервисом, по этому от этого класса может быть нужно будет отказаться.
 *
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public abstract class ActivityWithMenuAndOCR extends ActivityWithMenu {

    private OCRService serviceOCR;

    public OCRService getServiceOCR() {
        return serviceOCR;
    }

    public ActivityWithMenuAndOCR setServiceOCR(OCRService serviceOCR) {
        this.serviceOCR = serviceOCR;
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PrepareOCRAsyncTask ocrAsyncTask = new PrepareOCRAsyncTask();
        try {
            ocrAsyncTask.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), e);
        }

    }

    private class PrepareOCRAsyncTask extends AsyncTask<Context, Void, OCRService> {

        @Override
        protected OCRService doInBackground(Context... params) {
            setServiceOCR(TesseractOCRServiceImp.getInstance(params[0]));
            getServiceOCR().prepare();
            return getServiceOCR();
        }

    }

    public String asyncExtractText(Bitmap bitmap, OCRService.LANGUAGE langCode) {
        final ExtractTextAsyncTask extractTextAsyncTask = new ExtractTextAsyncTask();
        Request request = new Request(bitmap, langCode);
        String result = null;
        try {
            result = extractTextAsyncTask.execute(request).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("TAG", result);
        return result;
    }

    class Request {
        Bitmap captcha;
        OCRService.LANGUAGE string;

        public Request(Bitmap captcha, OCRService.LANGUAGE string) {
            this.captcha = captcha;
            this.string = string;
        }
    }

    private class ExtractTextAsyncTask extends AsyncTask<Request, Void, String> {

        @Override
        protected String doInBackground(Request... params) {
            Request param = params[0];
            return getServiceOCR().extractText(param.captcha, param.string);
        }

        @Override
        protected void onPostExecute(String resultJson) {
            Log.i("TAG", resultJson);
        }
    }

}
