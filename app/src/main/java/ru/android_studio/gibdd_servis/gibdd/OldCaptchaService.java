package ru.android_studio.gibdd_servis.gibdd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class OldCaptchaService {

    private static String getPhpSessId() throws IOException {
        URL url = new URL("http://www.gibdd.ru/proxy/check/getSession.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        return getPhpsessIdByUrlConnection(urlConnection);
    }

    private static Bitmap getCaptchaImage(final String phpsessId) throws IOException {
        Log.i(TAG, "phpsessId: " + phpsessId);

        String imageUrl = "http://www.gibdd.ru/proxy/check/getCaptcha.php?PHPSESSID=" + phpsessId;
        URL url = new URL(imageUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        urlConnection.setRequestProperty("X-Compress", "0");
        urlConnection.setRequestProperty("Referer", "http://www.gibdd.ru/check/fines/");
        urlConnection.setRequestProperty("Host", "www.gibdd.ru");
        urlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
        urlConnection.setRequestProperty("Connection", "keep-alive");
        String cookie = String.format("captchaSessionId=%s; _ym_uid=1462452903560071241; PHPSESSID=%s; _ym_isad=1; _ga=GA1.2.74263411.1462452903; BITRIX_SM_REGKOD=00; BITRIX_SM_IP_REGKOD=77; siteType=pda", phpsessId, phpsessId);
        urlConnection.setRequestProperty("Cookie", cookie);
        return BitmapFactory.decodeStream(urlConnection.getInputStream());
    }

    private static final String TAG = "OldCaptchaService";

    private static String getPhpsessIdByUrlConnection(URLConnection urlConnection) {
        String resultPhpsessId = null;
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> next : headerFields.entrySet()) {
            String key = next.getKey();
            List<String> value = next.getValue();
            if (key != null && key.equals("Set-Cookie")) {
                Log.i(TAG, "key: " + key);
                for (String s : value) {
                    if (s.startsWith(CommonRequest.PHPSESS_ID)) {
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

    public static CaptchaResult captchaRequest() throws IOException {
        String phpSessId = getPhpSessId();
        if (phpSessId == null || phpSessId.isEmpty()) {
            Log.d(TAG, "phpsessid can't be null or empty");
            return null;
        }

        Bitmap captchaImage = getCaptchaImage(phpSessId);

        CaptchaResult captchaResult = new CaptchaResult();
        captchaResult.setSessionId(phpSessId);
        captchaResult.setImage(captchaImage);
        return captchaResult;
    }
}
