package com.guyuan.dear.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.analyse.AnalyseFragment;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.busbean.LoginBusBean;
import com.guyuan.dear.busbean.TokenBusBean;
import com.guyuan.dear.databinding.ActivityMainBinding;
import com.guyuan.dear.focus.FocusFragment;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.mine.MineFragment;
import com.guyuan.dear.office.OfficeFragment;
import com.guyuan.dear.service.BackService;
import com.guyuan.dear.utils.ActivityUtils;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.WorkFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseNoToolbarActivity<ActivityMainBinding, BaseViewModel> implements RadioGroup.OnCheckedChangeListener {

    private Fragment currentFragment;
    private FocusFragment focusFragment;
    private WorkFragment workFragment;
    private OfficeFragment officeFragment;
    private AnalyseFragment analyseFragment;
    private MineFragment mineFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static final String OPEN_TYPE = "open_type";
    public static final int COMMON = 0x001;//自动登录进入首页
    public static final int LOGIN = 0x002;//账号登录进入首页


    public static void start(Context context, int openType) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(OPEN_TYPE, openType);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        startBackService();
        setListeners();
    }

    private void initFragments() {
        //根据菜单加载对应的fragment
        LoginBean loginBean = CommonUtils.getLoginInfo();
        if (loginBean != null && loginBean.getAppMenus() != null) {
            for (LoginBean.AppMenusBean menusBean : loginBean.getAppMenus()) {
                String url = menusBean.getUrl();
                String title = menusBean.getTitle();
                ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList = new ArrayList<>();
                if (menusBean.getChildren() != null) {
                    menuList.addAll(menusBean.getChildren());
                }
                switch (url) {
                    case ConstantValue.FOCUS://我的关注
                        binding.homeFocusRb.setVisibility(View.VISIBLE);
                        binding.homeFocusRb.setText(title);
                        focusFragment = FocusFragment.newInstance(title, menuList);
                        fragmentList.add(focusFragment);
                        break;

                    case ConstantValue.WORK://我的工作
                        binding.homeWorkRb.setVisibility(View.VISIBLE);
                        binding.homeWorkRb.setText(title);
                        workFragment = WorkFragment.newInstance(title, menuList);
                        fragmentList.add(workFragment);
                        break;

                    case ConstantValue.OFFICE://掌上办公
                        binding.homeOfficeRb.setVisibility(View.VISIBLE);
                        binding.homeOfficeRb.setText(title);
                        officeFragment = OfficeFragment.newInstance(title, menuList);
                        fragmentList.add(officeFragment);
                        break;

                    case ConstantValue.ANALYSE://智能分析
                        binding.homeAnalysisRb.setVisibility(View.VISIBLE);
                        binding.homeAnalysisRb.setText(title);
                        analyseFragment = AnalyseFragment.newInstance(title, menuList);
                        fragmentList.add(analyseFragment);
                        break;

                    case ConstantValue.MINE://我的社区
                        binding.homeMineRb.setVisibility(View.VISIBLE);
                        binding.homeMineRb.setText(menusBean.getTitle());
                        mineFragment = MineFragment.newInstance();
                        fragmentList.add(mineFragment);
                        break;

                    default:
                        break;
                }
            }
        } else {
            showToastTip("该账号无菜单");
        }

        if (fragmentList.size() > 0) {
            //设置默认第一个选中
            currentFragment = fragmentList.get(0);
            ActivityUtils.addFragmentToActivity(fragmentManager, fragmentList.get(0), R.id.container,
                    FocusFragment.TAG);

            if (currentFragment instanceof FocusFragment) {
                binding.homeFocusRb.setChecked(true);
            } else if (currentFragment instanceof WorkFragment) {
                binding.homeWorkRb.setChecked(true);
            } else if (currentFragment instanceof OfficeFragment) {
                binding.homeOfficeRb.setChecked(true);
            } else if (currentFragment instanceof AnalyseFragment) {
                binding.homeAnalysisRb.setChecked(true);
            } else if (currentFragment instanceof MineFragment) {
                binding.homeMineRb.setChecked(true);
            }
        }

    }

    //设置监听
    private void setListeners() {
        binding.rdGroup.setOnCheckedChangeListener(this);
    }

    //开启后台service
    private void startBackService() {
        int openType = getIntent().getIntExtra(OPEN_TYPE, 0);
        switch (openType) {
            case COMMON://免登录进入首页需要重新登录获取最新菜单
                DearApplication.getInstance().startBackService(BackService.LOGIN, null);
                break;
            case LOGIN://从登陆进入首页不需要刷新菜单
                initFragments();
                break;
        }
    }


    //解决其他界面崩溃导致首页页面叠加
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    public BaseViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home_focus_rb:
                switchContent(currentFragment, focusFragment, FocusFragment.TAG);
                break;

            case R.id.home_work_rb:
                switchContent(currentFragment, workFragment, WorkFragment.TAG);
                break;

            case R.id.home_office_rb:
                switchContent(currentFragment, officeFragment, OfficeFragment.TAG);
                break;

            case R.id.home_analysis_rb:
                switchContent(currentFragment, analyseFragment, AnalyseFragment.TAG);
                break;

            case R.id.home_mine_rb:
                switchContent(currentFragment, mineFragment, MineFragment.TAG);
                break;

            default:
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object o) {
        if (o instanceof TokenBusBean) {     //退出登录
            CommonUtils.logout(this);
        } else if (o instanceof LoginBusBean) {//获取最新菜单
            initFragments();
        }

    }


    //切换fragment
    private void switchContent(Fragment from, Fragment to, String tag) {
        if (currentFragment != to) {
            currentFragment = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.container, to, tag).commitAllowingStateLoss(); //
                // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}