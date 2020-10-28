package com.guyuan.dear.mine;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMineBinding;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.scan.ScanActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GlideUtils;

/**
 * @author : tl
 * @description : 我的
 * @since: 2020/9/8 11:56
 * @company : 固远（深圳）信息技术有限公司
 **/
public class MineFragment extends BaseDataBindingFragment<FragmentMineBinding, BaseViewModel> implements View.OnClickListener {

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
        if (user != null && user.getUserInfo() != null) {
            binding.loginOrRegisterTv.setText(user.getUserInfo().getName());
            binding.tipTv.setText(user.getUserInfo().getMobile());
            String imgUrl = user.getUserInfo().getImgUrl();
            GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(binding.ivAvatar, imgUrl);
        } else {
            showToastTip("用户信息为空/UserInfo为空。");
        }

        if (ConstantValue.hasNewVersion) {
            binding.tvNewVersion.setVisibility(View.VISIBLE);
        } else {
            binding.tvNewVersion.setVisibility(View.GONE);
        }
        setLister();
    }


    private void setLister() {
        binding.llUserSetting.setOnClickListener(this);
        binding.llLogout.setOnClickListener(this);
        binding.llModifyPw.setOnClickListener(this);
        binding.llAbout.setOnClickListener(this);
        binding.llSystemSetting.setOnClickListener(this);
        binding.homeBarLl.homeQrIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //个人信息
            case R.id.ll_user_setting:

                break;
            //退出
            case R.id.ll_logout:
                CommonUtils.logout(getContext());
                break;
            //修改密码
            case R.id.ll_modify_pw:

                break;
            //关于
            case R.id.ll_about:

                break;
            //系统设置
            case R.id.ll_system_setting:

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
