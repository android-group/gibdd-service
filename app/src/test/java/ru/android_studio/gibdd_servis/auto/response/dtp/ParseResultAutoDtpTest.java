package ru.android_studio.gibdd_servis.auto.response.dtp;

import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.model.dtp.ResultAutoDTP;
import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoDTP;

public class ParseResultAutoDtpTest {

    ParseResultAutoDTP parseResultAutoDTP = ParseResultAutoDTP.getInstance();

    @Test
    public void mapSuccessResult() {
        ResultAutoDTP expected = new ResultAutoDTP();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("00000000000000000");
        expected.setStatusCode("2");
        expected.setErrorDescription("Найдено большое количество ДТП, требуется уточнить параметры запроса");

        ResultAutoDTP result = new ResultAutoDTP();
        result.setType(ResponseStatus.SUCCESS);
        String resultText = ResponseDataDTP.SUCCESS;
        parseResultAutoDTP.mapSuccessResult(resultText, result);
        expected.setResponse(resultText);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void mapFailureResult() {

    }
}