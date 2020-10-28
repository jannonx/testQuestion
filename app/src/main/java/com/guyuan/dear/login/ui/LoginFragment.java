package com.guyuan.dear.login.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.google.gson.Gson;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentLoginBinding;
import com.guyuan.dear.login.data.LoginViewModel;

import okhttp3.RequestBody;

/**
 * created by tl
 * created at 2020/8/24
 */

public class LoginFragment extends BaseDataBindingFragment<FragmentLoginBinding,LoginViewModel> implements View.OnClickListener {

    private LoginViewModel viewModel;
    private String name;
    private String pwd;
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
            binding.nameEt.setText(viewModel.getUserName());
        }

        setListener();
    }


    private void setListener() {
        binding.loginTv.setOnClickListener(this);
        binding.nameClearIv.setOnClickListener(this);
        binding.passWordShowIv.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_tv:
                String name = binding.nameEt.getText().toString();
                String pwd = binding.pwdEt.getText().toString();
                viewModel.checkLogin(name, pwd);
                break;

            case R.id.name_clear_iv:
                binding.nameEt.setText("");
                break;

            case R.id.pass_word_show_iv:
                if (showPwd) {
                    binding.passWordShowIv.setImageResource(R.mipmap.login_hide_psd);
                    binding.pwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.pwdEt.setSelection(binding.pwdEt.getText().length());
                    showPwd = false;
                } else {
                    binding.passWordShowIv.setImageResource(R.mipmap.login_show_psd);
                    binding.pwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    binding.pwdEt.setSelection(binding.pwdEt.getText().length());
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
