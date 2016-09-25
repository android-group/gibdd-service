package ru.android_studio.gibdd_servis.auto.response.wanted;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoWanted;

public class ParseResultAutoWantedTest {

    @Test
    public void testParseResultIsNull() {
        ResultAutoWanted result = ParseResultAutoWanted.parseResult(ResponseDataWanted.EMPTY);
    }
}