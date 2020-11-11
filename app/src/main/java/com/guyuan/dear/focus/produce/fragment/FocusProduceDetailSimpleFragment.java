package com.guyuan.dear.focus.produce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.TagStaffAdapter;
import com.guyuan.dear.databinding.FragmentFocusProduceDetailSimpleBinding;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;

import java.util.ArrayList;

/**
 * @description: 我的关注--客户详情(NestedScrollView)
 * @author: Jannonx
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceDetailSimpleFragment extends BaseDataBindingFragment<FragmentFocusProduceDetailSimpleBinding, FocusProduceViewModel> {

    public static final String TAG = FocusProduceDetailSimpleFragment.class.getSimpleName();

    private static final int REQUEST_SEND_SELECT_PERSON = 0x001;
    private static final int REQUEST_COPY_SELECT_PERSON = 0x002;
    private FollowProducePlanFragment planFragment;
    private FocusProduceBean produceBean;
    private ProduceApplyDialog dialog;


    public static FocusProduceDetailSimpleFragment newInstance(FocusProduceBean data) {
        Bundle bundle = new Bundle();
        FocusProduceDetailSimpleFragment fragment = new FocusProduceDetailSimpleFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_detail_simple;
    }

    @Override
    protected void initialization() {
        FragmentManager fragmentManager = getChildFragmentManager();
        planFragment = (FollowProducePlanFragment) fragmentManager.findFragmentById(R.id.factory_view);
        Bundle arguments = getArguments();
        produceBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        viewModel.getBasicInfoById(produceBean.getEquipmentId());

        viewModel.getBasicInfoEvent().observe(getActivity(), new Observer<FocusProduceBean>() {
            @Override
            public void onChanged(FocusProduceBean data) {
                setProduceData(data);
            }
        });

        binding.tvCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showApplyDialog();
            }
        });
    }


    private void setProduceData(FocusProduceBean data) {
        planFragment.setProduceData(data);

        binding.tvProductName.setText(data.getName());
        binding.tvProductCode.setText(data.getCode());
        binding.tvDutyUnit.setText(data.getPrincipalDept());

        //设置生产状态
        binding.tvProduceStatus.setText(data.getStatusText());
        binding.tvProduceStatus.setBackgroundResource(data.getStatusTextBg());
        int color_blue_ff1b97fc = data.getStatusTextColor();
        binding.tvProduceStatus.setTextColor(getActivity().getResources().getColor(color_blue_ff1b97fc));
        binding.tvSubStatus.setVisibility(ProductStatusType.TYPE_PRODUCE_DELAY == data.getStatusType()
                ? View.VISIBLE : View.GONE);
        binding.tvProjectName.setText(data.getProjectName());

        binding.tvActualStart.setText(data.getActualStartTime());
        binding.tvPlanStart.setText(data.getPlanStartTime());
        binding.tvActualComplete.setText(data.getActualEndTime());
        binding.tvPlanComplete.setText(data.getPlanEndTime());


    }

    private void showApplyDialog() {
        ProduceApplyDialog.OnDialogClickListener dialogListener = new ProduceApplyDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(String content) {

            }

            @Override
            public void onSendClick(TagStaffAdapter adapter) {
                PickStaffsActivity.startForResult(FocusProduceDetailSimpleFragment.this,
                        REQUEST_SEND_SELECT_PERSON,
                        "请选审批送人",
                        adapter == null ? new ArrayList<>() : adapter.getTagDataList(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        ConstantValue.CONST_MAX_STAFF_COUNT);
            }

            @Override
            public void onCopyClick(TagStaffAdapter adapter) {
                PickStaffsActivity.startForResult(FocusProduceDetailSimpleFragment.this,
                        REQUEST_COPY_SELECT_PERSON,
                        "请选审抄送人",
                        adapter == null ? new ArrayList<>() : adapter.getTagDataList(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        ConstantValue.CONST_MAX_STAFF_COUNT);
            }
        };
        dialog = new ProduceApplyDialog(getActivity(), dialogListener);
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            ToastUtils.showLong(getActivity(), "选择人员失败");
            return;
        }
        ArrayList<StaffBean> list = data.getParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS);
        if (list == null) {
            ToastUtils.showLong(getActivity(), "选择人员失败");
            return;
        }
        switch (requestCode) {
            case REQUEST_SEND_SELECT_PERSON:
                dialog.setSendToList(list);
                break;
            case REQUEST_COPY_SELECT_PERSON:
                dialog.setCopyToList(list);
                break;
            default:
        }

    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
