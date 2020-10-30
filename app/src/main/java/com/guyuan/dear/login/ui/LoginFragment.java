package com.guyuan.dear.login.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentLoginBinding;
import com.guyuan.dear.login.data.LoginViewModel;


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
                viewModel.checkLogin(name, pwd);
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

    @Override
    protected int getVariableId() {
        return 0;
    }
}
