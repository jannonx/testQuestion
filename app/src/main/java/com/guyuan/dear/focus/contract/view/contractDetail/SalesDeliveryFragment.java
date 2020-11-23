package com.guyuan.dear.focus.contract.view.contractDetail;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentSalesDeliveryBinding;

/**
 * @author: 廖华凯
 * @description: 我的关注-销售-销售概况-销售详情-收货信息
 * @since: 2020/10/10 14:54
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SalesDeliveryFragment extends BaseMvvmFragment<FragmentSalesDeliveryBinding,ContractDetailViewModel> {

    public static SalesDeliveryFragment getInstance() {
        return new SalesDeliveryFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_sales_delivery_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sales_delivery;
    }

//    private AppCompatTextView tvBuyer;
//    private AppCompatTextView tvBuyerAddress;
//    private AppCompatTextView tvReceivePerson;
//    private AppCompatTextView tvContactNumber;
//
//    public static SalesDeliveryFragment getInstance(){
//        return new SalesDeliveryFragment();
//    }
//
//    @Override
//    protected int getLayoutID() {
//        return R.layout.fragment_sales_delivery;
//    }
//
//    @Override
//    protected void initialization() {
//        tvBuyer = rootView.findViewById(R.id.fragment_sales_delivery_tv_buyer);
//        tvBuyerAddress = rootView.findViewById(R.id.fragment_sales_delivery_tv_buyer_address);
//        tvReceivePerson = rootView.findViewById(R.id.fragment_sales_delivery_tv_receiver_person);
//        tvContactNumber = rootView.findViewById(R.id.fragment_sales_delivery_tv_contact_number);
//    }
//
//    public void updateViewByData(DetailContractBean data){
//        tvBuyer.setText(data.getBuyer());
//        tvBuyerAddress.setText(data.getBuyerAddress());
//        tvReceivePerson.setText(data.getReceivePerson());
//        tvContactNumber.setText(data.getContactNumber());
//    }
}
