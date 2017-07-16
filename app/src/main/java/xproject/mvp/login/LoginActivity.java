package xproject.mvp.login;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xproject.mvp.base.BaseActivity;
import xproject.mvplogin.R;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int actionbarHeight = getSupportActionBar().getHeight();
            toolbar.getLayoutParams().height = actionbarHeight;
            toolbar.setPadding(toolbar.getPaddingLeft(), actionbarHeight, toolbar.getPaddingRight(), toolbar.getPaddingBottom());
        }
        Button strictModeButton = (Button) findViewById(R.id.strict_mode_forward);
        Button leakageButton = (Button) findViewById(R.id.leakage_forward);
        Button picassoButton = (Button) findViewById(R.id.picasso_forward);
        strictModeButton.setOnClickListener(this);
        leakageButton.setOnClickListener(this);
        picassoButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.strict_mode_forward: {
                Intent intent = new Intent(this, StrictModeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.leakage_forward: {
                Intent intent = new Intent(this, LeakageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.picasso_forward: {
                Intent intent = new Intent(this, PicassoActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private String getText(TextView textView) {
        return textView == null ? "" : textView.getText().toString();
    }

    @Override
    public void showAnimation(boolean visible) {
//        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void submitDone() {
//        Snackbar.make(submitBtn, "submit done", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//        showAnimation(false);
//        if (editList == null) return;
//
//        for (EditText editText : editList) {
//            editText.setText("");
//        }
    }

    @Override
    public LoginPresenter newPresenter() {
        return new LoginPresenter(this);
    }
}