package ru.android_studio.gibdd_servis.car.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.car.gibdd.CaptchaAsyncTask;
import ru.android_studio.gibdd_servis.car.gibdd.CaptchaResult;
import ru.android_studio.gibdd_servis.car.gibdd.CarRequestAsyncTask;
import ru.android_studio.gibdd_servis.car.gibdd.CheckType;
import ru.android_studio.gibdd_servis.car.model.CarRequest;


/**
 * Created by olga on 22.05.2016.
 * <p>
 * Проверка машины
 *
 * @author olga
 * @author Ruslan Suleymanov
 * @author Yury Andreev
 * @version 0.1
 */
public class CarActivity extends ActivityWithMenuAndOCR {

    private static final String TAG = "CarActivity";

    /**
     * Картинка с капчей
     */
    @BindView(R.id.captcha_image_view)
    ImageView captchaImageView;
    @BindView(R.id.vin_edit_text)
    EditText vinEditText;
    @BindView(R.id.captcha_edit_text)
    EditText captchaEditText;
    @BindView(R.id.vin_check_box)
    CheckBox vinCheckBox;
    @BindView(R.id.vin_text_view)
    TextView vinTextView;

    @BindView(R.id.check_type_spinner)
    Spinner checkTypeSpinner;

    /**
     * Ответ на запрос капчи от сайта ГИБДД
     */
    //private CaptchaResult callbackCaptchaResult = new CaptchaResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.auto_logo);
        setMenuConfig();
        loadCaptcha();
    }

    private CaptchaAsyncTask captchaAsyncTask;
    /**
     * Загрузить картинку капчи
     */
    @OnClick(R.id.captcha_image_view)
    void loadCaptcha() {
        Log.d(TAG, "START loadCaptcha");

        // if use execute when method in AsyncTask 'doInBackground' will not call
        captchaAsyncTask = new CaptchaAsyncTask(this, captchaImageView);
        captchaAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Log.d(TAG, "END loadCaptcha");
    }

    /**
     * Загрузить картинку капчи
     */
    @OnClick(R.id.check_button)
    void checkButton() {
        Log.d(TAG, "START checkButton");

        String sessionId = null;
        try {
            sessionId = captchaAsyncTask.get().getSessionId();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        CarRequest carRequest = new CarRequest();
        carRequest.setJsessionid(sessionId);

        carRequest.setCaptchaWord(captchaEditText.getText().toString());
        carRequest.setVin(vinEditText.getText().toString());

        CheckType checkType = CheckType.values()[checkTypeSpinner.getSelectedItemPosition()];
        carRequest.setCheckType(checkType);

        final CarRequestAsyncTask carRequestAsyncTask = new CarRequestAsyncTask(this);
        carRequestAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, carRequest);
        Log.d(TAG, "END checkButton");
    }

    @OnClick(R.id.vin_check_box)
    void changeValue() {
        if (vinCheckBox.isChecked()) {
            vinTextView.setText("Номер шасси или кузова");
        } else {
            vinTextView.setText("Идентификационный номер (VIN)");
        }
    }

    /**
     * Распознать капчу через тесеракт
     */
    /*private void recognizeCaptcha() {
        Bitmap captchaBitmap = gibddService.retrieveCaptcha();
        String result = asyncExtractText(captchaBitmap, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN);
        Toast.makeText(getApplicationContext(), "result: " + result, Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
