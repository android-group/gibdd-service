package ru.android_studio.gibdd_servis.car.gibdd;

import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class RequestUtil {
    public static void printHeadersByUrlConnection(URLConnection urlConnection) {
        System.out.println("print headers:");
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        for (Map.Entry<String, List<String>> next : headerFields.entrySet()) {
            String key = next.getKey();
            if (isCookie(key)) {
                List<String> value = next.getValue();
                System.out.println("key:" + key);
                System.out.println("value:" + value);
            }
        }
    }

    public static boolean isCookie(String key) {
        return key != null && (key.equals("Set-Cookie") || key.equals("Cookie"));
    }
}
