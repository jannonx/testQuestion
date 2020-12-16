package com.guyuan.dear.focus.hr.bindingAdapters;

import android.text.TextUtils;
import android.widget.ExpandableListView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.hr.adapter.PickStaffsExpListAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.focus.hr.bean.PickStaffsExpParentBean;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.work.contractPause.beans.DeptBean;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsBindingAdapter {

    @BindingAdapter("setUserCircleNetImage")
    public static void setUserCircleNetImage(AppCompatImageView view, String url) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(view, url);

    }


    @BindingAdapter(value = {"setPickStaffsExpListData", "setPickStaffItemCallback"}, requireAll = true)
    public static void setPickStaffsExpListData(ExpandableListView view, List<PickStaffsExpParentBean> data,
                                                PickStaffsExpListAdapter.ItemCallback callback) {
        if (data == null) {
            return;
        }
        PickStaffsExpListAdapter adapter = new PickStaffsExpListAdapter(data, view.getContext());
        view.setAdapter(adapter);
        view.setDividerHeight(0);
        AtomicBoolean isCollapseInvokedByUser = new AtomicBoolean(true);
        view.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //设置一个flag标记事件：某一个item展开而导致其他item收起。
                isCollapseInvokedByUser.set(false);
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setExpanded(i == groupPosition);
                    if (i != groupPosition) {
                        view.collapseGroup(i);
                    }
                }
                adapter.notifyDataSetChanged();
                isCollapseInvokedByUser.set(true);
            }
        });
        view.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //根据flag判断本次收起是否因为：1，item本来展开，用户直接点击item，导致这个item收起。
                //还是：2，用户点击了其他item，在其他item展开时，收起这个item
                //如果是1，才需要更新data的数据和更新UI。避免重复调用notifyDataSetChanged影响app性能。
                if (isCollapseInvokedByUser.get()) {
                    data.get(groupPosition).setExpanded(false);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        adapter.setItemCallback(callback);

    }


    @BindingAdapter(value = {"setPickStaffsHistoryStaffs", "setPickStaffsHistoryItemClickListener"}, requireAll = true)
    public static void setPickStaffsHistoryStaffs(RecyclerView view, List<PickStaffBean> data,
                                                  PickStaffsHistoryItemClickListener listener) {
        if (data == null) {
            return;
        }
        if (view.getAdapter() == null) {
            GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 5, RecyclerView.VERTICAL, false);
            PickStaffsHistoryStaffsAdapter adapter = new PickStaffsHistoryStaffsAdapter(data, view.getContext());
            view.setLayoutManager(layoutManager);
            view.setAdapter(adapter);
            adapter.setItemClickListener(listener);
        }
    }

    @BindingAdapter("setExpListParentRightArrowIndicator")
    public static void setExpListParentRightArrowIndicator(AppCompatImageView view, boolean isExpanded) {
        if (isExpanded) {
            view.setImageResource(R.drawable.ic_svg_bottom_arrow);
        } else {
            view.setImageResource(R.drawable.ic_svg_right_arrow);
        }
    }

    @BindingAdapter("setShowSelectVsTotal")
    public static void setShowSelectVsTotal(AppCompatTextView view, PickStaffsExpParentBean data) {
        int total = data.getStaffTotal();
        int selectCount = 0;
        for (PickStaffBean staff : data.getStaffs()) {
            if (staff.isPick()) {
                selectCount++;
            }
        }
        view.setText(String.format(Locale.CHINA, "%d/%d", selectCount, total));
    }

    @BindingAdapter("setShowStaffDept")
    public static void setShowStaffDept(AppCompatTextView view, List<DeptBean> depts) {
        if (depts == null || depts.isEmpty()) {
            return;
        }
        String deptName = depts.get(0).getDeptName();
        if (TextUtils.isEmpty(deptName)) {
            deptName = "未知部门";
        } else {
            //部门名称超出4个字节就把后面的截掉。
            if (deptName.length() > 4) {
                deptName = deptName.substring(0, 4);
                deptName = deptName+"...";
            }
        }
        view.setText(deptName);
    }


}
