
package com.guyuan.dear.utils.keyboardlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class KeyboardLinearLayout extends LinearLayout {


    private KeyboardHelper mHelper;

    public KeyboardLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHelper = new KeyboardHelper(this);
    }

    public KeyboardLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = new KeyboardHelper(this);
    }

    public KeyboardLinearLayout(Context context) {
        super(context);
        mHelper = new KeyboardHelper(this);
    }

    public void setOnKeyboardStateListener(OnKeyboardStateListener listener) {
        mHelper.setOnKeyboardStateListener(listener);
    }

    public KeyboardHelper getKeyBoardHelper(){
        return mHelper;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHelper.init();
    }

}
