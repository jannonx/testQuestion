package com.guyuan.dear.focus.contract.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.guyuan.dear.focus.contract.bean.ContractDetailBean;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractBaseInfoFragment;
import com.guyuan.dear.focus.contract.view.contractDetail.ContractCommentsFragment;
import com.guyuan.dear.focus.contract.view.contractDetail.SalesDeliveryFragment;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/10 11:50
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractDetailPagerAdapter extends FragmentStateAdapter {

    private String[] mTitles = new String[]{"销售产品", "收货信息", "跟进动态记录"};
    private ContractBaseInfoFragment baseInfoFragment;
    private SalesDeliveryFragment salesDeliveryFragment;
    private ContractCommentsFragment commentsFragment;
    private long mBuyerFirstCreateDate;

    public ContractDetailPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,long buyerFirstCreteDate) {
        super(fragmentManager, lifecycle);
        mBuyerFirstCreateDate = buyerFirstCreteDate;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment result = null;
        switch (position) {
            case 0:
                if (baseInfoFragment == null) {
                    baseInfoFragment = ContractBaseInfoFragment.getInstance();
                }
                result = baseInfoFragment;
                break;
            case 1:
                if (salesDeliveryFragment == null) {
                    salesDeliveryFragment = SalesDeliveryFragment.getInstance();
                }
                result = salesDeliveryFragment;
                break;
            case 2:
                if(commentsFragment==null){
                    commentsFragment = ContractCommentsFragment.getInstance(mBuyerFirstCreateDate);
                }
                result = commentsFragment;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public String getTitle(int pos) {
        return mTitles[pos];
    }

    public void update(ContractDetailBean bean) {
        if (baseInfoFragment != null) {
            baseInfoFragment.updateViewByData(bean);
        }
        if (salesDeliveryFragment != null) {
            salesDeliveryFragment.updateViewByData(bean);
        }
        if(commentsFragment!=null){
            commentsFragment.updateViewByDate(bean);
        }

    }
}
