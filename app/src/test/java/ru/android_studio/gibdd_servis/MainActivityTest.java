package ru.android_studio.gibdd_servis;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit-tests for {@link MainActivity}
 * <br>Created by Ruslan Suleymanov on 23.06.2016.
 *
 * @author Ruslan Suleymanov
 * @version 0.1
 */
@Ignore
// @TODO need to work
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() {
        activity = new MainActivity();
    }

    @Test
    public void testOnCreate() {
        fail("Not emplemented!");
    }

    @Test
    public void testGetCurrentMenuId() {
        final String methodName = "getCurrentMenuId";
        assertEquals(String.format("%1$s() must return %2$s", methodName, 0), 0, activity.getCurrentMenuId());
    }
}