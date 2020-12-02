package com.guyuan.dear.work.qc.views.home;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.focus.qc.views.qcReportList.ReportListFragment;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.qc.views.materialQc.MaterialQcFragment;
import com.guyuan.dear.work.qc.views.productQc.ProductQcFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的工作-质检首页
 *
 * @author 廖华凯
 */
public class QcHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, QcHomeViewModel> {

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, QcHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>() {
            {
                add("成品质检");
                add("原材料质检");
                add("我的质检");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return new ArrayList<Fragment>() {
            {
                add(ProductQcFragment.getInstance());
                add(MaterialQcFragment.getInstance());
                add(ReportListFragment.getInstance(ReportListFragment.REPORT_TYPE_ONLY_MY_REPORTS));
            }
        };
    }

    @Override
    protected void init() {
        //当提交成功后，跳到我的申请列表，并刷新列表
        getViewModel().refreshMyApplyList.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    vpBase.setCurrentItem(2, true);
                }
            }
        });

    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }
}