package ru.android_studio.gibdd_servis.auto.response;

import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAuto;

public class ParseResultAutoTest {

    @Test
    public void testParseResult_SUCCESS() {
        ResponseStatus result = ParseResultAuto.getResponseStatus(ResponseDataAuto.SUCCESS);
        Assert.assertEquals(ResponseStatus.SUCCESS, result);
    }

    @Test
    public void testParseResult_BAD_REQUEST() {
        ResponseStatus result = ParseResultAuto.getResponseStatus(ResponseDataAuto.BAD_REQUEST);
        Assert.assertEquals(ResponseStatus.BAD_REQUEST, result);
    }

    @Test
    public void testParseResult_INVALID_CAPTCHA() {
        ResponseStatus result = ParseResultAuto.getResponseStatus(ResponseDataAuto.CAPTCHA_NOT_VALID);
        Assert.assertEquals(ResponseStatus.CAPTCHA_NOT_VALID, result);
    }
}