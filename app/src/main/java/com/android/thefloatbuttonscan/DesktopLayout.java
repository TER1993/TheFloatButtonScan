package com.android.thefloatbuttonscan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class DesktopLayout extends LinearLayout {

    public DesktopLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(context).inflate(
                R.layout.desklayout, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.mylinnerlayout);
        linearLayout.setBackgroundResource(MyManager.btnNormal);
        linearLayout.getBackground().setAlpha(170);
        this.addView(view);
    }

}
