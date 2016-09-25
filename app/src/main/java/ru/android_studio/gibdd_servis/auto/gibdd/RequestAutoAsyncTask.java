package ru.android_studio.gibdd_servis.auto.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.auto.activity.ResultAutoActivity;
import ru.android_studio.gibdd_servis.auto.model.RequestAuto;
import ru.android_studio.gibdd_servis.auto.model.ResponseAuto;
import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.model.history.ResultAutoHistory;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoHistory;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Запрос проверки автомобиля с сайта ГИБДД
 */
public class RequestAutoAsyncTask extends AsyncTask<RequestAuto, Void, ResponseAuto> {

    private static final String TAG = "RequestDriverAsyncTask";
    private Context context;
    /**
     * Окно отображается при открытом асинх таске
     */
    private ProgressDialog progressDialog;

    public RequestAutoAsyncTask(Context context) {
        this.context = context;
    }

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
        if (params.length == 0) {
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

        String resultText = result.getResultText();

        ResponseStatus responseStatus = ParseResultAutoHistory.getResponseStatus(resultText);
        if (responseStatus == null) {
            Toast.makeText(context, "Can't do something", LENGTH_LONG).show();
            return;
        }

        if (responseStatus == ResponseStatus.SUCCESS) {
            Intent intent = new Intent(context, ResultAutoActivity.class);
            intent.putExtra("result_text", resultText);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, responseStatus.getText(), LENGTH_LONG).show();
        }
        Log.d(TAG, "END onPostExecute");
    }
}
