package com.vivianaranha.applicationtwo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText messageFromApp1;

    String packageOne = "com.vivianaranha.applicationone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageFromApp1 = (EditText) findViewById(R.id.editText);
    }

    public void loadFile(View v) {
        PackageManager packageManager = getPackageManager();

        FileInputStream fileInputStream = null;

        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageOne, PackageManager.GET_META_DATA);

            String fullPath = applicationInfo.dataDir + "/files/myFile.txt";

            fileInputStream = new FileInputStream(new File(fullPath));
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while((read = fileInputStream.read()) != -1){
                stringBuffer.append((char) read);
            }

            messageFromApp1.setText(stringBuffer.toString());


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
