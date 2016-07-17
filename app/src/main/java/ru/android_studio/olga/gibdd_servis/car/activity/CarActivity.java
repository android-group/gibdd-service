package ru.android_studio.olga.gibdd_servis.car.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android_studio.olga.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.olga.gibdd_servis.R;
import ru.android_studio.olga.gibdd_servis.camera.Camera;
import ru.android_studio.olga.gibdd_servis.car.service.CarAbstractGibddService;
import ru.android_studio.olga.gibdd_servis.service.OCRService;


/**
 * Created by olga on 22.05.2016.
 *
 * Проверка машины
 *
 * @author olga
 * @author Ruslan Suleymanov
 * @author Yury Andreev
 * @version 0.1
 */
public class CarActivity extends ActivityWithMenuAndOCR {

    //private CarAbstractGibddService gibddService = new CarAbstractGibddService();

    @BindView(R.id.captcha_image_view)
    ImageView captchaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.auto_logo);
        setMenuConfig();

        //loadCaptcha();
    }

    /**
     * Загрузить картинку капчи
     */
    /*@OnClick(R.id.captcha_image_view)
    void loadCaptcha() {
        gibddService.retrieveCaptcha(captchaImageView);
    }*/

    /**
     * Загрузить картинку капчи
     */
    @OnClick(R.id.check_button)
    void checkButton() {
        // @TODO
        // check fields and send message
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
