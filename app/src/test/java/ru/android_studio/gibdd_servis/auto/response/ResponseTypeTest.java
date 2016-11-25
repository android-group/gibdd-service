package ru.android_studio.gibdd_servis.auto.response;


import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;

public class ResponseTypeTest {

    @Test
    public void testGetByStatus() {
        for (ResponseStatus expected : ResponseStatus.values()) {
            ResponseStatus actual = ResponseStatus.getByStatus(expected.getType());
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testGetByStatusInvalid() {
        String invalidType = "test";
        ResponseStatus actual = ResponseStatus.getByStatus(invalidType);
        Assert.assertNull(actual);
    }
}