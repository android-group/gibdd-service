package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;

/**
 * Created by y.andreev on 03.06.2016.
 * @author y.andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class FineActivity extends ActivityWithMenuAndOCR {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        setMenuConfig();
    }

    @Override
    int getCurrentMenuId() {
        return R.id.menu_fine_btn;
    }
}
