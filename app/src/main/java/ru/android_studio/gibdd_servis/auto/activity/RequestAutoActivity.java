package ru.android_studio.gibdd_servis.auto.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.ActivityWithCaptcha;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.gibdd.CheckAutoType;
import ru.android_studio.gibdd_servis.auto.gibdd.RequestAutoAsyncTask;
import ru.android_studio.gibdd_servis.auto.model.RequestAuto;
import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.CheckType;
import ru.android_studio.gibdd_servis.gibdd.NewCaptchaAsyncTask;


/**
 * Created by olga on 22.05.2016.
 * <p/>
 * Проверка машины
 * Проверка наличия неуплаченных штрафов по данным транспортного средства
 *
 * @author olga
 * @author Ruslan Suleymanov
 * @author Yury Andreev
 * @version 0.1
 */
public class RequestAutoActivity extends ActivityWithCaptcha {

    private static final String TAG = "RequestAutoActivity";

    @BindView(R.id.vin_edit_text)
    EditText vinEditText;

    @BindView(R.id.vin_text_view)
    TextView vinTextView;

    @BindView(R.id.check_type_spinner)
    Spinner checkTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_auto);
        loadCaptcha();
    }

    @OnClick(R.id.check_button)
    void checkButton() {
        Log.d(TAG, "START checkButton");
        if (captchaEditText.length() == 0 && vinEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show();
            return;
        } else if (vinEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, заполните поле VIN номер.", Toast.LENGTH_SHORT).show();
            return;
        } else if (captchaEditText.length() == 0) {
            Toast.makeText(this, "Пожалуйста, введите символы с картинки.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestAuto requestAuto = new RequestAuto();
        String sessionId = getSessionId();
        if (sessionId != null) {
            requestAuto.setJsessionid(sessionId);
            requestAuto.setCaptchaWord(captchaEditText.getText().toString());
            requestAuto.setVin(vinEditText.getText().toString());
            requestAuto.setCheckAutoType(getCheckAutoType());

            final RequestAutoAsyncTask requestAutoAsyncTask = new RequestAutoAsyncTask(this);
            requestAutoAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestAuto);
            Log.d(TAG, "END checkButton");
        }
    }

    private CheckAutoType getCheckAutoType() {
        return CheckAutoType.values()[checkTypeSpinner.getSelectedItemPosition()];
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }

    @Override
    public BaseCaptchaAsyncTask getBaseCaptchaAsyncTask() {
        return new NewCaptchaAsyncTask(this, captchaImageView, CheckType.AUTO);
    }
}
