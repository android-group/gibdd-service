package ru.android_studio.gibdd_servis;

import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.activity.AutoType;

/**
 * Created by yuryandreev on 20/09/16.
 */
public class AutoTypeTest {

    @Test
    public void testGetByTypeNumberString() {
        AutoType expected = AutoType.a01;
        AutoType actual = AutoType.getByTypeNumberString("01");
        Assert.assertEquals(expected, actual);
    }

}