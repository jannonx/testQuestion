package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.databinding.FragmentFocusInstallationDebugAllBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.projectsite.activity.FocusInstallationDebugDetailActivity;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 我的关注--工程现场--货物清点报告--详情页面--到货清单
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallationDebugAllFragment extends BaseDataBindingFragment<FragmentFocusInstallationDebugAllBinding, FocusProjectSiteViewModel> {

    public static final String TAG = InstallationDebugAllFragment.class.getSimpleName();
    private List<SimpleTabBean> listData=new ArrayList<>();

    public static InstallationDebugAllFragment newInstance() {
        Bundle bundle = new Bundle();
        InstallationDebugAllFragment fragment = new InstallationDebugAllFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_installation_debug_all;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            SimpleTabBean simpleTabBean = new SimpleTabBean();
            listData.add(simpleTabBean);
        }
        ProjectReportAdapter checkGoodsAdapter = new ProjectReportAdapter(getContext(),
                listData, R.layout.item_install_project);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FocusInstallationDebugDetailActivity.start(getContext(), ProjectReportType.TYPE_INSTALLATION_DEBUG);
            }
        });
    }


    private void setProduceData(FocusProduceBean data) {


    }

    @Override
    protected int getVariableId() {
        return 0;
    }


}
