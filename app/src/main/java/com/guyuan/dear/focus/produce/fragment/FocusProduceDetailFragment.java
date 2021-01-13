package com.guyuan.dear.focus.produce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.databinding.FragmentFocusProduceDetailBinding;
import com.guyuan.dear.dialog.RemarkDialog;
import com.guyuan.dear.dialog.SimpleConfirmViewDialog;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.focus.produce.bean.ContractStatusType;
import com.guyuan.dear.focus.produce.bean.EventProduceListRefresh;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.OperateProduceType;
import com.guyuan.dear.focus.produce.bean.ProductStatusType;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.office.approval.ui.ApprovalActivity;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.adapters.AddCopyListAdapter;
import com.guyuan.dear.work.contractPause.adapters.AddSendListAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.guyuan.dear.office.approval.ui.ApprovalActivity.IS_APPROVED;

/**
 * @description: 我的关注--生产详情
 * 待生产的详情页||生产中到生产完成的详情页面
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceDetailFragment extends BaseDataBindingFragment<FragmentFocusProduceDetailBinding, FocusProduceViewModel>
        implements View.OnClickListener {

    public static final String TAG = FocusProduceDetailFragment.class.getSimpleName();
    public static final String BUSINESS_ID = "businessId";
    public static final String BUSINESS_TYPE = "businessType";
    public static final String REMARKS = "remarks";
    public static final String STATUS = "status";
    public static final String TYPE = "type";

    private static final int REQUEST_SEND_SELECT_PERSON = 0x001;
    private static final int REQUEST_COPY_SELECT_PERSON = 0x002;
    private FocusProduceStatusFragment statusFragment;
    private FollowProducePlanFragment planFragment;
    private FollowProducePlanFragment simplePlanFragment;

    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    //生产信息
    private FocusProduceBean produceBean, initBean;

    private boolean isFooterBtnShow, isProducePause, isProduceIng;
    private ProduceApplyDialog dialog;
    private int businessId = -1;
    private int businessType;
    private int status = -1;//1.同意；2.拒绝
    private int type;
    private boolean isApproved = false;
    //自己隐藏
    private ArrayList<StaffBean> hiddenStaffList = new ArrayList<>();
    private RemarkDialog.OnDialogClickListener remarkComplexListener;
    private RemarkDialog.OnDialogClickListener remarkSimpleListener;

    public static FocusProduceDetailFragment newInstance(FocusProduceBean data, boolean isFooterBtnShow) {
        Bundle bundle = new Bundle();
        FocusProduceDetailFragment fragment = new FocusProduceDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        bundle.putSerializable(ConstantValue.KEY_BOOLEAN, isFooterBtnShow);
        fragment.setArguments(bundle);
        return fragment;
    }


    //审批入口
    public static FocusProduceDetailFragment newInstance(FocusProduceBean data, int businessId,
                                                         int businessType, int type, boolean isApproved) {
        Bundle bundle = new Bundle();
        FocusProduceDetailFragment fragment = new FocusProduceDetailFragment();
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
        return R.layout.fragment_focus_produce_detail;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }


        initBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        isFooterBtnShow = arguments.getBoolean(ConstantValue.KEY_BOOLEAN, false);
//        LogUtils.showLog("listData=" + (produceBean == null));

        LogUtils.showLog("initialization=" + initBean.getStatusType().getDes());
        //现请求数据
        viewModel.getBasicInfoById(initBean.getPlanId());
        viewModel.getBasicInfoEvent().observe(getActivity(), new Observer<FocusProduceBean>() {
            @Override
            public void onChanged(FocusProduceBean data) {
                jumpToPage(data);
                if (data.getContractStatusType() == ContractStatusType.TYPE_CONTRACT_PAUSE) {
                    CommonUtils.getContractStatus(BaseApiService.PROJECT_ID, String.valueOf(data.getProjectId()));
                }
            }
        });

        //操作
        viewModel.getExecuteEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
                EventBus.getDefault().post(new EventProduceListRefresh());
                getActivity().finish();
            }
        });
    }


    /**
     * 根据状态不同显示不同页面
     */
    private void jumpToPage(FocusProduceBean data) {
        LogUtils.showLog("jumpToPage=" + initBean.getStatusType().getDes());
        data.setStopStatus(initBean.getStopStatus());
        if (ProductStatusType.TYPE_PRODUCE_WAIT == initBean.getStatusType()) {
            binding.clRootComplex.setVisibility(View.GONE);
            binding.clRootEmpty.setVisibility(View.GONE);
            binding.rlRootSimple.setVisibility(View.VISIBLE);
            FragmentManager fragmentManager = getChildFragmentManager();
            simplePlanFragment = (FollowProducePlanFragment) fragmentManager.findFragmentById(R.id.factory_view_simple);
            initProductSimple();
            setProduceDataSimple(data);
        } else if (ProductStatusType.TYPE_UNKNOWN == initBean.getStatusType()) {
            binding.clRootComplex.setVisibility(View.GONE);
            binding.rlRootSimple.setVisibility(View.GONE);
            binding.clRootEmpty.setVisibility(View.VISIBLE);
        } else {
            initProductComplex();
            setProduceDataComplex(data);
        }

    }

    //++++++++++++++++++++++++待生产状态++++++++++++++++++++++++//

    private void initProductSimple() {
        SimpleConfirmViewDialog.OnClickListener listener = new SimpleConfirmViewDialog.OnClickListener() {
            @Override
            public void onConfirm() {
                ExecuteRequestBody body = new ExecuteRequestBody();
                body.setEquipmentId(produceBean.getEquipmentId());
                body.setId(produceBean.getPlanId());
                body.setType(OperateProduceType.TYPE_EXECUTE_START.getCode());
                String str = GsonUtil.objectToString(body);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);
                viewModel.postExecuteProduceInfo(requestBody);
            }
        };

        binding.tvCommitBtnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleConfirmViewDialog.showTitle(getContext(), "确认提交生产开始吗？", listener);
            }
        });


        setApprovalSimple();
    }

    //设置审批
    private void setApprovalSimple() {
        businessId = getArguments().getInt(BUSINESS_ID);
        businessType = getArguments().getInt(BUSINESS_TYPE);
        status = getArguments().getInt(STATUS);
        type = getArguments().getInt(TYPE);
        isApproved = getArguments().getBoolean(IS_APPROVED);
        if (isApproved) {
            setRemarkDialogSimpleListener();
            binding.produceApprovalLlSimple.setVisibility(View.VISIBLE);
            binding.produceRejectTvSimple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.REJECT;
                    RemarkDialog.show(getActivity(), "请输入驳回备注", remarkSimpleListener);
                }
            });

            binding.produceAcceptTvSimple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.ACCEPT;
                    RemarkDialog.show(getActivity(), "请输入通过备注", remarkSimpleListener);
                }
            });
        }

    }


    private void setRemarkDialogSimpleListener() {
        remarkSimpleListener = new RemarkDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(ExecuteRequestBody data) {
                String remark = data.getReason();
                viewModel.approval(businessId, businessType, remark, status, type);
            }
        };
    }

    private void setProduceDataSimple(FocusProduceBean data) {
        produceBean = data;
        simplePlanFragment.setProduceData(data);

        binding.tvProductNameSimple.setText(data.getName());
        binding.tvProductCodeSimple.setText(data.getCode());
        binding.tvDutyUnitSimple.setText(data.getPrincipalDept());

        //设置生产状态
        binding.tvProduceStatusSimple.setText(data.getStatusText());
        binding.tvProduceStatusSimple.setBackgroundResource(data.getStatusTextBg());
        int color_blue_ff1b97fc = data.getStatusTextColor();
        binding.tvProduceStatusSimple.setTextColor(getActivity().getResources().getColor(color_blue_ff1b97fc));

        binding.tvProjectNameSimple.setText(data.getProjectName());
        binding.tvProjectCodeSimple.setText(data.getProjectCode());

        binding.tvActualStartSimple.setText(data.getActualStartTime());
        binding.tvPlanStartSimple.setText(data.getPlanStartTime());
        binding.tvActualCompleteSimple.setText(data.getActualEndTime());
        binding.tvPlanCompleteSimple.setText(data.getPlanEndTime());

        LogUtils.showLog("isFooterBtnShow=" + isFooterBtnShow + "...getStatusType=" + (ProductStatusType.TYPE_PRODUCE_WAIT == data.getStatusType()));
        //我的工作 and 待开始 and 权限
        binding.tvCommitBtnSimple.setVisibility((isFooterBtnShow && CommonUtils.isShowButton(ConstantValue.PRODUCE_APPROVE_START)
                && ProductStatusType.TYPE_PRODUCE_WAIT == data.getStatusType()) ? View.VISIBLE : View.GONE);
    }

    //++++++++++++++++++++++++待生产状态++++++++++++++++++++++++//
    //============================================================//
    //++++++++++++++++++++++++待生产后生产状态++++++++++++++++++++++++//
    private void initProductComplex() {
        //设置视图
        binding.clRootEmpty.setVisibility(View.GONE);
        binding.rlRootSimple.setVisibility(View.GONE);
        binding.clRootComplex.setVisibility(View.VISIBLE);

        binding.tvActivateBtn.setOnClickListener(this);
        binding.tvPauseBtn.setOnClickListener(this);
        binding.tvCompleteBtn.setOnClickListener(this);

        setApprovalComplex();
    }


    //设置审批
    private void setApprovalComplex() {
        businessId = getArguments().getInt(BUSINESS_ID);
        businessType = getArguments().getInt(BUSINESS_TYPE);
        status = getArguments().getInt(STATUS);
        type = getArguments().getInt(TYPE);
        isApproved = getArguments().getBoolean(IS_APPROVED);
        if (isApproved) {
            setRemarkDialogComplexListener();
            binding.produceApprovalLl.setVisibility(View.VISIBLE);

            binding.produceRejectTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.REJECT;
                    RemarkDialog.show(getActivity(), "请输入驳回备注", remarkComplexListener);
                }
            });

            binding.produceAcceptTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status = ApprovalActivity.ACCEPT;
                    RemarkDialog.show(getActivity(), "请输入通过备注", remarkComplexListener);
                }
            });
        }
    }

    private void setRemarkDialogComplexListener() {
        remarkComplexListener = new RemarkDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(ExecuteRequestBody data) {
                String remark = data.getReason();
                viewModel.approval(businessId, businessType, remark, status, type);
            }
        };
    }


    private void setProduceDataComplex(FocusProduceBean data) {
        produceBean = data;
        initViewPager();
        //暂停状态
        isProducePause = ProductStatusType.TYPE_PRODUCE_EXCEPTION == data.getStatusType();
        //生产状态
        isProduceIng = ProductStatusType.TYPE_PRODUCE_ING == data.getStatusType();
        binding.tvActivateBtn.setVisibility(isFooterBtnShow && isProducePause
                && CommonUtils.isShowButton(ConstantValue.PRODUCE_APPROVE_ACTIVATE) ? View.VISIBLE : View.GONE);
        binding.llApplyPanel.setVisibility(isFooterBtnShow && isProduceIng
                && CommonUtils.isShowButton(ConstantValue.PRODUCE_PAUSE_AND_FINISH) ? View.VISIBLE : View.GONE);

        planFragment.setProduceData(data);

        binding.tvProductName.setText(data.getName());
        binding.tvProductCode.setText(data.getCode());
        binding.tvDutyUnit.setText(data.getPrincipalDept());

        //设置生产状态
        binding.tvProduceStatus.setText(data.getStatusText());
        binding.tvProduceStatus.setBackgroundResource(data.getStatusTextBg());
        int color_blue_ff1b97fc = data.getStatusTextColor();
        binding.tvProduceStatus.setTextColor(getActivity().getResources().getColor(color_blue_ff1b97fc, null));

        binding.tvProjectName.setText(data.getProjectName());
        binding.tvProjectCode.setText(data.getProjectCode());

        //暂停--其他
        binding.labelActualStart.setText(isProducePause ? "实际暂停生产时间：" : "实际开始生产时间：");
        binding.labelPlanStart.setText(isProducePause ? "实际开始生产时间：" : "计划生产开始时间：");
        CalenderUtils calenderUtils = CalenderUtils.getInstance();
        Date pauseDate = calenderUtils.parseSmartFactoryDateStringFormat(data.getPauseTime());
        String pauseTime = calenderUtils.getDateByDate(pauseDate.getTime());
        binding.tvActualStart.setText(isProducePause ? pauseTime : data.getActualStartTime());
        binding.tvPlanStart.setText(isProducePause ? data.getActualStartTime() : data.getPlanStartTime());


        binding.tvActualComplete.setText(isProducePause ? "暂停中" :
                (ProductStatusType.TYPE_PRODUCE_COMPLETE == data.getStatusType()
                        || ProductStatusType.TYPE_PRODUCE_DELAY_NOT_FINISH == data.getStatusType())
                        ? data.getActualEndTime() : "生产中");
        binding.tvActualComplete.setVisibility(ProductStatusType.TYPE_CONTRACT_PAUSE == data.getStatusType() ? View.GONE : View.VISIBLE);
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
                        body.setId(produceBean.getPlanId());
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
                LogUtils.showLog("tv_ok....onCommitInfo");
                content.setEquipmentId(produceBean.getEquipmentId());
                content.setId(produceBean.getPlanId());
                String str = GsonUtil.objectToString(content);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);
                viewModel.postExecuteProduceInfo(requestBody);
            }

            @Override
            public void onSendClick(AddSendListAdapter sendListAdapter, AddCopyListAdapter copyListAdapter) {
                LogUtils.showLog("size=" + (sendListAdapter == null ? "new ArrayList<>()" : sendListAdapter.getList().size()));
                PickStaffsActivity.startForResult(FocusProduceDetailFragment.this,
                        REQUEST_SEND_SELECT_PERSON,
                        "请选审批送人",
                        sendListAdapter == null ? new ArrayList<>() : sendListAdapter.getList(),
                        CommonUtils.getCurrentStaffList(),
                        copyListAdapter == null ? new ArrayList<>() : copyListAdapter.getList(),
                        ConstantValue.CONST_MAX_STAFF_COUNT);
            }

            @Override
            public void onCopyClick(AddCopyListAdapter addCopyListAdapter, AddSendListAdapter sendListAdapter) {
                PickStaffsActivity.startForResult(FocusProduceDetailFragment.this,
                        REQUEST_COPY_SELECT_PERSON,
                        "请选审抄送人",
                        addCopyListAdapter == null ? new ArrayList<>() : addCopyListAdapter.getList(),
                        CommonUtils.getCurrentStaffList(),
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

    public FocusProduceBean getProduceBean() {
        return produceBean;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }


}
