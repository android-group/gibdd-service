package ru.android_studio.gibdd_servis.driver.activity;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.ActivityWithCaptcha;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.driver.gibdd.RequestDriverAsyncTask;
import ru.android_studio.gibdd_servis.driver.model.RequestDriver;
import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.CheckType;
import ru.android_studio.gibdd_servis.gibdd.NewCaptchaAsyncTask;

/**
 * Created on 20.05.2016.
 * <p/>
 * Проверка водителя
 * Проверка водительского удостоверения
 *
 * @author Yury Andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class RequestDriverActivity extends ActivityWithCaptcha {

    private static final String TAG = "RequestDriverActivity";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    int year;
    int month;
    int day;

    @BindView(R.id.date_of_issue_edit_text)
    EditText dateOfIssueEditText;

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int years, int monthOfYear,
                              int dayOfMonth) {
            year = years;
            month = monthOfYear;
            day = dayOfMonth;
            setDate(String.format("%d.%d.%d", day, month, year));
        }
    };

    @BindView(R.id.series_license_edit_text)
    EditText seriesEditText;

    @BindView(R.id.number_license_edit_text)
    EditText numberEditText;

    private void setDate(String date) {
        dateOfIssueEditText.setHint(date);
    }

    @OnClick(R.id.check_button)
    void checkButton() {
        Log.d(TAG, "START checkButton");
        if (captchaEditText.length() == 0 &&
                seriesEditText.length() == 0 &&
                numberEditText.length() == 0 &&
                dateOfIssueEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show();
            return;
        } else if (seriesEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, заполните серию.", Toast.LENGTH_SHORT).show();
            return;
        } else if (numberEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, заполните номер", Toast.LENGTH_SHORT).show();
            return;
        } else if (dateOfIssueEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, заполните дату выдачи", Toast.LENGTH_SHORT).show();
            return;
        } else if (captchaEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, введите символы с картинки.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestDriver requestDriver = new RequestDriver();
        requestDriver.setJsessionid(getSessionId());
        requestDriver.setCaptchaWord(captchaEditText.getText().toString());

        String number = numberEditText.getText().toString();
        String series = seriesEditText.getText().toString();

        requestDriver.setNum(series + number);

        requestDriver.setDate(dateOfIssueEditText.getText().toString());
        final RequestDriverAsyncTask requestDriverAsyncTask = new RequestDriverAsyncTask(this);
        requestDriverAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestDriver);
        Log.d(TAG, "END checkButton");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_driver);
        loadCaptcha();

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        setDate(SIMPLE_DATE_FORMAT.format(c.getTime()));
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

    @Override
    public BaseCaptchaAsyncTask getBaseCaptchaAsyncTask() {
        return new NewCaptchaAsyncTask(this, captchaImageView, CheckType.DRIVER);
    }
}
