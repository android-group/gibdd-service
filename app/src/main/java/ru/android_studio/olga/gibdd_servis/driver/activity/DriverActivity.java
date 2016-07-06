package ru.android_studio.olga.gibdd_servis.driver.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.*;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

import butterknife.ButterKnife;
import ru.android_studio.olga.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.olga.gibdd_servis.R;

/**
 * Created by Yury Andreev on 20.05.2016.
 * @author Yury Andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class DriverActivity extends ActivityWithMenuAndOCR implements View.OnClickListener{

    private static final String TAG = "DriverActivity";
    int year;
    int month;
    int day;
    EditText et;



    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int years, int monthOfYear,
                              int dayOfMonth) {
            year = years;
            month = monthOfYear;
            day = dayOfMonth;
            et.setText("Today is " + day + "/" + month + "/" + year);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.driver_logo);
        setMenuConfig();

        findViewById(R.id.imageButton1).setOnClickListener(this);
        et = (EditText)findViewById(R.id.DateOfIssueEditText);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

    }



    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.imageButton1) {
           DatePickerDialog dialog = new DatePickerDialog(this, listener, year, month, day);
           dialog.show();
      }
    }


    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_driver_btn;
    }


}
