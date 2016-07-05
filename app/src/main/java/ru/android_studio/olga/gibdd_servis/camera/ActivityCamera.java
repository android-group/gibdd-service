package ru.android_studio.olga.gibdd_servis.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import ru.android_studio.olga.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.olga.gibdd_servis.R;

import static ru.android_studio.olga.gibdd_servis.camera.FileHelper.getOutputMediaFileUri;

/**
 * Created by y.andreev on 06.07.2016.
 *
 * Activity with menu and OCR and camera
 */
public abstract class ActivityCamera extends ActivityWithMenuAndOCR {
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    private Uri fileUri;

    //private ImageView imgPreview;

    /**
     * Открывать только фронтальную камеру
     * http://stackoverflow.com/questions/19667094/intent-does-not-set-the-camera-parameters/19670928#19670928
     */
    private static final String EXTRAS_CAMERA_FACING = "android.intent.extras.CAMERA_FACING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_camera);

        //imgPreview = (ImageView) findViewById(R.id.img_preview);
        /*Button captureBtn = (Button) findViewById(R.id.capture_picture_btn);

        if (captureBtn != null) {
            captureBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    captureImage();
                }
            });
        }*/

        // Checking camera availability
        if (!Hardware.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MediaType.IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra(EXTRAS_CAMERA_FACING, 1);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {
            // hide video preview
            //videoPreview.setVisibility(View.GONE);

            //imgPreview.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            //imgPreview.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    /*
     * Here we restore the fileUri again
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }
}
