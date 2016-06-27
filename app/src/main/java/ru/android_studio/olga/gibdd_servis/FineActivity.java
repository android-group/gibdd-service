package ru.android_studio.olga.gibdd_servis;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ru.android_studio.olga.gibdd_servis.service.OCRService;

/**
 * Created by y.andreev on 03.06.2016.
 * @author y.andreev
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class FineActivity extends ActivityWithMenuAndOCR {

    private Button checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        setMenuConfig();

        checkButton = (Button) findViewById(R.id.CheckButton);
        if (checkButton != null) {
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
    }

    @Override
    int getCurrentMenuId() {
        return R.id.menu_fine_btn;
    }
}
