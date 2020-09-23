package com.guyuan.dear.focus.hr.view.hrSummary;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.title_tab_strip.TabContent;
import com.guyuan.dear.customizeview.title_tab_strip.TitleTabStripView;
import com.guyuan.dear.databinding.FragmentHrSummaryBinding;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;

import static com.guyuan.dear.focus.hr.view.hrSummary.HrSummaryViewModel.TAB_ID_ALL_STAFF;
import static com.guyuan.dear.focus.hr.view.hrSummary.HrSummaryViewModel.TAB_ID_CONSTRUCTION_SITE_STAFF;

/**
 * @author: 廖华凯
 * @description: 我的关注-人员-人员概况界面
 * @since: 2020/9/17 16:55
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryFragment extends BaseMvvmFragment<FragmentHrSummaryBinding, HrSummaryViewModel> {


    public static HrSummaryFragment getInstance() {
        return new HrSummaryFragment();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hr_summary;
    }


    @Override
    protected int getViewModelBrId() {
        return BR.fragment_hr_summary_vm;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initViews() {
        initHrTypeIndicator();
    }

    /**
     * 初始化切换“全部人员”和“施工人员”的下滑键
     */
    public void initHrTypeIndicator() {
        TitleTabStripView tabView = getViewDataBinding().fragmentHrSummaryTabViewToggleHrType;
        tabView.setTabContents(new ArrayList<TabContent>() {
            {
                add(new TabContent() {
                    @Override
                    public String getTabTitle() {
                        return "全部人员";
                    }

                    @Override
                    public int getTabId() {
                        return TAB_ID_ALL_STAFF;
                    }
                });
                add(new TabContent() {
                    @Override
                    public String getTabTitle() {
                        return "施工现场人员";
                    }

                    @Override
                    public int getTabId() {
                        return TAB_ID_CONSTRUCTION_SITE_STAFF;
                    }
                });
            }
        });
        tabView.setTabListener(new TitleTabStripView.TabListener() {
            @Override
            public void onTabChange(View view, TabContent tabContent) {
                int tabId = tabContent.getTabId();
                getViewModel().setMode(tabId);

            }
        });
        tabView.setCurrentSelected(0);
    }


    @Override
    protected void initListeners() {


    }

}
