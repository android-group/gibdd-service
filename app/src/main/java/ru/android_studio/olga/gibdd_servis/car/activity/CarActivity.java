package ru.android_studio.olga.gibdd_servis.car.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import ru.android_studio.olga.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.olga.gibdd_servis.R;
import ru.android_studio.olga.gibdd_servis.car.service.CarAbstractGibddService;
import ru.android_studio.olga.gibdd_servis.service.OCRService;


/**
 * Created by olga on 22.05.2016.
 *
 * @author olga
 * @author Ruslan Suleymanov
 * @author Yury Andreev
 * @version 0.1
 */
public class CarActivity extends ActivityWithMenuAndOCR implements View.OnClickListener {

    private CarAbstractGibddService gibddService = new CarAbstractGibddService();

    private ImageView captchaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.auto_logo);
        setMenuConfig();

        captchaImageView = (ImageView) findViewById(R.id.captcha_image_view);
        captchaImageView.setOnClickListener(this);

        loadCaptcha();

        Button checkButton = (Button) findViewById(R.id.check_button);
        checkButton.setOnClickListener(this);
    }

    /**
     * Загрузить картинку капчи
     */
    private void loadCaptcha() {
        gibddService.retrieveCaptcha(captchaImageView);
    }

    /**
     * Распознать капчу через тесеракт
     */
    private void recognizeCaptcha() {
        Bitmap captchaBitmap = gibddService.retrieveCaptcha();
        String result = asyncExtractText(captchaBitmap, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN);
        Toast.makeText(getApplicationContext(), "result: " + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_button :

                break;
            case R.id.captcha_image_view :
                loadCaptcha();
                break;
        }
    }
}
