package ru.android_studio.olga.gibdd_servis;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ru.android_studio.olga.gibdd_servis.pageObject.MenuPage;

/**
 * Тестирование переходов по меню
 */
public class MenuTestUI extends TestUI<MainActivity> {

    public MenuTestUI() {
    }

    @Rule
    public ActivityTestRule<CarActivity> carActivityTestRule = new ActivityTestRule<>(CarActivity.class);

    /**
     * Начиная с кнопки "Проверка автомобиля"
     * */
    @Test
    public void testMenuCarBtn() {
        menuPage.clickMenuCarBtn();
        new MenuPage(carActivityTestRule.getActivity()).clickMenuDriverBtn();
        //menuPage.clickMenuDriverBtn();
        //menuPage.clickMenuFineBtn();
        //menuPage.clickMenuQuestionBtn();
    }

    /*

    *//**
     * Начиная с кнопки "Проверка водителя"
     * *//*
    @Test
    public void testMenuMenuDriverBtn() {
        menuPage.clickMenuDriverBtn();
        menuPage.clickMenuFineBtn();
        menuPage.clickMenuQuestionBtn();
        menuPage.clickMenuCarBtn();
    }

    *//**
     * Начиная с кнопки "Проверка штрафов"
     * *//*
    @Test
    public void testMenuFromMenuFineBtn() {
        menuPage.clickMenuFineBtn();
        menuPage.clickMenuQuestionBtn();
        menuPage.clickMenuCarBtn();
        menuPage.clickMenuDriverBtn();
    }

    *//**
     * Начиная с кнопки "Прием обращений"
     * *//*
    @Test
    public void testMenuFromMenuQuestionBtn() {
        menuPage.clickMenuQuestionBtn();
        menuPage.clickMenuCarBtn();
        menuPage.clickMenuDriverBtn();
        menuPage.clickMenuFineBtn();
    }*/
}