package com.android.thefloatbuttonscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiverOfStartService extends BroadcastReceiver {
    public MyReceiverOfStartService() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Intent myIntent = new Intent(context, MyServiceOfloatButton.class);
        context.startService(myIntent);
    }
}
