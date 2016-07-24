package ru.android_studio.gibdd_servis.pageObject;

import android.app.Activity;

/**
 * Абстрактный класс для удобства (предпологается выносить логику). Не удалять.
 */
public abstract class AbstractPage {

    protected abstract int getId();

    private Activity activity;

    public AbstractPage(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
