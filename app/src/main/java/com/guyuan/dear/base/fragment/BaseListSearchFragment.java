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
public abstract class BaseListSearchFragment<T, VB extends ViewDataBinding> extends BaseListFragment<T, VB> {


    protected Date[] dates = new Date[2];
    protected CalenderUtils calenderUtils;

    protected TextView mTvSelectStartTime, mTvSelectEndTime;
    protected AppCompatImageView ivClearBtn;
    protected BaseRecyclerView recycleView;
    protected AppCompatEditText etSearch;


    @Override
    public int getLayoutID() {
        return R.layout.fragment_list_search;
    }


    @Override
    protected void initView() {
        etSearch = rootView.findViewById(R.id.et_search);
        ivClearBtn = rootView.findViewById(R.id.iv_clear);
        //    initDate();
        initListener();
        init();
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

    protected abstract void init();

    protected void editTextChanged(String text) {

    }

}