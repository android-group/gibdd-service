package ru.android_studio.gibdd_servis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.auto.activity.RequestAutoActivity;
import ru.android_studio.gibdd_servis.auto.gibdd.CheckAutoType;
import ru.android_studio.gibdd_servis.driver.activity.RequestDriverActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        AdMob.addMobBanner(this);
    }

    // Проверка водителя
    @OnClick(R.id.menu_driver_btn)
    void menuDriverBtnOnClick() {
        Intent intent = new Intent(getApplicationContext(), RequestDriverActivity.class);
        startActivity(intent);
    }

    // Проверка истории регистрации в ГИБДД
    @OnClick(R.id.menu_car_history_btn)
    void menuCarHistoryBtnOnClick() {
        Intent intent = new Intent(getApplicationContext(), RequestAutoActivity.class);
        intent.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.HISTORY);
        startActivity(intent);
    }

    // Проверка на участие в дорожно-транспортных происшествиях
    @OnClick(R.id.menu_car_dtp_btn)
    void menuCarDtpBtnOnClick() {
        Intent intent = new Intent(getApplicationContext(), RequestAutoActivity.class);
        intent.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.DTP);
        startActivity(intent);
    }

    // Проверка наличия ограничений
    @OnClick(R.id.menu_car_restrict_btn)
    void menuCarRestrictBtnOnClick() {
        Intent intent = new Intent(getApplicationContext(), RequestAutoActivity.class);
        intent.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.RESTRICT);
        startActivity(intent);
    }

    // Проверка нахождения в розыске
    @OnClick(R.id.menu_car_wanted_btn)
    void menuCarWantedBtnOnClick() {
        Intent intent = new Intent(getApplicationContext(), RequestAutoActivity.class);
        intent.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.WANTED);
        startActivity(intent);
    }
}