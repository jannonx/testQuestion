package com.guyuan.dear.mine;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMineBinding;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.mine.activity.AboutUsActivity;
import com.guyuan.dear.mine.activity.FeedBackActivity;
import com.guyuan.dear.mine.activity.PrivacyPolicyActivity;
import com.guyuan.dear.mine.activity.SafetyCenterActivity;
import com.guyuan.dear.mine.activity.UserInfoActivity;
import com.guyuan.dear.mine.data.MineViewModel;
import com.guyuan.dear.scan.ScanActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.LogUtils;

/**
 * @description: 我的
 * @author: Jannonx
 * @since: 2020/10/30 11:08
 * @company: 固远（深圳）信息技术有限公司
 */
public class MineFragment extends BaseDataBindingFragment<FragmentMineBinding, MineViewModel> implements View.OnClickListener {

    public static final String TAG = "MineFragment";
    private LoginBean user;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initialization() {
        user = CommonUtils.getLoginInfo();
        LogUtils.showLog("name="+user.getUserInfo().getName());
        if (user != null && user.getUserInfo() != null) {
            binding.tvName.setText(user.getUserInfo().getName());
            binding.tvPhone.setText(user.getUserInfo().getUserPhone());
            String imgUrl = user.getUserInfo().getImgUrl();
            GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(binding.ivAvatar, imgUrl);
        } else {
            showToastTip("用户信息为空/UserInfo为空。");
        }

//        if (ConstantValue.hasNewVersion) {
//            binding.tvNewVersion.setVisibility(View.VISIBLE);
//        } else {
//            binding.tvNewVersion.setVisibility(View.GONE);
//        }
        initListener();
    }


    private void initListener() {
        binding.rlUserInfo.setOnClickListener(this);
        binding.llSystemSetting.setOnClickListener(this);
        binding.llSafetyCenter.setOnClickListener(this);
        binding.llPrivacyPolicy.setOnClickListener(this);
        binding.llFeedBack.setOnClickListener(this);
        binding.llAbout.setOnClickListener(this);

        binding.homeBarLl.homeQrIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //个人信息
            case R.id.rl_user_info:
                UserInfoActivity.start(getContext(), "个人信息");
                break;
            //系统设置
            case R.id.ll_system_setting:
//                SystemSettingActivity.start(getContext(), "系统设置");
                break;
            //修改密码
            case R.id.ll_safety_center:
                SafetyCenterActivity.start(getContext(), "修改密码");
                break;
            //隐私政策
            case R.id.ll_privacy_policy:
                PrivacyPolicyActivity.start(getContext(), "隐私政策");
                break;
            //意见反馈
            case R.id.ll_feed_back:
                FeedBackActivity.start(getContext(), "意见反馈");
                break;
            //关于我们
            case R.id.ll_about:
                AboutUsActivity.start(getContext(), "关于我们");
                break;
            //扫码
            case R.id.home_qr_iv:
                ScanActivity.starter(getContext(), "");
                break;
        }
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
