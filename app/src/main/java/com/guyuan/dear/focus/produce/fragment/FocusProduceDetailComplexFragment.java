package com.guyuan.dear.focus.produce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProduceDetailComplexBinding;
import com.guyuan.dear.dialog.RemarkDialog;
import com.guyuan.dear.dialog.SimpleConfirmViewDialog;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.OperateProduceType;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.office.approval.ui.ApprovalActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.adapters.AddCopyListAdapter;
import com.guyuan.dear.work.contractPause.adapters.AddSendListAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.guyuan.dear.focus.produce.fragment.FocusProduceDetailSimpleFragment.BUSINESS_ID;
import static com.guyuan.dear.focus.produce.fragment.FocusProduceDetailSimpleFragment.BUSINESS_TYPE;
import static com.guyuan.dear.focus.produce.fragment.FocusProduceDetailSimpleFragment.STATUS;
import static com.guyuan.dear.focus.produce.fragment.FocusProduceDetailSimpleFragment.TYPE;
import static com.guyuan.dear.office.approval.ui.ApprovalActivity.IS_APPROVED;

/**
 * @description: 我的关注--客户详情(CoordinatorLayout)
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceDetailComplexFragment extends BaseDataBindingFragment<FragmentFocusProduceDetailComplexBinding, FocusProduceViewModel>
        implements View.OnClickListener {

    public static final String TAG = FocusProduceDetailComplexFragment.class.getSimpleName();

    private static final int REQUEST_SEND_SELECT_PERSON = 0x001;
    private static final int REQUEST_COPY_SELECT_PERSON = 0x002;
    private FocusProduceStatusFragment statusFragment;
    private FollowProducePlanFragment planFragment;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private FocusProduceBean produceBean;
    private boolean isFooterBtnShow, isProducePause, isProduceIng;
    private ProduceApplyDialog dialog;
    private int businessId = -1;
    private int businessType;
    private int status = -1;//1.同意；2.拒绝
    private int type;
    private boolean isApproved = false;
    private RemarkDialog.OnDialogClickListener remarkListener;

    public static FocusProduceDetailComplexFragment newInstance(FocusProduceBean data, boolean isFooterBtnShow) {
        Bundle bundle = new Bundle();
        FocusProduceDetailComplexFragment fragment = new FocusProduceDetailComplexFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        bundle.putSerializable(ConstantValue.KEY_BOOLEAN, isFooterBtnShow);
        fragment.setArguments(bundle);
        return fragment;
    }


    //审批入口
    public static FocusProduceDetailSimpleFragment newInstance(FocusProduceBean data, int businessId,
                                                               int businessType, int type, boolean isApproved) {
        Bundle bundle = new Bundle();
        FocusProduceDetailSimpleFragment fragment = new FocusProduceDetailSimpleFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        bundle.putSerializable(ConstantValue.KEY_BOOLEAN, false);
        bundle.putInt(BUSINESS_ID, businessId);
        bundle.putInt(BUSINESS_TYPE, businessType);
        bundle.putInt(TYPE, type);
        bundle.putBoolean(IS_APPROVED, isApproved);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_detail_complex;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        produceBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        isFooterBtnShow = arguments.getBoolean(ConstantValue.KEY_BOOLEAN, false);
        LogUtils.showLog("listData=" + (produceBean == null));
        initViewPager();
        initData();
    }

    private void initData() {
        binding.tvActivateBtn.setOnClickListener(this);
        binding.tvPauseBtn.setOnClickListener(this);
        binding.tvCompleteBtn.setOnClickListener(this);
        viewModel.getBasicInfoById(produceBean.getEquipmentId());

        viewModel.getBasicInfoEvent().observe(getActivity(), new Observer<FocusProduceBean>() {
            @Override
            public void onChanged(FocusProduceBean data) {
                setProduceData(data);
            }
        });
        //操作
        viewModel.getExecuteEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
                ToastUtils.showLong(getContext(), "提交成功!");
                getActivity().finish();
            }
        });

        setApproval();
    }


    //设置审批
    private void setApproval() {
        businessId = getArguments().getInt(BUSINESS_ID);
        businessType = getArguments().getInt(BUSINESS_TYPE);
        status = getArguments().getInt(STATUS);
        type = getArguments().getInt(TYPE);
        isApproved = getArguments().getBoolean(IS_APPROVED);
        if (isApproved) {
            setRemarkDialogListener();
            binding.produceApprovalLl.setVisibility(View.VISIBLE);


            binding.produceRejectTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.REJECT;
                    RemarkDialog.show(getActivity(), "请输入驳回备注", remarkListener);
                }
            });

            binding.produceAcceptTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.ACCEPT;
                    RemarkDialog.show(getActivity(), "请输入通过备注", remarkListener);
                }
            });
        }
    }

    private void setRemarkDialogListener() {
        remarkListener = new RemarkDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(ExecuteRequestBody data) {
                String remark = data.getReason();
                viewModel.approval(businessId, businessType, remark, status, type);
            }
        };
    }


    private void setProduceData(FocusProduceBean data) {
        //暂停状态
        isProducePause = ProductStatusType.TYPE_PRODUCE_EXCEPTION == data.getStatusType();
        //生产状态
        isProduceIng = ProductStatusType.TYPE_PRODUCE_ING == data.getStatusType();
        binding.tvActivateBtn.setVisibility(isFooterBtnShow && isProducePause ? View.VISIBLE : View.GONE);
        binding.llApplyPanel.setVisibility(isFooterBtnShow && isProduceIng ? View.VISIBLE : View.GONE);

        planFragment.setProduceData(data);

        binding.tvProductName.setText(data.getName());
        binding.tvProductCode.setText(data.getCode());
        binding.tvDutyUnit.setText(data.getPrincipalDept());

        //设置生产状态
        binding.tvProduceStatus.setText(data.getStatusText());
        binding.tvProduceStatus.setBackgroundResource(data.getStatusTextBg());
        int color_blue_ff1b97fc = data.getStatusTextColor();
        binding.tvProduceStatus.setTextColor(getActivity().getResources().getColor(color_blue_ff1b97fc));
        if (data.getStatusType() == ProductStatusType.TYPE_PRODUCE_DELAY_FINISH ||
                data.getStatusType() == ProductStatusType.TYPE_PRODUCE_DELAY_NOT_FINISH) {
            binding.tvSubStatus.setVisibility(View.VISIBLE);
        } else {
            binding.tvSubStatus.setVisibility(View.GONE);
        }

        binding.tvProjectName.setText(data.getProjectName());

        binding.tvActualStart.setText(data.getActualStartTime());
        binding.tvPlanStart.setText(data.getPlanStartTime());
        binding.tvActualComplete.setText(data.getActualEndTime());
        binding.tvPlanComplete.setText(data.getPlanEndTime());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_activate_btn:
                showApplyDialog(OperateProduceType.TYPE_EXECUTE_ACTIVATE);
                break;
            case R.id.tv_pause_btn:
                showApplyDialog(OperateProduceType.TYPE_EXECUTE_PAUSE);
                break;
            case R.id.tv_complete_btn:
                SimpleConfirmViewDialog.OnClickListener listener = new SimpleConfirmViewDialog.OnClickListener() {
                    @Override
                    public void onConfirm() {
                        ExecuteRequestBody body = new ExecuteRequestBody();
                        body.setEquipmentId(produceBean.getEquipmentId());
                        body.setType(OperateProduceType.TYPE_EXECUTE_COMPLETE.getCode());
                        String str = GsonUtil.objectToString(body);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                                "charset=utf-8"), str);
                        viewModel.postExecuteProduceInfo(requestBody);
                    }
                };

                SimpleConfirmViewDialog.showTitle(getContext(), "确认完成生产吗？", listener);
                break;
            default:
        }
    }

    private void showApplyDialog(OperateProduceType type) {
        ProduceApplyDialog.OnDialogClickListener dialogListener = new ProduceApplyDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(ExecuteRequestBody content) {
                content.setEquipmentId(produceBean.getEquipmentId());
                String str = GsonUtil.objectToString(content);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);
                viewModel.postExecuteProduceInfo(requestBody);
            }

            @Override
            public void onSendClick(AddSendListAdapter sendListAdapter,AddCopyListAdapter copyListAdapter) {
                LogUtils.showLog("size="+( sendListAdapter == null ? "new ArrayList<>()" : sendListAdapter.getList().size()));
                PickStaffsActivity.startForResult(FocusProduceDetailComplexFragment.this,
                        REQUEST_SEND_SELECT_PERSON,
                        "请选审批送人",
                        sendListAdapter == null ? new ArrayList<>() : sendListAdapter.getList(),
                        new ArrayList<>(),
                        copyListAdapter == null ? new ArrayList<>() : copyListAdapter.getList(),
                        ConstantValue.CONST_MAX_STAFF_COUNT);
            }

            @Override
            public void onCopyClick(AddCopyListAdapter addCopyListAdapter,AddSendListAdapter sendListAdapter) {
                PickStaffsActivity.startForResult(FocusProduceDetailComplexFragment.this,
                        REQUEST_COPY_SELECT_PERSON,
                        "请选审抄送人",
                        addCopyListAdapter == null ? new ArrayList<>() : addCopyListAdapter.getList(),
                        new ArrayList<>(),
                        sendListAdapter == null ? new ArrayList<>() : sendListAdapter.getList(),
                        ConstantValue.CONST_MAX_STAFF_COUNT);
            }
        };
        dialog = new ProduceApplyDialog(getActivity(), type, dialogListener);
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


    private void initViewPager() {
        List<Fragment> tabFragmentList = new ArrayList<>();
        statusFragment = FocusProduceStatusFragment.newInstance(produceBean);
        planFragment = FollowProducePlanFragment.newInstance();

        tabFragmentList.add(statusFragment);
        tabFragmentList.add(planFragment);

        titleList = getResources().getStringArray(R.array.focus_produce_tab);
        TabAdapter tabAdapter = new
                TabAdapter(getChildFragmentManager(), Arrays.asList(titleList), tabFragmentList, getContext(),
                getCustomViewId());

        binding.viewPager.setAdapter(tabAdapter);


        //设置TabLayout和ViewPager联动
        binding.viewPager.setOffscreenPageLimit(2);
        binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        binding.tabLayout.setTabIndicatorFullWidth(false);
        binding.tabLayout.setupWithViewPager(binding.viewPager, false);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setTabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            View view = tabAdapter.getView();
            if (tab != null) {
                setContent(i, view);
                tab.setCustomView(view);
                if (i == startPosition) {
                    setTabSelect(tab);
                    tab.select();
                } else {
                    setTabUnselected(tab);
                }
            }
        }
    }


    private int getCustomViewId() {
        return R.layout.layout_tab_text;
    }

    protected void setTabSelect(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (selectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        }
        tv.setTextSize(15f);
    }

    protected void setTabUnselected(TabLayout.Tab tab) {
        TextView tv = tab.getCustomView().findViewById(R.id.tv_tab_text);
        if (unSelectedTextColor == 0) {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_blue_1677ff));
        }
        tv.setTextSize(15f);
    }


    private void setContent(int position, View customView) {
        TextView tv = customView.findViewById(R.id.tv_tab_text);
        tv.setText(Arrays.asList(titleList).get(position));
    }


    @Override
    protected int getVariableId() {
        return 0;
    }


}
