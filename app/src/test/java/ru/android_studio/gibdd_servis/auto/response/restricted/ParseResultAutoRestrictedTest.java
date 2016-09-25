package ru.android_studio.gibdd_servis.auto.response.restricted;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.model.restricted.Ogrkod;
import ru.android_studio.gibdd_servis.auto.model.restricted.Organs;
import ru.android_studio.gibdd_servis.auto.model.restricted.RestrictedItem;
import ru.android_studio.gibdd_servis.auto.model.restricted.ResultAutoRestricted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoRestricted;

public class ParseResultAutoRestrictedTest {

    ParseResultAutoRestricted parser = ParseResultAutoRestricted.getInstance();

    @Test
    public void testParseResult_SUCCESS() {
        ResultAutoRestricted expected = new ResultAutoRestricted();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("00000000000000000");
        expected.setCount("16");
        expected.setError("0");

        RestrictedItem item = new RestrictedItem();
        item.setDateadd("19.06.2015 0:00:00");
        item.setDateogr("19.06.2015");

        item.setDivid("3021");
        item.setDivtype(Organs.a2);
        item.setGid("36#SР0071613");

        item.setRegid("1120");
        item.setRegname("Воронежская область");

        item.setOgrkod(Ogrkod.a1);

        item.setTsyear("1994");
        item.setTsmodel("ГАЗ 330210");

        List<RestrictedItem> list = new ArrayList<>();
        list.add(item);
        expected.setRestrictedItem(list);

        ResultAutoRestricted result = new ResultAutoRestricted();
        String responseSuccess = ResponseDataRestricted.SUCCESS;
        parser.mapSuccessResult(responseSuccess, result);
        expected.setResponse(responseSuccess);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseResult_SUCCESS_2() {
        ResultAutoRestricted expected = new ResultAutoRestricted();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("00000000000000000");
        expected.setCount("16");
        expected.setError("0");

        List<RestrictedItem> list = new ArrayList<>();
        RestrictedItem item = new RestrictedItem();
        item.setDateadd("19.06.2015 0:00:00");
        item.setDateogr("19.06.2015");

        item.setDivid("3021");
        item.setDivtype(Organs.a2);
        item.setGid("36#SР0071613");

        item.setOgrkod(Ogrkod.a1);
        item.setRegid("1120");
        item.setRegname("Воронежская область");

        item.setTsmodel("ГАЗ 330210");
        item.setTsyear("1994");
        list.add(item);

        RestrictedItem item2 = new RestrictedItem();
        item2.setDateadd("26.08.2015 0:00:00");
        item2.setDateogr("26.08.2015");

        item2.setDivid("1151001");
        item2.setDivtype(Organs.a6);
        item2.setGid("83#С00001654");

        item2.setOgrkod(Ogrkod.a4);
        item2.setRegid("1151");
        item2.setRegname("Ненецкий автономный округ");

        item2.setTsmodel("ПВ-204");
        item2.setTsyear("1988");
        list.add(item2);

        RestrictedItem item3 = new RestrictedItem();
        item3.setDateadd("21.05.2016 0:00:00");
        item3.setDateogr("21.05.2016");

        item3.setDivid("1103412");
        item3.setDivtype(Organs.a2);
        item3.setGid("23#FF000786");

        item3.setOgrkod(Ogrkod.a1);
        item3.setRegid("1103");
        item3.setRegname("Краснодарский край");

        item3.setTsmodel("ИЖ ЮПИТЕР");
        item3.setTsyear("1990");
        list.add(item3);

        RestrictedItem item4 = new RestrictedItem();
        item4.setDateadd("14.07.2016 0:00:00");
        item4.setDateogr("14.07.2016");

        item4.setDivid("1103412");
        item4.setDivtype(Organs.a2);
        item4.setGid("23#FF002260");

        item4.setOgrkod(Ogrkod.a1);
        item4.setRegid("1103");
        item4.setRegname("Краснодарский край");

        item4.setTsmodel("ЗИЛ БЕЗ МОДЕЛИ");
        item4.setTsyear("1984");
        list.add(item4);

        expected.setRestrictedItem(list);

        ResultAutoRestricted result = new ResultAutoRestricted();
        String responseSuccess = ResponseDataRestricted.SUCCESS_2;
        parser.mapSuccessResult(responseSuccess, result);
        expected.setResponse(responseSuccess);
        Assert.assertEquals(expected, result);
    }
}