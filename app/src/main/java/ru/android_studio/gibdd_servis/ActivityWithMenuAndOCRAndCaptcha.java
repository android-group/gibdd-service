package ru.android_studio.gibdd_servis;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.CaptchaResult;

public abstract class ActivityWithMenuAndOCRAndCaptcha extends ActivityWithMenuAndOCR {

    private static final String TAG = "MenuAndOCRAndCaptcha";

    @BindView(R.id.captcha_image_view)
    protected ImageView captchaImageView;

    @BindView(R.id.captcha_edit_text)
    protected EditText captchaEditText;

    private BaseCaptchaAsyncTask captchaAsyncTask;

    private CaptchaResult captchaResult;

    /**
     * Загрузить картинку капчи
     */
    @OnClick(R.id.captcha_image_view)
    protected void loadCaptcha() {
        Log.d(TAG, "START loadCaptcha");

        captchaAsyncTask = getBaseCaptchaAsyncTask();

        // if use execute when method in AsyncTask 'doInBackground' will not call
        captchaAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Log.d(TAG, "END loadCaptcha");
    }

    /*
    * Нужно выбрать одну из реализаций, старая или новая версия запроса капчи
    * */
    public abstract BaseCaptchaAsyncTask getBaseCaptchaAsyncTask();

    protected String getSessionId() {
        String sessionId = null;
        try {
            sessionId = getCaptchaResult().getSessionId();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, "can't get session id", e);
        }
        return sessionId;
    }

    private CaptchaResult getCaptchaResult() throws ExecutionException, InterruptedException {
        if (captchaResult == null) {
            captchaResult = captchaAsyncTask.get();
        }
        return captchaResult;
    }
}
