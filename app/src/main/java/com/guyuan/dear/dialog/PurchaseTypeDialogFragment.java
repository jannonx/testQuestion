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
 * created by tl on 2020/2/6
 */
public class PurchaseTypeDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {
  public static final String TAG = "PurchaseTypeDialogFragment";
  private PurchaseTypeDialogListener listener;

  public static PurchaseTypeDialogFragment newInstance() {

    Bundle args = new Bundle();

    PurchaseTypeDialogFragment fragment = new PurchaseTypeDialogFragment();
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
    View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_purchase_type, null);
    TextView purchase_office = view.findViewById(R.id.purchase_office);
    TextView purchase_produce = view.findViewById(R.id.purchase_produce);
    TextView purchase_other = view.findViewById(R.id.purchase_other);
    TextView purchase_cancel = view.findViewById(R.id.purchase_cancel);

    purchase_office.setOnClickListener(this);
    purchase_produce.setOnClickListener(this);
    purchase_other.setOnClickListener(this);
    purchase_cancel.setOnClickListener(this);

    BottomSheetDialog sheetDialog = new BottomSheetDialog(getActivity(),
        R.style.BottomSheetDialogFragment);
    sheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    sheetDialog.setContentView(view);
    sheetDialog.setCanceledOnTouchOutside(true);

    return sheetDialog;
  }


  public void setListener(PurchaseTypeDialogListener listener) {
    this.listener = listener;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.purchase_office:
        if (listener != null) {
          listener.getType(1);
        }
        dismiss();
        break;

      case R.id.purchase_produce:
        if (listener != null) {
          listener.getType(2);
        }
        dismiss();
        break;

      case R.id.purchase_other:
        if (listener != null) {
          listener.getType(3);
        }
        dismiss();
        break;

      case R.id.purchase_cancel:
        dismiss();
        break;
    }
  }

  public interface PurchaseTypeDialogListener {
    void getType(int type);
  }
}
