//package com.guyuan.dear.dialog;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.guyuan.dear.R;
//import com.guyuan.dear.base.adapter.TabFlowAdapter;
//import com.guyuan.dear.base.adapter.TabRecyclerViewAdapter;
//import com.guyuan.dear.base.bean.SimpleTabBean;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * @description: Tab+列表弹框
// * @author:Jannonx
// * @date: 2020/5/25 12:02
// */
//public class SimpleTabRecyclerViewDialog extends Dialog {
//
////    @BindView(R.id.tv_all_dep)
////    AppCompatTextView tvAllDep;
////    @BindView(R.id.factory_view)
////    RecyclerView factoryView;
////    @BindView(R.id.tfl_tb)
////    TagFlowLayout tagFlowLayout;
//
//    protected List<SimpleTabBean> listData = new ArrayList<>();
//    protected List<SimpleTabBean> childListData = new ArrayList<>();
//    private Context context;//上下文
//    private OnSelectItemClickListener listener;
//    private TabFlowAdapter childAdapter;
//    private TabRecyclerViewAdapter parentAdapter;
//    private SimpleTabBean childBean;
//    private int parentId = 0;
//    private int childId = -1;//0--所有部门
//
//    private boolean isAllDep;
//
//    public static void show(Context context, List<SimpleTabBean> listData, OnSelectItemClickListener listener) {
//        show(context, listData, listener, false);
//    }
//
//    public static void showAllDep(Context context, List<SimpleTabBean> listData, OnSelectItemClickListener listener) {
//        show(context, listData, listener, true);
//    }
//
//    public static void show(Context context, List<SimpleTabBean> listData, OnSelectItemClickListener listener, boolean isAllDep) {
//        if (listData == null || listData.isEmpty()) {
//            Toast.makeText(context, "暂无数据", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        SimpleTabRecyclerViewDialog dialog = new SimpleTabRecyclerViewDialog(context);
//        dialog.setData(listData, listener, isAllDep);
//        dialog.show();
//    }
//
//    private SimpleTabRecyclerViewDialog(@NonNull Context context) {
//        super(context);
//        this.context = context;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //提前设置Dialog的一些样式
//        Window dialogWindow = getWindow();
//        //不留边距
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
//        dialogWindow.getDecorView().setBackgroundColor(Color.TRANSPARENT);
//        dialogWindow.setGravity(Gravity.BOTTOM);//设置dialog显示居中
////    dialogWindow.setWindowAnimations();设置动画效果
//        setContentView(R.layout.dialog_tab_recycler_view);
//        ButterKnife.bind(this);
//
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.BOTTOM;
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(params);
//        setCanceledOnTouchOutside(true);//点击外部Dialog消失
//
//        initData();
//    }
//
//    private void initData() {
//        //初始化数据
//        tvAllDep.setVisibility(isAllDep ? View.VISIBLE : View.GONE);
//        tvAllDep.setSelected(true);
//        for (int jk = 0; jk < listData.size(); jk++) {
//            if (listData.get(jk).isSelected()) {
////                LogUtils.showLog("parentId000=" + jk);
//                parentId = jk;
//            }
//        }
//
////        LogUtils.showLog("parentId1111=" + parentId);
//        //选中父类下的子列表
//        childListData.addAll(listData.get(parentId).getChildList());
//        //初始化子类索引
//        for (int jk = 0; jk < childListData.size(); jk++) {
//            if (childListData.get(jk).isSelected()) {
//                tvAllDep.setSelected(false);
//                childId = jk;
//                childBean = childListData.get(jk);
//            }
//        }
//
//        //所有部门选中状态
//        if (tvAllDep.isSelected()) {
//            childBean = new SimpleTabBean();
//            childBean.setTitle("所有部门");
//            childBean.setId(0);
//            childId = -1;
//        }
//
//        tvAllDep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvAllDep.setSelected(!tvAllDep.isSelected());
//                if (tvAllDep.isSelected()) {
//                    childBean = new SimpleTabBean();
//                    childBean.setTitle("所有部门");
//                    childBean.setId(0);
//                    childId = -1;
//                } else {
//                    childBean = null;
//                }
//
//                for (SimpleTabBean simpleTabBean : childListData) {
//                    simpleTabBean.setSelected(false);
//                }
//                childAdapter.notifyDataChanged();
//            }
//        });
//
//        //设置布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        parentAdapter = new TabRecyclerViewAdapter(context, listData);
//        factoryView.setLayoutManager(linearLayoutManager);
//        factoryView.setAdapter(parentAdapter);
//        parentAdapter.setOnItemClickListener(new TabRecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(SimpleTabBean item, int position) {
////                SimpleTabBean simpleTabBean = listData.get(position);
//
//                tvAllDep.setSelected(false);
//                parentId = position;
//                childBean = null;
////                LogUtils.showLog("tabBean=" + item.getTitle() + "...tab=" + item.getChildList().size());
////                item.setSelected(true);
////                parentAdapter.notifyDataSetChanged();
//                for (int i = 0; i < listData.size(); i++) {
//                    SimpleTabBean simpleTabBean = listData.get(i);
//                    simpleTabBean.setSelected(i == position);
//                }
//
//                parentAdapter.notifyDataSetChanged();
//
//                childListData.clear();
//                List<SimpleTabBean> childList = item.getChildList();
//
//                for (SimpleTabBean childBean : childList) {
//                    childBean.setSelected(false);
//                }
//                childListData.addAll(item.getChildList());
//                childAdapter.notifyDataChanged();
//            }
//        });
//
//        childAdapter = new TabFlowAdapter(getContext(), childListData);
//        tagFlowLayout.setAdapter(childAdapter);
//        childAdapter.setOnItemClickListener(new TabFlowAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(SimpleTabBean tabBean, int position) {
//                childId = position;
//                childBean = childListData.get(position);
//                tvAllDep.setSelected(false);
//                for (int i = 0; i < childListData.size(); i++) {
//                    SimpleTabBean simpleTabBean = childListData.get(i);
//                    simpleTabBean.setSelected(i == 0);
//                }
//                childAdapter.notifyDataChanged();
//
//            }
//        });
//
//        smoothMoveToPosition(factoryView,parentId);
//        factoryView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
//                    mShouldScroll = false;
//                    smoothMoveToPosition(factoryView, mToPosition);
//                }
//            }
//        });
//
//
//    }
//
//
//    @OnClick({R.id.tv_cancel, R.id.tv_ok})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_cancel:
//                dismiss();
//                break;
//            case R.id.tv_ok:
//                LogUtils.showLog("parentId=" + parentId + "...childId=" + childId
//                    + "..childBean=" + (childBean == null ? "空bean" : childBean.getTitle()));
//                if (childBean == null) {
//                    ToastUtils.showShort(context, "请选择生产部门");
//                }
//
//                if (listener != null && childBean != null) {
//                    listener.onItemClick(childBean, parentId, childId);
//                    dismiss();
//                }
//                break;
//        }
//    }
//
//
//    private void setData(List<SimpleTabBean> dataList, OnSelectItemClickListener listener, boolean isAllDep) {
//        this.listData.addAll(dataList);
//        this.listener = listener;
//        this.isAllDep = isAllDep;
//        for (int jk = 0; jk < listData.size(); jk++) {
//            if (listData.get(jk).isSelected()) {
////                LogUtils.showLog("parentId____=" + jk);
//                parentId = jk;
//            }
//        }
//    }
//
//    public interface OnSelectItemClickListener {
//        void onItemClick(SimpleTabBean childBean, int parentId, int childId);
//    }
//
//    //目标项是否在最后一个可见项之后
//    private boolean mShouldScroll;
//    //记录目标项位置
//    private int mToPosition;
//
//    /**
//     * 滑动到指定位置
//     */
//    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
//        // 第一个可见位置
//        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
//        // 最后一个可见位置
//        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
//        if (position < firstItem) {
//            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
//            mRecyclerView.smoothScrollToPosition(position);
//        } else if (position <= lastItem) {
//            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
//            int movePosition = position - firstItem;
//            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
//                int top = mRecyclerView.getChildAt(movePosition).getTop();
//                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
//                mRecyclerView.smoothScrollBy(0, top);
//            }
//        } else {
//            // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
//            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
//            mRecyclerView.smoothScrollToPosition(position);
//            mToPosition = position;
//            mShouldScroll = true;
//        }
//    }
//
//
//}
