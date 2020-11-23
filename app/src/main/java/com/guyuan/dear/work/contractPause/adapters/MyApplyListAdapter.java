package com.guyuan.dear.work.contractPause.adapters;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.databinding.ItemMyApplyListBinding;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 14:29
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyListAdapter extends BaseDBRecycleAdapter<MyApplyBean, ItemMyApplyListBinding> {
    public MyApplyListAdapter(List<MyApplyBean> listData) {
        super(listData, R.layout.item_my_apply_list);
    }

    @Override
    protected void bindDataToView(Holder holder, MyApplyBean item, int position) {
        holder.binding.setData(item);
    }

//    private List<MyApplyBean> list;
//    private Context context;
//
//    public MyApplyListAdapter(List<MyApplyBean> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_my_apply_list, parent, false);
//        return new ViewHolder(binding.getRoot());
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        ItemMyPauseApplyListBinding binding = DataBindingUtil.getBinding(holder.itemView);
//        final MyApplyBean bean = list.get(position);
//        binding.setData(bean);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(myPauseListItemClickListener!=null){
//                    myPauseListItemClickListener.onClickMyPauseApply(bean,position);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder{
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }


}
