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

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.SimpleRecyclerViewAdapter;
import com.guyuan.dear.databinding.DialogSimpleRecyclerViewBinding;
import com.guyuan.dear.utils.ScreenUtils;


import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 列表弹框选择器，数据为List<String>
 * @author:Jannonx
 * @date: 2020/5/25 12:02
 */
public class SimpleRecyclerViewDialog extends Dialog {

//  @BindView(R.id.base_recycleView)
//  BaseRecyclerView mBaseRecycleView;
//  @BindView(R.id.tv_cancel)
//  AppCompatTextView mTvCancel;

    protected List<String> listData = new ArrayList<>();
    private Context context;//上下文
    private OnSelectItemClickListener listener;
    private DialogSimpleRecyclerViewBinding viewBinding;

    public static void show(Context context, List<String> listData, OnSelectItemClickListener listener) {
        if (listData == null || listData.isEmpty()) {
            Toast.makeText(context, "暂无数据", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleRecyclerViewDialog dialog = new SimpleRecyclerViewDialog(context);
        dialog.setData(listData, listener);
        dialog.show();
    }

    private SimpleRecyclerViewDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        //不留边距
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        dialogWindow.setGravity(Gravity.BOTTOM);//设置dialog显示居中
//    dialogWindow.setWindowAnimations();设置动画效果
//        setContentView(R.layout.dialog_simple_recycler_view);
//    ButterKnife.bind(this);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_simple_recycler_view, null, false);
        setContentView(viewBinding.getRoot());//核心代码
//      LayoutInflater.from(context).inflate(R.layout.dialog_simple_recycler_view,get)
//      DataBindingUtil.bind(this)
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失

        initData();
    }

    private void initData() {
//        viewBinding.
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewBinding.baseRecycleView.getLayoutParams();
        layoutParams.height = listData.size() <= 5 ? LinearLayout.LayoutParams.WRAP_CONTENT :
                ScreenUtils.dip2px(context, 342);
        viewBinding.baseRecycleView.setLayoutParams(layoutParams);
        SimpleRecyclerViewAdapter simpleRecyclerViewAdapter = new SimpleRecyclerViewAdapter(context, listData,
                R.layout.item_simple_recycler_view);
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


    private void setData(List<String> dataList, OnSelectItemClickListener listener) {
        this.listData.addAll(dataList);
        this.listener = listener;

    }

    public interface OnSelectItemClickListener {
        void onItemClick(String bean, int position);
    }


}
