package ru.android_studio.gibdd_servis.fine.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.driver.activity.ResultDriverActivity;
import ru.android_studio.gibdd_servis.fine.activity.ResultFineActivity;
import ru.android_studio.gibdd_servis.fine.model.RequestFine;
import ru.android_studio.gibdd_servis.fine.model.ResponseFine;

/**
 * Запрос проверки штрафов с сайта ГИБДД
 */
public class RequestFineAsyncTask extends AsyncTask<RequestFine, Void, ResponseFine> {

    private static final String TAG = "RequestFineAsyncTask";
    private Context context;

    public RequestFineAsyncTask(Context context) {
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
    protected ResponseFine doInBackground(RequestFine... params) {
        Log.d(TAG, "START doInBackground");
        if(params.length == 0) {
            throw new IllegalArgumentException("RequestFine can't be null");
        }
        RequestFine requestFine = params[0];

        try {
            return InfoFineService.clientRequest(requestFine);
        } catch (IOException e) {
            Log.e(TAG, "Error to get captcha", e);
            Toast.makeText(context, "Can't load captcha image, please try again later", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseFine result) {
        Log.d(TAG, "START onPostExecute");

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        Intent intent = new Intent(context, ResultFineActivity.class);
        intent.putExtra("result_text", result.getResultText());
        context.startActivity(intent);

        Log.d(TAG, "END onPostExecute");
    }
}
