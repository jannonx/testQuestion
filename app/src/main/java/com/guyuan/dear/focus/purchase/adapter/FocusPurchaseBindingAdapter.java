package com.guyuan.dear.focus.purchase.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.CommonBindingAdapter;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.focus.purchase.data.bean.PurchaseRecordBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

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
                tv.setBackgroundResource(R.drawable.bg_tag_orange);
                break;

            case 2:
                tv.setText("换货");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagBlue);
                tv.setBackgroundResource(R.drawable.bg_assess);
                break;

            case 3:
                tv.setText("待签收");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagBlue);
                tv.setBackgroundResource(R.drawable.bg_assess);
                break;

            case 4:
                tv.setText("已到货");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagGreen);
                tv.setBackgroundResource(R.drawable.bg_pass);
                break;

            case 5:
                tv.setText("拖期");
                tv.setTextAppearance(tv.getContext(), R.style.TextTagOrange);
                tv.setBackgroundResource(R.drawable.bg_tag_orange);
                break;
        }
    }


    @BindingAdapter("focusPurchaseStatus")
    public static void getFocusPurchaseStatus(TextView tv, int status) {
        switch (status) {
            case 1:
                tv.setText("退货");
                tv.setTextAppearance(R.style.TextTagOrange);
                tv.setBackgroundResource(R.drawable.bg_tag_orange);
                break;

            case 2:
                tv.setText("换货");
                tv.setTextAppearance(R.style.TextTagOrange);
                tv.setBackgroundResource(R.drawable.bg_tag_orange);
                break;

            case 3:
                tv.setText("待签收");
                tv.setTextAppearance(R.style.TextTagBlue);
                tv.setBackgroundResource(R.drawable.bg_assess);
                break;

            case 4:
                tv.setText("已到货");
                tv.setTextAppearance(R.style.TextTagGreen);
                tv.setBackgroundResource(R.drawable.bg_pass);
                break;

            case 5:
                tv.setText("拖期");
                tv.setTextAppearance(R.style.TextTagOrange);
                tv.setBackgroundResource(R.drawable.bg_tag_orange);
                break;
        }
    }

    @BindingAdapter(value = {"purchaseStatus", "purchaseNumber"})
    public static void setPurchaseReturnOrReplaceNumber(TextView tv, int status, int number) {
        Context context = tv.getContext();
        switch (status) {
            case 1://退货数量
                tv.setText(String.format(context.getString(R.string.return_goods_number), number));
                break;

            case 2://换货数量
                tv.setText(String.format(context.getString(R.string.replace_goods_number), number));
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
            case 0:
                tv.setText(String.format(tv.getResources().getString(R.string.focus_purchase_result), "未质检完成"));
                break;

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
                CommonBindingAdapter.setRightContentWithTitle(tv, String.format(tv.getResources()
                        .getString(R.string.focus_purchase_return_reason), reason));
                break;

            case 2:   //换货
                CommonBindingAdapter.setRightContentWithTitle(tv, String.format(tv.getResources()
                        .getString(R.string.focus_purchase_replace_reason), reason));

            case 5:
                CommonBindingAdapter.setRightContentWithTitle(tv, String.format(tv.getResources()
                        .getString(R.string.focus_purchase_delay_reason), reason));
                break;

            default:
                tv.setVisibility(View.GONE);
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