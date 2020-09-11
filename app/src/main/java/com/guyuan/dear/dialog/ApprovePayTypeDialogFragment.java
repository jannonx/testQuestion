package com.guyuan.dear.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.guyuan.dear.R;

/**
 * created by tl on 2020/2/13
 */


public class ApprovePayTypeDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {
  public static final String TAG = "ApprovePayTypeDialogFragment";
  private ApprovePayTypeDialogListener listener;

  public static ApprovePayTypeDialogFragment newInstance() {

    Bundle args = new Bundle();

    ApprovePayTypeDialogFragment fragment = new ApprovePayTypeDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onStart() {
    super.onStart();
    Window window = getDialog().getWindow();
    WindowManager.LayoutParams params = window.getAttributes();
    params.gravity = Gravity.BOTTOM;
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    window.setAttributes(params);
    //  window.setWindowAnimations(R.style.anim_dialog_city);
  }

  @Override
  @NonNull
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_pay_type, null);
    TextView pay_type1 = view.findViewById(R.id.pay_type1);
    TextView pay_type2 = view.findViewById(R.id.pay_type2);
    TextView pay_type3 = view.findViewById(R.id.pay_type3);
    TextView pay_type4 = view.findViewById(R.id.pay_type4);
    TextView pay_type5 = view.findViewById(R.id.pay_type5);
    TextView pay_type6 = view.findViewById(R.id.pay_type6);
    TextView pay_type7 = view.findViewById(R.id.pay_type7);
    TextView pay_type_cancel = view.findViewById(R.id.pay_type_cancel);

    pay_type1.setOnClickListener(this);
    pay_type2.setOnClickListener(this);
    pay_type3.setOnClickListener(this);
    pay_type4.setOnClickListener(this);
    pay_type5.setOnClickListener(this);
    pay_type6.setOnClickListener(this);
    pay_type7.setOnClickListener(this);
    pay_type_cancel.setOnClickListener(this);

    BottomSheetDialog sheetDialog = new BottomSheetDialog(getActivity(),
        R.style.BottomSheetDialogFragment);
    sheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    sheetDialog.setContentView(view);
    sheetDialog.setCanceledOnTouchOutside(true);

    return sheetDialog;
  }


  public void setListener(ApprovePayTypeDialogListener listener) {
    this.listener = listener;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.pay_type1:
        if (listener != null) {
          listener.getType(1);
        }
        dismiss();
        break;

      case R.id.pay_type2:
        if (listener != null) {
          listener.getType(2);
        }
        dismiss();
        break;

      case R.id.pay_type3:
        if (listener != null) {
          listener.getType(3);
        }
        dismiss();
        break;

      case R.id.pay_type4:
        if (listener != null) {
          listener.getType(4);
        }
        dismiss();
        break;

      case R.id.pay_type5:
        if (listener != null) {
          listener.getType(5);
        }
        dismiss();
        break;

      case R.id.pay_type6:
        if (listener != null) {
          listener.getType(6);
        }
        dismiss();
        break;

      case R.id.pay_type7:
        if (listener != null) {
          listener.getType(7);
        }
        dismiss();
        break;

      case R.id.pay_type_cancel:
        dismiss();
        break;
    }
  }

  public interface ApprovePayTypeDialogListener {
    void getType(int type);
  }
}
