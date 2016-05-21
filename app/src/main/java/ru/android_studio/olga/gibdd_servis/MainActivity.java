package ru.android_studio.olga.gibdd_servis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.main_logo);

        findViewById(R.id.button_driver).setOnClickListener(this);
        findViewById(R.id.button_car).setOnClickListener(this);
        findViewById(R.id.button_fine).setOnClickListener(this);
        findViewById(R.id.button_message).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_fine:
                Log.i(TAG, "button_fine");
                break;
            case R.id.button_driver:
                Log.i(TAG, "button_driver");
                Intent intentDriverActivity = new Intent(this, DriverActivity.class);
                startActivity(intentDriverActivity);
                break;
            case R.id.button_car:
                Log.i(TAG, "button_car");
                Intent intentCarActivity = new Intent(this, CarActivity.class);
                startActivity(intentCarActivity);
                break;
            case R.id.button_message:
                Log.i(TAG, "button_message");
                break;
        }
    }
}