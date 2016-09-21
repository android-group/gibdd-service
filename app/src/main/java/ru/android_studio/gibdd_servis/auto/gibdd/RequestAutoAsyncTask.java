package ru.android_studio.gibdd_servis.auto.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.auto.activity.ResultAutoActivity;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoHelper;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoObject;
import ru.android_studio.gibdd_servis.auto.model.RequestAuto;
import ru.android_studio.gibdd_servis.auto.model.ResponseAuto;
import ru.android_studio.gibdd_servis.common.CaptchaNumberIsNotValid;
import ru.android_studio.gibdd_servis.common.NoDataFoundActivity;

import static android.widget.Toast.LENGTH_LONG;

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


        String resultText = result.getResultText();

        Class<?> clazz;

        ResultAutoObject resultAutoObject = ResultAutoHelper.parseResult(resultText);
        if(resultAutoObject == null) {
            Toast.makeText(context, "Can't do something", LENGTH_LONG).show();
            return;
        }

        switch (resultAutoObject.getType()) {
            case SUCCESS:
                clazz = ResultAutoActivity.class;
                break;
            default:
                Toast.makeText(context, resultAutoObject.getType().getText(), LENGTH_LONG).show();
                return;
        }

        Intent intent = new Intent(context, clazz);
        intent.putExtra("result_text", resultText);
        context.startActivity(intent);

        Log.d(TAG, "END onPostExecute");
    }
}
