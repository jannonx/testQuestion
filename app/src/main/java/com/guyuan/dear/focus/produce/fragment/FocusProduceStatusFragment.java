package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.focus.produce.adapter.FocusProduceStatusAdapter;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.bean.ProduceStateBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description: 我的关注--生产详情
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusFragment extends BaseListFragment<ProduceStateBean, FragmentListBinding, FocusProduceViewModel> {

    public static final String TAG = FocusProduceStatusFragment.class.getSimpleName();
    private FocusProduceBean produceBean;
    private View footerView;

    public static FocusProduceStatusFragment newInstance(FocusProduceBean data) {
        Bundle bundle = new Bundle();
        FocusProduceStatusFragment fragment = new FocusProduceStatusFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        produceBean = (FocusProduceBean) arguments.getSerializable(ConstantValue.KEY_CONTENT);

//        footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_focus_produce_status, null);
        FocusProduceStatusAdapter listAdapter = new FocusProduceStatusAdapter(getContext(), listData,
                R.layout.item_focus_produce_status);

        adapter = new BaseRecyclerViewAdapter(listAdapter);
//        adapter.addFooterView(footerView);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));


        viewModel.getProduceStateList(produceBean.getPlanId());

        viewModel.getStatusListEvent().observe(getActivity(), new Observer<List<ProduceStateBean>>() {
            @Override
            public void onChanged(List<ProduceStateBean> data) {
                dealStateDataList(data);
            }
        });
    }

    /**
     * 处理嵌套数据->列表数据
     *
     * @param data
     */
    private void dealStateDataList(List<ProduceStateBean> data) {
        List<ProduceStateBean> tempListO = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            ProduceStateBean parentBean = data.get(i);
            tempListO.add(parentBean);
            LogUtils.showLog("tempListO="+tempListO.get(i).getProdStatus());
            List<ProduceStateBean> texamineFlows = parentBean.getTexamineFlows();
            if (texamineFlows != null && texamineFlows.size() != 0) {
                tempListO.addAll(texamineFlows);
                LogUtils.showLog("texamineFlows="+tempListO.get(i).getProdStatus());
            }
        }
        List<ProduceStateBean> tempListT = new ArrayList<>();
        for (int i = tempListO.size() - 1; i >= 0; i--) {
            LogUtils.showLog("tempListT="+tempListO.get(i).getProdStatus());
            tempListT.add(tempListO.get(i));
        }

        setListData(tempListT);
    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
