package com.android.thefloatbuttonscan;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;


public class MyServiceOfloatButton extends Service {
    private MyManager myManager;

    public MyServiceOfloatButton() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myManager = new MyManager(this);

        myManager.initialization();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myManager.reMoveButton();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (myManager.sign)
            myManager.changePosition(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
