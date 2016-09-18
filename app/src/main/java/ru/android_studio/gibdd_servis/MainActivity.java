package ru.android_studio.gibdd_servis;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.auto.activity.RequestAutoActivity;
import ru.android_studio.gibdd_servis.driver.activity.RequestDriverActivity;
import ru.android_studio.gibdd_servis.fine.activity.RequestFineActivity;
import ru.android_studio.gibdd_servis.ocr.OCRService;
import ru.android_studio.gibdd_servis.ocr.imp.TesseractOCRServiceImp;
import ru.android_studio.gibdd_servis.question.activity.QuestionActivity;

public class MainActivity extends ActivityWithMenu {

    //public static TesseractOCRServiceImp ocrService;
    private static final String TAG = "MainActivity";

    @BindView(R.id.menu_car_btn)
    View menuCarBtn;

    @BindView(R.id.menu_driver_btn)
    View menuDriverBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener menuOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCurrentMenuId() == v.getId()) {
                    Log.i("ActivityWithMenu", "this is current menu");
                    return;
                }

                switch (v.getId()) {
                    //проверка штрафов
                    case R.id.menu_fine_btn:
                        Intent intentFineActivity = new Intent(getApplicationContext(), RequestFineActivity.class);
                        startActivity(intentFineActivity);
                        break;
                    //проверка водителя
                    case R.id.menu_driver_btn:
                        Intent intentDriverActivity = new Intent(getApplicationContext(), RequestDriverActivity.class);
                        startActivity(intentDriverActivity);
                        break;
                    //проверка автомобиля
                    case R.id.menu_car_btn:
                        Intent intentCarActivity = new Intent(getApplicationContext(), RequestAutoActivity.class);
                        startActivity(intentCarActivity);
                        break;
                    //прием обращений
                    case R.id.menu_question_btn:
                        Intent intentQuestionActivity = new Intent(getApplicationContext(), QuestionActivity.class);
                        startActivity(intentQuestionActivity);
                        break;
                }
            }
        };

        ButterKnife.bind(this);

        menuCarBtn.setOnClickListener(menuOnClickListener);
        menuDriverBtn.setOnClickListener(menuOnClickListener);

        toolbar.setLogo(R.mipmap.ic_main);
        setSupportActionBar(toolbar);

        setMenuConfig();

        /*if (ocrService == null) {
            PrepareOCRAsyncTask ocrAsyncTask = new PrepareOCRAsyncTask();
            try {
                ocrAsyncTask.execute(this);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }*/
    }

    /*@Override
    protected void onDestroy() {
        if (ocrService != null) {
            try {
                ocrService.close();
            } catch (Throwable e) {
                Log.e(TAG, "Error by Tesseract OCR service closing", e);
            }
        }
        super.onDestroy();
    }*/


    @Override
    protected int getCurrentMenuId() {
        return 0;
    }

    /*private class PrepareOCRAsyncTask extends AsyncTask<Context, Void, OCRService> {

        @Override
        protected OCRService doInBackground(Context... params) {
            ocrService = TesseractOCRServiceImp.getInstance(params[0]);
            ocrService.prepare();
            return ocrService;
        }

    }*/

}