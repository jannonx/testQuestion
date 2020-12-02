package com.guyuan.dear.work.projectsite.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.databinding.FragmentWorkInstallationDebugIngBinding;
import com.guyuan.dear.databinding.FragmentWrokInstallationDebugDetailSingleBinding;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ProjectInstallAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.EventFocusSiteListRefresh;
import com.guyuan.dear.focus.projectsite.bean.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.activity.WorkInstallDebugSingleActivity;
import com.guyuan.dear.work.projectsite.activity.WorkSiteExploresActivity;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;
import com.guyuan.dear.work.projectsite.bean.EventInstallDebugRefresh;
import com.guyuan.dear.work.projectsite.bean.EventWorkSiteListRefresh;
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
public class InstallDebugSingleFragment extends BaseDataBindingFragment<FragmentWrokInstallationDebugDetailSingleBinding, WorkProjectSiteViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener {

    public static final String TAG = InstallDebugSingleFragment.class.getSimpleName();
    protected ArrayList<String> photoList = new ArrayList<>();
    protected ArrayList<String> imageDataList = new ArrayList<>();
    private InstallDebugBean debugBean;
    private List<InstallDebugBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter imageAdapter;
    private SiteExploreBean singleDetailData;
    private WorkInstallDebugSingleActivity activity;

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

        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        binding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.imageRecycleView.setAdapter(imageAdapter);
        binding.imageRecycleView.setPullRefreshEnabled(false);
        binding.imageRecycleView.setLoadMoreEnabled(false);
        viewModel.getInstallDebugDetailDataBySingle(debugBean.getId());


        viewModel.getInstallDebugDetailBySingleEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailData(data);
            }
        });

        viewModel.getUploadImageEvent().observe(this, new Observer<List<UploadBean>>() {
            @Override
            public void onChanged(List<UploadBean> dataList) {
                if (dataList.isEmpty()) return;
                List<String> imageUrlList = new ArrayList<>();
                for (UploadBean bean : dataList) {
                    LogUtils.showLog("upLoadPicAndVideo=" + bean.getUrl());
                    imageUrlList.add(bean.getUrl());
                }
                postInstallDebugInfo(imageUrlList);
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
                if (TextUtils.isEmpty(binding.etSearch.getText().toString())) {
                    ToastUtils.showLong(getContext(), "请填写文本");
                    return;
                }
                if (photoList.isEmpty()) {
                    ToastUtils.showLong(getContext(), "请选择图片");
                    return;
                }
                activity.checkPhotoAndFileUpLoad(imageDataList);
            }
        });

        binding.ivPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openAlbum(BaseTabActivity.FIRST);
            }
        });

        viewModel.getCommitInstallDebugInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                getActivity().finish();
                EventBus.getDefault().post(new EventAnswerListRefresh());
                EventBus.getDefault().post(new EventWorkSiteListRefresh());
            }
        });
    }

    /**
     * 提交安装调试信息
     */
    private void postInstallDebugInfo(List<String> imageArr) {
        PostInstallationDebugInfo body = new PostInstallationDebugInfo();
        if (TextUtils.isEmpty(binding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }
        if (imageArr == null || imageArr.size() == 0) {
            ToastUtils.showLong(getContext(), "请选择图片");
            return;
        }
        body.setId(singleDetailData.getId());
        body.setRemark(binding.etSearch.getText().toString());
        body.setStatus(InstallDebugSatisfyType.TYPE_INSTALL_ING.getCode());
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

    @Override
    public ArrayList<String> getSelectedMediaList() {
        return photoList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (WorkInstallDebugSingleActivity) getActivity();
        }
    }

    @Override
    public void onPhotoSelected(ArrayList<String> dataList) {
        photoList.clear();
        photoList.addAll(dataList);
        imageDataList.clear();
        imageDataList.addAll(photoList);
        imageAdapter.refreshData();
    }
}
