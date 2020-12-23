package com.guyuan.dear.work.goodssign.adapter;

import android.view.View;

import com.example.mvvmlibrary.base.adapter.BaseDBRecycleAdapter;
import com.guyuan.dear.BR;
import com.guyuan.dear.databinding.ItemWorkGoodsSignDetailBinding;
import com.guyuan.dear.work.goodssign.data.bean.GoodsSignBean;

import java.util.List;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/17 20:15
 * @company : 固远（深圳）信息技术有限公司
 **/

public class GoodsSignDetailAdapter extends BaseDBRecycleAdapter<GoodsSignBean, ItemWorkGoodsSignDetailBinding> {

    private GoodsDetailListener listener;

    public GoodsSignDetailAdapter(List<GoodsSignBean> listData, int layoutID) {
        super(listData, layoutID);
    }

    @Override
    protected void bindDataToView(Holder holder, GoodsSignBean item, int position) {
        holder.binding.setVariable(BR.goodsSignBean, item);
        holder.binding.receiveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.sign(item.getId(), item.getNumber());
                }
            }
        });
    }

    public void setListener(GoodsDetailListener listener) {
        this.listener = listener;
    }

    public interface GoodsDetailListener {
        void sign(int id, int receiveNum);
    }
}