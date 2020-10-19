package com.guyuan.dear.focus.sales.view.contractDetail;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.mvvmlibrary.base.fragment.BaseFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.focus.sales.bean.ContractDetailBean;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-销售概况-销售详情-收货信息
 * @since: 2020/10/10 14:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SalesDeliveryFragment extends BaseFragment {

    private AppCompatTextView tvBuyer;
    private AppCompatTextView tvBuyerAddress;
    private AppCompatTextView tvReceivePerson;
    private AppCompatTextView tvContactNumber;

    public static SalesDeliveryFragment getInstance(){
        return new SalesDeliveryFragment();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sales_delivery;
    }

    @Override
    protected void initialization() {
        tvBuyer = rootView.findViewById(R.id.fragment_sales_delivery_tv_buyer);
        tvBuyerAddress = rootView.findViewById(R.id.fragment_sales_delivery_tv_buyer_address);
        tvReceivePerson = rootView.findViewById(R.id.fragment_sales_delivery_tv_receiver_person);
        tvContactNumber = rootView.findViewById(R.id.fragment_sales_delivery_tv_contact_number);
    }

    public void updateViewByData(ContractDetailBean data){
        tvBuyer.setText(data.getBuyer());
        tvBuyerAddress.setText(data.getBuyerAddress());
        tvReceivePerson.setText(data.getReceivePerson());
        tvContactNumber.setText(data.getContactNumber());
    }
}
