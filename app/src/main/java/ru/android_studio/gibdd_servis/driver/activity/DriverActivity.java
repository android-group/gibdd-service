package ru.android_studio.gibdd_servis.driver.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.camera.Camera;

/**
 * Created on 20.05.2016.
 *
 * Проверка водителя
 *
 * @author Yury Andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class DriverActivity extends ActivityWithMenuAndOCR {

    private static final String TAG = "DriverActivity";

    int year;
    int month;
    int day;

    @BindView(R.id.result_camera)
    ImageView resultCamera;

    @BindView(R.id.DateOfIssueEditText)
    EditText dateOfIssueEditText;

    @BindView(R.id.camera)
    Button camera;

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int years, int monthOfYear,
                              int dayOfMonth) {
            year = years;
            month = monthOfYear;
            day = dayOfMonth;
            dateOfIssueEditText.setText("Today is " + day + "/" + month + "/" + year);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.driver_logo);
        setMenuConfig();

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

    }

    @OnClick(R.id.camera)
    public void openCamera() {
        Log.d(TAG, "openCamera");
        Camera.open(this);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        Bitmap photo = Camera.showResult(requestCode, resultCode, this);
         if(photo != null) {
            resultCamera.setImageBitmap(photo);
        }
    }

    @OnClick(R.id.calendar)
    public void openCalendar() {
        Log.d(TAG, "openCalendar");
        DatePickerDialog dialog = new DatePickerDialog(this, listener, year, month, day);
        dialog.show();
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_driver_btn;
    }
}
