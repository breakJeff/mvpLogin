package xproject.mvp.login;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import xproject.mvplogin.R;

public class StrictModeActivity extends BaseActivity {

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.strict_mode_main);
        setActionBar();
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

}
