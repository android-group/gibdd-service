package ru.android_studio.olga.gibdd_servis;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import ru.android_studio.olga.gibdd_servis.service.OCRService;
import ru.android_studio.olga.gibdd_servis.service.imp.TesseractOCRServiceImp;

/**
 * Created by Ruslan Suleymanov on 23.06.16.
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
        setServiceOCR(new TesseractOCRServiceImp(this));
        getServiceOCR().prepare();
    }

    public void asyncExtractText(Bitmap bitmap, OCRService.LANGUAGE langCode) {
        final ExtractTextAsyncTask extractTextAsyncTask = new ExtractTextAsyncTask();
        Request request = new Request(bitmap, langCode);
        try {
            String result = extractTextAsyncTask.execute(request).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
