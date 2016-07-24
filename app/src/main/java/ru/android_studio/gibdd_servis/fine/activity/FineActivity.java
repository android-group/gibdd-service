package ru.android_studio.gibdd_servis.fine.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.service.OCRService;

/**
 * Created by y.andreev on 03.06.2016.
 *
 * Проверка штрафов
 *
 * @author y.andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class FineActivity extends ActivityWithMenuAndOCR implements View.OnClickListener {

    @BindView(R.id.check_button)
    Button checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.fine_logo);
        setMenuConfig();

        /*Добавить listener на кнопку
         *Добавить загрузку капчи
        * */

        checkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i(TAG, "call onClick()...");
                Log.i(TAG, "getting captcha...");
                Bitmap captcha = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.c13249);

                Log.i(TAG, "extract text from captcha...");
                String text = asyncExtractText(captcha, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN);

                Log.i(TAG, String.format("captcha text = %s", text));
                Toast toast = Toast.makeText(FineActivity.this, text, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_fine_btn;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_button) {
            EditText GosNumber_ET = (EditText) v.findViewById(R.id.fine_Number_ET);
            String GosNumber = GosNumber_ET.getText().toString();
            EditText Serial_Number_ET = (EditText) v.findViewById(R.id.fine_SerialNumber_ET);
            String Serial_Number = Serial_Number_ET.getText().toString();
            EditText Captcha_ET = (EditText) v.findViewById(R.id.fine_Captcha_ET);
            String Captcha = Captcha_ET.getText().toString();

            int checkGosNumber = checkNumber(GosNumber);
            int checkSerial_Number = checkSerial(Serial_Number);
            int checkCaptchaS = checkCaptcha(Captcha);

            if (checkCaptchaS + checkSerial_Number + checkGosNumber == 0) {
                /*
                * Запрос по введенным данным в базе ГИБДД
                * */
            } else {
                if (checkGosNumber == 1) {/*Сообщение о неправильном государственном знаке автомобиля*/}
                if (checkSerial_Number == 1) {/*Сообщение о неправильных серии и номере водительских  */}
                if (checkCaptchaS == 1) {/*Сообщение о неправильно введенной капче*/}
            }
        }
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
