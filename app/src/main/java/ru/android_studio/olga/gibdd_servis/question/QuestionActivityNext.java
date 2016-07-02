package ru.android_studio.olga.gibdd_servis.question;

import android.os.Bundle;

import ru.android_studio.olga.gibdd_servis.ActivityWithMenu;
import ru.android_studio.olga.gibdd_servis.R;

/**
 * Created by olga on 02.07.2016.
 */
public class QuestionActivityNext extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_next);

        addToolbarByIconId(R.mipmap.question_logo);
        setMenuConfig();
    }

    protected int getCurrentMenuId() {
        return R.id.menu_question_btn;
    }
}
