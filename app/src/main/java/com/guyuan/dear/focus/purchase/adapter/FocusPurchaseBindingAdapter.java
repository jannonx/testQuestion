package com.guyuan.dear.focus.purchase.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseRecordBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 11:13
 * @company : 固远（深圳）信息技术有限公司
 **/

public class FocusPurchaseBindingAdapter {

    @BindingAdapter("focusPurchaseStatus")
    public static void getFocusPurchaseStatus(TextView tv, int status) {
        switch (status) {
            case 1:
                tv.setText("退货");
                break;

            case 2:
                tv.setText("换货");
                break;

            case 3:
                tv.setText("待签收");
                break;

            case 4:
                tv.setText("已到货");
                break;

            case 5:
                tv.setText("拖期");
                break;
        }
    }

    @BindingAdapter("focusPurchaseDetailList")
    public static void setFocusPurchaseDetailList(BaseRecyclerView rv, List<PurchaseRecordBean> dataList) {
        FocusPurchaseRecordAdapter recordAdapter = new FocusPurchaseRecordAdapter(dataList, R.layout.item_focus_purchase_record);
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(recordAdapter);
        rv.setLayoutManager(new LinearLayoutManager(DearApplication.getInstance()));
        rv.setAdapter(adapter);
    }
}