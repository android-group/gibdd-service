package ru.android_studio.olga.gibdd_servis;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.android_studio.olga.gibdd_servis.service.OCRService;
import ru.android_studio.olga.gibdd_servis.service.imp.TesseractOCRServiceImp;

/**
 * Created by Ruslan Suleymanov on 23.06.16.
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public abstract class ActivityWithMenuAndOCR extends ActivityWithMenu {

    private OCRService serviceOCR;

    public OCRService getServiceOCR() {
        return serviceOCR;
    }

    public ActivityWithMenuAndOCR setServiceOCR(OCRService serviceOCR) {
        this.serviceOCR = serviceOCR;
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setServiceOCR(new TesseractOCRServiceImp(this));
        getServiceOCR().prepare();
    }
}
