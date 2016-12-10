package ru.android_studio.gibdd_servis.gibdd;

import android.content.Context;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Запрос капчи с сайта ГИБДД
 */
public class NewCaptchaAsyncTask extends BaseCaptchaAsyncTask {

    private CheckType checkType;

    public NewCaptchaAsyncTask(Context context, ImageView captchaImageView, CheckType checkType) {
        super(context, captchaImageView);

        this.checkType = checkType;
    }

    @Override
    CaptchaResult captchaRequest() throws IOException {
        return NewCaptchaService.captchaRequest(checkType);
    }
}