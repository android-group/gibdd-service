package ru.android_studio.gibdd_servis;


import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.activity.ResponseType;

/**
 * Created by yuryandreev on 20/09/16.
 */
public class ResponseTypeTest {

    @Test
    public void testGetByStatus() {
        Assert.assertEquals(ResponseType.getByStatus("200"), ResponseType.SUCCESS);
    }
}