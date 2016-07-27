package ru.android_studio.gibdd_servis.gibdd;

import android.content.Context;
import android.widget.ImageView;

import java.io.IOException;

public class OldCaptchaAsyncTask extends BaseCaptchaAsyncTask {

    public OldCaptchaAsyncTask(Context context, ImageView captchaImageView) {
        super(context, captchaImageView);
    }

    @Override
    CaptchaResult captchaRequest() throws IOException {
        return OldCaptchaService.captchaRequest();
    }
}
