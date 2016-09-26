package ru.android_studio.gibdd_servis.auto.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.auto.activity.ResultAutoDtpActivity;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoHistoryActivity;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoRestrictActivity;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoWantedActivity;
import ru.android_studio.gibdd_servis.auto.model.RequestAuto;
import ru.android_studio.gibdd_servis.auto.model.ResponseAuto;
import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoHistory;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Запрос проверки автомобиля с сайта ГИБДД
 */
public class RequestAutoAsyncTask extends AsyncTask<RequestAuto, Void, ResponseAuto> {

    private static final String TAG = "RequestDriverAsyncTask";
    private Context context;
    private CheckAutoType checkAutoType;

    /**
     * Окно отображается при открытом асинх таске
     */
    private ProgressDialog progressDialog;

    public RequestAutoAsyncTask(Context context, CheckAutoType checkAutoType) {
        this.context = context;
        this.checkAutoType = checkAutoType;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "START onPreExecute");
        progressDialog = new ProgressDialog(context);
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
            Class<?> aClass = null;
            switch (checkAutoType) {
                case HISTORY:
                    aClass = ResultAutoHistoryActivity.class;
                    break;
                case RESTRICT:
                    aClass = ResultAutoRestrictActivity.class;
                    break;
                case WANTED:
                    aClass = ResultAutoWantedActivity.class;
                    break;
                case DTP:
                    aClass = ResultAutoDtpActivity.class;
                    break;
                default:
                    // nothing
                    break;
            }

            Intent intent = new Intent(context, aClass);
            intent.putExtra("result_text", resultText);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, responseStatus.getText(), LENGTH_LONG).show();
        }
        Log.d(TAG, "END onPostExecute");
    }
}
