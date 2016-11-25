package ru.android_studio.gibdd_servis.gibdd;

import java.io.IOException;

public class NotFoundResult extends IOException {
    public NotFoundResult(String message) {
        super(message);
    }
}
