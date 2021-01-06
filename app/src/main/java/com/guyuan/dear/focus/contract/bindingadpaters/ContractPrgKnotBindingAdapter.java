package com.guyuan.dear.focus.contract.bindingadpaters;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.ClientAcceptanceBillsDecorator;
import com.guyuan.dear.focus.contract.adapter.ClientAcceptanceBillAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractHistoryAdapter;
import com.guyuan.dear.focus.contract.adapter.ContractHistoryTypeWrapper;
import com.guyuan.dear.focus.contract.bean.ContractPrgKnot;
import com.guyuan.dear.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 15:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgKnotBindingAdapter {
    @BindingAdapter("updateBgByKnotStatus")
    public static void updateBgByKnotStatus(AppCompatImageView view, int status) {
        switch (status) {
            case ContractPrgKnot.KNOT_STATUS_FINISHED:
                view.setImageResource(R.drawable.ic_svg_round_filled_green_2fc25b_24dp);
                break;
            case ContractPrgKnot.KNOT_STATUS_PROCESSING:
                view.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
                break;
            case ContractPrgKnot.KNOT_STATUS_DEFAULT:
                view.setImageResource(R.drawable.ic_svg_round_filled_grey_cccccc_24dp);
            default:
                break;
        }
    }

    @BindingAdapter("updateTextStyleByClientAcceptance")
    public static void updateTextStyleByClientAcceptance(AppCompatTextView view,int acceptStatus){
        //验收状态，0:待验收，10合格，20不合格
        switch (acceptStatus){
            case 0:
                view.setTextColor(Color.parseColor("#1890FF"));
                view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
                break;
            case 10:
                view.setTextColor(Color.parseColor("#00B578"));
                view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
                break;
            case 20:
                view.setTextColor(Color.RED);
                view.setBackgroundResource(R.drawable.bg_orange_ffece3_corner_2);
                break;
            default:
                break;
        }
    }

    @BindingAdapter("setClientAcceptanceImgUrls")
    public static void setClientAcceptanceImgUrls(BaseRecyclerView view, List<String> imgUrls){
        if(imgUrls==null){
            imgUrls = new ArrayList<>();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),6, RecyclerView.VERTICAL,false);
        ClientAcceptanceBillAdapter adapter = new ClientAcceptanceBillAdapter(imgUrls);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.addItemDecoration(new ClientAcceptanceBillsDecorator());
        view.setAdapter(wrapper);
        view.setPullRefreshEnabled(false);
        view.setLoadMoreEnabled(false);

    }

    @BindingAdapter("loadImgFromNet")
    public static void loadImgFromNet(AppCompatImageView view,String url){
        GlideUtils.getInstance().loadImageFromGuYuanServer(view,url,R.drawable.ic_svg_equipment_icon_default);
    }

    @BindingAdapter("setContractHistoryList")
    public static void setContractHistoryList(RecyclerView view, List<ContractHistoryTypeWrapper> list){
        if(list==null){
            list = new ArrayList<>();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        ContractHistoryAdapter adapter = new ContractHistoryAdapter(list,view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }
}
