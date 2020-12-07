package com.guyuan.dear.customizeview.searchview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.qc.views.qcSearchList.QcSearchListActivity;

import static com.guyuan.dear.focus.qc.views.qcSearchList.QcSearchListActivity.SEARCH_TYPE_ALL;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/4 12:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcReportsSearchView extends FrameLayout {
    private AppCompatEditText edtSearchContent;
    private AppCompatCheckedTextView tvClickSearch;
    private int searchType = SEARCH_TYPE_ALL;

    public QcReportsSearchView(@NonNull Context context) {
        this(context, null);
    }

    public QcReportsSearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QcReportsSearchView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_search_bar, this, false);
        edtSearchContent = inflate.findViewById(R.id.et_search);
        edtSearchContent.setHint("请输入产品名称/代号/出厂编号");
        tvClickSearch = inflate.findViewById(R.id.tv_search_btn);
        tvClickSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWords = edtSearchContent.getText().toString();
                if(TextUtils.isEmpty(keyWords)){
                    return;
                }
                QcSearchListActivity.start(getContext(),"质量报告搜索",keyWords,searchType);
            }
        });
        addView(inflate);
    }

    /**
     * 搜索qc报告
     * @param searchType {@link QcSearchListActivity#SEARCH_TYPE_ALL}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_PASS_REPORTS}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_REJECTED_REPORTS}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_MY_REPORTS}
     */
    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
