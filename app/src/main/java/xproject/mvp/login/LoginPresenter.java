package xproject.mvp.login;

import android.os.Handler;
import android.os.Looper;

import xproject.mvp.model.UserInfo;

public class LoginPresenter implements LoginContract.Presenter {

    public static final int SLEEP_TIME = 2000;

    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void submitData(UserInfo userInfo) {
        view.showAnimation(true);
        final Handler mainHandler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.submitDone();
                    }
                });
            }
        }.start();

    }

    @Override
    public void start() {

    }
}
