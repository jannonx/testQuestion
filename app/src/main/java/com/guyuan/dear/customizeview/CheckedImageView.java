package com.guyuan.dear.customizeview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/22 11:07
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckedImageView extends AppCompatImageView implements Checkable {
    private static final String TAG = "CheckedImageLayout";
    private boolean isChecked = false;

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    public CheckedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CheckedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckedImageView(Context context) {
        super(context);
    }

    public void setChecked(boolean check){
        isChecked = check;
        refreshDrawableState();
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void toggle() {
        setChecked(!isChecked);
    }


    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
