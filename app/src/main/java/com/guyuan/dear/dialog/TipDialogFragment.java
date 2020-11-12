package com.guyuan.dear.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseDialogFragment;

import retrofit2.http.PUT;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/12 16:56
 * @company : 固远（深圳）信息技术有限公司
 **/

public class TipDialogFragment extends BaseDialogFragment {

    public static final String TAG = "TipDialogFragment";

    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private OnSure sureListener;
    private OnCancel cancelListener;
    private String title;
    private String content;


    public static TipDialogFragment newInstance(String title, String content) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(CONTENT, content);
        TipDialogFragment fragment = new TipDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_tip;
    }

    @Override
    protected void setContentView(View view) {
        TextView tip_title = view.findViewById(R.id.tip_title);
        TextView tip_content_tv = view.findViewById(R.id.tip_content_tv);
        TextView tip_cancel_tv = view.findViewById(R.id.tip_cancel_tv);
        TextView tip_sure_tv = view.findViewById(R.id.tip_sure_tv);
        if (getArguments() != null) {
            String title = getArguments().getString(TITLE);
            String content = getArguments().getString(CONTENT);

            if (!TextUtils.isEmpty(title)) {
                tip_title.setVisibility(View.VISIBLE);
                tip_title.setText(title);
            }

            if (!TextUtils.isEmpty(content)) {
                tip_content_tv.setVisibility(View.VISIBLE);
                tip_content_tv.setText(content);
            }
        }

        tip_sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureListener != null) {
                    sureListener.sure();
                }
            }
        });

        tip_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelListener != null) {
                    cancelListener.cancel();
                }
            }
        });
    }


    public TipDialogFragment setOnSureListener(OnSure onSure) {
        this.sureListener = onSure;
        return this;
    }

    public TipDialogFragment setOnCancelListener(OnCancel onCancel) {
        this.cancelListener = onCancel;
        return this;
    }

    public interface OnSure {
        void sure();
    }

    public interface OnCancel {
        void cancel();
    }

    public void showDialog() {
        show(getActivity().getSupportFragmentManager(), TAG);
    }
}