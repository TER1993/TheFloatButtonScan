package com.android.thefloatbuttonscan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

public class MainActivity extends Activity {
    MyManager myManager;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myManager = new MyManager(this);
//        myManager.initialization();

        if (!checkPermission()) {
            requestPermission();
        }


        this.finish();
    }


    /**
     * 当目标版本大于23时候，检查权限
     */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(this);
        } else {
            return true;
        }
    }

    /**
     * 申请权限的状态code
     */
    int request_code = 1;

    /**
     * 开启权限管理界面，授权。
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"));
        startActivityForResult(intent, request_code);
    }

    /**
     * 回调申请结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                //用户授权成功
                if (checkPermission()) {
                    //用户拒绝授权
                } else {
                    Toast.makeText(this, "弹窗权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
                default:
                    break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
