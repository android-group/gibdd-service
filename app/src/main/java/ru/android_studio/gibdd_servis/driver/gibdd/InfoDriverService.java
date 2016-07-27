package ru.android_studio.gibdd_servis.driver.gibdd;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.android_studio.gibdd_servis.driver.model.RequestDriver;
import ru.android_studio.gibdd_servis.driver.model.ResponseDriver;
import ru.android_studio.gibdd_servis.gibdd.CommonRequest;

/**
 * Данный проект эммулирует взаимодействие пользователя с сайтом гибдд на странице "ПРОВЕРКА ТРАНСПОРТНОГО СРЕДСТВА".
 */
public class InfoDriverService {

    private static final String REQUEST = "http://check.gibdd.ru/proxy/check/driver";
    private final static String TAG = "InfoDriverService";

    public static ResponseDriver clientRequest(RequestDriver requestDriver) throws IOException {
        URL url = new URL(REQUEST);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("User-Agent", CommonRequest.USER_AGENT);
        urlConnection.setRequestProperty("X-Compress", "0");
        urlConnection.setRequestProperty("Referer", "http://www.gibdd.ru/check/auto/");
        urlConnection.setRequestProperty("Host", "www.gibdd.ru");
        urlConnection.setRequestProperty("Accept", "image/webp,image/*,*/*;q=0.8");
        urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
        urlConnection.setRequestProperty("Connection", "keep-alive");
        String cookie = String.format("JSESSIONID=%s; _ga=GA1.2.1593191729.1468750977; _ym_uid=1468750977525215266; _ym_isad=1", requestDriver.getJsessionid());
        urlConnection.setRequestProperty("Cookie", cookie);

        // Send post request
        urlConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        String urlParameters = String.format("num=%s&date=%s&captchaWord=%s", requestDriver.getNum(), requestDriver.getDate(), requestDriver.getCaptchaWord());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();


        int responseCode = urlConnection.getResponseCode();
        Log.i(TAG, "\nSending 'POST' request to URL : " + url);
        Log.i(TAG, "Post parameters : " + urlParameters);
        Log.i(TAG, "Response Code : " + responseCode);

        ResponseDriver responseDriver = new ResponseDriver();
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
}