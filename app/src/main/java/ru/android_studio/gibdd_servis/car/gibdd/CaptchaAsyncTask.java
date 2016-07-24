package ru.android_studio.gibdd_servis.car.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Запрос капчи с сайта ГИБДД
 */
public class CaptchaAsyncTask extends AsyncTask<Void, Void, CaptchaResult> {

    private static final String TAG = "CaptchaAsyncTask";
    private Context context;
    private ImageView captchaImageView;
    //private CaptchaResult captchaResult;
    /**
     * Окно отображается при открытом асинх таске
     */
    private ProgressDialog progressDialog;

    public CaptchaAsyncTask(Context context, ImageView captchaImageView) {
        super();
        this.context = context;
        this.captchaImageView = captchaImageView;
        //this.captchaResult = captchaResult;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "START onPreExecute");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Processing");
        progressDialog.show();
        Log.d(TAG, "END onPreExecute");
    }

    @Override
    protected CaptchaResult doInBackground(Void... params) {
        Log.d(TAG, "START doInBackground");

        try {
            return CaptchaService.captchaRequest();
        } catch (IOException e) {
            Log.e(TAG, "Error to get captcha", e);
            Toast.makeText(context, "Can't load captcha image, please try again later", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    protected void onPostExecute(CaptchaResult result) {
        Log.d(TAG, "START onPostExecute");

        captchaImageView.setImageBitmap(result.getImage());
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Log.d(TAG, "END onPostExecute");
    }
}