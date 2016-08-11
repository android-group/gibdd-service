package ru.android_studio.gibdd_servis.driver.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.driver.activity.ResultDriverActivity;
import ru.android_studio.gibdd_servis.driver.model.RequestDriver;
import ru.android_studio.gibdd_servis.driver.model.ResponseDriver;

/**
 * Запрос проверки автомобиля с сайта ГИБДД
 */
public class RequestDriverAsyncTask extends AsyncTask<RequestDriver, Void, ResponseDriver> {

    private static final String TAG = "RequestDriverAsyncTask";
    private Context context;

    public RequestDriverAsyncTask(Context context) {
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
        progressDialog.setMessage("Processing");
        progressDialog.show();
        Log.d(TAG, "END onPreExecute");
    }

    @Override
    protected ResponseDriver doInBackground(RequestDriver... params) {
        Log.d(TAG, "START doInBackground");
        if(params.length == 0) {
            throw new IllegalArgumentException("RequestDriver can't be null");
        }
        RequestDriver requestDriver = params[0];

        try {
            return InfoDriverService.clientRequest(requestDriver);
        } catch (IOException e) {
            Log.e(TAG, "Error client request", e);
            Toast.makeText(context, "Can't to make client request, please try again later", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseDriver result) {
        Log.d(TAG, "START onPostExecute");

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Intent intent = new Intent(context, ResultDriverActivity.class);
        intent.putExtra("result_text", result.getResultText());
        context.startActivity(intent);

        Log.d(TAG, "END onPostExecute");
    }
}
