package ru.android_studio.gibdd_servis.fine.gibdd;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.android_studio.gibdd_servis.fine.model.RequestFine;
import ru.android_studio.gibdd_servis.fine.model.ResponseFine;
import ru.android_studio.gibdd_servis.gibdd.CommonRequest;

/**
 * Данный проект эммулирует взаимодействие пользователя с сайтом гибдд на странице "ПРОВЕРКА ТРАНСПОРТНОГО СРЕДСТВА".
 */
public class InfoFineService {

    public static ResponseFine clientRequest(RequestFine requestFine) throws IOException {
        URL url = new URL("http://www.gibdd.ru/proxy/check/fines/2.0/client.php");
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
        String cookie = String.format("JSESSIONID=%s; _ga=GA1.2.1593191729.1468750977; _ym_uid=1468750977525215266; _ym_isad=1", requestFine.getJsessionid());
        urlConnection.setRequestProperty("Cookie", cookie);

        // Send post request
        urlConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
        String req = String.format("fines:%s:%s:%s", requestFine.getRegnum(), requestFine.getRegreg(), requestFine.getStsnum());
        String urlParameters = String.format("req=%s&captchaWord=%s&regnum=%s&regreg=%s&stsnum=%s", req, requestFine.getCaptchaWord(), requestFine.getRegnum(), requestFine.getRegreg(), requestFine.getStsnum());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();


        int responseCode = urlConnection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

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
}