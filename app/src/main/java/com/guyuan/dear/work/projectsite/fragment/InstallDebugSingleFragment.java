package com.guyuan.dear.work.projectsite.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.databinding.FragmentWrokInstallationDebugDetailSingleBinding;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;

import com.guyuan.dear.focus.projectsite.bean.InstallDebugBean;

import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.focus.projectsite.type.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.type.ProjectReportType;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ScreenUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.utils.keyboardlayout.OnKeyboardStateListener;
import com.guyuan.dear.work.projectsite.activity.WorkInstallDebugSingleActivity;
import com.guyuan.dear.work.projectsite.bean.EventAnswerListRefresh;
import com.guyuan.dear.work.projectsite.bean.EventWorkSiteListRefresh;
import com.guyuan.dear.work.projectsite.bean.PostInstallationDebugInfo;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @description: 我的工作--工程现场--安装调试
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallDebugSingleFragment extends BaseDataBindingFragment<FragmentWrokInstallationDebugDetailSingleBinding, WorkProjectSiteViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener, OnKeyboardStateListener {

    public static final String TAG = InstallDebugSingleFragment.class.getSimpleName();
    protected ArrayList<Uri> photoList = new ArrayList<>();
    protected ArrayList<String> imageDataList = new ArrayList<>();
    private InstallDebugBean debugBean;
    private List<InstallDebugBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter imageAdapter;
    private SiteExploreBean singleDetailData;
    private WorkInstallDebugSingleActivity activity;
    private Handler handler = new Handler();

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
                imageDataList, R.layout.item_explorate_image, true);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        binding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.imageRecycleView.setAdapter(imageAdapter);
        binding.imageRecycleView.setPullRefreshEnabled(false);
        binding.imageRecycleView.setLoadMoreEnabled(false);
        viewModel.getInstallDebugDetailDataBySingle(debugBean.getId());

        imageViewAdapter.setAdapterListener(new ContentImageViewAdapter.OnListAdapterListener() {
            @Override
            public void onDeleteCLick(int position) {
                photoList.remove(position);
                if (imageDataList.size() == 0) {
                    binding.labelDocument.setText("拍照电子档");
                    binding.tvTip.setText("点击此框上传资料拍照照片");
                }
            }
        });
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
        binding.tvTime.setText(data.getShowDebugTime());

        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(data.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));

        binding.labelDocument.setText(imageDataList.size() == 0 ? "拍照电子档" : "电子文件档");
        binding.tvTip.setText(imageDataList.size() == 0 ? "点击此框上传资料拍照照片" : "点击图片，放大查看");
        binding.tvActivateBtn.setVisibility(CommonUtils.isShowButton(ConstantValue.PROJECT_SITE_INSTALL_START) ? View.VISIBLE : View.GONE);
    }

    private void initListener() {
        binding.kflContent.setOnKeyboardStateListener(this);

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

                if (editable.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(wordLimitNum, editable.length());
                    binding.etSearch.setText(editable);
                    //设置光标在最后
                    binding.etSearch.setSelection(binding.etSearch.getText().toString().length());
                }

                //已输入字数
                int enteredWords = wordLimitNum - editable.length();
                //TextView显示剩余字数
                binding.tvNumber.setText((wordLimitNum - enteredWords) + "/240");
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

//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        setGuideBottomHeight(0);
//                        //设置ScrollView滚动到顶部
//                        binding.nsvContent.fullScroll(NestedScrollView.FOCUS_DOWN);
//                    }
//                });
            }
        });

        binding.clPickPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openAlbum(BaseTabActivity.FIRST);
            }
        });

        viewModel.getCommitInstallDebugInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                getActivity().finish();
                photoList.clear();
                EventBus.getDefault().post(new EventAnswerListRefresh());
                EventBus.getDefault().post(new EventWorkSiteListRefresh());
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                //设置ScrollView滚动到顶部
                int perHeight = ScreenUtils.dip2px(getContext(), 200);
                LogUtils.showLog("perHeight=" + perHeight);
                setGuideBottomHeight(perHeight);
                binding.nsvContent.fullScroll(NestedScrollView.FOCUS_DOWN);
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
    public ArrayList<Uri> getSelectedMediaList() {
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
    public void onPhotoSelected(ArrayList<Uri> dataList) {
        photoList.clear();
        photoList.addAll(dataList);
        imageDataList.clear();
        imageDataList.addAll(CommonUtils.getFilePath(photoList));
        imageAdapter.refreshData();

        binding.labelDocument.setText(imageDataList.size() == 0 ? "拍照电子档" : "电子文件档");
        binding.tvTip.setText(imageDataList.size() == 0 ? "点击此框上传资料拍照照片" : "点击图片，放大查看");
    }

    @Override
    public void onKeyboardOpened(int height) {
        LogUtils.showLog("keyboardHeight=" + height);
//        binding.viewEmptyBottom.setVisibility(View.GONE);
//        keyboardHeight = height- ScreenUtils.dip2px(getContext(), 150);
//        setGuideBottomHeight(height);
//        binding.nsvContent.fullScroll(NestedScrollView.FOCUS_DOWN);

//        binding.etSearch.setCursorVisible(true);
//        binding.etSearch.requestFocus();
//        binding.etSearch.setFocusable(true);//获得焦点
//        binding.etSearch.setFocusableInTouchMode(true);//获得焦点
    }

    @Override
    public void onKeyboardClosed() {
        LogUtils.showLog("onKeyboardClosed");
//        binding.viewEmptyBottom.setVisibility(View.VISIBLE);
//        setGuideBottomHeight(0);
//        binding.nsvContent.fullScroll(NestedScrollView.FOCUS_DOWN);
    }


//
//    private int keyboardHeight;
//
//    private void setBottomViewHeight() {
//        int perHeight = ScreenUtils.dip2px(getContext(), 20);
//        setGuideBottomHeight(keyboardHeight + getCurrentCursorLine(binding.etSearch) * perHeight);
//        LogUtils.showLog("setBottomViewHeight=" + getCurrentCursorLine(binding.etSearch) * perHeight);
//        LogUtils.showLog("setBottomViewHeight11=" + (keyboardHeight + getCurrentCursorLine(binding.etSearch) * perHeight));
//        binding.nsvContent.fullScroll(NestedScrollView.FOCUS_DOWN);
//    }

    /**
     * 设置属性高度
     */
    public void setGuideBottomHeight(int height) {
//        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.vGuideBottom.getLayoutParams();
//        layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
//        int topMargin = ScreenUtils.dip2px(getContext(), height);
//        layoutParams.height = topMargin;
//        LogUtils.showLog("topMargin=" + topMargin);
//        LogUtils.showLog("getVisibility=" + (binding.vGuideBottom.getVisibility() == View.VISIBLE));
//        LogUtils.showLog("getHeight00=" + (binding.vGuideBottom.getHeight()));
//        binding.vGuideBottom.setLayoutParams(layoutParams);
//        LogUtils.showLog("getHeight11=" + (binding.vGuideBottom.getHeight()));
//        binding.vGuideBottom.setVisibility(height == 0 ? View.GONE : View.VISIBLE);
    }
}
