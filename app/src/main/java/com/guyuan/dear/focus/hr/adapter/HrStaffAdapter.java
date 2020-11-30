package com.guyuan.dear.focus.hr.adapter;

import android.view.View;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemHrStaffBinding;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/22 15:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrStaffAdapter extends BaseDBRecycleAdapter<StaffBean, ItemHrStaffBinding> {

    public HrStaffAdapter(List<StaffBean> listData) {
        super(listData, R.layout.item_hr_staff);
    }

    @Override
    protected void bindDataToView(Holder holder, StaffBean item, int position) {
        holder.binding.setData(item);
    }

    public interface HrStaffAdapterItemClickListener {
        void onItemClick(StaffBean staffBean, int pos);
    }


    //    private int grpType;
//    private long deptId;
//    private static final int VIEW_TYPE_LOAD_MORE = 1;
//    private HrStaffAdapterCallback callback;
//
//    public HrStaffAdapter(Context context, @NonNull List<StaffBasicInfo> listData, int grpType, long deptId) {
//        super(context, listData, R.layout.item_hr_staff);
//        this.grpType = grpType;
//        this.deptId = deptId;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position < listData.size()) {
//            return super.getItemViewType(position);
//        } else {
//            return VIEW_TYPE_LOAD_MORE;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size() + 1;
//    }
//
//    @Override
//    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType != VIEW_TYPE_LOAD_MORE) {
//            return super.onCreateViewHolder(parent, viewType);
//        } else {
//            View view = LayoutInflater.from(context).inflate(R.layout.item_hr_load_more, parent, false);
//            return new HrStaffLoadMoreVh(context, view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
//        int viewType = getItemViewType(position);
//        if (viewType != VIEW_TYPE_LOAD_MORE) {
//            super.onBindViewHolder(holder, position);
//        } else {
//            bindDataToView(holder, null, position);
//        }
//    }
//
//
//    @Override
//    protected void bindDataToView(BaseRecyclerViewHolder holder, StaffBasicInfo item, int position) {
//        int viewType = getItemViewType(position);
//        if (viewType != VIEW_TYPE_LOAD_MORE) {
//            holder.setText(R.id.item_hr_staff_tv_name, item.getName());
//            holder.setOnClickListener(R.id.item_hr_staff_iv_icon, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (callback != null) {
//                        callback.onClickStaff(item);
//                    }
//                }
//            });
//        } else {
//            holder.setOnClickListener(R.id.item_hr_load_more_iv_add, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (callback != null) {
//                        callback.onClickLoadMore(grpType, deptId, listData.size(), 50,position);
//                    }
//                }
//            });
//        }
//    }
//
//
//    public static class HrStaffLoadMoreVh extends BaseRecyclerViewHolder {
//
//        public HrStaffLoadMoreVh(Context context, View itemView) {
//            super(context, itemView);
//        }
//    }
//
//
//    public interface HrStaffAdapterCallback {
//        void onClickLoadMore(int grpType, long deptId, int pageStartIndex, int pageSize,int itemPos);
//
//        void onClickStaff(StaffBasicInfo item);
//    }
//
//    public void setCallback(HrStaffAdapterCallback callback) {
//        this.callback = callback;
//    }
}
