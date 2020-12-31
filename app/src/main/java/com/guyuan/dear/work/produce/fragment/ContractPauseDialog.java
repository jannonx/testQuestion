package com.guyuan.dear.work.produce.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogContractPauseBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ReviewerDataBean;


/**
 * @description: 我的关注--生产计划--合同暂停弹框
 * @author: Jannonx
 * @since: 2020/12/30 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class ContractPauseDialog extends Dialog {

    private Context context;//上下文
    private FocusProduceBean contractBean;
    private DialogContractPauseBinding viewBinding;
    private ContractPauseDialog(@NonNull Context context, FocusProduceBean bean) {
        super(context);
        this.contractBean = bean;
    }

    public static void show(@NonNull Context context, FocusProduceBean bean) {
        ContractPauseDialog dialog = new ContractPauseDialog(context, bean);
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        //不留边距
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);//设置动画效果

        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_contract_pause, null, false);
        setContentView(viewBinding.getRoot());//核心代码

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失

        ReviewerDataBean reviewerDataBean = contractBean.getContractStopCauseVO();
        if (reviewerDataBean!=null){
            viewBinding.tvDimensionality.setText(reviewerDataBean.getDimensionality());
            viewBinding.tvTime.setText(reviewerDataBean.getStopTime());
            viewBinding.tvPerson.setText(reviewerDataBean.getAuditName());
            viewBinding.tvStopCause.setText("暂停原因："+reviewerDataBean.getStopCause());
        }

        viewBinding.tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });

    }

}
