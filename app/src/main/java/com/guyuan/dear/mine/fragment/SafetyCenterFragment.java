package com.guyuan.dear.mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentSafetyCenterBinding;
import com.guyuan.dear.mine.activity.SafetyCenterActivity;
import com.guyuan.dear.mine.data.MineViewModel;
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
                getActivity().finish();
            }
        });
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

        if (!newPw.equals(confirmPw)) {
            ToastUtils.showLong(getContext(), "新密码和确认密码不一致");
            return;
        }

        viewModel.editUserPassWord(curPw, newPw);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            SafetyCenterActivity activity = (SafetyCenterActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }
}
