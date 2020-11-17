package com.guyuan.dear.work.qc.bindingAdpaters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.work.qc.adapers.ProductionInfoAdapter;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.beans.BaseQcSubmitBean;
import com.guyuan.dear.work.qc.beans.ProductInfo;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 15:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcBingingAdapter {
    @BindingAdapter("showQcResult")
    public static void showQcResult(AppCompatTextView view,int result){
        if(result == BaseQcSubmitBean.QC_RESULT_PASS){
            view.setText("合格");
        }else {
            view.setText("异常");
        }
    }

    @BindingAdapter("showNegativePositive")
    public static void showNegativePositive(AppCompatTextView view,boolean isPositive){
        if(isPositive){
            view.setText("是");
        }else {
            view.setText("否");
        }
    }

    @BindingAdapter("showQcApproachName")
    public static void showQcApproachName(AppCompatTextView view, BaseQcApproachBean bean){
        if(bean==null){
            return;
        }
        view.setText(bean.getApproachName());
    }

    @BindingAdapter("setProductionInfos")
    public static void setProductionInfos(BaseRecyclerView view, List<ProductInfo> data){
        if(data==null){
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,false);
        ProductionInfoAdapter adapter = new ProductionInfoAdapter(data);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);
    }
}
