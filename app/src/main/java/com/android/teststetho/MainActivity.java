package com.android.teststetho;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        String resp;
        TextView tv = (TextView) findViewById(R.id.message1);
        try {
            resp = (new Background()).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            resp = e.getMessage();
        }

        assert tv != null;
        tv.setText(resp);

    }
}
