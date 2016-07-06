package ru.android_studio.olga.gibdd_servis.service.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import ru.android_studio.olga.gibdd_servis.service.OCRService;

/**
 * Сервис для работы с Tesseract OCR
 * <br>см. https://github.com/bieliaievays/Tess-two_example/blob/master/app/src/main/java/com/ashomok/tesseractsample/MainActivity.java
 * <br>Created by Ruslan Suleymanov on 21.06.16.
 * @author Ruslan Suleymanov
 * @version 0.2
 */
public class TesseractOCRServiceImp implements OCRService, Closeable {

    private static volatile boolean prepared = false;

    private final ReadWriteLock locker = new ReentrantReadWriteLock();

    private final Map<LANGUAGE, TessBaseAPI> tessMap = new HashMap<>(LANGUAGE.values().length);

    private static final String TAG = TesseractOCRServiceImp.class.getSimpleName();

    public static final String DATA_PATH =
        (Environment.getExternalStorageDirectory() != null ? Environment.getExternalStorageDirectory().toString() : "") + "/TesseractSample/";
    public static final String TESSDATA = "tessdata";

    private volatile Context context;

    private static volatile TesseractOCRServiceImp instance = null;

    protected TesseractOCRServiceImp() {
        Log.i(TAG, "Default constructor called for Tesseract OCR service");
    }

    protected TesseractOCRServiceImp(Context context) {
        Log.i(TAG, "Constructor with Context called for Tesseract OCR service");
        try {
            getLocker().writeLock().lock();
            this.context = context;
        } finally {
            getLocker().writeLock().unlock();
        }
    }

    public static TesseractOCRServiceImp getInstance() {
        if (instance == null) {
            synchronized (TesseractOCRServiceImp.class) {
                if (instance == null) {
                    instance = new TesseractOCRServiceImp();
                    instance.prepare();
                }
            }
        }
        return instance;
    }

    public static TesseractOCRServiceImp getInstance(Context context) {
        if (instance == null) {
            synchronized (TesseractOCRServiceImp.class) {
                if (instance == null) {
                    instance = new TesseractOCRServiceImp(context);
                    instance.prepare();
                }
            }
        }
        if (!instance.getContext().equals(context)) {
            synchronized (TesseractOCRServiceImp.class) {
                instance.setContext(context);
                instance.prepare();
            }
        }
        return instance;
    }

    public Context getContext() {
        try {
            getLocker().readLock().lock();
            return context;
        } finally {
            getLocker().readLock().unlock();
        }
    }

    public TesseractOCRServiceImp setContext(Context context) {
        try {
            getLocker().writeLock().lock();
            if (this.context != context)
                prepared = false;
            this.context = context;
            return this;
        } finally {
            getLocker().writeLock().unlock();
        }
    }

    @Override
    public String extractText(Bitmap bitmap, LANGUAGE lang_code) {
        Log.i(TAG, "call extractText(Bitmap bitmap, LANGUAGE lang_code)...");
        try {
            getLocker().readLock().lock();
            if (bitmap == null)
                throw new IllegalArgumentException("Illegal argument: bitmap == null");
            if (lang_code == null)
                throw new IllegalArgumentException("Illegal argument: lang_code == null");
            prepare();
            if (!isPrepared())
                return null;

            TessBaseAPI tessBaseApi = tessMap.get(lang_code);

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
            return extractedText;
        } finally {
            getLocker().readLock().unlock();
            Log.i(TAG, "extractText(Bitmap bitmap, LANGUAGE lang_code) finished");
        }
    }

    @Override
    public String extractText(Uri imgUri, LANGUAGE langCode) {
        Log.i(TAG, "call extractText(Uri imgUri, LANGUAGE langCode)...");
        try {
            getLocker().readLock().lock();
            if (imgUri == null)
                throw new IllegalArgumentException("Illegal argument: imgUri == null");
            prepare();
            if (!isPrepared())
                return null;

            String result = null;
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
                try {
                    getLocker().writeLock().lock();
                    Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);

                    result = extractText(bitmap, langCode);
                } finally {
                    getLocker().writeLock().unlock();
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return result;
        } finally {
            getLocker().readLock().unlock();
            Log.i(TAG, "extractText(Uri imgUri, LANGUAGE langCode) finished");
        }
    }

    @Override
    public void prepare() {
        Log.i(TAG, "call prepare() ...");
        try {
            getLocker().readLock().lock();
            if (isPrepared())
                return;

            Log.i(TAG, "prepare Tesseract OCR service ...");
            try {
                getLocker().writeLock().lock();
                try {
                    prepareDirectory(DATA_PATH + TESSDATA);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                copyTessDataFiles(TESSDATA);

                TessBaseAPI tessBaseApi = null;
                for (LANGUAGE lang : LANGUAGE.values()) {
                    tessBaseApi = tessMap.get(lang);

                    if (tessBaseApi == null) {
                        try {
                            tessBaseApi = new TessBaseAPI();
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            if (tessBaseApi == null) {
                                Log.e(TAG, "TessBaseAPI is null. TessFactory not returning tess object.");
                            }
                            throw e;
                        }
                    }

                    tessBaseApi.init(DATA_PATH, lang.getLang());

                    tessMap.put(lang, tessBaseApi);
                }

                prepared = true;
                Log.i(TAG, "Tesseract OCR service prepared");
            } finally {
                getLocker().writeLock().unlock();
            }
        } finally {
            getLocker().readLock().unlock();
            Log.i(TAG, "prepare() finished");
        }
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

    public static boolean isPrepared() {
        return prepared;
    }

    public ReadWriteLock getLocker() {
        return locker;
    }

    @Override
    public void close() throws IOException {
        Log.i(TAG, "call close() ...");
        try {
            getLocker().writeLock().lock();

            for (LANGUAGE lang : tessMap.keySet()) {
                TessBaseAPI tessAPI = tessMap.get(lang);
                if (tessAPI != null) {
                    tessAPI.end();
                    tessMap.remove(lang);
                }
            }

            Log.i(TAG, "Tesseract OCR service closed");
        } finally {
            try {
                prepared = false;
            } finally {
                getLocker().writeLock().unlock();
                Log.i(TAG, "close() finished");
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

}
