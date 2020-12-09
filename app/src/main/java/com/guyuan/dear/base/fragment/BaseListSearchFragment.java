package com.guyuan.dear.base.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.SearchBar;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.ToastUtils;

import java.util.Date;

/**
 * @description: 列表页面，带头部部件：搜索栏
 * @author: Jannonx
 * @since: 2020/9/17 11:14
 * @company: 固远（深圳）信息技术有限公司
 */
public abstract class BaseListSearchFragment<T, VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseListFragment<T, VB, VM> {


    protected Date[] dates = new Date[2];
    protected CalenderUtils calenderUtils;

    protected SearchBar searchBar;
    protected String searchContent = "";

    @Override
    public int getLayoutID() {
        return R.layout.fragment_list_search;
    }


    @Override
    protected void initView() {
        searchBar = rootView.findViewById(R.id.searchBar);
        init();
        initListener();
    }


    protected void initListener() {
        searchBar.setSearchListener(new SearchBar.OnSearchListener() {
            @Override
            public void onSearch(String searchContent) {
                if (TextUtils.isEmpty(searchContent)) {
                    ToastUtils.showShort(getContext(), ConstantValue.TIP_SEARCH);
                } else {
                    currentType = REFRESH;
                    refresh();
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchContent = s.toString();
                if (TextUtils.isEmpty(searchContent)) {
                    refresh();
                }
            }
        });

    }

    protected abstract void init();

}