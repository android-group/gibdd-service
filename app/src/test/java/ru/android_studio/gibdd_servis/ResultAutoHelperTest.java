package ru.android_studio.gibdd_servis;

import junit.framework.Assert;

import org.junit.Test;

import ru.android_studio.gibdd_servis.auto.activity.AutoType;
import ru.android_studio.gibdd_servis.auto.activity.ResponseType;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoHelper;
import ru.android_studio.gibdd_servis.auto.activity.ResultAutoObject;
import ru.android_studio.gibdd_servis.auto.activity.Vehicle;

/**
 * Created by yuryandreev on 19/09/16.
 */
public class ResultAutoHelperTest {

    @Test
    public void testParseResultIsNull() {
        String data = "";
        ResultAutoObject result = ResultAutoHelper.parseResult(data);
        Assert.assertTrue(result == null);
    }

    @Test
    public void testCaptchaNumberIsNotValid() {
        ResultAutoObject result = ResultAutoHelper.parseResult(GibddResponseData.CAPTCHA_NUMBER_IS_NOT_VALID);
        Assert.assertNotNull(result);

        ResultAutoObject expected = new ResultAutoObject();
        expected.setResponse(GibddResponseData.CAPTCHA_NUMBER_IS_NOT_VALID);
        expected.setType(ResponseType.CAPTCHA_NUMBER_IS_NOT_VALID);
        expected.setMessage("Цифры с картинки введены неверно");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseResultIsSuccessMapping() {
        ResultAutoObject result = ResultAutoHelper.parseResult(GibddResponseData.SUCCESS_DATA);
        Assert.assertNotNull(result);

        ResultAutoObject expected = new ResultAutoObject();
        expected.setResponse(GibddResponseData.SUCCESS_DATA);
        expected.setType(ResponseType.SUCCESS);

        Vehicle vehicle = new Vehicle();
        vehicle.setColor("ЗЕЛЕНЫЙ");
        vehicle.setBodyNumber("00000000000000000");
        vehicle.setYear("1983");
        vehicle.setModel("СЗАП 8352");
        vehicle.setVin("00000000000000000");
        vehicle.setCategory("Е");
        vehicle.setType(AutoType.a82);
        vehicle.setPowerKwt("0");

        expected.setVehicle(vehicle);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseResultIsSuccessMapping2() {
        ResultAutoObject result = ResultAutoHelper.parseResult(GibddResponseData.SUCCESS_DATA_2);
        Assert.assertNotNull(result);

        ResultAutoObject expected = new ResultAutoObject();
        expected.setResponse(GibddResponseData.SUCCESS_DATA_2);
        expected.setType(ResponseType.SUCCESS);

        Vehicle vehicle = new Vehicle();
        vehicle.setColor("СЕРЫЙ");
        vehicle.setBodyNumber("WР1АВ29РХ4LА69033");
        vehicle.setYear("2003");
        vehicle.setModel("ПОРШЕ КАЙЕНН S");
        vehicle.setVin("WР1АВ29РХ4LА69033");
        vehicle.setCategory("В");
        vehicle.setType(AutoType.a21);
        vehicle.setPowerKwt("250");
        vehicle.setPowerHp("340");

        expected.setVehicle(vehicle);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testNoDataFound() {
        ResultAutoObject result = ResultAutoHelper.parseResult(GibddResponseData.NO_DATA_FOUND);
        Assert.assertNotNull(result);

        ResultAutoObject expected = new ResultAutoObject();
        expected.setResponse(GibddResponseData.NO_DATA_FOUND);
        expected.setType(ResponseType.NO_DATA_FOUND);
        expected.setMessage("404:No data found");

        Assert.assertEquals(expected, result);
    }
}