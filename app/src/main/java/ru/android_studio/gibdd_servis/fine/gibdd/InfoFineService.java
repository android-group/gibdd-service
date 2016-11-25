package ru.android_studio.gibdd_servis.fine.gibdd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import ru.android_studio.gibdd_servis.fine.model.RequestFine;
import ru.android_studio.gibdd_servis.fine.model.ResponseFine;
import ru.android_studio.gibdd_servis.gibdd.CommonRequest;

/**
 * Данный проект эммулирует взаимодействие пользователя с сайтом гибдд на странице "ПРОВЕРКА ТРАНСПОРТНОГО СРЕДСТВА".
 */
public class InfoFineService {

    private static final String REQUEST = "http://www.gibdd.ru/proxy/check/fines/2.0/client.php";

    public static ResponseFine clientRequest(RequestFine requestFine) throws IOException {
        URL url = new URL(REQUEST);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        urlConnection.setRequestProperty("X-Compress", "0");
        urlConnection.setRequestProperty("Referer", "http://www.gibdd.ru/check/fines/");
        urlConnection.setRequestProperty("Host", "www.gibdd.ru");
        urlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
        urlConnection.setRequestProperty("Connection", "keep-alive");
        String cookie = String.format("captchaSessionId=%s; _ym_uid=1469429827825360503; PHPSESSID=%s; _ym_isad=1; _ga=GA1.2.21475928.1469429827; _gat=1", requestFine.getPhpSessId(), requestFine.getPhpSessId());
        urlConnection.setRequestProperty("Cookie", cookie);

        // Send post request
        urlConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        String urlParameters = String.format("req=%s&captchaWord=%s&regnum=%s&regreg=%s&stsnum=%s", requestFine.getReq(), requestFine.getCaptchaWord(), requestFine.getRegnum(), requestFine.getRegreg(), requestFine.getStsnum());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();


        int responseCode = urlConnection.getResponseCode();
        Log.d(TAG, "\nSending 'POST' request to URL : " + url);
        Log.d(TAG, "Post parameters : " + urlParameters);
        Log.d(TAG, "Response Code : " + responseCode);

        ResponseFine responseDriver = new ResponseFine();
        responseDriver.setResultText(getText(urlConnection));
        return responseDriver;
    }

    private static String getText(HttpURLConnection urlConnection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private String getPhpsessId() throws IOException {
        URL url = new URL("http://www.gibdd.ru/check/auto/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        return getPhpsessIdByUrlConnection(urlConnection);
    }

    private Bitmap getCaptchaImage(final String phpsessId) throws IOException {
        String imageUrl = "http://www.gibdd.ru/proxy/check/getCaptcha.php?PHPSESSID=" + phpsessId;
        URL url = new URL(imageUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        urlConnection.setRequestProperty("X-Compress", "0");
        urlConnection.setRequestProperty("Referer", "http://www.gibdd.ru/check/auto/");
        urlConnection.setRequestProperty("Host", "www.gibdd.ru");
        urlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
        urlConnection.setRequestProperty("Connection", "keep-alive");
        String cookie = String.format("captchaSessionId=%s; _ym_uid=1462452903560071241; PHPSESSID=%s; _ym_isad=1; _ga=GA1.2.74263411.1462452903; BITRIX_SM_REGKOD=00; BITRIX_SM_IP_REGKOD=77; siteType=pda", phpsessId, phpsessId);
        urlConnection.setRequestProperty("Cookie", cookie);
        return BitmapFactory.decodeStream(urlConnection.getInputStream());
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

    private final static String TAG = "";
}