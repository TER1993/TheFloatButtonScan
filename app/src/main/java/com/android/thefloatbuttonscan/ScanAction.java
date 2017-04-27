package com.android.thefloatbuttonscan;

/**
 * Created by dell2 on 2016/8/22.
 */
public class ScanAction {
    //扫描到结果后系统会返回该广播
    public static final String  RECE_DATA_ACTION = "com.se4500.onDecodeComplete";
    //调用扫描广播
    public static final String START_SCAN_ACTION = "com.geomobile.se4500barcode";
    //停止扫描
    public static  final String STOP_SCAN = "com.geomobile.se4500barcodestop";
    //悬浮按钮开启action
    public static final String  OPEN_FLOAT_WINDOW = "com.android.thefloatbuttonscan.intent.open";
    //悬浮按钮关闭action
    public static final String  CLOSE_FLOAT_WINDOW = "com.android.thefloatbuttonscan.intent.close";
    //紫色
    public static final String  COLOR_PURPLE = "thefloatbuttonscan.color.purple";
    public static  final  String COLOR_BLUE = "thefloatbuttonscan.color.blue";
    public static  final  String COLOR_YELLOW = "thefloatbuttonscan.color.yellow";
    public  static  final  String COLOR_BLACK = "thefloatbuttonscan.color.black";
    public  static  final  String COLOR_RED = "thefloatbuttonscan.color.red";
    public  static  final  String VIBRATOR_OPEN = "thefloatbuttonscan.vibrator.open";
    public  static  final  String VIBRATOR_CLOSE = "thefloatbuttonscan.vibrator.close";
    //粉色
    public  static  final  String COLOR_PINK = "thefloatbuttonscan.color.pink";
    //青色
    public  static  final  String COLOR_CYAN = "thefloatbuttonscan.color.cyan";

    //扫描模式
    public  static  final  String  MODE_ONE = "thefloatbuttonscan.mode.one";
    public  static  final  String  MODE_TWO = "thefloatbuttonscan.mode.two";
    public  static  final  String  MODE_THREE = "thefloatbuttonscan.mode.three";

}
