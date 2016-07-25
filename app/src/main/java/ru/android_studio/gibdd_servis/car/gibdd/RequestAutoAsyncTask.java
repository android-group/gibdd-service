package ru.android_studio.gibdd_servis.car.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.car.activity.ResultAutoActivity;
import ru.android_studio.gibdd_servis.car.model.RequestAuto;
import ru.android_studio.gibdd_servis.car.model.ResponseAuto;

/**
 * Запрос проверки автомобиля с сайта ГИБДД
 */
public class RequestAutoAsyncTask extends AsyncTask<RequestAuto, Void, ResponseAuto> {

    private static final String TAG = "RequestDriverAsyncTask";
    private Context context;

    public RequestAutoAsyncTask(Context context) {
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
    protected ResponseAuto doInBackground(RequestAuto... params) {
        Log.d(TAG, "START doInBackground");
        if(params.length == 0) {
            throw new IllegalArgumentException("RequestDriver can't be null");
        }
        RequestAuto requestAuto = params[0];

        try {
            return InfoAutoService.clientRequest(requestAuto);
        } catch (IOException e) {
            Log.e(TAG, "Error to get captcha", e);
            Toast.makeText(context, "Can't load captcha image, please try again later", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseAuto result) {
        Log.d(TAG, "START onPostExecute");

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Intent intent = new Intent(context, ResultAutoActivity.class);
        intent.putExtra("result_text", result.getResultText());
        context.startActivity(intent);

        Log.d(TAG, "END onPostExecute");
    }
}
