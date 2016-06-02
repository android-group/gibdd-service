package ru.android_studio.olga.gibdd_servis;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by y.andreev on 03.06.2016.
 */
public abstract class ActivityWithMenu extends AppCompatActivity implements View.OnClickListener {

    @IdRes
    abstract int getCurrentMenuId();

    public void setMenuConfig() {
        findViewById(R.id.menu_car).setOnClickListener(this);
        findViewById(R.id.menu_driver).setOnClickListener(this);
        findViewById(R.id.menu_fine).setOnClickListener(this);
        findViewById(R.id.menu_question).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(getCurrentMenuId() == v.getId()) {
            Log.i("ActivityWithMenu", "this is current menu");
            return;
        }

        switch (v.getId()) {
            case R.id.menu_driver:
                Intent intentDriverActivity = new Intent(this, DriverActivity.class);
                startActivity(intentDriverActivity);
                break;
            case R.id.menu_question:
                Intent intentQuestionActivity = new Intent(this, QuestionActivity.class);
                startActivity(intentQuestionActivity);
                break;
            case R.id.menu_fine:
                Intent intentFineActivity = new Intent(this, FineActivity.class);
                startActivity(intentFineActivity);
                break;
            case R.id.menu_car:
                Intent intentCarActivity = new Intent(this, CarActivity.class);
                startActivity(intentCarActivity);
                break;
        }
    }
}
