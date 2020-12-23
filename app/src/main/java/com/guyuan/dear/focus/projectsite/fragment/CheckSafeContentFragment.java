package com.guyuan.dear.focus.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentCheckSafeContentBinding;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.projectsite.adapter.ProjectReportAdapter;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;


/**
 * @description: 我的关注--工程现场--货物清点报告--详情页面--到货清单
 * @author: 许建宁
 * @since: 2020/11/11 11:20
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckSafeContentFragment extends BaseDataBindingFragment<FragmentCheckSafeContentBinding, FocusProjectSiteViewModel> {

    public static final String TAG = CheckSafeContentFragment.class.getSimpleName();
    private List<SiteExploreBean> listData=new ArrayList<>();

    public static CheckSafeContentFragment newInstance() {
        Bundle bundle = new Bundle();
        CheckSafeContentFragment fragment = new CheckSafeContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_check_safe_content;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        for (int i = 0; i < 2; i++) {
            SiteExploreBean simpleTabBean = new SiteExploreBean();
            listData.add(simpleTabBean);
        }
        ProjectReportAdapter checkGoodsAdapter = new ProjectReportAdapter(getContext(),
                listData, R.layout.item_goods_list);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(checkGoodsAdapter);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
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
