package com.android.thefloatbuttonscan;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    MyManager myManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myManager = new MyManager(this);
//        myManager.initialization();

        this.finish();
    }
}
