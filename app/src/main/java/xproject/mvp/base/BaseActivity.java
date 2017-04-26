package xproject.mvp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        presenter = newPresenter();
        presenter.start();
    }

    protected abstract void initView();

    protected abstract T newPresenter();

}
