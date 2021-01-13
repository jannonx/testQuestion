package com.guyuan.dear.work.projectsite.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
import com.guyuan.dear.databinding.FragmentWorkSiteExplorationIngBinding;
import com.guyuan.dear.focus.projectsite.adapter.CheckContentAdapter;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.type.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.ProjectSiteOpinionBean;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.utils.keyboardlayout.OnKeyboardStateListener;
import com.guyuan.dear.work.projectsite.activity.WorkSiteExploresActivity;
import com.guyuan.dear.work.projectsite.bean.EventWorkSiteListRefresh;
import com.guyuan.dear.work.projectsite.bean.PostSiteExploreInfo;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewAdapter;

/**
 * @description: 我的工作--工程现场--现场勘查
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class SiteExplorationFragment extends BaseDataBindingFragment<FragmentWorkSiteExplorationIngBinding, WorkProjectSiteViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener, OnKeyboardStateListener {

    public static final String TAG = SiteExplorationFragment.class.getSimpleName();
    protected ArrayList<Uri> photoList = new ArrayList<>();
    protected ArrayList<String> imageDataList = new ArrayList<>();
    private SiteExploreBean detailProjectData;
    // 是否满足条件、是否安全(1:是，2:否)
    private static final int TYPE_CONDITION_OK = 1;
    private static final int TYPE_CONDITION_EXCEPTION = 2;
    private List<ProjectSiteOpinionBean> listData = new ArrayList<>();
    private int isConditionOK = 1;
    public BaseRecyclerViewAdapter adapter;
    public BaseRecyclerViewAdapter imageAdapter;
    private WorkSiteExploresActivity activity;

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

        binding.labelDocument.setText(imageDataList.size() == 0 ? "拍照电子档" : "电子文件档");
        binding.tvTip.setText(imageDataList.size() == 0 ? "点击此框上传资料拍照照片" : "点击图片，放大查看");

        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image, true);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        binding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.imageRecycleView.setAdapter(imageAdapter);
        binding.imageRecycleView.setPullRefreshEnabled(false);
        binding.imageRecycleView.setLoadMoreEnabled(false);

        binding.tvActivateBtn.setText(detailProjectData.getProjectReportType() == ProjectReportType.TYPE_SITE_EXPLORATION
                ? "完成勘查" : "完成排查");
        binding.tvActivateBtn.setVisibility(detailProjectData.getProjectReportType() == ProjectReportType.TYPE_SITE_EXPLORATION ?
                CommonUtils.isShowButton(ConstantValue.PROJECT_SITE_SURVEY_FINISH) ? View.VISIBLE : View.GONE :
                CommonUtils.isShowButton(ConstantValue.PROJECT_SITE_SAFE_INVESTIGATE_FINISH) ? View.VISIBLE : View.GONE);
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
        binding.flRoot.setOnKeyboardStateListener(this);
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
//                setBottomViewHeight();
                if (editable.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(wordLimitNum, editable.length());
                    binding.etSearch.setText(editable);

                    binding.etSearch.setCursorVisible(true);
                    binding.etSearch.requestFocus();
                    binding.etSearch.setFocusable(true);//获得焦点
                    binding.etSearch.setFocusableInTouchMode(true);//获得焦点
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
                    ToastUtils.showLong(getContext(), "请填内容");
                    return;
                }
                if (imageDataList == null || imageDataList.size() == 0) {
                    ToastUtils.showLong(getContext(), "请选择图片");
                    return;
                }
                activity.checkPhotoAndFileUpLoad(imageDataList);
            }
        });

        binding.clPickPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openAlbum(BaseTabActivity.FIRST);
            }
        });


        viewModel.getCommitSiteExploreInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                getActivity().finish();
                EventBus.getDefault().post(new EventWorkSiteListRefresh());
            }
        });
        viewModel.getCommitCheckSafeInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                getActivity().finish();
                EventBus.getDefault().post(new EventWorkSiteListRefresh());
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

        viewModel.getUploadImageEvent().observe(this, new Observer<List<UploadBean>>() {
            @Override
            public void onChanged(List<UploadBean> dataList) {
                if (dataList.isEmpty()) return;
                List<String> imageUrlList = new ArrayList<>();
                for (UploadBean bean : dataList) {
                    LogUtils.showLog("upLoadPicAndVideo=" + bean.getUrl());
                    imageUrlList.add(bean.getUrl());
                }
                postSiteExploreInfo(imageUrlList);
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

    private void postSiteExploreInfo(List<String> imageList) {
        PostSiteExploreInfo body = new PostSiteExploreInfo();

        body.setId(detailProjectData.getId());
        body.setSatisfyFlag(isConditionOK);
//        List<String> imageArr = new ArrayList<>();
//        imageArr.add("https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/.png160612221432475");
        //图片
        body.setImgUrl(imageList);

        body.setAuditExplain(binding.etSearch.getText().toString());
//        List<ProjectSiteOpinionBean> opinionList = new ArrayList<>();
//        for (ProjectSiteOpinionBean opinionBean : listData) {
//            opinionList.add(opinionBean);
//        }
        body.setPsAuditItemDetailParamsList(listData);

        String str = GsonUtil.objectToString(body);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                "charset=utf-8"), str);

        //先压缩图片
        if (ProjectReportType.TYPE_SITE_EXPLORATION == detailProjectData.getProjectReportType()) {
            viewModel.commitSiteExploreInfo(requestBody);
        } else {
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

    @Override
    public ArrayList<Uri> getSelectedMediaList() {
        return photoList;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (WorkSiteExploresActivity) getActivity();
        }
    }

    @Override
    public void onKeyboardOpened(int height) {
//        LogUtils.showLog("keyboardHeight=" + height);
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
//        LogUtils.showLog("onKeyboardClosed");
//        binding.viewEmptyBottom.setVisibility(View.VISIBLE);
//        setGuideBottomHeight(0);
//        binding.nsvContent.fullScroll(NestedScrollView.FOCUS_DOWN);
    }

    private int keyboardHeight;

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
//    public void setGuideBottomHeight(int height) {
//        binding.vGuideBottom.setVisibility(height == 0 ? View.GONE : View.VISIBLE);
//        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.vGuideBottom.getLayoutParams();
//        layoutParams.height = height;
//        LogUtils.showLog("getVisibility=" + (binding.vGuideBottom.getVisibility() == View.VISIBLE));
//        LogUtils.showLog("getHeight00=" + (binding.vGuideBottom.getHeight()));
//        binding.vGuideBottom.setLayoutParams(layoutParams);
//        LogUtils.showLog("getHeight11=" + (binding.vGuideBottom.getHeight()));
//    }

    /**
     * 获取editView光标所在行数
     */
    private int getCurrentCursorLine(EditText editText) {
        int selectionStart = Selection.getSelectionStart(editText.getText());
        Layout layout = editText.getLayout();

        if (selectionStart != -1) {
            return layout.getLineForOffset(selectionStart) + 1;
        }
        return -1;
    }
}
