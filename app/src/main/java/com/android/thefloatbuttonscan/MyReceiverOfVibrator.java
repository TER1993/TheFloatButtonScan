package com.android.thefloatbuttonscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by kaluoke123 on 2016/11/4.
 */

public class MyReceiverOfVibrator extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences share = context.getSharedPreferences("ButtonStatus", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        switch (intent.getAction()) {
            case ScanAction.VIBRATOR_OPEN:
                MyManager.ifVibrator = true;
                editor.putBoolean("ifVibrator", true);
                editor.commit();
                break;
            case ScanAction.VIBRATOR_CLOSE:
                MyManager.ifVibrator = false;
                editor.putBoolean("ifVibrator", false);
                editor.commit();
                break;
        }
    }
}
