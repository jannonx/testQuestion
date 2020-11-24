package com.guyuan.dear.work.projectsite.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.example.httplibrary.bean.RefreshBean;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.DialogWorkProjectCheckBinding;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.databinding.FragmentWorkCheckGoodImgBinding;
import com.guyuan.dear.databinding.FragmentWorkSiteExplorationIngBinding;
import com.guyuan.dear.focus.projectsite.adapter.CheckContentAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ProjectModuleType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.adapter.SiteExplorationAdapter;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.bean.PostSiteExploreInfo;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--现场勘查
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class SiteExplorationFragment extends BaseDataBindingFragment<FragmentWorkSiteExplorationIngBinding, WorkProjectSiteViewModel> {

    public static final String TAG = SiteExplorationFragment.class.getSimpleName();
    private SiteExploreBean detailProjectData;
    // 是否满足条件、是否安全(1:是，2:否)
    private static final int TYPE_CONDITION_OK = 1;
    private static final int TYPE_CONDITION_EXCEPTION = 2;
    private List<ProjectSiteOpinionBean> listData = new ArrayList<>();
    private int isConditionOK = 1;
    public BaseRecyclerViewAdapter adapter;

    public static SiteExplorationFragment newInstance(SiteExploreBean siteExploreBean) {
        Bundle args = new Bundle();
        SiteExplorationFragment fragment = new SiteExplorationFragment();
        args.putSerializable(ConstantValue.KEY_CONTENT, siteExploreBean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_site_exploration_ing;
    }

    @Override
    protected void initialization() {
        detailProjectData = (SiteExploreBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);

        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        CheckContentAdapter checkContentAdapter = new CheckContentAdapter(getContext(),
                listData, R.layout.item_explorate_content, detailProjectData);
        adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        binding.tvActivateBtn.setText(detailProjectData.getProjectReportType() == ProjectReportType.TYPE_SITE_EXPLORATION
                ? "完成勘查" : "完成排查");
        getWorkDetailData();
        initListener();
    }

    private void getWorkDetailData() {
        switch (detailProjectData.getProjectReportType()) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                viewModel.getSiteExploreDetailData(detailProjectData.getId());
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                viewModel.getCheckSafeDetailData(detailProjectData.getId());
                break;


            default:
        }
    }

    private void initListener() {
        switchRadioButton(binding.rbRight, true);
        switchRadioButton(binding.rbWrong, false);
        //默认正常
        binding.rgCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.rb_right:
                        isConditionOK = TYPE_CONDITION_OK;
                        switchRadioButton(binding.rbRight, true);
                        switchRadioButton(binding.rbWrong, false);
                        break;
                    case R.id.rb_wrong:
                        isConditionOK = TYPE_CONDITION_EXCEPTION;
                        switchRadioButton(binding.rbRight, false);
                        switchRadioButton(binding.rbWrong, true);
                        break;
                    default:
                        break;
                }
            }
        });

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
                postSiteExploreInfo();
            }
        });


        viewModel.getCommitSiteExploreInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                getActivity().finish();
                EventBus.getDefault().post(new EventInstallDebugRefresh());
            }
        });
        viewModel.getCommitCheckSafeInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                getActivity().finish();
                EventBus.getDefault().post(new EventInstallDebugRefresh());
            }
        });

        viewModel.getSiteExploreDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailDataByType(data);
            }
        });
        viewModel.getCheckSafeDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailDataByType(data);
            }
        });
    }

    private void setDetailDataByType(SiteExploreBean data) {
        switch (detailProjectData.getProjectReportType()) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                setSiteExploreData(data);
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                setCheckSafeData(data);
                break;
            default:
        }
    }

    private void postSiteExploreInfo() {
        PostSiteExploreInfo body = new PostSiteExploreInfo();
        if (TextUtils.isEmpty(binding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }

        body.setId(detailProjectData.getId());
        body.setSatisfyFlag(isConditionOK);
        List<String> imageArr = new ArrayList<>();
        imageArr.add("https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/.png160612221432475");
        body.setImgUrl(imageArr);

        body.setAuditExplain(binding.etSearch.getText().toString());
        List<ProjectSiteOpinionBean> opinionList = new ArrayList<>();
        for (ProjectSiteOpinionBean opinionBean : listData) {
            opinionList.add(opinionBean);
        }
        body.setPsAuditItemDetailParamsList(opinionList);

        String str = GsonUtil.objectToString(body);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), str);
        if (ProjectReportType.TYPE_SITE_EXPLORATION==detailProjectData.getProjectReportType()){
            viewModel.commitSiteExploreInfo(requestBody);
        }else{
            viewModel.postCheckSafeInfo(requestBody);
        }
    }


    private void setCheckSafeData(SiteExploreBean data) {
        data.setProjectReportType(detailProjectData.getProjectReportType());
        detailProjectData = data;

        binding.tvProjectName.setText(detailProjectData.getProjectName());

        binding.tvProjectCode.setText(detailProjectData.getProjectNum());
        binding.labelCheckName.setText("安全人员：");
        binding.tvCheckName.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());

        //状态属性设置
        binding.tvProjectStatus.setText(detailProjectData.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(detailProjectData.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(detailProjectData.getStatusTextBg());
        int statusTextColor = detailProjectData.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));

        listData.addAll(data.getPsAuditItemVOList());
        adapter.refreshData();
    }

    private void setSiteExploreData(SiteExploreBean data) {
        data.setProjectReportType(detailProjectData.getProjectReportType());
        detailProjectData = data;

        binding.tvProjectName.setText(detailProjectData.getProjectName());

        binding.tvProjectCode.setText(detailProjectData.getProjectNum());
        binding.tvCheckName.setText(detailProjectData.getName());

        binding.tvCompanyName.setText(detailProjectData.getCusName());
        binding.tvTime.setText(detailProjectData.getCreateTime());
        binding.tvCompanyLocation.setText(detailProjectData.getDestination());

        //状态属性设置
        binding.tvProjectStatus.setText(detailProjectData.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(detailProjectData.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(detailProjectData.getStatusTextBg());
        int statusTextColor = detailProjectData.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));

        listData.addAll(data.getPsAuditItemVOList());
        adapter.refreshData();


    }

    /**
     * 切换radioButton状态
     *
     * @param button    组件
     * @param isChecked 选中
     */
    private void switchRadioButton(AppCompatRadioButton button, boolean isChecked) {
        //定义底部标签图片大小和位置
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable = getContext().getResources().getDrawable(isChecked ?
                R.drawable.bg_work_project_rb_selector : R.drawable.bg_work_project_rb_unselector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        button.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
