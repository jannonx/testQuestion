package com.guyuan.dear.work.contractPause.bindingAdapters;

import android.graphics.Color;
import android.widget.AdapterView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.work.contractPause.adapters.MyApplyListAdapter;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyListBindingAdapter {
//    @BindingAdapter(value = {"setMyApplyListData","setMyApplyListItemClickListener"},requireAll = true)
//    public static void setMyApplyListData(RecyclerView view, List<MyApplyBean> data,
//                                          AdapterView.OnItemClickListener listener) {
//        if(data==null){
//            return;
//        }
//        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
//        MyApplyListAdapter adapter = new MyApplyListAdapter(data,view.getContext());
//        view.setAdapter(adapter);
//        view.setLayoutManager(layoutManager);
//        adapter.setMyPauseListItemClickListener(listener);
//
//    }

    @BindingAdapter("setMyApplyListTagTextProperty")
    public static void setMyPauseApplyListTagTextProperty(AppCompatTextView view, int state) {
        switch (state) {
            case MyApplyBean.APPLY_APPROVED:
                view.setText("已通过");
                view.setTextColor(Color.parseColor("#00B578"));
                view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
                break;
            case MyApplyBean.APPLY_PENDING_FOR_START:
                view.setText("待审批");
                view.setTextColor(Color.parseColor("#1677FF"));
                view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
                break;
            case MyApplyBean.APPLY_PROCESSING:
                view.setText("审批中");
                view.setTextColor(Color.parseColor("#1677FF"));
                view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
                break;
            case MyApplyBean.APPLY_REJECTED:
                view.setText("驳回");
                view.setTextColor(Color.parseColor("#FF6010"));
                view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);
                break;
            default:
                break;
        }
    }
}
