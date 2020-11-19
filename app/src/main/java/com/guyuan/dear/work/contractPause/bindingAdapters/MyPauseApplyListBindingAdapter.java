package com.guyuan.dear.work.contractPause.bindingAdapters;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.work.contractPause.adapters.MyPauseApplyListAdapter;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyListBindingAdapter {
    @BindingAdapter(value = {"setMyPauseApplyListData","setMyPauseApplyListItemClickListener"},requireAll = true)
    public static void setMyPauseApplyListData(RecyclerView view, List<MyPauseApplyBean> data,
                                               MyPauseApplyListAdapter.MyPauseListItemClickListener listener) {
        if(data==null){
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        MyPauseApplyListAdapter adapter = new MyPauseApplyListAdapter(data,view.getContext());
        view.setAdapter(adapter);
        view.setLayoutManager(layoutManager);
        adapter.setMyPauseListItemClickListener(listener);

    }

    @BindingAdapter("setMyPauseApplyListTagTextProperty")
    public static void setMyPauseApplyListTagTextProperty(AppCompatTextView view, int state) {
        switch (state) {
            case MyPauseApplyBean.APPLY_APPROVED:
                view.setText("已通过");
                view.setTextColor(Color.parseColor("#00B578"));
                view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
                break;
            case MyPauseApplyBean.APPLY_PENDING_FOR_START:
                view.setText("待审批");
                view.setTextColor(Color.parseColor("#1677FF"));
                view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
                break;
            case MyPauseApplyBean.APPLY_PROCESSING:
                view.setText("审批中");
                view.setTextColor(Color.parseColor("#1677FF"));
                view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
                break;
            case MyPauseApplyBean.APPLY_REJECTED:
                view.setText("驳回");
                view.setTextColor(Color.parseColor("#FF6010"));
                view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);
                break;
            default:
                break;
        }
    }
}
