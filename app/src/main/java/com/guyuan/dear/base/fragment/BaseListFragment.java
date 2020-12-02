package com.guyuan.dear.base.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.android.material.button.MaterialButton;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.focus.assess.adapter.AssessListAdapter;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;
import tl.com.easy_recycleview_library.interfaces.OnRefreshListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/7
 * @company : 固远（深圳）信息技术有限公司
 **/
public abstract class BaseListFragment<T, VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseDataBindingFragment<VB, VM> {

    protected BaseRecyclerView recycleView;
    protected View empty_view;
    protected ImageView no_data_iv;
    protected TextView tv_empty;
    protected FrameLayout list_container;
    protected MaterialButton tv_refresh;
    private int emptyImgID = R.mipmap.ic_no_data;
    private String emptyTip = ConstantValue.TIP_NO_DATA;

    public final int LOAD_MORE = 0X0100;
    public final int REFRESH = 0X0101;
    protected List<T> listData = new ArrayList<T>();
    /**
     * 一页拉取的数据条数
     */
    protected final int PAGE_SIZE = 30;
    protected final int FIRST_PAGE = 1;
    /**
     * 当前页数
     */
    protected int currentPage = FIRST_PAGE;
    protected int currentType = REFRESH;
    protected BaseRecyclerViewAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initialization() {
        list_container = rootView.findViewById(R.id.list_container);
        recycleView = rootView.findViewById(R.id.base_recycleView);
        empty_view = rootView.findViewById(R.id.empty_view);
        no_data_iv = rootView.findViewById(R.id.no_data_iv);
        tv_empty = rootView.findViewById(R.id.tv_empty);
        tv_refresh = rootView.findViewById(R.id.tv_refresh);
        tv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        recycleView.setEmptyView(empty_view);
        initView();
        setEmptyView();
        canPull();
        canLoadMore();
        setListCompleteObserver();
    }


    protected abstract void initView();

    protected abstract void refresh();

    protected abstract void loadMore();

    protected abstract boolean isPullEnable();

    protected abstract boolean isLoadMoreEnable();

    /**
     * 是否开启下拉刷新 (默认开启)
     */
    protected void canPull() {
        if (isPullEnable()) {
            recycleView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    currentType = REFRESH;
                    refresh();
                }
            });
        }
        recycleView.setPullRefreshEnabled(isPullEnable());
    }

    //设置列表获取数据完成监听
    private void setListCompleteObserver() {
        if ((isPullEnable() || isLoadMoreEnable() && viewModel != null)) {
            viewModel.getListComplete().observe(this, new Observer<Void>() {
                @Override
                public void onChanged(Void o) {
                    recycleView.refreshComplete(PAGE_SIZE);
                }
            });
        }
    }


    /**
     * 是否开启上拉加载 (默认开启)
     */
    protected void canLoadMore() {
        if (isLoadMoreEnable()) {
            recycleView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    currentType = LOAD_MORE;
                    loadMore();
                }
            });
        }
        recycleView.setLoadMoreEnabled(isLoadMoreEnable());
    }

    public void setListData(List<T> dataList) {
        if (dataList == null) {
            return;
        }

        switch (currentType) {
            case REFRESH:
                if (adapter != null) {
                    listData.clear();
                    listData.addAll(dataList);
                    adapter.refreshData();
                    recycleView.refreshComplete(PAGE_SIZE);
                }
                LogUtils.showLog("REFRESH....dataList=" + dataList.size());
                break;

            case LOAD_MORE:
                if (adapter != null) {
                    listData.addAll(dataList);
                    adapter.refreshData();
                    recycleView.refreshComplete(PAGE_SIZE);
                }
                LogUtils.showLog("LOAD_MORE.....dataList=" + dataList.size());
                break;
            default:
        }
    }

    protected void setContainerBackground(int color) {
        if (color <= 0) {
            list_container.setBackgroundColor(getResources().getColor(R.color.bg_window));
        } else {
            list_container.setBackgroundColor(getResources().getColor(color));
        }
    }

    protected void setEmptyView() {
        no_data_iv.setImageResource(emptyImgID);
        tv_empty.setText(emptyTip);
    }

    protected void setEmptyResByID(int resID) {
        this.emptyImgID = resID;
    }

    protected void setEmpty_tip(String tip) {
        this.emptyTip = tip;
    }

    /**
     * 添加单个header
     *
     * @param baseRecyclerViewAdapter adapter
     * @param view                    header
     */
    protected void addHeader(BaseRecyclerViewAdapter baseRecyclerViewAdapter, View view) {
        baseRecyclerViewAdapter.addHeaderView(view);
    }

    /**
     * 添加多个header
     *
     * @param baseRecyclerViewAdapter adapter
     * @param views                   headers
     */
    protected void addHeaders(BaseRecyclerViewAdapter baseRecyclerViewAdapter, @NonNull List<View>
            views) {
        for (View view : views) {
            baseRecyclerViewAdapter.addHeaderView(view);
        }
    }

    /**
     * 添加单个footer
     *
     * @param baseRecyclerViewAdapter adapter
     * @param footer                  footer
     */
    protected void addFooter(BaseRecyclerViewAdapter baseRecyclerViewAdapter, View footer) {
        baseRecyclerViewAdapter.addFooterView(footer);
    }

    /**
     * 添加多个footer
     *
     * @param baseRecyclerViewAdapter adapter
     * @param footers                 footer
     */
    protected void addFooters(BaseRecyclerViewAdapter baseRecyclerViewAdapter, List<View> footers) {
        for (View view : footers) {
            baseRecyclerViewAdapter.addFooterView(view);
        }
    }


    protected <T extends BaseDBRecycleAdapter> void setDefaultAdapter(T listAdapter) {
        adapter = new BaseRecyclerViewAdapter(listAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
    }
}
