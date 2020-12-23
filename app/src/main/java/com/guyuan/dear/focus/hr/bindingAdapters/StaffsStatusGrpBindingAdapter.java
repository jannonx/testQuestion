package com.guyuan.dear.focus.hr.bindingAdapters;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.focus.hr.adapter.HrStaffAdapter;
import com.guyuan.dear.focus.hr.adapter.HrStaffGrpAdapter;
import com.guyuan.dear.focus.hr.bean.HrStaffsByDept;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 18:26
 * @company: 固远（深圳）信息技术有限公司
 **/
public class StaffsStatusGrpBindingAdapter {
    @BindingAdapter(value = {"hrStaffGrpShowStaffs", "setStaffGridItemClickListener"})
    public static void hrStaffGrpShowStaffs(BaseRecyclerView view, List<HrStaffsByDept> data, HrStaffAdapter.HrStaffAdapterItemClickListener listener) {
        if (data == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        HrStaffGrpAdapter adapter = new HrStaffGrpAdapter(data);
        adapter.setItemClickListener(listener);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setPullRefreshEnabled(false);
        view.setLoadMoreEnabled(false);
    }


    @BindingAdapter(value = {"showStaffGridView", "setStaffGridItemClickListener"})
    public static void showStaffGridView(BaseRecyclerView view, List<StaffBean> data, HrStaffAdapter.HrStaffAdapterItemClickListener listener) {
        if (data == null) {
            return;
        }
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 5, RecyclerView.VERTICAL, false);
        HrStaffAdapter adapter = new HrStaffAdapter(data);
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        view.setLayoutManager(layoutManager);
        view.setAdapter(wrapper);
        view.setLoadMoreEnabled(false);
        view.setPullRefreshEnabled(false);
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                listener.onItemClick(data.get(i), i);
            }
        });
    }
}
