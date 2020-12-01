package com.guyuan.dear.focus.purchase.adapter;

import android.view.View;
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

    @BindingAdapter("focusPurchaseStatusWithBackground")
    public static void getFocusPurchaseStatusWithBackground(TextView tv, int status) {
        switch (status) {
            case 1:
                tv.setText("退货");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagOrange);
                break;

            case 2:
                tv.setText("换货");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagBlue);
                break;

            case 3:
                tv.setText("待签收");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagBlue);
                break;

            case 4:
                tv.setText("已到货");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagGreen);
                break;

            case 5:
                tv.setText("拖期");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagOrange);
                break;
        }
    }


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
        rv.setPullRefreshEnabled(false);
        rv.setLoadMoreEnabled(false);

    }

    @BindingAdapter("focusPurchaseResult")
    public static void setFocusPurchaseResult(TextView tv, int status) {
        switch (status) {
            case 1:
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_result), "合格"));
                break;

            case 2:
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_result), "不合格"));
                break;

            default:
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_result), ""));
                break;
        }
    }

    @BindingAdapter(value = {"focusPurchaseType", "focusPurchaseReason"})
    public static void setFocusPurchaseReason(TextView tv, int type, String reason) {
        switch (type) {
            case 1:    //退货
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_return_reason), reason));
                break;

            case 2:   //换货
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_replace_reason), reason));
                break;
        }
    }

    @BindingAdapter(value = {"focusPurchaseType", "returnNumber", "replaceNumber"})
    public static void setFocusPurchaseExceptionNumber(TextView tv, int type, int returnNumber, int replaceNumber) {
        switch (type) {
            case 1:    //退货
                tv.setVisibility(View.VISIBLE);
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_return), returnNumber));
                break;

            case 2:   //换货
                tv.setVisibility(View.VISIBLE);
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_replace), replaceNumber));
                break;

            default:
                tv.setVisibility(View.GONE);
                break;
        }
    }

}