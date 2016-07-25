package ru.android_studio.gibdd_servis.gibdd;

public class RequestUtil {


    public static boolean isCookie(String key) {
        return key != null && (key.equals("Set-Cookie") || key.equals("Cookie"));
    }
}
