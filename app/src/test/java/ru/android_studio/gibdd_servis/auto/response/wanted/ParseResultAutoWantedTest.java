package ru.android_studio.gibdd_servis.auto.response.wanted;

import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoWanted;

public class ParseResultAutoWantedTest {

    ParseResultAutoWanted parseResultAutoWanted = ParseResultAutoWanted.getInstance();
    @Test
    public void testParseResultIsNull() {
        ResultAutoWanted expected = new ResultAutoWanted();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("00000000000000000");
        expected.setCount("5");
        expected.setError("0");

        ResultAutoWanted actual = new ResultAutoWanted();
        String resultText = ResponseDataWanted.SUCCESS;
        parseResultAutoWanted.mapSuccessResult(resultText, actual);
        expected.setResponse(resultText);

        Assert.assertEquals(expected, actual);
    }
}