package ru.android_studio.gibdd_servis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 * Абстрактный класс для взаимодействия с ГИБДД
 *
 * скорее всего у всех сервисов ГИБДД капча запрашивается одинаково
 * и запросы с ответами могут происходить подобным образом
 */
public abstract class AbstractGibddService implements RequestService {

    protected static final String USER_AGENT = "Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    private static final String PHPSESS_ID = "PHPSESSID";
    private String sessionId;

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
                System.out.println("key: " + key);
                for (String s : value) {
                    if (s.startsWith(PHPSESS_ID)) {
                        int start = s.indexOf("=") + 1;
                        int endIndex = s.indexOf(";");
                        resultPhpsessId = s.substring(start, endIndex);
                    }
                }
            }
        }
        System.out.println("resultPhpsessId: " + resultPhpsessId);
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
            e.printStackTrace();
        }
    }

    public Bitmap retrieveCaptcha() {
        try {
            return new RetrieveCaptchaTask().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImageView imageView;

    private class RetrieveCaptchaTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            try {
                String newSessionId = getNewSessionId();
                if (newSessionId == null) {
                    System.out.println("ERROR");
                    return null;
                }

                setSessionId(newSessionId);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                return BitmapFactory.decodeStream(getCaptcha());
            } catch (IOException e) {
                e.printStackTrace();
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