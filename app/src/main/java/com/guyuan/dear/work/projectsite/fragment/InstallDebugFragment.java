package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentWorkInstallationDebugIngBinding;
import com.guyuan.dear.focus.projectsite.activity.FocusSiteExplorationDetailActivity;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.activity.WorkInstallDebugSingleActivity;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--安装调试
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallDebugFragment extends BaseDataBindingFragment<FragmentWorkInstallationDebugIngBinding, WorkProjectSiteViewModel> {

    public static final String TAG = InstallDebugFragment.class.getSimpleName();
    private SiteExploreBean detailData;
    private List<InstallDebugBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;

    public static InstallDebugFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        InstallDebugFragment fragment = new InstallDebugFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_installation_debug_ing;
    }

    @Override
    protected void initialization() {
        EventBus.getDefault().register(this);
        detailData = (SiteExploreBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProjectInstallAdapter installDebugAdapter = new ProjectInstallAdapter(getContext(),
                listData, R.layout.item_install_project);
        adapter = new BaseRecyclerViewAdapter(installDebugAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                InstallDebugBean installDebugBean = listData.get(position);
                if (FunctionModuleType.TYPE_WORK == detailData.getModuleType() &&
                        InstallDebugSatisfyType.TYPE_INSTALL_WAIT == installDebugBean.getInstallDebugSatisfyType()) {
                    WorkInstallDebugSingleActivity.start(getContext(), installDebugBean);
                } else {
                    SiteExploreBean siteExploreBean = new SiteExploreBean();
                    siteExploreBean.setId(installDebugBean.getId());
                    siteExploreBean.setStatus(installDebugBean.getStatus());
                    siteExploreBean.setModuleType(detailData.getModuleType());
                    siteExploreBean.setProjectReportType(ProjectReportType.TYPE_INSTALLATION_DEBUG);
                    FocusSiteExplorationDetailActivity.start(getContext(), siteExploreBean);
                }

            }
        });

        viewModel.getInstallDebugDetailData(detailData.getId());
        viewModel.getInstallDebugDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailData(data);
            }
        });

    }


    private void setDetailData(SiteExploreBean data) {
        data.setProjectReportType(detailData.getProjectReportType());
        data.setModuleType(detailData.getModuleType());
        detailData = data;

        binding.tvProjectName.setText(data.getProjectName());
        binding.labelProjectCode.setText("项目编号：");
        binding.tvProjectCode.setText(data.getProjectNumber());
        binding.labelCheckName.setText("现场监工：");
        binding.tvCheckName.setText(data.getPersonLiableName());


        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(data.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));


        binding.tvCompanyName.setText(data.getCustomerName());
        binding.tvTime.setText(data.getCreateTime());
        binding.tvCompanyLocation.setText(data.getAddress());

        List<InstallDebugBean> checkGoodsListData = data.getAppInstallDebugItemVOList();
        LogUtils.showLog("checkGoodsListData.size=" + checkGoodsListData.size());
        listData.clear();
        listData.addAll(checkGoodsListData);
        adapter.refreshData();

    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInstallDebugMessage(EventInstallDebugRefresh event) {
        ToastUtils.showLong(getContext(), "提交成功");
        viewModel.getInstallDebugDetailData(detailData.getId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }
}
