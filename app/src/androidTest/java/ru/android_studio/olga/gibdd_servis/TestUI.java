package ru.android_studio.olga.gibdd_servis;

import android.app.Activity;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.lang.reflect.ParameterizedType;
import ru.android_studio.olga.gibdd_servis.pageObject.MenuPage;

/**
 * Класс предоставляет осномные возможности необходимые для тестирования интерфейса
 */
@RunWith(AndroidJUnit4.class)
public class TestUI<T extends Activity> {

    @Rule
    public ActivityTestRule<T> activityTestRule = new ActivityTestRule<T>(clazz());

    private Class<T> clazz() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Activity activity;

    @Before
    public void setUp() {
        System.out.println("Before");
        wakeUpDevice();
        load();
    }

    protected MenuPage menuPage;

    /*
    * Оживляем телефон прежде чем запустить тест
    * */
    private void load() {
        activity = activityTestRule.getActivity();

        // init Page objects
        menuPage = new MenuPage(activity);

    }

    private void wakeUpDevice() {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Point[] coordinates = new Point[4];
        coordinates[0] = new Point(248, 1520);
        coordinates[1] = new Point(248, 929);
        coordinates[2] = new Point(796, 1520);
        coordinates[3] = new Point(796, 929);
        try {
            if (!uiDevice.isScreenOn()) {
                uiDevice.wakeUp();
                uiDevice.swipe(coordinates, 10);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
