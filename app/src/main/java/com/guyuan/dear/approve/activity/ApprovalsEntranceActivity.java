//package com.guyuan.dear.approve.activity;
//
//import android.content.Context;
//import android.content.Intent;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import com.google.android.material.tabs.TabLayout;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
///**
// * @description: 掌上办公--审批--我的审批(待我审批/我已审批)
// * @author:Jannonx
// * @date: 2020/6/15 15:14
// */
//public class ApprovalsEntranceActivity  extends BaseTabActivity<ActivityWithToolbarBinding, ApproveViewModel> {
//
//
//    private ApplyNotApproveFragment notApproveFragment;
//    private ApplyYetApprovedFragment yetApprovedFragment;
//
//    public static void start(Context context) {
//        Intent starter = new Intent(context, ApprovalsEntranceActivity.class);
//        context.startActivity(starter);
//    }
//
//    @Override
//    protected List<String> getTitles() {
//        String[] titles = {"待我审批", "我已审批"};
//        return new ArrayList<>(Arrays.asList(titles));
//    }
//
//    @Override
//    protected List<Fragment> getFragments() {
//        List<Fragment> fragmentList = new ArrayList<>();
//        notApproveFragment = ApplyNotApproveFragment.getInstance();
//        yetApprovedFragment = ApplyYetApprovedFragment.getInstance();
//        fragmentList.add(notApproveFragment);
//        fragmentList.add(yetApprovedFragment);
//        return fragmentList;
//    }
//
//    @Override
//    protected int getCustomViewId() {
//        return R.layout.tab_common;
//    }
//
//    public ApprovePresenter getPresenter() {
//        return mPresenter;
//    }
//
//    @Override
//    protected void init() {
//        setTitleCenter("我的审批");
//        base_tl.setTabMode(TabLayout.MODE_FIXED);
//        application.getApiServiceComponent()
//                .getApproveComponent(new ApproveModule(this))
//                .inject(this);
//    }
//
//    @Override
//    protected List<Integer> setTabSelectedIconList() {
//        List<Integer> selectList = new ArrayList<>();
//        selectList.add(R.mipmap.approve_for_me);
//        selectList.add(R.mipmap.my_approve);
//        return selectList;
//    }
//
//    @Override
//    protected List<Integer> setTabUnselectedIconList() {
//        List<Integer> unselectedList = new ArrayList<>();
//        unselectedList.add(R.mipmap.approve_for_me);
//        unselectedList.add(R.mipmap.my_approve);
//        return unselectedList;
//    }
//
//    @Override
//    public void showApplyNotApproveList(RefreshBean<ApprovalData> result) {
//        notApproveFragment.showApplyNotApproveList(result);
//    }
//
//    @Override
//    public void showApplyYetApproveList(RefreshBean<ApprovalData> result) {
//        yetApprovedFragment.showApplyYetApproveList(result);
//    }
//
//    @Override
//    public void showMyApplyList(RefreshBean<ApplyBean> result) {
//
//    }
//
//    @Override
//    public void showApplyCopyList(RefreshBean<ApplyBean> result) {
//
//    }
//
//    @Override
//    public void showApproveByMe(Integer result) {
//
//    }
//
//    @Override
//    public void showApplyInfo(boolean result) {
//
//    }
//
//
//    @Override
//    public void setPresenter(@NonNull ApproveContract.Presenter presenter) {
//
//    }
//
//    @Override
//    public void onLoadFail(ErrorResultBean.ErrorBean errorBean) {
//
//    }
//
//    @Override
//    public void show(String tip) {
//        showLoadingWithStatus(fragmentManager, tip);
//    }
//
//    @Override
//    public void hide() {
//        hideLoading();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mPresenter != null) {
//            mPresenter.unBindPresent();
//        }
//    }
//
//}
