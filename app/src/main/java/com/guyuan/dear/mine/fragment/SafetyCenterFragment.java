package com.guyuan.dear.mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentSafetyCenterBinding;
import com.guyuan.dear.mine.activity.SafetyCenterActivity;
import com.guyuan.dear.mine.data.MineViewModel;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ToastUtils;


/**
 * @description: 我的--安全中心
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class SafetyCenterFragment extends BaseDataBindingFragment<FragmentSafetyCenterBinding, MineViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static SafetyCenterFragment newInstance() {
        Bundle args = new Bundle();
        SafetyCenterFragment fragment = new SafetyCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_safety_center;
    }

    @Override
    protected void initialization() {
        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserPw();
            }
        });

        viewModel.getEditUserPwEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer dataRefreshBean) {
                ToastUtils.showShort(getContext(), "修改密码成功！");
                CommonUtils.logout(getContext());
            }
        });

        setInputEditView(binding.etCurrentPw);
        setInputEditView(binding.etNewPw);
        setInputEditView(binding.etConfirmPw);
    }


    /**
     * 保存密码
     */
    private void saveUserPw() {
        String curPw = binding.etCurrentPw.getText().toString();
        String newPw = binding.etNewPw.getText().toString();
        String confirmPw = binding.etConfirmPw.getText().toString();
        if (TextUtils.isEmpty(curPw) || TextUtils.isEmpty(newPw) || TextUtils.isEmpty(confirmPw)) {
            ToastUtils.showLong(getContext(), "密码信息不能为空");
            return;
        }

        if (checkInputMinLength(binding.etCurrentPw) || checkInputMinLength(binding.etNewPw)
                || checkInputMinLength(binding.etConfirmPw)) {
            ToastUtils.showLong(getContext(), "输入字数必须大于或等于6位");
            return;
        }

        if (!newPw.equals(confirmPw)) {
            ToastUtils.showLong(getContext(), "新密码和确认密码不一致");
            return;
        }

        if (newPw.equals(curPw)) {
            showToastTip("新密码不能与原密码一致");
            return;
        }

        viewModel.editUserPassWord(curPw, newPw);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    /**
     * 输入文字下限
     */
    private boolean checkInputMinLength(AppCompatEditText editView) {
        //输入文字下限
        int wordMinNum = 6;
        return editView.getText().length() < wordMinNum;

    }

    private void setInputEditView(AppCompatEditText editView) {
        //记录字数上限
        int wordLimitNum = 16;
        editView.addTextChangedListener(new TextWatcher() {
            //记录输入的字数
            private CharSequence enterWords;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //实时记录输入的字数
                enterWords = s;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(wordLimitNum, editable.length());
                    editView.setText(editable);
                    //设置光标在最后
                    editView.setSelection(editView.getText().toString().length());
                }
            }
        });


    }
}
