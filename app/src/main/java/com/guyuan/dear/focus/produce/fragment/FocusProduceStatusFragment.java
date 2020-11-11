package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的关注--生产详情
 * @author: Jannonx
 * @since: 2020/11/2 14:27
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusProduceStatusFragment extends BaseListFragment<ProduceStateBean, FragmentListBinding, FocusProduceViewModel> {

    public static final String TAG = FocusProduceStatusFragment.class.getSimpleName();
    private FocusProduceBean produceBean;

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

        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer_focus_produce_status, null);
        FocusProduceStatusAdapter listAdapter = new FocusProduceStatusAdapter(getContext(), listData,
                R.layout.item_focus_produce_status);

        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        recycleView.setLoadMoreEnabled(false);
        recycleView.setPullRefreshEnabled(false);
        adapter.addFooterView(footerView);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

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
        List<ProduceStateBean> tempList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            ProduceStateBean parentBean = data.get(i);
            tempList.add(parentBean);
            List<ProduceStateBean> parentDataList = parentBean.getTexamineFlows();
            for (int jk = 0; jk < data.size(); jk++) {
                ProduceStateBean childBean = parentDataList.get(jk);
                tempList.add(childBean);
            }
        }
        setListData(tempList);
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
