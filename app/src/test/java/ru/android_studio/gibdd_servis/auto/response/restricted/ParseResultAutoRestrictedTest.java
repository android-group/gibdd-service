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
    public void testParseResult_SUCCESS_00000000000000000_SMALL() {
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
        String responseSuccess = ResponseDataRestricted.SUCCESS_00000000000000000_SMALL;
        parser.mapSuccessResult(responseSuccess, result);
        expected.setResponse(responseSuccess);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseResult_SUCCESS_00000000000000000() {
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
        String responseSuccess = ResponseDataRestricted.SUCCESS_00000000000000000;
        parser.mapSuccessResult(responseSuccess, result);
        expected.setResponse(responseSuccess);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseResult_SUCCESS_11111111111111111() {
        ResultAutoRestricted expected = new ResultAutoRestricted();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("11111111111111111");
        expected.setCount("7");
        expected.setError("0");

        List<RestrictedItem> list = new ArrayList<>();
        RestrictedItem item = new RestrictedItem();
        item.setDateadd("05.03.2010 0:00:00");
        item.setDateogr("05.03.2010");

        item.setDivid("5001");
        item.setDivtype(Organs.a6);
        item.setGid("34#500101972");

        item.setOgrkod(Ogrkod.a5);
        item.setRegid("1118");
        item.setRegname("Волгоградская область");

        item.setTsmodel("НИССАНХТRАLL");
        item.setTsyear("2001");
        list.add(item);

        RestrictedItem item2 = new RestrictedItem();
        item2.setDateadd("03.03.2010 0:00:00");
        item2.setDateogr("03.03.2010");

        item2.setDivid("5001");
        item2.setDivtype(Organs.a6);
        item2.setGid("34#500101964");

        item2.setOgrkod(Ogrkod.a5);
        item2.setRegid("1118");
        item2.setRegname("Волгоградская область");

        item2.setTsmodel("ТОУОТА LАND СRUISЕR 200");
        item2.setTsyear("2001");
        list.add(item2);

        RestrictedItem item3 = new RestrictedItem();
        item3.setDateadd("09.03.2010 0:00:00");
        item3.setDateogr("09.03.2010");

        item3.setDivid("5001");
        item3.setDivtype(Organs.a6);
        item3.setGid("34#500101981");

        item3.setOgrkod(Ogrkod.a5);
        item3.setRegid("1118");
        item3.setRegname("Волгоградская область");

        item3.setTsmodel("ФОЛЬКСВАГЕН ПАССАТ");
        item3.setTsyear("1992");
        list.add(item3);

        RestrictedItem item4 = new RestrictedItem();
        item4.setDateadd("09.03.2010 0:00:00");
        item4.setDateogr("09.03.2010");

        item4.setDivid("5001");
        item4.setDivtype(Organs.a6);
        item4.setGid("34#500101982");

        item4.setOgrkod(Ogrkod.a5);
        item4.setRegid("1118");
        item4.setRegname("Волгоградская область");

        item4.setTsmodel("МАЗДА СХ7");
        item4.setTsyear("2007");
        list.add(item4);

        RestrictedItem item5 = new RestrictedItem();
        item5.setDateadd("15.06.2016 0:00:00");
        item5.setDateogr("15.06.2016");

        item5.setDivid("1140012");
        item5.setDivtype(Organs.a2);
        item5.setGid("78#FF007719");

        item5.setOgrkod(Ogrkod.a1);
        item5.setRegid("1140");
        item5.setRegname("город Санкт-Петербург");

        item5.setTsmodel("МАЗ 5205");
        item5.setTsyear("1981");
        list.add(item5);

        RestrictedItem item6 = new RestrictedItem();
        item6.setDateadd("14.07.2016 0:00:00");
        item6.setDateogr("14.07.2016");

        item6.setDivid("1157018");
        item6.setDivtype(Organs.a7);
        item6.setGid("59#FF000924");

        item6.setOgrkod(Ogrkod.a1);
        item6.setRegid("1157");
        item6.setRegname("Пермский край");

        item6.setTsmodel("ГАЗ 66");
        item6.setTsyear("1970");
        list.add(item6);

        RestrictedItem item7 = new RestrictedItem();
        item7.setDateadd("28.04.2011 0:00:00");
        item7.setDateogr("28.04.2011");

        item7.setDivid("1120101");
        item7.setDivtype(Organs.a6);
        item7.setGid("36@101007288");

        item7.setOgrkod(Ogrkod.a1);
        item7.setRegid("1120");
        item7.setRegname("Воронежская область");

        item7.setTsmodel("ВАЗ 2110");
        item7.setTsyear(null);
        list.add(item7);

        expected.setRestrictedItem(list);

        ResultAutoRestricted result = new ResultAutoRestricted();
        String responseSuccess = ResponseDataRestricted.SUCCESS_11111111111111111;
        parser.mapSuccessResult(responseSuccess, result);
        expected.setResponse(responseSuccess);

        Assert.assertEquals(expected, result);
    }
}