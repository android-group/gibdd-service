package ru.android_studio.gibdd_servis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.auto.activity.RequestAutoActivity;
import ru.android_studio.gibdd_servis.auto.gibdd.CheckAutoType;
import ru.android_studio.gibdd_servis.driver.activity.RequestDriverActivity;

public class MainActivity extends ActivityWithToolbar {

    private static final String TAG = "MainActivity";

    @BindView(R.id.menu_car_history_btn)
    View menuCarHistoryBtn;

    @BindView(R.id.menu_car_dtp_btn)
    View menuCarDtpBtn;

    @BindView(R.id.menu_car_restrict_btn)
    View menuCarRestrictBtn;

    @BindView(R.id.menu_car_wanted_btn)
    View menuCarWantedBtn;

    @BindView(R.id.menu_driver_btn)
    View menuDriverBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener menuOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // Проверка штрафов
                    /*case R.id.menu_fine_btn:
                        Intent intentFineActivity = new Intent(getApplicationContext(), RequestFineActivity.class);
                        startActivity(intentFineActivity);
                        break;*/
                    // Проверка водителя
                    case R.id.menu_driver_btn:
                        Intent intentDriverActivity = new Intent(getApplicationContext(), RequestDriverActivity.class);
                        startActivity(intentDriverActivity);
                        break;
                    // Проверка истории регистрации в ГИБДД
                    case R.id.menu_car_history_btn:
                        Intent intentCarHistoryActivity = new Intent(getApplicationContext(), RequestAutoActivity.class);
                        intentCarHistoryActivity.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.HISTORY);
                        startActivity(intentCarHistoryActivity);
                        break;
                    // Проверка на участие в дорожно-транспортных происшествиях
                    case R.id.menu_car_dtp_btn:
                        Intent intentCarDtpActivity = new Intent(getApplicationContext(), RequestAutoActivity.class);
                        intentCarDtpActivity.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.DTP);
                        startActivity(intentCarDtpActivity);
                        break;
                    // Проверка наличия ограничений
                    case R.id.menu_car_restrict_btn:
                        Intent intentCarRestrictActivity = new Intent(getApplicationContext(), RequestAutoActivity.class);
                        intentCarRestrictActivity.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.RESTRICT);
                        startActivity(intentCarRestrictActivity);
                        break;
                    // Проверка нахождения в розыске
                    case R.id.menu_car_wanted_btn:
                        Intent intentCarWantedActivity = new Intent(getApplicationContext(), RequestAutoActivity.class);
                        intentCarWantedActivity.putExtra(RequestAutoActivity.CHECK_AUTO_TYPE, CheckAutoType.WANTED);
                        startActivity(intentCarWantedActivity);
                        break;
                    //прием обращений
                    /*case R.id.menu_question_btn:
                        Intent intentQuestionActivity = new Intent(getApplicationContext(), QuestionActivity.class);
                        startActivity(intentQuestionActivity);
                        break;*/
                }
            }
        };

        ButterKnife.bind(this);

        menuCarHistoryBtn.setOnClickListener(menuOnClickListener);
        menuCarDtpBtn.setOnClickListener(menuOnClickListener);
        menuCarRestrictBtn.setOnClickListener(menuOnClickListener);
        menuCarWantedBtn.setOnClickListener(menuOnClickListener);
        menuDriverBtn.setOnClickListener(menuOnClickListener);

        toolbar.setLogo(R.mipmap.ic_main);
    }
}