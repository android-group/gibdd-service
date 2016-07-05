package ru.android_studio.olga.gibdd_servis.camera;

import android.content.Context;
import android.content.pm.PackageManager;

public class Hardware {
    /**
     * Checking device has camera hardware or not
     */
    public static boolean isDeviceSupportCamera(Context context) {
        // this device has a camera
// no camera on this device
        return context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA);
    }
}
