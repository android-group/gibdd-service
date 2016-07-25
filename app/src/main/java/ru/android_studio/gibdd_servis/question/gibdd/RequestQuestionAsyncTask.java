package ru.android_studio.gibdd_servis.question.gibdd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import ru.android_studio.gibdd_servis.driver.activity.ResultDriverActivity;
import ru.android_studio.gibdd_servis.question.model.RequestQuestion;
import ru.android_studio.gibdd_servis.question.model.ResponseQuestion;

/**
 * Запрос проверки автомобиля с сайта ГИБДД
 */
public class RequestQuestionAsyncTask extends AsyncTask<RequestQuestion, Void, ResponseQuestion> {

    private static final String TAG = "RequestDriverAsyncTask";
    private Context context;

    public RequestQuestionAsyncTask(Context context) {
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
    protected ResponseQuestion doInBackground(RequestQuestion... params) {
        Log.d(TAG, "START doInBackground");
        if(params.length == 0) {
            throw new IllegalArgumentException("RequestDriver can't be null");
        }
        RequestQuestion requestDriver = params[0];

        try {
            return InfoQuestionService.clientRequest(requestDriver);
        } catch (IOException e) {
            Log.e(TAG, "Error to get captcha", e);
            Toast.makeText(context, "Can't load captcha image, please try again later", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ResponseQuestion result) {
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
