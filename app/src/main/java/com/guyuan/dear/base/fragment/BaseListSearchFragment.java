package com.guyuan.dear.base.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.utils.CalenderUtils;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;
import tl.com.easy_recycleview_library.interfaces.OnRefreshListener;

/**
 * @description: 列表页面，带头部部件：搜索栏
 * @author: Jannonx
 * @since: 2020/9/17 11:14
 * @company: 固远（深圳）信息技术有限公司
 */
public abstract class BaseListSearchFragment<T, VB extends ViewDataBinding> extends BaseDataBindingFragment<VB> {
    public final int LOAD_MORE = 0X0100;
    public final int REFRESH = 0X0101;
    protected List<T> listData = new ArrayList<T>();
    protected BaseRecyclerViewAdapter adapter;
    protected final int PAGE_SIZE = 30;//一页拉取的数据条数
    protected final int FIRST_PAGE = 1;
    protected int currentPage = FIRST_PAGE;//当前页数
    protected int currentType = REFRESH;


    protected Date[] dates = new Date[2];
    protected CalenderUtils calenderUtils;

    protected TextView mTvSelectStartTime, mTvSelectEndTime;
    protected AppCompatImageView ivClearBtn;
    protected BaseRecyclerView recycleView;
    protected View emptyView;
    protected AppCompatEditText etSearch;

    @Override
    public void initialization() {

        recycleView = rootView.findViewById(R.id.base_recycleView);
        emptyView = rootView.findViewById(R.id.empty_view);
        etSearch = rootView.findViewById(R.id.et_search);
        ivClearBtn = rootView.findViewById(R.id.iv_clear);

        recycleView.setEmptyView(emptyView);
//        initDate();
        initListener();
        canPull();
        canLoadMore();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_list_search;
    }

    private void initDate() {
        calenderUtils = CalenderUtils.getInstance();
        //一年
        dates[0] = calenderUtils.getSettingDate(CalenderUtils.getPassOneYear(), 0, 0, 0);
        dates[1] = calenderUtils.getSettingDate(new Date(), 23, 59, 59);

        mTvSelectStartTime.setText(calenderUtils.toSmartFactoryDateFormatByDay(dates[0].getTime()));
        mTvSelectEndTime.setText(calenderUtils.toSmartFactoryDateFormatByDay(dates[1].getTime()));
    }


    protected void initListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ivClearBtn.setVisibility(TextUtils.isEmpty(editable.toString()) ? View.GONE : View.VISIBLE);
                if (TextUtils.isEmpty(editable.toString())) {
                    etSearch.clearFocus();
                }
                editTextChanged(editable.toString());
            }
        });


    }


    protected void editTextChanged(String text) {

    }


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

    /**
     * 填充数据
     *
     * @param dataList 列表数据
     */
    public void setListData(List<T> dataList) {
        switch (currentType) {
            case REFRESH:
                listData.clear();
                listData.addAll(dataList);
                adapter.refreshData();
                recycleView.refreshComplete(PAGE_SIZE);
                break;

            case LOAD_MORE:
                listData.addAll(dataList);
                adapter.refreshData();
                recycleView.refreshComplete(PAGE_SIZE);
                break;

            default:
        }
    }
} 