package com.guyuan.dear.focus.contract.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.guyuan.dear.focus.contract.bean.DetailContractBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractCommentsFragment;
import com.guyuan.dear.focus.contract.view.contractDetail.ProductBaseInfoFragment;
import com.guyuan.dear.focus.contract.view.contractDetail.SalesDeliveryFragment;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 11:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailPagerAdapter extends FragmentStateAdapter {

//    private String[] mTitles = new String[]{"跟进动态", "销售产品", "收货信息"};
    private String[] mTitles = new String[]{"销售产品", "收货信息"};
    private ProductBaseInfoFragment baseInfoFragment;
    private SalesDeliveryFragment salesDeliveryFragment;
    private ContractCommentsFragment commentsFragment;

    public ContractDetailPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment result = null;
        switch (position) {
            case 0:
                if (baseInfoFragment == null) {
                    baseInfoFragment = ProductBaseInfoFragment.getInstance();
                }
                result = baseInfoFragment;

                break;
            case 1:
                if (salesDeliveryFragment == null) {
                    salesDeliveryFragment = SalesDeliveryFragment.getInstance();
                }
                result = salesDeliveryFragment;

                break;
            default:
                break;
        }
        return result;
    }

    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        Fragment result = null;
//        switch (position) {
//            case 0:
//                if (commentsFragment == null) {
//                    commentsFragment = ContractCommentsFragment.getInstance();
//                }
//                result = commentsFragment;
//
//                break;
//            case 1:
//                if (baseInfoFragment == null) {
//                    baseInfoFragment = ProductBaseInfoFragment.getInstance();
//                }
//                result = baseInfoFragment;
//
//                break;
//            case 2:
//                if (salesDeliveryFragment == null) {
//                    salesDeliveryFragment = SalesDeliveryFragment.getInstance();
//                }
//                result = salesDeliveryFragment;
//                break;
//            default:
//                break;
//        }
//        return result;
//    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public String getTitle(int pos) {
        return mTitles[pos];
    }

//    public void update(DetailContractBean bean) {
////        if (baseInfoFragment != null) {
////            baseInfoFragment.updateViewByData(bean);
////        }
////        if (salesDeliveryFragment != null) {
////            salesDeliveryFragment.updateViewByData(bean);
////        }
//        if (commentsFragment != null) {
////            commentsFragment.updateViewByDate(bean);
//        }
//
//    }
}
