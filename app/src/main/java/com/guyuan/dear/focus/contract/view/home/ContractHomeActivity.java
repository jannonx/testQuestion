package com.guyuan.dear.focus.contract.view.home;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.contract.view.abnormalContractList.AbnormalContractListFragment;
import com.guyuan.dear.focus.contract.view.allContractList.AllContractListFragment;
import com.guyuan.dear.focus.contract.view.contractStatusList.ContractStatusListFragment;
import com.guyuan.dear.focus.contract.view.contractSum.ComContractsSumFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注-合同-进来主界面
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:41
 * @company: 固远（深圳）信息技术有限公司
 */
public class ContractHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, SalesHomeViewModel> {

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ContractHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>() {
            {
                add("合同概况");
//                add("合同暂停");
//                add("合同重启");
                add("合同异常");
                add("合同详情");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return new ArrayList<Fragment>() {
            {
                add(ComContractsSumFragment.getInstance());
//                add(ContractStatusListFragment.getInstance(ContractStatusListFragment.STATUS_TYPE_ON_PAUSE));
//                add(ContractStatusListFragment.getInstance(ContractStatusListFragment.STATUS_TYPE_RESTART));
//                add(ContractStatusListFragment.getInstance(ContractStatusListFragment.STATUS_TYPE_EXCEPTION));
                add(AbnormalContractListFragment.getInstance());
//                add(ContractStatusListFragment.getInstance(ContractStatusListFragment.STATUS_TYPE_TOTAL));
                add(AllContractListFragment.getInstance());
            }
        };
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        vpBase.setOffscreenPageLimit(10);
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.selector_tab_contract_home_summary);
//        tabDrawableList.add(R.drawable.selector_tab_contract_home_pause);
//        tabDrawableList.add(R.drawable.selector_tab_contract_home_restart);
        tabDrawableList.add(R.drawable.selector_tab_contract_home_exceptions);
        tabDrawableList.add(R.drawable.selector_tab_contract_home_details);
        return tabDrawableList;
    }


}