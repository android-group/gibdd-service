package ru.android_studio.gibdd_servis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import butterknife.BindView;
import ru.android_studio.gibdd_servis.auto.activity.RequestAutoActivity;
import ru.android_studio.gibdd_servis.driver.activity.RequestDriverActivity;
import ru.android_studio.gibdd_servis.fine.activity.RequestFineActivity;
import ru.android_studio.gibdd_servis.question.activity.QuestionActivity;

/**
 * Created by y.andreev on 03.06.2016.
 *
 * Активити с Нижним меню
 */
public abstract class ActivityWithMenu extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
//
//    public void hideKeyboard() {
//        menu.setVisibility(View.GONE);
//    }
//
//    public void showKeyboard() {
//        menu.setVisibility(View.VISIBLE);
//    }


    @IdRes
    protected abstract int getCurrentMenuId();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.menu)
//    View menu;

//    @BindView(R.id.menu_car_btn)
//    View menuCarBtn;
//
//    @BindView(R.id.menu_driver_btn)
//    View menuDriverBtn;

    /*@BindView(R.id.menu_fine_btn)
    View menuFindBtn;

    @BindView(R.id.menu_question_btn)
    View menuQuestionBtn;*/


    /**
     * Добавляем обработку кликов ко кнопкам меню
     */
    public void setMenuConfig() {
//        menuCarBtn.setOnClickListener(menuOnClickListener);
//        menuDriverBtn.setOnClickListener(menuOnClickListener);
//        menuFindBtn.setOnClickListener(menuOnClickListener);
//        menuQuestionBtn.setOnClickListener(menuOnClickListener);
    }

    /**
     * Листнер для обработки кликов по меню
     */


    /**
     * Добавить тулбар на страницу
     *
     * @param iconId - id картинки for example R.mipmap.auto_logo
     */
    protected void addToolbarByIconId(int iconId) {
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setLogo(iconId);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
    }
}
