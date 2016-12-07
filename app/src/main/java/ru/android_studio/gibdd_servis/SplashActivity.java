package ru.android_studio.gibdd_servis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Заставка на экране перед загруженным приложением
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Каждые 10 сек проверяем есть ли подключение к интернету
        if (isAvalibleInternetConnection()) {
            // если интернет РАБОТАЕТ
            openMenuPage();

        } else {
            // если интернет НЕ РАБОТАЕТ
            openNotInternetPage();
        }
    }

    private boolean isAvalibleInternetConnection() {
        return true;
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