package ru.android_studio.gibdd_servis.auto.response.wanted;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;
import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;
import ru.android_studio.gibdd_servis.auto.model.wanted.Wanted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoWanted;

public class ParseResultAutoWantedTest {

    ParseResultAutoWanted parseResultAutoWanted = ParseResultAutoWanted.getInstance();

    @Test
    public void testParseResult_SUCCESS_11111111111111111() {
        ResultAutoWanted expected = new ResultAutoWanted();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("11111111111111111");
        expected.setCount("3");
        expected.setError("0");

        ArrayList<Wanted> wantedList = new ArrayList<>();

        Wanted wanted = new Wanted();
        wanted.setW_data_pu("02.12.2011");
        wanted.setW_god_vyp("1992");
        wanted.setW_model("ГАЗ66");
        wanted.setW_rec("5");
        wanted.setW_reg_inic("Чеченская Республика");
        wanted.setW_un_gic("1215729");
        wanted.setW_user("9998");
        wanted.setW_vid_uch("Т");

        wantedList.add(wanted);
        expected.setWantedList(wantedList);

        ResultAutoWanted actual = new ResultAutoWanted();
        String resultText = ResponseDataWanted.SUCCESS_11111111111111111;
        parseResultAutoWanted.mapSuccessResult(resultText, actual);
        expected.setResponse(resultText);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseResult_SUCCESS_00000000000000000() {
        ResultAutoWanted expected = new ResultAutoWanted();
        expected.setType(ResponseStatus.SUCCESS);
        expected.setVin("00000000000000000");
        expected.setCount("5");
        expected.setError("0");

        ResultAutoWanted actual = new ResultAutoWanted();
        String resultText = ResponseDataWanted.SUCCESS_00000000000000000;
        parseResultAutoWanted.mapSuccessResult(resultText, actual);
        expected.setResponse(resultText);

        Assert.assertEquals(expected, actual);
    }
}