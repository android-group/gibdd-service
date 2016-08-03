package ru.android_studio.gibdd_servis.ocr;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Интерфейс для работы с сервисом OCR
 * <br>Created by Ruslan Suleymanov on 22.06.16.
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public interface OCRService {

    enum LANGUAGE {
        /** код английского языка по ISO 639-3 (см. http://www-01.sil.org/iso639-3/codes.asp?order=639_3&letter=r) */
        LANGUAGE_CODE_ENGLISH("eng"),
        /** код русского языка по ISO 639-3 (см. http://www-01.sil.org/iso639-3/codes.asp?order=639_3&letter=r) */
        LANGUAGE_CODE_RUSSIAN("rus");

        final String lang;
        LANGUAGE(String lang) {
            this.lang = lang;
        }

        public String getLang() {
            return lang;
        }
    }

    /**
     * Разпознать текст на изображении
     * <br>don't run this code in main thread - it stops UI thread. Create AsyncTask instead.
     * http://developer.android.com/intl/ru/reference/android/os/AsyncTask.html
     *
     * @param bitmap изображение текста
     * @param lang_code код языка текста по ISO 639-3 (см. http://www-01.sil.org/iso639-3/codes.asp?order=639_3&letter=r)
     * @return разпознанный текст
     */
    String extractText(Bitmap bitmap, LANGUAGE lang_code);

    /**
     * Разпознать текст на изображении
     * <br>don't run this code in main thread - it stops UI thread. Create AsyncTask instead.
     * http://developer.android.com/intl/ru/reference/android/os/AsyncTask.html
     *
     * @param imgUri
     * @param lang_code код языка текста по ISO 639-3 (см. http://www-01.sil.org/iso639-3/codes.asp?order=639_3&letter=r)
     * @return разпознанный текст
     */
    String extractText(Uri imgUri, LANGUAGE lang_code);

    /**
     * Подготовить сервис OCR
     */
    void prepare();
}
