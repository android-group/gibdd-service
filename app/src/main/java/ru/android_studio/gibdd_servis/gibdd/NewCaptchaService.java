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

/**
 * Получить капчу и jsessionid
 */
class NewCaptchaService {

    private final static String TAG = "NewCaptchaService";

    /**
     * Получить капчу и jsessionid
     *
     * @return CaptchaResult image captcha with jsessionid
     * @throws IOException
     */
    static CaptchaResult captchaRequest(CheckType checkType) throws IOException {
        Log.d(TAG, "START captchaRequest");

        URL url = new URL("http://check.gibdd.ru/proxy/captcha.jpg");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        urlConnection.setRequestProperty("X-Compress", "0");
        urlConnection.setRequestProperty("Referer", "http://www.gibdd.ru/check/" + checkType.getTitle() + "/");
        urlConnection.setRequestProperty("Host", "www.gibdd.ru");
        urlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
        urlConnection.setRequestProperty("Connection", "keep-alive");
        urlConnection.setRequestProperty("Cookie", "_ga=GA1.2.1593191729.1468750977; _ym_uid=1468750977525215266; _ym_isad=1");

        Log.d(TAG, "do request captcha");

        String sessionId = getSessionIdByURLConnection(urlConnection);
        Bitmap captchaBitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());

        Log.d(TAG, "sessionId : " + sessionId);

        CaptchaResult captchaResult = new CaptchaResult();
        captchaResult.setSessionId(sessionId);
        captchaResult.setImage(captchaBitmap);

        Log.d(TAG, "END captchaRequest");
        return captchaResult;
    }

    private static String getSessionIdByURLConnection(URLConnection urlConnection) throws IOException {
        if(urlConnection == null) {
            throw new IOException("Connection can't be null");
        }
        Log.d(TAG, "START getSessionIdByURLConnection");

        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        if(headerFields == null) {
            throw new IOException("headerFields is null");
        }

        Log.d(TAG, "find session id in headerFields");
        for (Map.Entry<String, List<String>> next : headerFields.entrySet()) {
            String key = next.getKey();
            if (RequestUtil.isCookie(key)) {
                return findSessionId(next.getValue());
            }
        }

        throw new NotFoundResult(String.format("can't find %s id", CommonRequest.JSESSIONID));
    }

    private static String findSessionId(List<String> value) throws NotFoundResult {
        Log.d(TAG, "findSessionId");

        for (String s : value) {
            if (s.startsWith(CommonRequest.JSESSIONID)) {
                int start = s.indexOf("=") + 1;
                int endIndex = s.indexOf(";");
                String result = s.substring(start, endIndex);

                Log.d(TAG, CommonRequest.JSESSIONID + ": " + result);
                return result;
            }
        }
        throw new NotFoundResult(String.format("can't find %s id", CommonRequest.JSESSIONID));
    }
}
