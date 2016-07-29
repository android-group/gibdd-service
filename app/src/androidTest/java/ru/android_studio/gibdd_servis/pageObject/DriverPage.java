package ru.android_studio.gibdd_servis.pageObject;

import android.app.Activity;
import android.support.test.espresso.matcher.ViewMatchers;

import com.squareup.spoon.Spoon;

import ru.android_studio.gibdd_servis.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Главное окно с облаком тегов
 */
public class DriverPage extends RequestPage {

    @Override
    protected int getId() {
        return R.id.driver_activity;
    }

    public DriverPage(Activity activity) {
        super(activity);
    }

    /*
    * Проверка штрафов
    * */
    public void clickMenuFineBtn() {
        Spoon.screenshot(getActivity(), "before_click_menu_fine_btn");

        onView(withId(R.id.menu_fine_btn))
                .perform(actionWithAssertions(click()));

        Spoon.screenshot(getActivity(), "after_click_menu_fine_btn");
    }

    /*
    * Проверка водителя
    * */
    public void clickMenuDriverBtn() {
        Spoon.screenshot(getActivity(), "before_click_menu_driver_btn");

        onView(withId(R.id.menu_driver_btn))
                .perform(actionWithAssertions(click()));

        Spoon.screenshot(getActivity(), "after_click_menu_driver_btn");
    }


    /*
    * Проверка автомобиля
    * */
    public void clickMenuCarBtn() {
        Spoon.screenshot(getActivity(), "before_click_menu_car_btn");

        onView(withId(R.id.menu_car_btn))
                .perform(actionWithAssertions(click()));

        Spoon.screenshot(getActivity(), "after_click_menu_car_btn");
    }

    /*
    * Прием обращений
    * */
    public void clickMenuQuestionBtn() {
        Spoon.screenshot(getActivity(), "before_click_menu_question_btn");

        onView(withId(R.id.menu_question_btn))
                .perform(actionWithAssertions(click()));

        Spoon.screenshot(getActivity(), "after_click_menu_question_btn");
    }
}