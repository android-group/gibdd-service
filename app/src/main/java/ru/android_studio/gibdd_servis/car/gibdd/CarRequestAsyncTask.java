package ru.android_studio.gibdd_servis.car.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.car.activity.CarResultActivity;
import ru.android_studio.gibdd_servis.car.model.CarRequest;
import ru.android_studio.gibdd_servis.car.model.CarResponse;
import ru.android_studio.gibdd_servis.fine.activity.FineActivity;

/**
 * Запрос проверки автомобиля с сайта ГИБДД
 */
public class CarRequestAsyncTask extends AsyncTask<CarRequest, Void, CarResponse> {

    private static final String TAG = "CarRequestAsyncTask";
    private Context context;

    public CarRequestAsyncTask(Context context) {
        this.context = context;
    }

    /**
     * Окно отображается при открытом асинх таске
     */
    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "START onPreExecute");
        progressDialog = new ProgressDialog(context);
        // progressDialog.setMessage("Processing");
        progressDialog.show();
        Log.d(TAG, "END onPreExecute");
    }

    @Override
    protected CarResponse doInBackground(CarRequest... params) {
        Log.d(TAG, "START doInBackground");
        if(params.length == 0) {
            throw new IllegalArgumentException("CarRequest can't be null");
        }
        CarRequest carRequest = params[0];

        try {
            return CarInfoService.clientRequest(carRequest);
        } catch (IOException e) {
            Log.e(TAG, "Error to get captcha", e);
            Toast.makeText(context, "Can't load captcha image, please try again later", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    protected void onPostExecute(CarResponse result) {
        Log.d(TAG, "START onPostExecute");

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Intent intentCarResultActivity = new Intent(context, CarResultActivity.class);
        intentCarResultActivity.putExtra("result_text", result.getResultText());

        context.startActivity(intentCarResultActivity);

        Log.d(TAG, "END onPostExecute");
    }
}
