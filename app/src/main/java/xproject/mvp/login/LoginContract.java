package xproject.mvp.login;

import xproject.mvp.base.BasePresenter;
import xproject.mvp.base.BaseView;
import xproject.mvp.model.UserInfo;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showAnimation(boolean visible);
        void submitDone();
    }

    interface Presenter extends BasePresenter {
        void submitData(UserInfo userInfo);
    }
}
