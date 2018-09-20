package com.android.thefloatbuttonscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.SystemProperties;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by dell2 on 2016/8/17.
 */
public class MyManager {
    private WindowManager mWindowManager;
    public WindowManager.LayoutParams mLayout;
    //悬浮窗口的布局
    public DesktopLayout mDesktopLayout;
    //扫描控制类
    public TheScanManager theScanManager;
    public boolean sign = false;
    // 声明屏幕的宽高
    private float x, y;
    private Context context;
    private long startTime;
    private boolean tag = false;
    private static boolean ifopen = false;
    public static boolean ifVibrator = false;
    private MyFloatButtonReceiver myFloatButtonReceiver;
    public static int btnNormal = R.drawable.red_normal;
    public static int btnPress = R.drawable.red_press;
    public static LinearLayout llayout;

    private int startX;
    private int startY;

    public MyManager(Context context) {
        this.context = context;
        theScanManager = new TheScanManager(context);
    }

    //初始化按钮及功能
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initialization() {


        initStatus();
        createWindowManager();
        createDesktopLayout();
        if (ifopen) {
            mWindowManager.addView(mDesktopLayout, mLayout);
        }
        registerMyreceiver();

    }




    /**
     * 关闭DesktopLayout
     */

    public void closeDesk() {
        if (ifopen) {
            mWindowManager.removeView(mDesktopLayout);
            SharedPreferences share = context.getSharedPreferences("ButtonStatus", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putBoolean("ifopen", false);
            editor.commit();
            ifopen = false;
        }

    }

    /**
     * 显示DesktopLayout
     */
    public void showDesk() {
        if (!ifopen) {
            mWindowManager.addView(mDesktopLayout, mLayout);
            SharedPreferences share = context.getSharedPreferences("ButtonStatus", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            editor.putBoolean("ifopen", true);
            editor.commit();
            ifopen = true;
        }

    }

    private void initStatus() {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("ButtonStatus", Context.MODE_PRIVATE);
        ifopen = mSharedPreferences.getBoolean("ifopen", false);
        ifVibrator = mSharedPreferences.getBoolean("ifVibrator", false);
        btnNormal = mSharedPreferences.getInt("btnNormal", R.drawable.red_normal);
        btnPress = mSharedPreferences.getInt("btnPress", R.drawable.red_press);

    }

    public void reMoveButton() {
        if (mDesktopLayout != null) {
            mWindowManager.removeView(mDesktopLayout);
        }
    }

    /*
     * 注册广播
     * */
    public void registerMyreceiver() {
        myFloatButtonReceiver = new MyFloatButtonReceiver();
        context.registerReceiver(myFloatButtonReceiver, new IntentFilter(ScanAction.OPEN_FLOAT_WINDOW));
        context.registerReceiver(myFloatButtonReceiver, new IntentFilter(ScanAction.CLOSE_FLOAT_WINDOW));
        IntentFilter intentFilter = new IntentFilter(ScanAction.RECE_DATA_ACTION);
        intentFilter.setPriority(40000); //优先级设置为比系统优先级高，为了能比系统提前收到完成广播，否则系统会停止继续发送广播
        context.registerReceiver(myFloatButtonReceiver, intentFilter);
    }

    /*
     * 注销广播
     * */
    public void unregisterMyReceiver() {
        Intent intent = new Intent();
        intent.setAction("com.geomobile.se4500barcode.poweroff");
        context.sendBroadcast(intent);
        context.unregisterReceiver(myFloatButtonReceiver);
    }

    /*
     * 内部广播类
     * */
    public class MyFloatButtonReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ScanAction.OPEN_FLOAT_WINDOW)) {
                showDesk();
                sign = true;
            }
            if (action.equals(ScanAction.CLOSE_FLOAT_WINDOW)) {
                closeDesk();
                sign = false;
            }
        }
    }

    private void createWindowManager() {
        // 取得系统窗体
        mWindowManager = (WindowManager) context.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);

        // 窗体的布局样式
        mLayout = new WindowManager.LayoutParams();
        mLayout.x = 750;
        mLayout.y = 700;
        // 设置窗体显示类型——TYPE_SYSTEM_ALERT(系统提示)
        mLayout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

        // 设置窗体焦点及触摸：
        // FLAG_NOT_FOCUSABLE(不能获得按键输入焦点)
        mLayout.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // 设置显示的模式
        mLayout.format = PixelFormat.RGBA_8888;

        // 设置对齐的方法
        mLayout.gravity = Gravity.TOP | Gravity.LEFT;

        // 设置窗体宽度和高度
        mLayout.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayout.height = WindowManager.LayoutParams.WRAP_CONTENT;


    }

    public void changePosition(Context context) {
        if (getScreenHeight(context) > getScreenWidth(context)) {
            mLayout.x = 750;
            mLayout.y = 700;
        } else {
            mLayout.x = 1500;
            mLayout.y = 260;
        }
        mWindowManager.updateViewLayout(mDesktopLayout, mLayout);

    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    /*
     * 获得系统状态栏高度*/
    private int getStatusBarHeight() {
        Class<?> c = null;

        Object obj = null;

        Field field = null;

        int x = 0, sbar = 0;

        try {

            c = Class.forName("com.android.internal.R$dimen");

            obj = c.newInstance();

            field = c.getField("status_bar_height");

            x = Integer.parseInt(field.get(obj).toString());

            sbar = context.getResources().getDimensionPixelSize(x);


        } catch (Exception e1) {

            e1.printStackTrace();


        }

        return sbar;
    }


    public void createDesktopLayout() {
        mDesktopLayout = new DesktopLayout(context);
        llayout = (LinearLayout) mDesktopLayout.findViewById(R.id.mylinnerlayout);
        llayout.getBackground().setAlpha(170);
        mDesktopLayout.setOnTouchListener(new View.OnTouchListener() {
            float mTouchStartX;
            float mTouchStartY;


            @Override
            public boolean onTouch(View v, MotionEvent event) {


                // 获取相对屏幕的坐标，即以屏幕左上角为原点
                x = event.getRawX();
                y = event.getRawY(); // 25是系统状态栏的高度
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();

                        startTime = System.currentTimeMillis();

                        if (ifVibrator) {
                            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            long[] pattern = {50, 50}; // 停止 开启 停止 开启
                            vibrator.vibrate(pattern, -1);
                        }
                        llayout.setBackgroundResource(btnPress);
                        llayout.getBackground().setAlpha(170);
                        // 获取相对View的坐标，即以此View左上角为原点
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        long duingtime = System.currentTimeMillis() - startTime;
                        if (duingtime > 500) {
                            if (!tag) {
                                //停止扫描前设置这个
                                SystemProperties.set("persist.sys.scanstopimme", "true");
                                theScanManager.stopScan();
                                tag = true;
                            }
                            llayout.setBackgroundResource(btnNormal);
                            llayout.getBackground().setAlpha(100);
                            // 更新浮动窗口位置参数
                            int statusBarHeight = getStatusBarHeight();
                            mLayout.x = (int) (x - mTouchStartX);
                            mLayout.y = (int) (y - mTouchStartY) - statusBarHeight;
                            mWindowManager.updateViewLayout(v, mLayout);
                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        tag = false;
                        llayout.setBackgroundResource(btnNormal);
                        llayout.getBackground().setAlpha(170);
                        // 更新浮动窗口位置参数

                        if (Math.abs(event.getRawX() - startX) < dip2px(context, 18)
                                && Math.abs(event.getRawY() - startY) < dip2px(context, 18)) {
                            //开始扫描前设置这个
                            SystemProperties.set("persist.sys.scanstopimme", "false");
                            theScanManager.startScan();
                            initScanTime(); //过一秒停止扫描
                        }

                        break;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        break;
                }
                return true;
            }
        });
    }

    private void initScanTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                //停止扫描前设置这个
                SystemProperties.set("persist.sys.scanstopimme", "true");
                theScanManager.stopScan();
            }
        }, 650); //0.65秒后执行Runnable中的run方法,否则初始化失败
    }


    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
