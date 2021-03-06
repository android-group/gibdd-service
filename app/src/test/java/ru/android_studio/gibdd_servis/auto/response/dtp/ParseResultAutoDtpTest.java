package ru.android_studio.gibdd_servis.auto.response.dtp;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import ru.android_studio.gibdd_servis.auto.model.dtp.Accidents;
import ru.android_studio.gibdd_servis.auto.model.dtp.DamagePoints;
import ru.android_studio.gibdd_servis.auto.model.dtp.ResultAutoDtp;
import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoDtp;

public class ParseResultAutoDtpTest {

    ParseResultAutoDtp parseResultAutoDtp = ParseResultAutoDtp.getInstance();

    @Test
    public void mapSuccessResult_SUCCESS_00000000000000000() {
        ResultAutoDtp expected = new ResultAutoDtp();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("00000000000000000");
        expected.setStatusCode("2");
        expected.setErrorDescription("Найдено большое количество ДТП, требуется уточнить параметры запроса");

        ResultAutoDtp result = new ResultAutoDtp();
        result.setType(ResponseStatus.SUCCESS);
        String resultText = ResponseDataDTP.SUCCESS_00000000000000000;
        parseResultAutoDtp.mapSuccessResult(resultText, result);
        expected.setResponse(resultText);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void mapSuccessResult_SUCCESS_WP1AB29PX4LA69033() {
        ResultAutoDtp expected = new ResultAutoDtp();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("WP1AB29PX4LA69033");
        expected.setStatusCode("1");
        expected.setErrorDescription("");

        ArrayList<Accidents> accidentsArrayList = new ArrayList<>();
        Accidents accidents = new Accidents();
        accidents.setAccidentDateTime("22.03.2015 03:45");
        accidents.setAccidentNumber("460049940");
        accidents.setAccidentType("Наезд на стоящее ТС");
        accidents.setRegionName("Московская область");
        accidents.setVehicleDamageState("Повреждено");
        accidents.setVehicleMark("PORSCHE");
        accidents.setVehicleYear("нет данных");
        accidents.setVehicleModel("Прочие модели Porsche");
        accidents.setDamagePoints(new ArrayList<DamagePoints>());
        accidentsArrayList.add(accidents);
        expected.setAccidents(accidentsArrayList);

        ResultAutoDtp result = new ResultAutoDtp();
        result.setType(ResponseStatus.SUCCESS);
        String resultText = ResponseDataDTP.SUCCESS_WP1AB29PX4LA69033;
        parseResultAutoDtp.mapSuccessResult(resultText, result);
        expected.setResponse(resultText);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void mapFailureResult() {

    }
}