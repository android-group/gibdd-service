package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

/**
 * Created by y.andreev on 03.06.2016.
 */
public class QuestionActivity  extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setMenuConfig();
        addToolbarByIconId(R.mipmap.question_logo);
    }

    @Override
    int getCurrentMenuId() {
        return R.id.menu_question_btn;
    }
}
