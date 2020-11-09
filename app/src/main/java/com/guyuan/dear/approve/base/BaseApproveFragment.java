package com.guyuan.dear.approve.base;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.activity.BaseActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.approve.activity.MineApplyListActivity;

import com.guyuan.dear.base.adapter.TagStaffAdapter;
import com.guyuan.dear.utils.view.flowlayout.TagFlowLayout;
import com.guyuan.dear.utils.view.flowlayout.TagAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.RequestBody;


/**
 * @description: 掌上办公--发起审核--BASE_FRAGMENT
 * @author: Jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public abstract class BaseApproveFragment<VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseDataBindingFragment<VB,VM> {

    public static final int REQUEST_PARTNER = 0x001;
    public static final int REQUEST_APPROVE = 0x002;
    public static final int REQUEST_COPY = 0x003;


    protected ArrayList<StaffBean> partnerList = new ArrayList<>();
    protected ArrayList<StaffBean> approveList = new ArrayList<>();
    protected ArrayList<StaffBean> copyList = new ArrayList<>();
    protected TagAdapter<StaffBean> partnerAdapter, approveAdapter, copyAdapter;
    protected OnDateSetListener mOnStartDateSelectedCallback;
    protected OnDateSetListener mOnStopDateSelectedCallback;
    protected Date startData, stopDate, selectDate;
    protected TagFlowLayout tflPartner, tflApprove, tflCopyTo;

    @Override
    protected void initialization() {
        if (getView() != null) {
            tflApprove = getView().findViewById(R.id.tfl_approver);
            tflCopyTo = getView().findViewById(R.id.tfl_copy_to);
            getView().findViewById(R.id.tfl_approver).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<StaffBean> disableListA = new ArrayList<>();
                    disableListA.addAll(partnerList);
                    disableListA.addAll(copyList);
//                SelectStaffsActivity.startForResult(REQUEST_APPROVE, this,
//                        "请选择审批人", approveList, disableListA, true, ConstantValue.CONST_MAX_STAFF_COUNT);
                }
            });
            getView().findViewById(R.id.iv_copy_to).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<StaffBean> disableListC = new ArrayList<>();
                    disableListC.addAll(partnerList);
                    disableListC.addAll(approveList);
//                SelectStaffsActivity.startForResult(REQUEST_COPY, this,
//                        "请选择抄送人", copyList, disableListC, true, ConstantValue.CONST_MAX_STAFF_COUNT);
                }
            });
            getView().findViewById(R.id.tv_btn_summit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!CommonUtils.isFastDoubleClick()) {
                        commitApprove();
                    }
                }
            });
        }
    }

    /**
     * 选择起始时间
     *
     * @param tvStartDate 起始TV
     * @param tvDuration  时长TV
     */
    protected void setStartDate(TextView tvStartDate, TextView tvDuration) {
        if (mOnStartDateSelectedCallback == null) {
            mOnStartDateSelectedCallback = new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {
                    startData = new Date(millSeconds);
                    String start = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(millSeconds);
                    tvStartDate.setText(start);
                    if (stopDate != null && tvDuration != null) {
                        //更新时长
                        updateOnLeavePeriod(tvDuration);
                    }
                }
            };
        }
        long minDate = System.currentTimeMillis();
        long maxDate = minDate + 1000 * 60 * 60 * 24L * 180;
        long selectedDate = minDate;
        if (startData != null && stopDate != null && startData.getTime() < stopDate.getTime()) {
            selectedDate = startData.getTime();
        }
        AlertDialogUtils.pickTime(childFragmentManager, "请选择起始时间",
                minDate, maxDate, selectedDate, Type.MONTH_DAY_HOUR_MIN, mOnStartDateSelectedCallback);


    }

    /**
     * 选择终止时间
     *
     * @param tvStartDate 起始TV
     * @param tvDuration  时长TV
     */
    protected void setStopDate(TextView tvStartDate, TextView tvDuration) {
        if (mOnStopDateSelectedCallback == null) {
            mOnStopDateSelectedCallback = new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {
                    stopDate = new Date(millSeconds);
                    String end = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(millSeconds);
                    tvStartDate.setText(end);
                    if (startData != null && tvDuration != null) {
                        //更新时长
                        updateOnLeavePeriod(tvDuration);
                    }
                }
            };
        }
        long minDate = System.currentTimeMillis() + 1000 * 60;
        long maxDate = minDate + 1000 * 60 * 60 * 24L * 180;
        long selectedDate = minDate;
        if (startData != null && stopDate != null && startData.getTime() < stopDate.getTime()) {
            selectedDate = stopDate.getTime();
        }
        AlertDialogUtils.pickTime(childFragmentManager, "请选择结束时间",
                minDate, maxDate, selectedDate, Type.MONTH_DAY_HOUR_MIN, mOnStopDateSelectedCallback);

    }

    /**
     * 选择时间
     *
     * @param tvSelectDate 选择TV
     */
    protected void setSelectedDate(TextView tvSelectDate) {
        if (mOnStopDateSelectedCallback == null) {
            mOnStopDateSelectedCallback = new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millSeconds) {
                    String str = CalenderUtils.getInstance().toSmartFactoryDateStringFormat(millSeconds);
                    tvSelectDate.setText(str);
                    selectDate = new Date(millSeconds);
                }
            };
        }
        long minDate = System.currentTimeMillis() + 1000 * 60;
        long maxDate = minDate + 1000 * 60 * 60 * 24L * 180;
        long selectedTime = minDate;

        if (selectDate != null) {
            selectedTime = selectDate.getTime();
        }

        AlertDialogUtils.pickTime(childFragmentManager, "请选择时间",
                minDate, maxDate, selectedTime, Type.MONTH_DAY_HOUR_MIN, mOnStopDateSelectedCallback);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PARTNER:
                if (data != null) {
                    List<StaffBean> extra = data.getParcelableArrayListExtra(ConstantValue.KEY_STAFF_LIST);
                    if (extra != null) {
                        setPartnerList(extra);
                    }
                }
                break;
            case REQUEST_APPROVE:
                if (data != null) {
                    List<StaffBean> extra = data.getParcelableArrayListExtra(ConstantValue.KEY_STAFF_LIST);
                    if (extra != null) {
                        setApproveList(extra);
                    }
                }
                break;

            case REQUEST_COPY:
                if (data != null) {
                    List<StaffBean> extra = data.getParcelableArrayListExtra(ConstantValue.KEY_STAFF_LIST);
                    if (extra != null) {
                        setCopyList(extra);
                    }
                }
                break;

            default:
        }
    }


    /**
     * @param dataList 选择同行人
     */
    private void setPartnerList(List<StaffBean> dataList) {
        partnerList.clear();
        partnerList.addAll(dataList);
        if (partnerAdapter == null) {
            partnerAdapter = new TagStaffAdapter(getContext(), partnerList);
            tflPartner.setAdapter(partnerAdapter);
        } else {
            partnerAdapter.notifyDataChanged();
        }

    }

    /**
     * @param dataList 选择审批人
     */
    private void setApproveList(List<StaffBean> dataList) {
        approveList.clear();
        approveList.addAll(dataList);
        if (approveAdapter == null) {
            approveAdapter = new TagStaffAdapter(getContext(), approveList);
            tflApprove.setAdapter(approveAdapter);
        } else {
            approveAdapter.notifyDataChanged();
        }
    }


    /**
     * @param dataList 选择抄送人
     */
    private void setCopyList(List<StaffBean> dataList) {
        copyList.clear();
        copyList.addAll(dataList);
        if (copyAdapter == null) {
            copyAdapter = new TagStaffAdapter(getContext(), copyList);
            tflCopyTo.setAdapter(copyAdapter);
        } else {
            copyAdapter.notifyDataChanged();
        }
    }


    /**
     * 感觉起始时间，终止时间，计算时长
     *
     * @param textView 请假时长文本
     */
    protected void updateOnLeavePeriod(TextView textView) {
        long duration = stopDate.getTime() - startData.getTime();
        String str = CalenderUtils.getInstance().getHourAndMinuteDescription(duration);
        textView.setText(str);
    }


    /**
     * 提交信息反馈
     *
     * @param result true?成功：失败
     */
    public void showApplyInfo(boolean result) {
        //获取栈顶的activity实例
        BaseActivity topActivity = (BaseActivity) ActivityUtils.getTopActivity();
        if (result) {
            showToastTip("提交申请成功！");
            if (topActivity != null) {
                topActivity.finish();
//                topActivity.setPlayContent("提交申请成功");

            }
            MineApplyListActivity.start(topActivity);
        } else {
            showToastTip("提交申请失败！");
//            if (topActivity != null) {
//                topActivity.setPlayContent("提交申请失败");
//            }
        }
    }

    private void commitApprove() {
        RequestBody body = getRequestBody();
//        if (body != null) {
//            mPresenter.postApplyInfo(body);
//        }
    }

    protected ArrayList<String> getPartners() {
        ArrayList<String> partners = new ArrayList<>();
        for (StaffBean bean : partnerList) {
            partners.add(bean.getId());
        }

        return partners;
    }

    protected ArrayList<String> getApproves() {
        ArrayList<String> approves = new ArrayList<>();
        for (StaffBean bean : approveList) {
            approves.add(bean.getId());
        }

        return approves;
    }

    protected ArrayList<String> getCopys() {
        ArrayList<String> copys = new ArrayList<>();
        for (StaffBean bean : copyList) {
            copys.add(bean.getId());
        }

        return copys;
    }


    /**
     * @return 获取提交的内容
     */
    protected abstract RequestBody getRequestBody();

    protected void setTflPartner(TagFlowLayout tagFlowLayout) {
        this.tflPartner = tagFlowLayout;
    }
}
