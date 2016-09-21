package ru.android_studio.gibdd_servis.common;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import ru.android_studio.gibdd_servis.R;

/**
 * Created by yuryandreev on 21/09/16.
 */
public class NoDataFoundActivity  extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.no_data_found);
    }
}
