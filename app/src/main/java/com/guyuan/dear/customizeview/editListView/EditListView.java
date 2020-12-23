package com.guyuan.dear.customizeview.editListView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :可动态增减编辑框的view
 * @since: 2020/11/11 11:57
 * @company : 固远（深圳）信息技术有限公司
 **/

public class EditListView extends ConstraintLayout {
    private String title = "评审内容要点事项";
    private String itemTitle = "评审点";
    private RecyclerView rv;
    private TextView titleTv;
    private TextView addTv;
    private EditListViewAdapter editListViewAdapter;
    private List<EditListViewBean> contentList = new ArrayList<>();

    public EditListView(@NonNull Context context) {
        this(context, null);
    }

    public EditListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EditListView, defStyleAttr, 0);

        title = ta.getString(R.styleable.EditListView_title);
        itemTitle = ta.getString(R.styleable.EditListView_itemTitle);

        ta.recycle();
    }


    @Override
    protected void onFinishInflate() {
        LayoutInflater.from(getContext()).inflate(R.layout.editlistview, this);
        super.onFinishInflate();
        initView();
        setRv();
    }

    private void initView() {
        rv = findViewById(R.id.elv_rv);
        titleTv = findViewById(R.id.elv_title_tv);
        addTv = findViewById(R.id.elv_add_tv);
        addTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editListViewAdapter.add();
            }
        });
        titleTv.setText(title);
    }


    private void setRv() {
        editListViewAdapter = new EditListViewAdapter(getContext(), itemTitle, contentList, R.layout.item_edit);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(editListViewAdapter);
    }

    public EditListViewAdapter getEditListViewAdapter() {
        return editListViewAdapter;
    }

    public void setEditable(boolean editable) {
        addTv.setVisibility(editable ? VISIBLE : GONE);
        editListViewAdapter.setEditable(editable);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        editListViewAdapter.setManager(fragmentManager);
    }
}