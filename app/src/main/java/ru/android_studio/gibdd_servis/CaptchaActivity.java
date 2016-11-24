package ru.android_studio.gibdd_servis;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.CaptchaResult;

public abstract class CaptchaActivity extends ActivityWithToolbar {

    private static final String TAG = "MenuAndOCRAndCaptcha";

    @BindView(R.id.captcha_image_view)
    protected ImageView captchaImageView;

    @BindView(R.id.captcha_edit_text)
    protected EditText captchaEditText;

    private AsyncTask<String, Void, CaptchaResult> calledCaptchaAsyncTask;

    /**
     * Загрузить картинку капчи
     */
    @OnClick(R.id.captcha_image_view)
    protected void loadCaptcha() {
        Log.d(TAG, "START loadCaptcha");
        clearPrevResult();

        // if use execute when method in AsyncTask 'doInBackground' will not call
        calledCaptchaAsyncTask = createCaptchaAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Log.d(TAG, "END loadCaptcha");
    }

    private void clearPrevResult() {
        captchaEditText.getText().clear();
    }


    /*
    * Нужно выбрать одну из реализаций, старая или новая версия запроса капчи
    * */
    public abstract BaseCaptchaAsyncTask createCaptchaAsyncTask();

    protected String getSessionId() {
        String sessionId = null;
        CaptchaResult captchaResult = null;
        try {
            captchaResult = calledCaptchaAsyncTask.get();
        } catch (InterruptedException | ExecutionException e) {
            Toast.makeText(this, "Интернет не доступен", Toast.LENGTH_SHORT).show();
        }

        if (captchaResult != null) {
            sessionId = captchaResult.getSessionId();
        } else {
            Toast.makeText(this, "Интернет не доступен", Toast.LENGTH_SHORT).show();
        }
        return sessionId;
    }

    protected void finishCauseInternetNotAvailable() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public String getCaptchaWord() {
        return captchaEditText.getText().toString();
    }
}
