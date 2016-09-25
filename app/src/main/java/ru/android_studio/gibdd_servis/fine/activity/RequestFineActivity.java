package ru.android_studio.gibdd_servis.fine.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.gibdd_servis.ActivityWithCaptcha;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.fine.gibdd.RequestFineAsyncTask;
import ru.android_studio.gibdd_servis.fine.model.RequestFine;
import ru.android_studio.gibdd_servis.gibdd.BaseCaptchaAsyncTask;
import ru.android_studio.gibdd_servis.gibdd.OldCaptchaAsyncTask;

/**
 * Created by y.andreev on 03.06.2016.
 * <p/>
 * Проверка штрафов
 * Проверка наличия неуплаченных штрафов по данным транспортного средства
 *
 * @author y.andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class RequestFineActivity extends ActivityWithCaptcha {

    private static final String TAG = "RequestFineActivity";

    @BindView(R.id.gos_number_edit_text)
    EditText gosNumberEditText;

    @BindView(R.id.gos_region_number_edit_text)
    EditText gosRegionNumberEditText;

    @BindView(R.id.serial_number_edit_text)
    EditText serialNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_fine);

        loadCaptcha();
    }

    @Override
    public BaseCaptchaAsyncTask getBaseCaptchaAsyncTask() {
        return new OldCaptchaAsyncTask(this, captchaImageView);
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_fine_btn;
    }

    @OnClick(R.id.check_button)
    void checkButton() {
        Log.d(TAG, "START checkButton");
        String gosNumber = gosNumberEditText.getText().toString();
        String gosRegionNumber = gosRegionNumberEditText.getText().toString();
        String serialNumber = serialNumberEditText.getText().toString();
        String captcha = captchaEditText.getText().toString();

        int checkGosNumber = checkNumber(gosNumber);
        int checkSerial_Number = checkSerial(serialNumber);
        int checkCaptchaS = checkCaptcha(captcha);

        if (checkCaptchaS + checkSerial_Number + checkGosNumber == 0) {
                /*
                * Запрос по введенным данным в базе ГИБДД
                * */

            RequestFine requestFine = new RequestFine();
            requestFine.setPhpSessId(getSessionId());
            requestFine.setCaptchaWord(captcha);
            requestFine.setRegnum(gosNumber);
            requestFine.setRegreg(gosRegionNumber);
            requestFine.setStsnum(serialNumber);

            final RequestFineAsyncTask requestFineAsyncTask = new RequestFineAsyncTask(this);
            requestFineAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestFine);
        } else {
            if (checkGosNumber == 1) {/*Сообщение о неправильном государственном знаке автомобиля*/}
            if (checkSerial_Number == 1) {/*Сообщение о неправильных серии и номере водительских  */}
            if (checkCaptchaS == 1) {/*Сообщение о неправильно введенной капче*/}
        }
        Log.d(TAG, "END checkButton");
    }

    private int checkNumber(String Number) {
        if (!Number.equals("")) return 0;
        else return 1;
    }

    private int checkSerial(String Serial) {
        if (!Serial.equals("")) return 0;
        else return 1;
    }

    private int checkCaptcha(String Captcha) {
        if (!Captcha.equals("")) return 0;
        else return 1;
    }
}
