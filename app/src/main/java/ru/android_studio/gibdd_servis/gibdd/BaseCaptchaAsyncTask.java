package ru.android_studio.gibdd_servis.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public abstract class BaseCaptchaAsyncTask extends AsyncTask<String, Void, CaptchaResult> {

    private static final String TAG = "BaseCaptchaAsyncTask";
    private Context context;
    private ImageView captchaImageView;
    private ProgressDialog progressDialog;

    BaseCaptchaAsyncTask(Context context, ImageView captchaImageView) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        /*
        because captcha ImageView can be null for check connection
        else if (captchaImageView == null) {
            throw new IllegalArgumentException("captchaImageView can't be null");
        }*/

        this.context = context;
        this.captchaImageView = captchaImageView;
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
            setCaptchaImage(result.getImage());
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Log.d(TAG, "END onPostExecute");
    }

    private void setCaptchaImage(Bitmap image) {
        if(captchaImageView != null) {
            captchaImageView.setImageBitmap(image);
        }
    }
}

