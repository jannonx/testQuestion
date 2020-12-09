package com.guyuan.dear.customizeview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;

import com.guyuan.dear.R;

/**
 * @author : 唐力
 * @description :通用搜索栏
 * @since: 2020/12/9 11:22
 * @company : 固远（深圳）信息技术有限公司
 **/

public class SearchBar extends RelativeLayout {

    private AppCompatEditText et_search;
    private AppCompatCheckedTextView tv_search_btn;
    private OnSearchListener searchListener;

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.layout_search_bar, this);
        initView();
    }

    private void initView() {
        et_search = findViewById(R.id.et_search);
        tv_search_btn = findViewById(R.id.tv_search_btn);
        tv_search_btn.setEnabled(false);
        tv_search_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent = et_search.getText().toString();
                if (searchListener != null) {
                    searchListener.onSearch(searchContent);
                }
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (searchListener != null) {
                    searchListener.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchListener != null) {
                    searchListener.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString().trim();
                if (TextUtils.isEmpty(content)) {
                    tv_search_btn.setEnabled(false);
                } else {
                    tv_search_btn.setEnabled(true);
                }

                if (searchListener != null) {
                    searchListener.afterTextChanged(s);
                }
            }
        });
    }

    //设置搜索栏提示语
    public void setHint(String hint) {
        if (!TextUtils.isEmpty(hint)) {
            et_search.setHint(hint);
        }
    }

    //设置搜索栏内容
    public void setSearchContent(String searchContent) {
        if (!TextUtils.isEmpty(searchContent)) {
            et_search.setText(searchContent);
        }
    }

    public void setSearchListener(OnSearchListener onSearchListener) {
        this.searchListener = onSearchListener;
    }

    public interface OnSearchListener {
        void onSearch(String searchContent);

        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable s);
    }
}