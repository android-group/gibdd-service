package ru.android_studio.gibdd_servis.gibdd;

import android.graphics.Bitmap;

public class CaptchaResult {

    private String sessionId;
    private Bitmap image;

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }
}
