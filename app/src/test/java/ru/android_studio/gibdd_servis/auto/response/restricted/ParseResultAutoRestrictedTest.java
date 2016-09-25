package ru.android_studio.gibdd_servis.auto.response.restricted;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.model.restricted.ResultAutoRestricted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoRestricted;

public class ParseResultAutoRestrictedTest {

    ParseResultAutoRestricted parser = ParseResultAutoRestricted.getInstance();

    @Test
    @Ignore
    public void testParseResultSuccess() {
        ResultAutoRestricted expected = new ResultAutoRestricted();
        expected.setType(ResponseStatus.SUCCESS);

        ResultAutoRestricted result = new ResultAutoRestricted();
        parser.mapSuccessResult(ResponseDataRestricted.SUCCESS, result);
        Assert.assertEquals(expected, result);
    }
}