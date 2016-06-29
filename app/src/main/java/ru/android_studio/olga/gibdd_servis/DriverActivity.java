package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.EditText;

/**
 * Created by Yury Andreev on 20.05.2016.
 * @author Yury Andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class DriverActivity extends ActivityWithMenuAndOCR {

    private static final String TAG = "DriverActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        setMenuConfig();
        addToolbarByIconId(R.mipmap.driver_logo);
    }

    @Override
    int getCurrentMenuId() {
        return R.id.menu_driver_btn;
    }


}
