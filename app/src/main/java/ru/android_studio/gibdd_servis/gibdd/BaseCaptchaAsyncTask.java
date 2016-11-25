package ru.android_studio.gibdd_servis.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public abstract class BaseCaptchaAsyncTask extends AsyncTask<String, Void, CaptchaResult> {

    private static final String TAG = "BaseCaptchaAsyncTask";
    CheckType checkType;
    private Context context;
    private ImageView captchaImageView;
    private ProgressDialog progressDialog;

    BaseCaptchaAsyncTask(Context context, ImageView captchaImageView) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        } else if (captchaImageView == null) {
            throw new IllegalArgumentException("captchaImageView can't be null");
        }

        this.context = context;
        this.captchaImageView = captchaImageView;
    }

    BaseCaptchaAsyncTask(Context context, ImageView captchaImageView, CheckType checkType) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        } else if (captchaImageView == null) {
            throw new IllegalArgumentException("captchaImageView can't be null");
        }

        this.context = context;
        this.captchaImageView = captchaImageView;
        this.checkType = checkType;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "START onPreExecute");
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Processing");
        progressDialog.show();
        Log.d(TAG, "END onPreExecute");
    }

    protected CaptchaResult doInBackground(String... urls) {
        try {
            return captchaRequest();
        } catch (IOException e) {
            return null;
        }
    }

    abstract CaptchaResult captchaRequest() throws IOException;

    @Override
    protected void onPostExecute(CaptchaResult result) {
        Log.d(TAG, "START onPostExecute");

        if (result != null) {
            captchaImageView.setImageBitmap(result.getImage());
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Log.d(TAG, "END onPostExecute");
    }
}

