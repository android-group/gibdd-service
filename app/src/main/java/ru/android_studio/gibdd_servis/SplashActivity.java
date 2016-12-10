package ru.android_studio.gibdd_servis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.CaptchaResult;
import ru.android_studio.gibdd_servis.gibdd.CheckType;
import ru.android_studio.gibdd_servis.gibdd.NewCaptchaAsyncTask;

/**
 * Заставка на экране перед загруженным приложением
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Каждые 10 сек проверяем есть ли подключение к интернету
        if (isAvailableInternetConnection()) {
            // если интернет РАБОТАЕТ
            openMenuPage();

        } else {
            // если интернет НЕ РАБОТАЕТ
            openNotInternetPage();
        }
    }

    public BaseCaptchaAsyncTask createCaptchaAsyncTask() {
        return new NewCaptchaAsyncTask(this, null, CheckType.DRIVER);
    }

    protected boolean isAvailableInternetConnection() {
        AsyncTask<String, Void, CaptchaResult> calledCaptchaAsyncTask = createCaptchaAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        try {
            CaptchaResult captchaResult = calledCaptchaAsyncTask.get();

            if (captchaResult != null) {
                return captchaResult.getSessionId() != null;
            }
        } catch (Throwable e) {
            // nothing
        }

        return false;
    }

    private void openNotInternetPage() {
        Intent intent = new Intent(this, NotInternetActivity.class);
        startActivity(intent);
        finish();
    }

    private void openMenuPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}