package com.android.thefloatbuttonscan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dell2 on 2016/8/22.
 */
public class TheScanManager {
    public static Timer timer;
    public boolean isRepeat = false;
    private Context context;
    public static boolean isScan = false; //是否可以开启扫描

    public TheScanManager(Context context) {
        this.context = context;
    }

    public void startScan() {
        if (!isScan) {
            isScan = true;
            timer = new Timer();
            Intent intent = new Intent();
            intent.setAction(ScanAction.START_SCAN_ACTION);
            context.sendBroadcast(intent, null);
            timer.schedule(new MyScanTask(), 1000);
        }

    }
    private  class  MyScanTask extends TimerTask {

        @Override
        public void run() {
            Log.i("111", "~~~");
            isScan = false;
        }
    }

    public void stopScan() {
        Intent intent = new Intent();
        intent.setAction(ScanAction.STOP_SCAN);
        context.sendBroadcast(intent, null);
    }

    public void repeatScan() {
        if (timer == null) {
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(new MyTask(), 100, 4 * 1000);
    }

    public void cancelRepeat() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public class MyTask extends TimerTask {
        @Override
        public void run() {
            //开始扫描前设置这个
            SystemProperties.set("persist.sys.scanstopimme", "false");
            startScan();
        }
    }


    //弹出框，提示未勾选相应选项功能，并跳转至勾选界面
    public void judgePropert() {
        String result = SystemProperties.get("persist.sys.keyreport", "true");
        if ("false".equals(result)) {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.key_test_back_title)
                    .setMessage(R.string.action_dialog_setting_config)
                    .setPositiveButton(
                            R.string.action_dialog_setting_config_sure_go,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    Intent intent = new Intent(
                                            Settings.ACTION_SETTINGS);
                                    Activity ac = (Activity) context;
                                    ac.startActivityForResult(intent, 1);


                                }
                            }).show();

        }
    }
}
