package com.guyuan.dear.base.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.ToastUtils;
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
public abstract class BaseListSearchFragment<T, VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseListFragment<T, VB, VM> {


    protected Date[] dates = new Date[2];
    protected CalenderUtils calenderUtils;

    protected TextView mTvSelectStartTime, mTvSelectEndTime;
    protected AppCompatTextView searchBtn;
    protected AppCompatEditText etSearch;


    @Override
    public int getLayoutID() {
        return R.layout.fragment_list_search;
    }


    @Override
    protected void initView() {
        etSearch = rootView.findViewById(R.id.et_search);
        searchBtn = rootView.findViewById(R.id.tv_search_btn);
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
//                ivClearBtn.setVisibility(TextUtils.isEmpty(editable.toString()) ? View.GONE : View.VISIBLE);
                if (TextUtils.isEmpty(editable.toString())) {
                    etSearch.clearFocus();
                    editEmptyChange();
                }
                editTextChanged(editable.toString());
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = etSearch.getText().toString();
                if (TextUtils.isEmpty(key)) {
                    ToastUtils.showShort(getContext(), ConstantValue.TIP_SEARCH);
                } else {
                    onSearch(key);
                }

            }
        });

    }

    protected abstract void init();

    /**
     * 点击按钮搜索
     *
     * @param text 搜索内容
     */
    protected void onSearch(String text) {

    }

    /**
     * 清空editText,刷新列表
     */
    protected void editEmptyChange() {

    }

    /**
     * 实时搜索
     *
     * @param text 搜索内容
     */
    protected void editTextChanged(String text) {

    }

}