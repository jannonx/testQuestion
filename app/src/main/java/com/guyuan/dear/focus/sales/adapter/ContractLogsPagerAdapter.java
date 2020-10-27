package com.guyuan.dear.focus.sales.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.guyuan.dear.focus.sales.view.contractProgress.ClientReceiptFragment;
import com.guyuan.dear.focus.sales.view.contractProgress.ContractPrgLogsFragment;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/14 11:57
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractLogsPagerAdapter extends FragmentStateAdapter {
    private String[] titles=new String[]{"订单动态记录","客户签收文件"};
    private ContractPrgLogsFragment logsFragment;
    private ClientReceiptFragment receiptFragment;
    public ContractLogsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                if(logsFragment==null){
                    logsFragment = ContractPrgLogsFragment.getInstance();
                }
                fragment = logsFragment;
                break;
            case 1:
                if(receiptFragment == null){
                    receiptFragment = ClientReceiptFragment.getInstance();
                }
                fragment = receiptFragment;
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public String[] getTitles() {
        return titles;
    }
}
