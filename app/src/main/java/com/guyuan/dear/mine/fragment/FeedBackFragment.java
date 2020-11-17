package com.guyuan.dear.mine.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.httplibrary.bean.ResultBean;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFeedBackBinding;
import com.guyuan.dear.focus.client.activity.FocusClientActivity;
import com.guyuan.dear.focus.client.bean.ClientCompanyBean;
import com.guyuan.dear.focus.client.bean.ListClientRequestBody;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.mine.activity.FeedBackActivity;
import com.guyuan.dear.mine.bean.MineRequestBody;
import com.guyuan.dear.mine.data.MineViewModel;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;

import okhttp3.RequestBody;

import static com.guyuan.dear.utils.ConstantValue.FIRST_PAGE;
import static com.guyuan.dear.utils.ConstantValue.PAGE_SIZE;

/**
 * @description: 我的--意见反馈
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FeedBackFragment extends BaseDataBindingFragment<FragmentFeedBackBinding, MineViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static FeedBackFragment newInstance() {
        Bundle args = new Bundle();
        FeedBackFragment fragment = new FeedBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_feed_back;
    }

    @Override
    protected void initialization() {
        initView();

        binding.tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postFeedBackRemark();

            }
        });
        viewModel.getFeedBackEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer resultBean) {
                ToastUtils.showShort(getContext(), "提交反馈成功！");
                getActivity().finish();
            }
        });
    }

    private void postFeedBackRemark() {
        String remark = binding.etFeedBack.getText().toString();
        if (TextUtils.isEmpty(remark)) {
            ToastUtils.showShort(getContext(), "请输入留言信息");
            return;
        }

        viewModel.postFeedBack(getRequestBody());
    }

    private RequestBody getRequestBody() {
        MineRequestBody body = new MineRequestBody();
        LoginBean loginInfo = CommonUtils.getLoginInfo();
        LogUtils.showLog("getId=" + loginInfo.getId());
//        body.setCreateBy(loginInfo.getId());
        body.setRemark(binding.etFeedBack.getText().toString());

        String str = GsonUtil.objectToString(body);
        return RequestBody.create(okhttp3.MediaType.parse("application/json; " +
                "charset=utf-8"), str);
    }

    private void initView() {
        //记录字数上限
        int wordLimitNum = 240;
        binding.etFeedBack.addTextChangedListener(new TextWatcher() {
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
                //已输入字数
                int enteredWords = wordLimitNum - editable.length();
                //TextView显示剩余字数
                binding.tvNumber.setText(wordLimitNum - enteredWords + "/240");
                int selectionStart = binding.etFeedBack.getSelectionStart();
                int selectionEnd = binding.etFeedBack.getSelectionEnd();
                if (enterWords.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(selectionStart - 1, selectionEnd);
                    binding.etFeedBack.setText(editable);
                    //设置光标在最后
                    binding.etFeedBack.setSelection(selectionEnd);
                }
            }
        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            FeedBackActivity activity = (FeedBackActivity) getActivity();
            viewModel = activity.getViewModel();
        }
    }

}
