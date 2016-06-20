package ru.android_studio.olga.gibdd_servis;

import org.junit.Test;

/**
 * Тестирование переходов по меню
 */
public class MenuTestUI extends TestUI<MainActivity> {

    /**
     * Начиная с кнопки "Проверка автомобиля"
     * */
    @Test
    public void testMenuCarBtn() {
        menuPage.clickMenuCarBtn();
        menuPage.clickMenuDriverBtn();
        menuPage.clickMenuFineBtn();
        menuPage.clickMenuQuestionBtn();
    }

    /**
     * Начиная с кнопки "Проверка водителя"
     * */
    @Test
    public void testMenuMenuDriverBtn() {
        menuPage.clickMenuDriverBtn();
        menuPage.clickMenuFineBtn();
        menuPage.clickMenuQuestionBtn();
        menuPage.clickMenuCarBtn();
    }

    /**
     * Начиная с кнопки "Проверка штрафов"
     * */
    @Test
    public void testMenuFromMenuFineBtn() {
        menuPage.clickMenuFineBtn();
        menuPage.clickMenuQuestionBtn();
        menuPage.clickMenuCarBtn();
        menuPage.clickMenuDriverBtn();
    }

    /**
     * Начиная с кнопки "Прием обращений"
     * */
    @Test
    public void testMenuFromMenuQuestionBtn() {
        menuPage.clickMenuQuestionBtn();
        menuPage.clickMenuCarBtn();
        menuPage.clickMenuDriverBtn();
        menuPage.clickMenuFineBtn();
    }
}