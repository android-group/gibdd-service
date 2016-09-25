package ru.android_studio.gibdd_servis;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import butterknife.BindView;

/**
 * Created by y.andreev on 03.06.2016.
 * <p/>
 * Активити с Нижним меню
 */
public abstract class ActivityWithMenu extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @IdRes
    protected abstract int getCurrentMenuId();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * Добавить тулбар на страницу
     *
     * @param iconId - id картинки for example R.mipmap.auto_logo
     */
    protected void addToolbarByIconId(int iconId) {
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setLogo(iconId);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
    }
}
