package ru.android_studio.gibdd_servis.pageObject;

import android.app.Activity;

/**
 * Страницы запроса с кнопкой "Проверить"
 */
public abstract class RequestPage extends AbstractPage {
    public RequestPage(Activity activity) {
        super(activity);
    }

    public void clickCheckBtn() {

    }
}
