package com.jannonx.electric.utils.keyboardlayout;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardHelper {

    private OnKeyboardStateListener mListener;
    private static final int UNKNOW = -1;
    private Rect mRect = new Rect();
    private Rect mOriRect = new Rect();
    private int mKeyboardHeight = UNKNOW;
    private boolean isKeyboardShow = false;
    private View mRootView;


    public void setOnKeyboardStateListener(OnKeyboardStateListener listener) {
        mListener = listener;
    }

    public KeyboardHelper(View root) {
        this.mRootView = root;
        if (this.mRootView == null) {
            throw new RuntimeException(KeyboardHelper.class.getName() + " RootView Can NOT be null");
        }
        setGlobalLayoutListener();
    }

    public void hideKeyboard() {
        hideKeyBoardForce(this.mRootView);
    }

    public void showKeyboard(EditText view) {
        showKeyBoardForce(view);
    }

    public boolean isKeyboardShow(){
        return isKeyboardShow;
    }
    private static void showKeyBoardForce(final View view) {
        if (view == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        });
    }

    private static void hideKeyBoardForce(final View view) {
        if (view == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    /**
     * this method must call after onLayout()
     */
    public void init() {
        if (mOriRect.height() == 0) {
            this.mRootView.getWindowVisibleDisplayFrame(mOriRect);
        }
    }

    private void setGlobalLayoutListener() {

        this.mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mOriRect.height() == 0) {
                    throw new RuntimeException(KeyboardHelper.class.getName() + " You must call init() before setGlobalLayoutListener()");
                }
                if (mListener == null) {
                    return;
                }

                mRootView.getWindowVisibleDisplayFrame(mRect);

                int height = mOriRect.height() - mRect.height();
                if (height == mKeyboardHeight) {
                    return;
                } else {
                    if (mKeyboardHeight != UNKNOW) {
                        if (height > 0) {
                            mListener.onKeyboardOpened(height);
                            isKeyboardShow = true;
                        } else {
                            mListener.onKeyboardClosed();
                            isKeyboardShow = false;
                        }
                    }

                    mKeyboardHeight = height;
                }
            }
        });
    }
}
