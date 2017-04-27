package com.android.thefloatbuttonscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dell2 on 2016/10/24.
 */

public class MyReceiverOfScanComplete extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TheScanManager.isScan = false;
        if (TheScanManager.timer != null) {
            TheScanManager.timer.cancel();
        }


    }
}
