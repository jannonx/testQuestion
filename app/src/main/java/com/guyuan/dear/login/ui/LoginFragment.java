package com.guyuan.dear.login.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.databinding.FragmentLoginBinding;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.login.data.AppMenusBean;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.data.LoginViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.SharedPreferencesUtils;

import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * created by tl
 * created at 2020/8/24
 */

public class LoginFragment extends BaseDataBindingFragment<FragmentLoginBinding, LoginViewModel> implements View.OnClickListener {

    private LoginViewModel viewModel;
    private boolean showPwd = false;

    public static final String TAG = "LoginFragment";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            viewModel = ((LoginActivity) getActivity()).getViewModel();
        }
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initialization() {
        if (viewModel != null) {
            binding.etAccount.setText(viewModel.getUserName());
        }
        binding.tvVersion.setText("迪尔空分 " + BuildConfig.BUILD_TYPE + "-v" + BuildConfig.VERSION_NAME);
        setListener();
    }


    private void setListener() {
        binding.loginTv.setOnClickListener(this);
        binding.ivClear.setOnClickListener(this);
        binding.ivPwShow.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_tv:
                String name = binding.etAccount.getText().toString();
                String pwd = binding.etPw.getText().toString();
                viewModel.checkLogin(name, pwd, new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LoginBean loginBean = (LoginBean) o;
                        showToastTip("登录成功");
                        saveLoginData(loginBean);
                    }
                });
                break;

            case R.id.iv_clear:
                binding.etAccount.setText("");
                break;

            case R.id.iv_pw_show:
                if (showPwd) {
                    binding.ivPwShow.setImageResource(R.mipmap.login_hide_psd);
                    binding.etPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etPw.setSelection(binding.etPw.getText().length());
                    showPwd = false;
                } else {
                    binding.ivPwShow.setImageResource(R.mipmap.login_show_psd);
                    binding.etPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    binding.etPw.setSelection(binding.etPw.getText().length());
                    showPwd = true;
                }
                break;

            default:
                break;
        }
    }

    private void saveLoginData(LoginBean newUser) {
        Context context = DearApplication.getInstance();
        List<AppMenusBean> menus = newUser.getAppMenus();
        if (menus != null && menus.size() > 0) {
            String name = binding.etAccount.getText().toString();
            String pwd = binding.etPw.getText().toString();
            DearApplication.getInstance().saveCacheData(ConstantValue.KEY_USER_NAME, name);
            DearApplication.getInstance().saveCacheData(ConstantValue.KEY_USER_PW, pwd);
            DearApplication.getInstance().saveCacheData(ConstantValue.USER_JSON_STRING, new Gson().toJson(newUser));
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(MainActivity.OPEN_TYPE, MainActivity.LOGIN);
            startActivity(intent);
            getActivity().finish();
        } else {
            SharedPreferencesUtils.removeData(context, ConstantValue.KEY_USER_NAME);
            SharedPreferencesUtils.removeData(context, ConstantValue.KEY_USER_PW);
            SharedPreferencesUtils.removeData(context, ConstantValue.USER_JSON_STRING);
            showToastTip("用户角色未设置任何权限，请联系系统管理员！");
        }
    }


    @Override
    protected int getVariableId() {
        return 0;
    }
}
