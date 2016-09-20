package ru.android_studio.gibdd_servis.auto.activity;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yuryandreev on 20/09/16.
 */
public class ResponseTypeTest {

    @Test
    public void testGetByStatus() {
        Assert.assertEquals(ResponseType.getByStatus("200"), ResponseType.SUCCESS);
    }
}