package ru.android_studio.gibdd_servis;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import ru.android_studio.gibdd_servis.ocr.OCRService;
import ru.android_studio.gibdd_servis.ocr.imp.TesseractOCRServiceImp;

/**
 * Created by Ruslan Suleymanov on 23.06.16.
 * <p/>
 * Активити с Нижним меню и Тесерактом.
 * Тесеракт сейчас будет отдельным сервисом, по этому от этого класса может быть нужно будет отказаться.
 *
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public abstract class ActivityWithToolbarAndOCR extends ActivityWithToolbar {

    private OCRService serviceOCR;

    public OCRService getServiceOCR() {
        return serviceOCR;
    }

    public ActivityWithToolbarAndOCR setServiceOCR(OCRService serviceOCR) {
        this.serviceOCR = serviceOCR;
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setServiceOCR(TesseractOCRServiceImp.getInstance(this));
        getServiceOCR().prepare();
        /*PrepareOCRAsyncTask ocrAsyncTask = new PrepareOCRAsyncTask();
        try {
            ocrAsyncTask.execute();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }*/

    }

    private class PrepareOCRAsyncTask extends AsyncTask<Void, Void, OCRService> {

        @Override
        protected OCRService doInBackground(Void... params) {
            setServiceOCR(TesseractOCRServiceImp.getInstance(getApplicationContext()));
            getServiceOCR().prepare();
            return getServiceOCR();
        }

        @Override
        protected void onPostExecute(OCRService ocrService) {
            Toast.makeText(getBaseContext(), "ORC is prepared", Toast.LENGTH_LONG).show();
        }
    }

    public String asyncExtractText(Bitmap bitmap, OCRService.LANGUAGE langCode) {
        final ExtractTextAsyncTask extractTextAsyncTask = new ExtractTextAsyncTask();
        Request request = new Request(bitmap, langCode);
        String result = null;
        try {
            result = extractTextAsyncTask.execute(request).get();
        } catch (Exception e) {
            Log.e(TAG, "Error extract text async task", e);
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
