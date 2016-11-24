package ru.android_studio.gibdd_servis.auto.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.CaptchaActivity;
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
public class RequestAutoActivity extends CaptchaActivity {

    private static final String TAG = "RequestAutoActivity";

    @BindView(R.id.vin_edit_text)
    EditText vinEditText;

    @BindView(R.id.vin_text_view)
    TextView vinTextView;

    CheckAutoType checkAutoType;

    public static final String CHECK_AUTO_TYPE = "check_auto_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        checkAutoType = (CheckAutoType) extras.get(CHECK_AUTO_TYPE);

        setContentView(R.layout.activity_auto);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_auto);
        getSupportActionBar().setSubtitle(checkAutoType.getTitile());
        loadCaptcha();
        if(getSessionId() == null) {
            finishCauseInternetNotAvailable();
        }
    }

    @OnClick(R.id.check_button)
    void checkButton() {
        Log.d(TAG, "START checkButton");

        boolean isCaptchaEmpty = captchaEditText.length() == 0;
        boolean isVinEmpty = vinEditText.length() == 0;
        if (isCaptchaEmpty && isVinEmpty) {
            Toast.makeText(this, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT).show();
            return;
        } else if (isVinEmpty) {
            Toast.makeText(this, "Пожалуйста, заполните поле VIN номер.", Toast.LENGTH_SHORT).show();
            return;
        } else if (isCaptchaEmpty) {
            Toast.makeText(this, "Пожалуйста, введите символы с картинки.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestAuto requestAuto = new RequestAuto();
        String sessionId = getSessionId();
        if (sessionId != null) {
            requestAuto.setJsessionid(sessionId);
            requestAuto.setCaptchaWord(getCaptchaWord());
            requestAuto.setVin(getVinText());
            requestAuto.setCheckAutoType(checkAutoType);

            final RequestAutoAsyncTask requestAutoAsyncTask = new RequestAutoAsyncTask(this, requestAuto.getCheckAutoType());
            requestAutoAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestAuto);
        }

        loadCaptcha();
        Log.d(TAG, "END checkButton");
    }

    public String getVinText() {
        return vinEditText.getText().toString();
    }

    @Override
    public BaseCaptchaAsyncTask createCaptchaAsyncTask() {
        return new NewCaptchaAsyncTask(this, captchaImageView, CheckType.AUTO);
    }
}
