package ru.android_studio.olga.gibdd_servis;

import org.junit.Test;

/**
 * Created by y.andreev on 20.06.2016.
 */
public class MenuTestUI extends TestUI<MainActivity> {

    /**
     * Тестирование переходов по меню
     * */
    @Test
    public void testMenuBtn() {
        menuPage.clickMenuCarBtn();
        menuPage.clickMenuDriverBtn();
        menuPage.clickMenuFineBtn();
        menuPage.clickMenuQuestionBtn();
    }
}
