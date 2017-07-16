package xproject.mvp.login;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.widget.Toolbar;

import xproject.mvp.base.BasePresenter;
import xproject.mvplogin.BuildConfig;
import xproject.mvplogin.R;

public abstract class BaseActivity extends xproject.mvp.base.BaseActivity {

    @Override
    protected boolean presenterEnabled() {
        return false;
    }

    protected void setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDialog()
                    .penaltyLog()
                    .penaltyFlashScreen()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }

    @Override
    protected void initView() {
        setupStrictMode();
    }

    protected void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int actionbarHeight = getSupportActionBar().getHeight();
            toolbar.getLayoutParams().height = actionbarHeight;
            toolbar.setPadding(toolbar.getPaddingLeft(), actionbarHeight, toolbar.getPaddingRight(), toolbar.getPaddingBottom());
        }
    }

    @Override
    protected BasePresenter newPresenter() {
        return null;
    }
}
