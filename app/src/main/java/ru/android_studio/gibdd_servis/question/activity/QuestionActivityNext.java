package ru.android_studio.gibdd_servis.question.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.R;

/**
 * Created by olga on 02.07.2016.
 *
 * Прием обращений следующая страница
 */
public class QuestionActivityNext extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_next);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_question);
    }

    protected int getCurrentMenuId() {
        return R.id.menu_question_btn;
    }
}
