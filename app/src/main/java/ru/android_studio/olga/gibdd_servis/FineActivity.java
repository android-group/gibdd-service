package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by y.andreev on 03.06.2016.
 * @author y.andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class FineActivity extends ActivityWithMenuAndOCR implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        setMenuConfig();
        /*Добавить listener на кнопку
         *Добавить загрузку капчи
        * */
    }


    @Override
    int getCurrentMenuId() {
        return R.id.menu_fine_btn;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fine_CheckButton_B) {

            EditText GosNumber_ET = (EditText)v.findViewById(R.id.fine_Number_ET);
            String GosNumber = GosNumber_ET.getText().toString();
            EditText Serial_Number_ET = (EditText)v.findViewById(R.id.fine_SerialNumber_ET);
            String Serial_Number = Serial_Number_ET.getText().toString();
            EditText Captcha_ET = (EditText)v.findViewById(R.id.fine_Captcha_ET);
            String Captcha= Captcha_ET.getText().toString();

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
        if (!Number.equals("")) return 0; else return 1;
    }

    private int checkSerial(String Serial) {
        if (!Serial.equals("")) return 0; else return 1;
    }
    private int checkCaptcha(String Captcha) {
        if (!Captcha.equals("")) return 0; else return 1;
    }
}
