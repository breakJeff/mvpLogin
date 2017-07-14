package xproject.mvp.login;

import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import xproject.mvp.base.BaseActivity;
import xproject.mvp.model.UserInfo;
import xproject.mvplogin.BuildConfig;
import xproject.mvplogin.R;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    private ProgressBar progressBar;
    private Button submitBtn;
    private List<EditText> editList;
    private EditText ageEditText;
    private EditText genderEditText;
    private EditText nameEditText;
    private EditText hobbyEditText;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.submit_pro);
        submitBtn = (Button) findViewById(R.id.submit);
        ageEditText = (EditText) findViewById(R.id.age);
        genderEditText = (EditText) findViewById(R.id.gender);
        nameEditText = (EditText) findViewById(R.id.name);
        hobbyEditText = (EditText) findViewById(R.id.hobby);
        editList = new ArrayList<>();
        editList.add(ageEditText);
        editList.add(genderEditText);
        editList.add(nameEditText);
        editList.add(hobbyEditText);

        submitBtn.setOnClickListener(this);
        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectCustomSlowCalls()
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()
//                    .penaltyDialog()
//                    .penaltyLog()
//                    .penaltyFlashScreen()
//                    .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectLeakedSqlLiteObjects()
//                    .detectLeakedClosableObjects()
//                    .detectActivityLeaks()
//                    .penaltyLog()
//                    .penaltyDeath()
//                    .build());
        }
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                }
            }
        }.start();
        writeToExternalStorage();
    }

    public void writeToExternalStorage() {
        File externalStorage = Environment.getExternalStorageDirectory();
        File destFile = new File(externalStorage, "dest.txt");
        try {
            OutputStream output = new FileOutputStream(destFile, true);
            output.write("coderunity.com".getBytes());
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                UserInfo userInfo = new UserInfo();
                userInfo.address = getText(ageEditText);
                userInfo.gender = getText(genderEditText);
                userInfo.hobby =  getText(hobbyEditText);
                userInfo.name = getText(nameEditText);
                presenter.submitData(userInfo);
                break;
        }
    }

    private String getText(TextView textView) {
        return textView == null ? "" : textView.getText().toString();
    }

    @Override
    public void showAnimation(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void submitDone() {
        Snackbar.make(submitBtn, "submit done", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        showAnimation(false);
        if (editList == null) return;

        for (EditText editText : editList) {
            editText.setText("");
        }
    }

    @Override
    public LoginPresenter newPresenter() {
        return new LoginPresenter(this);
    }
}