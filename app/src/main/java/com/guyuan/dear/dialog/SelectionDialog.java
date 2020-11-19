package com.guyuan.dear.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.SelectionDialogAdapter;
import com.guyuan.dear.base.adapter.SimpleRecyclerViewAdapter;
import com.guyuan.dear.databinding.DialogSimpleRecyclerViewBinding;
import com.guyuan.dear.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description:
 * @author:廖华凯
 * @date: 2020/5/25 12:02
 */
public abstract class SelectionDialog<T> extends Dialog {

    protected List<T> listData;
    private Context context;
    private OnSelectItemClickListener<T> listener;
    private DialogSimpleRecyclerViewBinding viewBinding;


    public SelectionDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        listData = setListData();
        listener = setOnItemClick();

    }

    public abstract List<T> setListData();
    public abstract OnSelectItemClickListener<T> setOnItemClick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        //不留边距
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        dialogWindow.setGravity(Gravity.BOTTOM);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_simple_recycler_view, null, false);
        setContentView(viewBinding.getRoot());
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);
        initData();
    }

    public abstract String getItemLabel(T item);

    private void initData() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewBinding.baseRecycleView.getLayoutParams();
        layoutParams.height = listData.size() <= 5 ? LinearLayout.LayoutParams.WRAP_CONTENT :
                ScreenUtils.dip2px(context, 342);
        viewBinding.baseRecycleView.setLayoutParams(layoutParams);
        SelectionDialogAdapter<T> simpleRecyclerViewAdapter = new SelectionDialogAdapter<T>(context, listData,
                R.layout.item_simple_recycler_view){

            @Override
            public String getItemLabel(T item) {
                return SelectionDialog.this.getItemLabel(item);
            }
        };
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(simpleRecyclerViewAdapter);
        viewBinding.baseRecycleView.setLayoutManager(new LinearLayoutManager(context));
        viewBinding.baseRecycleView.setAdapter(adapter);
        viewBinding.baseRecycleView.setPullRefreshEnabled(false);
        viewBinding.baseRecycleView.setLoadMoreEnabled(false);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (listener != null) {
                    listener.onItemClick(listData.get(position), position);
                    dismiss();
                }
            }
        });

        viewBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public interface OnSelectItemClickListener<T> {
        void onItemClick(T bean, int position);
    }


}
