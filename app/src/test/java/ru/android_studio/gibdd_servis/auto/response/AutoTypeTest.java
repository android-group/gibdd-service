package ru.android_studio.gibdd_servis.auto.response;

import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.history.AutoType;

public class AutoTypeTest {

    @Test
    public void testGetByTypeNumberString() {
        for (AutoType expected : AutoType.values()) {
            AutoType actual = AutoType.getByTypeNumberString(expected.getTypeNum());
            Assert.assertEquals(expected, actual);
        }

    }

    @Test
    public void testGetByTypeNumberStringInvalidParam() {
        String invalidTypeNum = "test";
        AutoType actual = AutoType.getByTypeNumberString(invalidTypeNum);
        Assert.assertNull(actual);
    }

}