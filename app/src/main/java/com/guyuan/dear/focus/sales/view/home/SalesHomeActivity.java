package com.guyuan.dear.focus.sales.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.sales.view.contractSum.ComContractsSumFragment;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注-销售-进来主界面
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/9/23 17:41
 * @company: 固远（深圳）信息技术有限公司
 */
public class SalesHomeActivity  extends BaseTabActivity<ActivityBaseTabBinding, SalesHomeViewModel> {

    public static void start(Context context,String title) {
        Intent starter = new Intent(context, SalesHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>(){
            {
                add("合同订单概况");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return new ArrayList<Fragment>(){
            {
                add(ComContractsSumFragment.getInstance());
            }
        };
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);

    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}