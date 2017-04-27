package com.android.thefloatbuttonscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by xu on 2017/4/18.
 */

public class MyReceiveOfMode extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences share = context.getSharedPreferences("ButtonStatus", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        switch (intent.getAction()) {
            case ScanAction.MODE_ONE:
                //MyManager.ifVibrator = true;
                editor.putInt("modenumber", 1);
                editor.commit();
                break;
            case ScanAction.MODE_TWO:
                //MyManager.ifVibrator = false;
                editor.putInt("modenumber", 2);
                editor.commit();
                break;
            case ScanAction.MODE_THREE:
                //MyManager.ifVibrator = false;
                editor.putInt("modenumber", 3);
                editor.commit();
                break;
        }
    }
}
