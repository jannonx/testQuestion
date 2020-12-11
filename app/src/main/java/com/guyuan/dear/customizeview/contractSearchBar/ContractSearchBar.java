package com.guyuan.dear.customizeview.contractSearchBar;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.view.contractSearchList.ContractSearchListActivity;

import static com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment.STATUS_TYPE_TOTAL;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/3 14:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractSearchBar extends FrameLayout {
    private AppCompatEditText edtSearchContent;
    private AppCompatCheckedTextView tvSubmit;
    private int searchType = STATUS_TYPE_TOTAL;

    public ContractSearchBar(@NonNull Context context) {
        this(context, null);
    }

    public ContractSearchBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContractSearchBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_search_bar, this, false);
        edtSearchContent = inflate.findViewById(R.id.et_search);
        edtSearchContent.setHint("请输入客户名称或合同编号");
        tvSubmit = inflate.findViewById(R.id.tv_search_btn);
        tvSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtSearchContent.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    ContractSearchListActivity.start(getContext(), "合同搜索", content, searchType);
                }
            }
        });
        edtSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvSubmit.setEnabled(!TextUtils.isEmpty(s.toString()));
            }
        });
        addView(inflate);
    }

    /**
     * @param searchType {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_EXCEPTION}
     *                   {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_TOTAL}
     *                   {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_ON_PAUSE}
     *                   {@link com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment#STATUS_TYPE_RESTART}
     */
    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
