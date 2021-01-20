package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentExploreContentBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceStatusAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @description: 我的关注--生产详情
 * @author: 许建宁
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusFragment extends BaseDataBindingFragment<FragmentExploreContentBinding, FocusProduceViewModel> {

    public static final String TAG = FocusProduceStatusFragment.class.getSimpleName();
    private FocusProduceBean produceBean;
    private View footerView;
    private FrameLayout llTempEmptyView;
    private BaseRecyclerViewAdapter adapter;
    private List<ProduceStateBean> listData = new ArrayList<>();

    public static FocusProduceStatusFragment newInstance(FocusProduceBean data) {
        Bundle bundle = new Bundle();
        FocusProduceStatusFragment fragment = new FocusProduceStatusFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_explore_content;
    }

    @Override
    protected void initialization() {
        Bundle arguments = getArguments();
        produceBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);
        if (produceBean == null) {
            return;
        }

        viewModel.getProduceStateList(produceBean.getPlanId());
        viewModel.getStatusListEvent().observe(getActivity(), new Observer<List<ProduceStateBean>>() {
            @Override
            public void onChanged(List<ProduceStateBean> data) {
                dealStateDataList(data);
                LogUtils.showLog("getStatusListEvent=" + data.size());
                llTempEmptyView.setVisibility(data.size() == 0 ? View.VISIBLE : View.GONE);
            }
        });
    }
    /**
     * 处理嵌套数据->列表数据
     *
     * @param data
     */
    private void dealStateDataList(List<ProduceStateBean> data) {
        List<ProduceStateBean> reverseList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ProduceStateBean parentBean = data.get(i);
            List<ProduceStateBean> texamineFlows = parentBean.getTexamineFlows();
            if (texamineFlows != null && texamineFlows.size() != 0) {
                Collections.reverse(texamineFlows);
                for (int ik = 0; ik < texamineFlows.size(); ik++) {
                    ProduceStateBean produceStateBean = texamineFlows.get(ik);
                    produceStateBean.setArType(parentBean.getArType());
                    reverseList.add(produceStateBean);
                }
            }
            reverseList.add(parentBean);
        }
        setListData(reverseList);
    }

    private void setListData(List<ProduceStateBean> dataList) {
        listData.clear();
        listData.addAll(dataList);
        FocusProduceStatusAdapter listAdapter = new FocusProduceStatusAdapter(getContext(), listData,
                R.layout.item_focus_produce_status);

        adapter = new BaseRecyclerViewAdapter(listAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setLoadMoreEnabled(false);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        addContentFooterView();
    }

    /**
     * 添加列表FooterView,增加拖动范围
     */
    private void addContentFooterView() {
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.coordinator_empty_view, binding.baseRecycleView, false);
        llTempEmptyView = footerView.findViewById(R.id.layout_root_view);
        footerView.findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getProduceStateList(produceBean.getPlanId());
            }
        });

        adapter.addFooterView(footerView);
    }



    @Override
    protected int getVariableId() {
        return 0;
    }


}
