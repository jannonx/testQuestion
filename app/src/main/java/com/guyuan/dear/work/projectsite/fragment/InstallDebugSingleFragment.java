package com.guyuan.dear.work.projectsite.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentWorkInstallationDebugIngBinding;
import com.guyuan.dear.databinding.FragmentWrokInstallationDebugDetailSingleBinding;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.bean.PostInstallationDebugInfo;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--安装调试
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallDebugSingleFragment extends BaseDataBindingFragment<FragmentWrokInstallationDebugDetailSingleBinding, WorkProjectSiteViewModel> {

    public static final String TAG = InstallDebugSingleFragment.class.getSimpleName();
    private InstallDebugBean debugBean;
    private List<InstallDebugBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;
    private SiteExploreBean singleDetailData;

    public static InstallDebugSingleFragment newInstance(InstallDebugBean data) {
        Bundle bundle = new Bundle();
        InstallDebugSingleFragment fragment = new InstallDebugSingleFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_wrok_installation_debug_detail_single;
    }

    @Override
    protected void initialization() {
        debugBean = (InstallDebugBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);

        viewModel.getInstallDebugDetailDataBySingle(debugBean.getId());

        viewModel.getInstallDebugDetailBySingleEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailData(data);
            }
        });

        initListener();
    }


    private void setDetailData(SiteExploreBean data) {
        data.setProjectReportType(ProjectReportType.TYPE_INSTALLATION_DEBUG);
        singleDetailData = data;

        binding.tvProjectName.setText(data.getProjectName());
        binding.tvConstructionOrganization.setText(data.getProjectName());
        binding.tvDutyPerson.setText(data.getPersonLiableName());
        binding.tvTime.setText(data.getDebugStartTime());

        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(data.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));
    }

    private void initListener() {
        //记录字数上限
        int wordLimitNum = 240;
        binding.etSearch.addTextChangedListener(new TextWatcher() {
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
//                viewBinding.tvOk.setClickable(!TextUtils.isEmpty(editable.toString()));
//                viewBinding.tvOk.setEnabled(!TextUtils.isEmpty(editable.toString()));
                //已输入字数
                int enteredWords = wordLimitNum - editable.length();
                //TextView显示剩余字数
                binding.tvNumber.setText(wordLimitNum - enteredWords + "/240");
                int selectionStart = binding.etSearch.getSelectionStart();
                int selectionEnd = binding.etSearch.getSelectionEnd();
                if (enterWords.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(selectionStart - 1, selectionEnd);
                    binding.etSearch.setText(editable);
                    //设置光标在最后
                    binding.etSearch.setSelection(selectionEnd);
                }
            }
        });

        binding.tvActivateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postInstallDebugInfo();
            }
        });

        viewModel.getCommitInstallDebugInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {

                getActivity().finish();
                EventBus.getDefault().post(new EventInstallDebugRefresh());
            }
        });
    }

    /**
     * 提交安装调试信息
     */
    private void postInstallDebugInfo() {
        PostInstallationDebugInfo body = new PostInstallationDebugInfo();
        if (TextUtils.isEmpty(binding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }
        body.setId(singleDetailData.getId());
        body.setRemark(binding.etSearch.getText().toString());
        body.setStatus(InstallDebugSatisfyType.TYPE_INSTALL_ING.getCode());
        List<String> imageArr = new ArrayList<>();
        imageArr.add("https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/.png160612221432475");
        body.setImgUrl(imageArr);
        String str = GsonUtil.objectToString(body);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), str);

        viewModel.postInstallDebugInfo(requestBody);
    }


    @Override
    protected int getVariableId() {
        return 0;
    }

}
