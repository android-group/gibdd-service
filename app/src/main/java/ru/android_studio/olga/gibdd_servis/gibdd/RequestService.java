package ru.android_studio.olga.gibdd_servis.gibdd;

import android.widget.ImageView;

/**
 * Created by y.andreev on 02.07.2016.
 *
 */
interface RequestService {

    /**
     * Получить url для запроса
     * @return
     */
    String getRequestUrl();

    /**
     * получить капчу
     * @param imageView
     */
    void retrieveCaptcha(ImageView imageView);

    /**
     * Запрос на сервер
     */
    // Response request(Request request);
}
