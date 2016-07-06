package ru.android_studio.olga.gibdd_servis;

import android.widget.ImageView;

import ru.android_studio.olga.gibdd_servis.model.Request;
import ru.android_studio.olga.gibdd_servis.model.Response;

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
