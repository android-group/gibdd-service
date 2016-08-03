package ru.android_studio.gibdd_servis.ocr.imp;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.ocr.OCRService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit-tests for {@link TesseractOCRServiceImp}
 * <br>Created by Ruslan Suleymanov on 22.06.16.
 *
 * @author Ruslan Suleymanov
 * @version 0.1
 */
@Ignore
// @TODO need to work
public class TesseractOCRServiceImpTest {

    private TesseractOCRServiceImp service;

    @Before
    public void setUp() {
        service = new TesseractOCRServiceImp();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGetSetContext() {
        final String methodName = "Context";
        TesseractOCRServiceImp returnedService = null;

        Context[] values = {mock(Context.class), null, mock(Context.class)};
        for (Context value : values) {
            returnedService = service.setContext(value);
            assertEquals(String.format("get%1$s() must return [%2$s] after call set%1$s([%2$s])", methodName, value), value, service.getContext());
            assertEquals(String.format("set%1$s([%2$s]) must return [%3$s]", methodName, value, service), service, returnedService);
        }

        service = new TesseractOCRServiceImp();
        assertNull(String.format("get%1$s() must return null after call %2$s()", methodName, service.getClass().getSimpleName()), service.getContext());
    }

    @Test
    public void testExtractTextFromBitmap() {
        final String methodName = "extractText";
        Bitmap captchaBMP = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.c13249);
        String value = service.extractText(captchaBMP, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN);
        assertEquals(String.format("%1$s([%2$s], [%3$s]) must return \"%4$s\" after call set%1$s([%2$s])",
                methodName, captchaBMP, OCRService.LANGUAGE.LANGUAGE_CODE_RUSSIAN, "13249"), null, value);
    }

    @Test
    @Ignore
    public void testExtractText1() {
        fail("Not implemented!");
    }

    @Test
    @Ignore
    public void testPrepare() throws IOException {
        AssetManager assetManager = mock(AssetManager.class);
        when(assetManager.list(TesseractOCRServiceImp.TESSDATA)).thenReturn(new String[0]);
        Context context = mock(Context.class);
        when(context.getAssets()).thenReturn(assetManager);
        service.setContext(context);

        service.prepare();

        verify(service, times(1)).prepareDirectory(TesseractOCRServiceImp.DATA_PATH + TesseractOCRServiceImp.TESSDATA);
        verify(service, times(1)).copyTessDataFiles(TesseractOCRServiceImp.TESSDATA);
    }

    @Test
    @Ignore
    public void testPrepareDirectory() {
        fail("Not implemented!");
    }

    @Test
    @Ignore
    public void testCopyTessDataFiles() {
        fail("Not implemented!");
    }
}