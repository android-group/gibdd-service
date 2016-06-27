package ru.android_studio.olga.gibdd_servis.service.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ru.android_studio.olga.gibdd_servis.service.OCRService;

/**
 * Сервис для работы с Tesseract OCR
 * <br>см. https://github.com/bieliaievays/Tess-two_example/blob/master/app/src/main/java/com/ashomok/tesseractsample/MainActivity.java
 * <br>Created by Ruslan Suleymanov on 21.06.16.
 * @author Ruslan Suleymanov
 * @version 0.1
 */
public class TesseractOCRServiceImp implements OCRService {

    private TessBaseAPI tessBaseApi;
    private static final String TAG = TesseractOCRServiceImp.class.getSimpleName();

    public static final String DATA_PATH =
        (Environment.getExternalStorageDirectory() != null ? Environment.getExternalStorageDirectory().toString() : "") + "/TesseractSample/";
    public static final String TESSDATA = "tessdata";

    private Context context;

    public Context getContext() {
        return context;
    }

    public TesseractOCRServiceImp setContext(Context context) {
        this.context = context;
        return this;
    }

    public TesseractOCRServiceImp() {
    }

    public TesseractOCRServiceImp(Context context) {
        this.context = context;
    }

    @Override
    public String extractText(Bitmap bitmap, LANGUAGE lang_code) {
        if (bitmap == null)
            throw new IllegalArgumentException("Illegal argument: bitmap == null");
        if (lang_code == null)
            throw new IllegalArgumentException("Illegal argument: lang_code == null");

        if (tessBaseApi == null) {
            try {
                tessBaseApi = new TessBaseAPI();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                if (tessBaseApi == null) {
                    Log.e(TAG, "TessBaseAPI is null. TessFactory not returning tess object.");
                }
            }
        }

        tessBaseApi.init(DATA_PATH, lang_code.getLang());

//       //EXTRA SETTINGS
//        //For example if we only want to detect numbers
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
//
//        //blackList Example
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_+=-qwertyuiop[]}{POIU" +
//                "YTRWQasdASDfghFGHjklJKLl;L:'\"\\|~`xcvXCVbnmBNM,./<>?");

        Log.d(TAG, "Training file loaded");
        tessBaseApi.setImage(bitmap);
        String extractedText = "empty result";
        try {
            extractedText = tessBaseApi.getUTF8Text();
        } catch (Exception e) {
            Log.e(TAG, "Error in recognizing text.");
        }
        tessBaseApi.end();
        return extractedText;
    }

    @Override
    public String extractText(Uri imgUri, LANGUAGE langCode) {
        if (imgUri == null)
            throw new IllegalArgumentException("Illegal argument: imgUri == null");
        String result = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
            Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);

            result = extractText(bitmap, langCode);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    @Override
    public void prepare() {
        try {
            prepareDirectory(DATA_PATH + TESSDATA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        copyTessDataFiles(TESSDATA);
    }

    /**
     * Prepare directory on external storage
     *
     * @param path
     * @throws Exception
     */
    protected void prepareDirectory(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e(TAG, "ERROR: Creation of directory " + path + " failed, check does Android Manifest have permission to write to external storage.");
            }
        } else {
            Log.i(TAG, "Created directory " + path);
        }
    }

    /**
     * Copy tessdata files (located on assets/tessdata) to destination directory
     *
     * @param path - name of directory with .traineddata files
     */
    protected void copyTessDataFiles(String path) {
        try {
            String fileList[] = getContext().getAssets().list(path);

            for (String fileName : fileList) {

                // open file within the assets folder
                // if it is not already there copy it to the sdcard
                String pathToDataFile = DATA_PATH + path + "/" + fileName;
                if (!(new File(pathToDataFile)).exists()) {

                    InputStream in = getContext().getAssets().open(path + "/" + fileName);

                    OutputStream out = new FileOutputStream(pathToDataFile);

                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();

                    Log.d(TAG, "Copied " + fileName + "to tessdata");
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to copy files to tessdata " + e.toString());
        }
    }

}
