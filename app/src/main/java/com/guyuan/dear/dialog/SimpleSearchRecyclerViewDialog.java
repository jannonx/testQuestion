//package com.guyuan.dear.dialog;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatEditText;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.appcompat.widget.AppCompatTextView;
//
//import com.gy.smartmanagement.R;
//import com.gy.smartmanagement.base.adapter.SimpleSearchRecyclerViewAdapter;
//import com.gy.smartmanagement.base.adapter.TabFlowAdapter;
//import com.gy.smartmanagement.base.bean.SimpleTabBean;
//import com.gy.smartmanagement.customizeview.flowlayout.TagFlowLayout;
//import com.gy.smartmanagement.utils.LogUtils;
//import com.gy.smartmanagement.utils.ToastUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * @description: 搜索+列表
// * @author:Jannonx
// * @date: 2020/6/29 20:00
// */
//public class SimpleSearchRecyclerViewDialog extends Dialog {
//
//    @BindView(R.id.label_title)
//    AppCompatTextView labelTitle;
//    @BindView(R.id.tfl_tb)
//    TagFlowLayout tagFlowLayout;
//    @BindView(R.id.iv_search)
//    AppCompatImageView ivSearch;
//    @BindView(R.id.et_search)
//    AppCompatEditText etSearch;
//    @BindView(R.id.rl_search_bar_root_view)
//    RelativeLayout searchRootLayout;
//
//
//    private Context context;//上下文
//    private OnSelectItemClickListener listener;
//    private SimpleSearchRecyclerViewAdapter simpleRecyclerViewAdapter;
//    private TabFlowAdapter adapter;
//    private SimpleTabBean selectBean;
//    private int childId;
//    protected List<SimpleTabBean> listData = new ArrayList<>();
//    protected List<SimpleTabBean> originalData = new ArrayList<>();
//
//    private boolean isSearchBarShow = true;
//    private int columnNumber = 3;
//    private String title;
//    private String hindTxt;
//
//    public static void show(Context context, String title, String hindTxt, List<SimpleTabBean> listData, OnSelectItemClickListener listener) {
//        show(context, title, hindTxt, listData, listener, true, 3);
//    }
//
//    public static void showTwoColumn(Context context, String title, List<SimpleTabBean> listData, OnSelectItemClickListener listener) {
//        show(context, title, "", listData, listener, false, 2);
//    }
//
//    public static void show(Context context, String title, String hindTxt, List<SimpleTabBean> listData, OnSelectItemClickListener listener,
//                            boolean isSearchBarShow, int columnNumber) {
//        if (listData == null || listData.isEmpty()) {
//            Toast.makeText(context, "暂无数据", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        SimpleSearchRecyclerViewDialog dialog = new SimpleSearchRecyclerViewDialog(context);
//        dialog.setData(title, hindTxt, listData, listener, isSearchBarShow, columnNumber);
//        dialog.show();
//    }
//
//
//    private SimpleSearchRecyclerViewDialog(@NonNull Context context) {
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
//        setContentView(R.layout.dialog_search_recycler_view);
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
//        labelTitle.setText(title);
//        etSearch.setHint(hindTxt);
//
//        for (int jk = 0; jk < listData.size(); jk++) {
//            if (listData.get(jk).isSelected()) {
//                childId = jk;
//                selectBean = listData.get(jk);
//            }
//        }
//        searchRootLayout.setVisibility(isSearchBarShow ? View.VISIBLE : View.GONE);
//
//
//        adapter = new TabFlowAdapter(getContext(), listData);
//        tagFlowLayout.setAdapter(adapter);
//        adapter.setOnItemClickListener(new TabFlowAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(SimpleTabBean tabBean, int position) {
//                childId = position;
//                selectBean = listData.get(position);
////                tvAllDep.setSelected(false);
//                for (int i = 0; i < listData.size(); i++) {
//                    SimpleTabBean simpleTabBean = listData.get(i);
//                    simpleTabBean.setSelected(i == position);
//                }
//                adapter.notifyDataChanged();
//
//            }
//        });
//
//        etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (TextUtils.isEmpty(editable.toString())) {
//                    listData.clear();
//                    listData.addAll(originalData);
//                    adapter.notifyDataChanged();
//                }
//            }
//        });
//
//    }
//
//
//    @OnClick({R.id.tv_cancel, R.id.tv_ok, R.id.tb_search_btn})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_cancel:
//                dismiss();
//                break;
//            case R.id.tv_ok:
//                LogUtils.showLog("...childId=" + childId
//                    + "..childBean=" + (selectBean == null ? "空bean" : selectBean.getTitle()));
//                if (selectBean == null) {
//                    String tip = title.contains("订单") ? "请选择订单编号" :
//                        title.contains("批号") ? "请选择生产批号" : "请选择";
//                    ToastUtils.showShort(context, tip);
//                }
//
//                if (listener != null && selectBean != null) {
//                    listener.onItemClick(selectBean, childId);
//                    dismiss();
//                }
//                break;
//
//            case R.id.tb_search_btn://搜索
//                searchByTraverse();
//                break;
//            default:
//
//                break;
//        }
//    }
//
//    /**
//     * 搜索遍历
//     */
//    private void searchByTraverse() {
//        String searchWord = etSearch.getText().toString();
//        List<SimpleTabBean> tempData = new ArrayList<>();
//        for (SimpleTabBean bean : originalData) {
//            if (bean.getTitle().contains(searchWord)) {
//                bean.setSelected(false);
//                tempData.add(bean);
//            }
//        }
//        listData.clear();
//        listData.addAll(tempData);
//        adapter.notifyDataChanged();
//
//    }
//
//    private void setData(String title, String hindTxt, List<SimpleTabBean> dataList,
//                         OnSelectItemClickListener listener, boolean isSearchBarShow, int columnNumber) {
//        this.title = title;
//        this.hindTxt = hindTxt;
//        this.listData.addAll(dataList);
//        this.originalData.addAll(dataList);
//        this.listener = listener;
//        this.isSearchBarShow = isSearchBarShow;
//        this.columnNumber = columnNumber;
//
//    }
//
//    public interface OnSelectItemClickListener {
//        void onItemClick(SimpleTabBean childBean, int childId);
//    }
//
//
//}
