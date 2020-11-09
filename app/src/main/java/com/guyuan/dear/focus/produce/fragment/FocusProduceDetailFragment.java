package com.guyuan.dear.focus.produce.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.TagStaffAdapter;
import com.guyuan.dear.databinding.FragmentFocusProduceDetailBinding;
import com.guyuan.dear.focus.client.adapter.TabAdapter;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceOverViewBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.focus.produce.ui.FocusProduceDetailActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.produce.fragment.ProduceApplyDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.RequestBody;

import static com.guyuan.dear.utils.ConstantValue.PAGE_SIZE;

/**
 * @description: 我的关注--客户详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceDetailFragment extends BaseDataBindingFragment<FragmentFocusProduceDetailBinding, FocusProduceViewModel> {

    public static final String TAG = "FocusProduceDetailFragment";
    private static final int REQUEST_SEND_SELECT_PERSON = 0x001;
    private static final int REQUEST_COPY_SELECT_PERSON = 0x002;
    private FocusProduceViewModel viewModel;
    private FocusProduceStatusFragment statusFragment;
    private FollowProducePlanFragment planFragment;


    private int startPosition = 0;//起始选中位置
    private String[] titleList;
    private int selectedTextColor, unSelectedTextColor;
    private FocusProduceBean produceBean;

    private ProduceApplyDialog dialog;


    public static FocusProduceDetailFragment newInstance(FocusProduceBean data) {
        Bundle bundle = new Bundle();
        FocusProduceDetailFragment fragment = new FocusProduceDetailFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_detail;
    }

    @Override
    protected void initialization() {
        initHeaderView();
        initViewPager();
        initData();
    }

    private void initData() {
        Bundle arguments = getArguments();
        produceBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        viewModel.getBasicInfoById(produceBean.getEquipmentId());

        viewModel.getBasicInfoEvent().observe(getActivity(), new Observer<FocusProduceBean>() {
            @Override
            public void onChanged(FocusProduceBean data) {
//                LogUtils.showLog("data" + data.getCompletionRate());
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

    private void showApplyDialog() {
        ProduceApplyDialog.OnDialogClickListener dialogListener = new ProduceApplyDialog.OnDialogClickListener() {
            @Override
            public void onCommitInfo(String content) {

            }

            @Override
            public void onSendClick(TagStaffAdapter adapter) {
                PickStaffsActivity.startForResult(FocusProduceDetailFragment.this,
                        REQUEST_SEND_SELECT_PERSON,
                        "请选审批送人",
                        adapter == null ? new ArrayList<>() : adapter.getTagDataList(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        ConstantValue.CONST_MAX_STAFF_COUNT);
            }

            @Override
            public void onCopyClick(TagStaffAdapter adapter) {
                PickStaffsActivity.startForResult(FocusProduceDetailFragment.this,
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

    private void setProduceData(FocusProduceBean data) {
        planFragment.setProduceData(data);
        binding.tvProductName.setText(data.getName());
        binding.tvProductCode.setText(data.getCode());
        binding.tvDutyUnit.setText(data.getPrincipalDept());

        binding.tvProjectName.setText(data.getProjectName());

        binding.tvActualStart.setText(data.getActualStartTime());
        binding.tvPlanStart.setText(data.getPlanStartTime());
        binding.tvActualComplete.setText(data.getActualEndTime());
        binding.tvPlanComplete.setText(data.getPlanEndTime());


    }

    private void initHeaderView() {
//        ClientCompanyBean clientCompanyBean = new ClientCompanyBean();
//        List<ClientContactBean> childList = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            ClientContactBean contactBean = new ClientContactBean();
//            contactBean.setPhone("ClientContactBean" + i);
//            childList.add(contactBean);
//        }
//        clientCompanyBean.setList(childList);
//        ClientPhoneHeaderAdapter listAdapter = new ClientPhoneHeaderAdapter(getContext(), clientCompanyBean.getList(),
//                R.layout.item_focus_client_phone);
//        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(listAdapter);
//        binding.baseRecycleView.setAdapter(adapter);
//        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.baseRecycleView.setLoadMoreEnabled(false);
//        binding.baseRecycleView.setPullRefreshEnabled(false);
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//            }
//        });
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
        statusFragment = FocusProduceStatusFragment.newInstance();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            FocusProduceDetailActivity activity = (FocusProduceDetailActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
