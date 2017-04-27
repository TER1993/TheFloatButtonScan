package com.android.thefloatbuttonscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by kaluoke123 on 2016/11/1.
 */

public class MyReceiverOfButtonColor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences share = context.getSharedPreferences("ButtonStatus", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        switch (intent.getAction()) {
            case ScanAction.COLOR_BLACK:
                MyManager.btnNormal = R.drawable.black_normal;
                MyManager.btnPress = R.drawable.black_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.black_normal);
                editor.putInt("btnPress", R.drawable.black_press);
                editor.commit();
                break;
            case ScanAction.COLOR_BLUE:
                MyManager.btnNormal = R.drawable.blue_normal;
                MyManager.btnPress = R.drawable.blue_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.blue_normal);
                editor.putInt("btnPress", R.drawable.blue_press);
                editor.commit();
                break;
            case  ScanAction.COLOR_CYAN:
                MyManager.btnNormal = R.drawable.cyan_narmal;
                MyManager.btnPress = R.drawable.cyan_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.cyan_narmal);
                editor.putInt("btnPress", R.drawable.cyan_press);
                editor.commit();
                break;
            case  ScanAction.COLOR_PINK:
                MyManager.btnNormal = R.drawable.pink_normal;
                MyManager.btnPress = R.drawable.pink_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.pink_normal);
                editor.putInt("btnPress", R.drawable.pink_press);
                editor.commit();
                break;
            case  ScanAction.COLOR_PURPLE:
                MyManager.btnNormal = R.drawable.purple_normal;
                MyManager.btnPress = R.drawable.purple_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.purple_normal);
                editor.putInt("btnPress", R.drawable.purple_press);
                editor.commit();
                break;
            case  ScanAction.COLOR_RED:
                MyManager.btnNormal = R.drawable.red_normal;
                MyManager.btnPress = R.drawable.red_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.red_normal);
                editor.putInt("btnPress", R.drawable.red_press);
                editor.commit();
                break;
            case  ScanAction.COLOR_YELLOW:
                MyManager.btnNormal = R.drawable.yellow_normal;
                MyManager.btnPress = R.drawable.yellow_press;
                MyManager.llayout.setBackgroundResource(MyManager.btnNormal);
                MyManager.llayout.getBackground().setAlpha(170);
                editor.putInt("btnNormal", R.drawable.yellow_normal);
                editor.putInt("btnPress", R.drawable.yellow_press);
                editor.commit();
                break;
        }
    }
}
