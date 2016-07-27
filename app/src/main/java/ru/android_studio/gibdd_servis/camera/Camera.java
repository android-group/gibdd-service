package ru.android_studio.gibdd_servis.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import static ru.android_studio.gibdd_servis.camera.FileHelper.getOutputMediaFileUri;

/**
 * Created by y.andreev on 06.07.2016.
 * Service for work with camera
 */
public abstract class Camera {

    private static final String TAG = "Camera";
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String EXTRAS_CAMERA_FACING = "android.intent.extras.CAMERA_FACING";

    private static Uri fileUri;

    public static void open(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MediaType.IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra(EXTRAS_CAMERA_FACING, 1);
        activity.startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    public static Bitmap showResult(int requestCode, int resultCode, Context context) {
        Log.d(TAG, "method showResult");
        Log.d(TAG, "requestCode: " + requestCode);
        Log.d(TAG, "resultCode" +  resultCode);

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // successfully captured the image
                // display it in image view
                Log.d(TAG, "Activity.RESULT_OK");
                return getBitmapCapturedImage();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(context, "User cancelled image capture", Toast.LENGTH_SHORT).show();
            } else {
                // failed to capture image
                Toast.makeText(context, "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }

        return null;
    }

    /*
     * Display image from a path to ImageView
     */
    private static Bitmap getBitmapCapturedImage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            return BitmapFactory.decodeFile(fileUri.getPath(), options);
        } catch (NullPointerException e) {
            Log.e(TAG, "Error get bitmap captured image", e);
        }
        return null;
    }
}
