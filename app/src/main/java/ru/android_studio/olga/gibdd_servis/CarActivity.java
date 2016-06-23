package ru.android_studio.olga.gibdd_servis;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by olga on 22.05.2016.
 * @author olga
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class CarActivity extends ActivityWithMenuAndOCR {

    private static final String TAG = "CarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setIcon(R.mipmap.auto_logo);

        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable = getResources().getDrawable(R.drawable.lens);

        final int width = Math.round(60 * density);
        final int height = Math.round(60 * density);

        drawable.setBounds(0, 0, width, height);

        TextView vinTextView = (TextView) findViewById(R.id.VINTextView);
        vinTextView.setCompoundDrawables(null, null, drawable, null);

        setMenuConfig();
    }

    @Override
    int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
