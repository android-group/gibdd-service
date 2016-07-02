package ru.android_studio.olga.gibdd_servis.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import ru.android_studio.olga.gibdd_servis.ActivityWithMenu;
import ru.android_studio.olga.gibdd_servis.R;

/**
 * Created by y.andreev on 03.06.2016.
 */
public class QuestionActivity  extends ActivityWithMenu implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        addToolbarByIconId(R.mipmap.question_logo);
        setMenuConfig();

        findViewById(R.id.btn_next).setOnClickListener(this);
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_question_btn;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next :
                Intent intent = new Intent(this, QuestionActivityNext.class);
                startActivity(intent);
                break;
        }
    }
}
