package com.guyuan.dear.office.approval.ui;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.busbean.ApprovalBusBean;
import com.guyuan.dear.busbean.LoginBusBean;
import com.guyuan.dear.busbean.TokenBusBean;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;
import com.guyuan.dear.office.approval.data.bean.ApprovalListBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.http.PUT;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:09
 * @company : 固远（深圳）信息技术有限公司
 **/
@AndroidEntryPoint
public class ApprovalActivity extends BaseTabActivity<ActivityBaseTabBinding, ApprovalViewModel> {

    public static final String APPROVAL_TYPE = "approvalType";
    public static final String IS_APPROVED = "isApproved";
    public static final int ACCEPT = 1;//通过
    public static final int REJECT = 2;//驳回

    private ApprovalFragment approvalFragment;
    private ApprovalFragment approvedFragment;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ApprovalActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected List<String> getTitles() {
        String[] titles = getResources().getStringArray(R.array.office_approval_title);
        return Arrays.asList(titles);
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        approvalFragment = ApprovalFragment.newInstance(ApprovalFragment.APPROVAL);
        approvedFragment = ApprovalFragment.newInstance(ApprovalFragment.APPROVED);
        fragmentList.add(approvalFragment);
        fragmentList.add(approvedFragment);
        return fragmentList;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        String title = getIntent().getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);
        setObserver();
    }

    private void setObserver() {
        viewModel.getApprovalListMLD().observe(this, new Observer<ApprovalListBean>() {
            @Override
            public void onChanged(ApprovalListBean approvalListBean) {
                approvalFragment.setListData(approvalListBean.getContent());
            }
        });

        viewModel.getApprovedListMLD().observe(this, new Observer<ApprovalListBean>() {
            @Override
            public void onChanged(ApprovalListBean approvalListBean) {
                approvedFragment.setListData(approvalListBean.getContent());
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ApprovalBusBean o) {
        approvalFragment.refresh();
        approvedFragment.refresh();
    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}