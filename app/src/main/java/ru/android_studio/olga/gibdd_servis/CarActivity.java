package ru.android_studio.olga.gibdd_servis;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ru.android_studio.olga.gibdd_servis.service.OCRService;

/**
 * Created by olga on 22.05.2016.
 * @author olga
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class CarActivity extends ActivityWithMenuAndOCR {

    private GibddService gibddService = new GibddService();
    private Bitmap captchaBitmap;
    private ImageView captchaImageView;

    private Button checkButton;

    private static final String TAG = "CarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_my);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setIcon(R.mipmap.auto_logo);

        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable = getResources().getDrawable(R.drawable.lens);

        final int width = Math.round(60 * density);
        final int height = Math.round(60 * density);

        drawable.setBounds(0, 0, width, height);

        TextView vinTextView = (TextView) findViewById(R.id.VINTextView);
        vinTextView.setCompoundDrawables(null, null, drawable, null);

        setMenuConfig();

        captchaImageView = (ImageView) findViewById(R.id.CuptureImageView);
        captchaImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                captchaBitmap = gibddService.getCaptchaBitmap();
                captchaImageView.setImageBitmap(captchaBitmap);
                asyncExtractText(captchaBitmap, OCRService.LANGUAGE.LANGUAGE_CODE_ENGLISH);
            }
        });

        checkButton = (Button) findViewById(R.id.CheckButton);
        if (checkButton != null) {
            checkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap captcha = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.c13249);
                    String text = asyncExtractText(captcha, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN);
                    Log.i(TAG, String.format("captcha text = %s", text));
                }
            });
        }
    }

    @Override
    int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
