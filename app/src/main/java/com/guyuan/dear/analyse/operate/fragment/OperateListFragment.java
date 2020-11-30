package com.guyuan.dear.analyse.operate.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.operate.data.OperateViewModel;
import com.guyuan.dear.databinding.FragmentAnalyseOperateOverviewBinding;

/**
 * @description: 我的关注--运营效益--概览
 * @author: 许建宁
 * @since: 2020/11/30 11:25
 * @company: 固远（深圳）信息技术有限公司
 */
public class OperateListFragment extends BaseDataBindingFragment<FragmentAnalyseOperateOverviewBinding, OperateViewModel>{

    public static final String TAG = OperateListFragment.class.getSimpleName();

    public static OperateListFragment newInstance() {
        Bundle args = new Bundle();
        OperateListFragment fragment = new OperateListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_analyse_operate_overview;
    }

    @Override
    protected void initialization() {

//        ProjectInstallAdapter checkGoodsAdapter = new ProjectInstallAdapter(getContext(),
//                listData, R.layout.item_install_project);
//        adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
//        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.baseRecycleView.setAdapter(adapter);
//        binding.baseRecycleView.setPullRefreshEnabled(false);
//        binding.baseRecycleView.setLoadMoreEnabled(false);
//
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                InstallDebugBean installDebugBean = listData.get(position);
//                detailData.setId(installDebugBean.getId());
//                FocusSiteExplorationDetailActivity.start(getContext(), detailData);
//            }
//        });
//
//        viewModel.getProjectSiteOverViewData();
//        viewModel.getProjectSiteOverViewEvent().observe(getActivity(), new Observer<ProjectOverViewBean>() {
//            @Override
//
//            public void onChanged(ProjectOverViewBean data) {
//                setData(data);
//            }
//        });
    }




    @Override
    protected int getVariableId() {
        return 0;
    }


}
