package ru.android_studio.gibdd_servis.driver.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCRAndCaptcha;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.camera.Camera;
import ru.android_studio.gibdd_servis.driver.gibdd.RequestDriverAsyncTask;
import ru.android_studio.gibdd_servis.driver.model.RequestDriver;
import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.CheckType;
import ru.android_studio.gibdd_servis.gibdd.NewCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.ocr.OCRService;

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
public class RequestDriverActivity extends ActivityWithMenuAndOCRAndCaptcha {

    public static final String WITHOUT_NON_DIGIT_CHARACTERS = "[^\\d]";
    private static final String TAG = "RequestDriverActivity";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    int year;
    int month;
    int day;
    @BindView(R.id.result_camera)
    ImageView resultCamera;
    @BindView(R.id.date_of_issue_edit_text)
    EditText dateOfIssueEditText;
    @BindView(R.id.recognize_series_camera)
    Button camera;
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
    private RecognizeType recognizeType;

    private void setDate(String date) {
        dateOfIssueEditText.setText(date);
    }

    @OnClick(R.id.check_button)
    void checkButton() {
        Log.d(TAG, "START checkButton");

        RequestDriver requestDriver = new RequestDriver();
        requestDriver.setJsessionid(getSessionId());
        requestDriver.setCaptchaWord(captchaEditText.getText().toString());

        String number = numberEditText.getText().toString();
        String series = seriesEditText.getText().toString();

        requestDriver.setNum(series + number);

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
        setMenuConfig();
        loadCaptcha();

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        setDate(SIMPLE_DATE_FORMAT.format(c.getTime()));
    }

    @OnClick(R.id.recognize_series_camera)
    public void recognizeSeriesCamera() {
        Log.d(TAG, "recognizeSeriesCamera");
        recognizeType = RecognizeType.SERIES;
        Camera.open(this);
    }

    @OnClick(R.id.recognize_number_camera)
    public void recognizeNumberCamera() {
        Log.d(TAG, "recognizeNumberCamera");
        recognizeType = RecognizeType.NUMBER;
        Camera.open(this);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        Bitmap photo = Camera.showResult(requestCode, resultCode, this);
        if (photo != null) {
            resultCamera.setImageBitmap(photo);
        }

        String text = asyncExtractText(photo, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN);

        String recognizedText = text.replaceAll(WITHOUT_NON_DIGIT_CHARACTERS, "");
        if (recognizeType == RecognizeType.NUMBER) {
            numberEditText.setText(recognizedText);
            numberEditText.requestFocus();
        } else if (recognizeType == RecognizeType.SERIES) {
            seriesEditText.setText(recognizedText);
            seriesEditText.requestFocus();
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
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

    private enum RecognizeType {
        SERIES,
        NUMBER
    }
}
