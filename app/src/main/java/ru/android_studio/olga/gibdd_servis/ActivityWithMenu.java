package ru.android_studio.olga.gibdd_servis;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.BindView;

/**
 * Created by y.andreev on 03.06.2016.
 */
public abstract class ActivityWithMenu extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    @IdRes
    abstract int getCurrentMenuId();


    @BindView(R.id.menu_car_btn)
    View menuCarBtn;
    @BindView(R.id.menu_driver_btn)
    View menuDriverBtn;
    @BindView(R.id.menu_fine_btn)
    View menuFindBtn;
    @BindView(R.id.menu_question_btn)
    View menuQuestionBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public void setMenuConfig() {
        menuCarBtn.setOnClickListener(menuOnClickListener);
        menuDriverBtn.setOnClickListener(menuOnClickListener);
        menuFindBtn.setOnClickListener(menuOnClickListener);
        menuQuestionBtn.setOnClickListener(menuOnClickListener);
    }

    private View.OnClickListener menuOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getCurrentMenuId() == v.getId()) {
                Log.i("ActivityWithMenu", "this is current menu");
                return;
            }

            switch (v.getId()) {
                //проверка штрафов
                case R.id.menu_fine_btn:
                    Intent intentFineActivity = new Intent(getApplicationContext(), FineActivity.class);
                    startActivity(intentFineActivity);
                    break;
                //проверка водителя
                case R.id.menu_driver_btn:
                    Intent intentDriverActivity = new Intent(getApplicationContext(), DriverActivity.class);
                    startActivity(intentDriverActivity);
                    break;
                //проверка автомобиля
                case R.id.menu_car_btn:
                    Intent intentCarActivity = new Intent(getApplicationContext(), CarActivity.class);
                    startActivity(intentCarActivity);
                    break;
                //прием обращений
                case R.id.menu_question_btn:
                    Intent intentQuestionActivity = new Intent(getApplicationContext(), QuestionActivity.class);
                    startActivity(intentQuestionActivity);
                    break;
            }
        }
    };

    protected void addToolbarByIconId(int iconId) {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setIcon(iconId);
    }
}
