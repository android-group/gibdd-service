package ru.android_studio.gibdd_servis;

import android.app.Activity;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.lang.reflect.ParameterizedType;

import ru.android_studio.gibdd_servis.pageObject.MenuPage;

/**
 * Класс предоставляет осномные возможности необходимые для тестирования интерфейса
 */
@RunWith(AndroidJUnit4.class)
@Ignore
public class TestUI<T extends Activity> {

    private static final String TAG = "TestUI";

    public TestUI() {
    }

    @Rule
    public ActivityTestRule<T> activityTestRule = new ActivityTestRule<>(clazz());

    @SuppressWarnings("unchecked")
    private Class<T> clazz() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Activity activity;

    @Before
    public void setUp() {
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
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        //wakeUpDevice();
        activity = activityTestRule.getActivity();

        // init Page objects
        menuPage = new MenuPage(activity);
    }

    protected MenuPage menuPage;
}
