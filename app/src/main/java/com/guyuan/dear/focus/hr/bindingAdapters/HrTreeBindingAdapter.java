package com.guyuan.dear.focus.hr.bindingAdapters;

import android.text.TextUtils;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.hr.adapter.HrTreeNavigatorAdapter;
import com.guyuan.dear.focus.hr.bean.StaffWorkStatusInfo;
import com.guyuan.dear.work.contractPause.beans.DeptBean;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeBindingAdapter {


    @BindingAdapter(value = {"hrTreeSetTopData","hrTreeSetTopItemListener"})
    public static void initTopRecyclerView(BaseRecyclerView view, List<DeptBean> data, HrTreeNavigatorAdapter.TopItemClickListener listener){
        if(data==null){
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL,false);
        HrTreeNavigatorAdapter adapter = new HrTreeNavigatorAdapter(data);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setPullRefreshEnabled(false);
        view.setLoadMoreEnabled(false);
        adapter.setListener(listener);
    }

    @BindingAdapter("hrTreeShowStaffDept")
    public static void hrTreeShowStaffDept(AppCompatTextView view, StaffWorkStatusInfo info) {
        StringBuilder sb = new StringBuilder();
        if (info != null) {
            String lv1DeptName = info.getLv1DeptName();
            if (!TextUtils.isEmpty(lv1DeptName)) {
                sb.append(lv1DeptName).append("/");
            }
            String lv2DeptName = info.getLv2DeptName();
            if (!TextUtils.isEmpty(lv2DeptName)) {
                sb.append(lv2DeptName);
            }
        }
        view.setText(sb.toString());
    }

    @BindingAdapter("hrTreeShowStaffStatusColor")
    public static void hrTreeShowStaffStatusColor(AppCompatImageView view, int status) {
        switch (status) {
            case StaffWorkStatusInfo.WORK_STATUS_ABSENT:
                view.setImageResource(R.drawable.ic_svg_round_filled_red_f04864_24dp);
                break;
            case StaffWorkStatusInfo.WORK_STATUS_EARLY_LEAVE:
                view.setImageResource(R.drawable.ic_svg_round_filled_purple_3436c7_24dp);
                break;
            case StaffWorkStatusInfo.WORK_STATUS_LATE:
                view.setImageResource(R.drawable.ic_svg_round_filled_orange_fa8c16_24dp);
                break;
            case StaffWorkStatusInfo.WORK_STATUS_NORMAL:
                view.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
                break;
            case StaffWorkStatusInfo.WORK_STATUS_ON_LEAVE:
                view.setImageResource(R.drawable.ic_svg_round_filled_green_2fc25b_24dp);
                break;
            default:
                break;
        }

    }

    @BindingAdapter("hrTreeShowStaffStatusTxt")
    public static void hrTreeShowStaffStatusTxt(AppCompatTextView view, int status) {
        String s = "";
        switch (status) {
            case StaffWorkStatusInfo.WORK_STATUS_ABSENT:
                s="缺席";
                break;
            case StaffWorkStatusInfo.WORK_STATUS_EARLY_LEAVE:
                s="早退";
                break;
            case StaffWorkStatusInfo.WORK_STATUS_LATE:
                s="迟到";
                break;
            case StaffWorkStatusInfo.WORK_STATUS_NORMAL:
                s="正常";
                break;
            case StaffWorkStatusInfo.WORK_STATUS_ON_LEAVE:
                s="请假";
                break;
            default:
                break;
        }
        view.setText(s);

    }
}
