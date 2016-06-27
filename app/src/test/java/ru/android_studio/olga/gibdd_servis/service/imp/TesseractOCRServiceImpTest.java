package ru.android_studio.olga.gibdd_servis.service.imp;

import android.content.Context;
import android.content.res.AssetManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;

import java.io.IOException;

/**
 * Unit-tests for {@link TesseractOCRServiceImp}
 * <br>Created by Ruslan Suleymanov on 22.06.16.
 * @author Ruslan Suleymanov
 * @version 0.1
 */
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

    @Test @Ignore
    public void testExtractText() {
        fail("Not implemented!");
    }

    @Test @Ignore
    public void testExtractText1() {
        fail("Not implemented!");
    }

    @Test @Ignore
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

    @Test @Ignore
    public void testPrepareDirectory() {
        fail("Not implemented!");
    }

    @Test @Ignore
    public void testCopyTessDataFiles() {
        fail("Not implemented!");
    }
}