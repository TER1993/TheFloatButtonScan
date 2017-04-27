package com.android.thefloatbuttonscan;

/**
 * Created by dell2 on 2016/8/23.
 */
public interface FloatButtonManagerInterface {
    //发送广播控制开启悬浮按钮，ScanAction.OPEN_FLOAT_WINDOW
    public void openFloatWindow();
    // 发送广播控制关闭悬浮按钮, ScanAction.CLOSE_FLOAT_WINDOW
    public void closeFloatwind();
}
