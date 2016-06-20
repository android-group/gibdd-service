package ru.android_studio.olga.gibdd_servis;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AutoGibddService extends GibddService {

    String vin;
    String captcha;
    String phpsessid;

    String[] divTypeArray = {
            "",
            "Судебные органы",
            "Судебный пристав",
            "Таможенные органы",
            "Органы социальной защиты",
            "Нотариус",
            "Органы внутренних дел или иные правоохранительные органы",
            "Органы внутренних дел или иные правоохранительные органы (прочие)"
    };

    String[] ogrkodArray = {
            "",
            "Запрет на регистрационные действия",
            "Запрет на снятие с учета",
            "Запрет на регистрационные действия и прохождение ГТО",
            "Утилизация (для транспорта не старше 5 лет)",
            "Аннулирование"
    };

    private class RequestTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                return clientRequest(vin, captcha, phpsessid);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String resultJson) {
            if (resultJson != null) {
                try {
                    JSONObject resultJsonObject = new JSONObject(resultJson);
                    if (restricted(resultJsonObject)) {

                    } else if (wanted(resultJsonObject)) {

                    } else {
                        //empty();
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    //Toast.makeText(getBaseContext(), R.string.invalid_captcha, Toast.LENGTH_LONG).show();
                    //finish();
                }
            }
        }

        /*
        * Значится в розыске
        * */
        private boolean wanted(JSONObject resultJsonObject) throws JSONException {
            JSONObject wanted = resultJsonObject.getJSONObject("wanted");
            JSONArray wantedRestricted = wanted.getJSONArray("records");
            if (wantedRestricted.length() < 1) {
                return false;
            }
            //for (int i = 0; i < wantedRestricted.length(); i++) {
            JSONObject item = wantedRestricted.getJSONObject(0);

            String model = item.getString("w_model");       // Марка (модель) ТС
            String godVyp = item.getString("w_god_vyp");    // Год выпуска ТС
            String dataPu = item.getString("w_data_pu");    // Дата постоянного учета
            String regZn = item.getString("w_reg_zn");      // Гос. рег. знак
            String kuzov = item.getString("w_kuzov");       // Номер кузова
            String shassi = item.getString("w_shassi");     // Номер шасси
            String regInic = item.getString("w_reg_inic");  // Регион инициатора розыска
            String dataOper = item.getString("w_data_oper");// Дата оперативного учета

            return true;
        }

        /*
        * Наложено ограничение
        * */
        private boolean restricted(JSONObject resultJsonObject) throws JSONException {
            JSONObject restricted = resultJsonObject.getJSONObject("restricted");
            JSONArray recordsRestricted = restricted.getJSONArray("records");

            //for (int i = 0; i < recordsRestricted.length(); i++) {
            if (recordsRestricted.length() < 1) {
                return false;
            }

            JSONObject item = recordsRestricted.getJSONObject(0);
            String tsmodel = item.getString("tsmodel"); // Марка (модель) ТС
            String tsyear = item.getString("tsyear");   // Год выпуска ТС
            String dateogr = item.getString("dateogr"); //Дата наложения ограничения
            String regname = item.getString("regname"); // Регион наложения ограничения

            String divtype = divTypeArray[item.getInt("divtype")]; // Кем наложено ограничение
            String ogrkod = divTypeArray[item.getInt("ogrkod")];   // Вид ограничения

            // подробнее
            String regid = item.getString("regid");
            String divid = item.getString("divid");
            String id = "" + regid + divid;
            String more = "/contacts/div" + id + "/";

            return true;
        }

        private String clientRequest(String vin, String captchaWord, String phpsessid) throws IOException {
            String client = "http://www.gibdd.ru/proxy/check/auto/2.0/client.php";
            URL url = new URL(client);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("User-Agent", USER_AGENT);
            urlConnection.setRequestProperty("X-Compress", "0");
            urlConnection.setRequestProperty("Referer", "http://www.gibdd.ru/check/auto/");
            urlConnection.setRequestProperty("Host", "www.gibdd.ru");
            urlConnection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            urlConnection.setRequestProperty("Accept-Language", "ru,en-US;q=0.8,en;q=0.6");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            urlConnection.setRequestProperty("Connection", "keep-alive");
            urlConnection.setRequestProperty("X-Csrf-Token", "20e09767aae4066db98837d68f5a91c4");
            urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            String cookie = String.format("PHPSESSID=%s; BITRIX_SM_IP_REGKOD=77; BITRIX_SM_REGKOD=00; BITRIX_SM_METOD=GEO;", phpsessid);
            urlConnection.setRequestProperty("Cookie", cookie);

            // Send post request
            urlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            String urlParameters = String.format("vin=%s&captchaWord=%s&captchaCode=", vin, captchaWord);
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();


            int responseCode = urlConnection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

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
}