package ru.android_studio.gibdd_servis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static ru.android_studio.gibdd_servis.gibdd.CommonRequest.PHPSESS_ID;
import static ru.android_studio.gibdd_servis.gibdd.CommonRequest.USER_AGENT;


/**
 * Абстрактный класс для взаимодействия с ГИБДД
 * <p/>
 * скорее всего у всех сервисов ГИБДД капча запрашивается одинаково
 * и запросы с ответами могут происходить подобным образом
 */
public abstract class AbstractGibddService implements RequestService {

    private static final String TAG = "AbstractGibddService";
    private String sessionId;
    private ImageView imageView;

    private String getSessionId() {
        return sessionId;
    }

    private void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private String getNewSessionId() throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(getRequestUrl()).openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", USER_AGENT);

        return getPhpsessIdByUrlConnection(urlConnection);
    }

    private String getPhpsessIdByUrlConnection(URLConnection urlConnection) {
        String resultPhpsessId = null;
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> next : headerFields.entrySet()) {
            String key = next.getKey();
            List<String> value = next.getValue();
            if (key != null && key.equals("Set-Cookie")) {
                Log.i(TAG, "key: " + key);
                for (String s : value) {
                    if (s.startsWith(PHPSESS_ID)) {
                        int start = s.indexOf("=") + 1;
                        int endIndex = s.indexOf(";");
                        resultPhpsessId = s.substring(start, endIndex);
                    }
                }
            }
        }
        Log.i(TAG, "resultPhpsessId: " + resultPhpsessId);
        return resultPhpsessId;
    }

    private InputStream getCaptcha() throws IOException {
        String imageUrl = "http://www.gibdd.ru/proxy/check/getCaptcha.php?PHPSESSID=" + getSessionId();
        URL url = new URL(imageUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", USER_AGENT);
        urlConnection.setRequestProperty("X-Compress", "0");
        urlConnection.setRequestProperty("Referer", getRequestUrl());
        urlConnection.setRequestProperty("Host", "www.gibdd.ru");
        urlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip,  4deflate, sdch");
        urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
        urlConnection.setRequestProperty("Connection", "keep-alive");

        String cookie = String.format("captchaSessionId=%s; _ym_uid=1462452903560071241; PHPSESSID=%s; _ym_isad=1; _ga=GA1.2.74263411.1462452903; BITRIX_SM_REGKOD=00; BITRIX_SM_IP_REGKOD=77; siteType=pda", getSessionId(), getSessionId());
        urlConnection.setRequestProperty("Cookie", cookie);

        return urlConnection.getInputStream();
    }

    public void retrieveCaptcha(ImageView imageView) {
        this.imageView = imageView;
        try {
            new RetrieveCaptchaTask().execute().get();
        } catch (Exception e) {
            Log.e(TAG, "Error retrieve captcha", e);
        }
    }

    private class RetrieveCaptchaTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            try {
                String newSessionId = getNewSessionId();
                if (newSessionId == null) {
                    Log.e(TAG, "Can't get new session Id");
                    return null;
                }

                setSessionId(newSessionId);
            } catch (IOException e) {
                Log.e(TAG, "Can't get new session Id", e);
            }

            try {
                return BitmapFactory.decodeStream(getCaptcha());
            } catch (IOException e) {
                Log.e(TAG, "Can't get captcha", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
            }
        }
    }

}