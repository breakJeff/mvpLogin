package xproject.mvp.login;

import android.os.SystemClock;

import xproject.mvplogin.R;

public class LeakageActivity extends BaseActivity {

    @Override
    protected void initView() {
//        super.initView();
        setContentView(R.layout.leakage_main);
        setActionBar();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }
}
