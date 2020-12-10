package com.guyuan.dear.work.projectsite.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseFileUploadActivity;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.UploadBean;
import com.guyuan.dear.databinding.FragmentWorkCheckGoodImgBinding;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.type.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.type.FunctionModuleType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.GsonUtil;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.projectsite.activity.WorkCheckGoodsActivity;
import com.guyuan.dear.work.projectsite.adapter.CheckGoodsAdapter;
import com.guyuan.dear.work.projectsite.bean.EventWorkSiteListRefresh;
import com.guyuan.dear.work.projectsite.bean.OnConfirmDialogListenerImpl;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description: 我的工作--工程现场--清点货物
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsFragment extends BaseDataBindingFragment<FragmentWorkCheckGoodImgBinding, WorkProjectSiteViewModel>
        implements BaseFileUploadActivity.PhotoSelectListener {
    // 到货或清点操作,1：到货操作标识，2：清点操作标识
    private static final int TYPE_GOOD_ARRIVE = 1;
    private static final int TYPE_GOOD_CHECK = 2;

    public static final String TAG = CheckGoodsFragment.class.getSimpleName();
    private SiteExploreBean detailData;
    protected ArrayList<Uri> photoList = new ArrayList<>();
    private List<CheckGoodsBean> listData = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;
    private WorkCheckGoodsActivity activity;

    private ProjectCheckConfirmDialog customerDialog;
    private PostCheckInfo postData;

    public static CheckGoodsFragment newInstance(SiteExploreBean data) {
        Bundle bundle = new Bundle();
        CheckGoodsFragment fragment = new CheckGoodsFragment();
        bundle.putSerializable(ConstantValue.KEY_CONTENT, data);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_check_good_img;
    }

    @Override
    protected void initialization() {
        detailData = (SiteExploreBean) getArguments().getSerializable(ConstantValue.KEY_CONTENT);

        binding.baseRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        CheckGoodsAdapter checkContentAdapter = new CheckGoodsAdapter(getContext(),
                listData, R.layout.item_goods_list, FunctionModuleType.TYPE_WORK);
        adapter = new BaseRecyclerViewAdapter(checkContentAdapter);
        binding.baseRecycleView.setAdapter(adapter);
        binding.baseRecycleView.setPullRefreshEnabled(false);
        binding.baseRecycleView.setLoadMoreEnabled(false);

        viewModel.getCheckGoodDetailData(detailData.getId());

        initListener();

    }

    private void initListener() {
        viewModel.getCheckGoodDetailEvent().observe(getActivity(), new Observer<SiteExploreBean>() {
            @Override
            public void onChanged(SiteExploreBean data) {
                setDetailData(data);
            }
        });
        viewModel.getCommitCheckGoodInfoEvent().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                photoList.clear();
                if (detailData.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING){
                    viewModel.getCheckGoodDetailData(detailData.getId());
                }else{
                    getActivity().finish();
                }
                EventBus.getDefault().post(new EventWorkSiteListRefresh());
            }
        });
        binding.tvActivateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定到货
                if (detailData.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING) {
                    postData = new PostCheckInfo();
                    postData.setId(detailData.getId());
                    postData.setSign(TYPE_GOOD_ARRIVE);
                    String str = GsonUtil.objectToString(postData);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                            "charset=utf-8"), str);
                    viewModel.postCheckGoodInfo(requestBody);
                } else {
                    //确认庆典
                    confirmCheckGoods();
                }

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
                postData.setCheckUrl(imageUrlList);
                String str = GsonUtil.objectToString(postData);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; " +
                        "charset=utf-8"), str);
                viewModel.postCheckGoodInfo(requestBody);
            }
        });
    }

    /**
     * 确认到货及完成清点
     */
    private void confirmCheckGoods() {
        customerDialog = new ProjectCheckConfirmDialog(getActivity(), detailData, new OnConfirmDialogListenerImpl() {
            @Override
            public void onPickImageClick() {
                activity.openAlbum(BaseTabActivity.FIRST);
            }

            @Override
            public void onCommitCheckGoodsInfo(PostCheckInfo data) {
                postData = data;
                activity.checkPhotoAndFileUpLoad(CommonUtils.getFilePath(photoList));
            }

            @Override
            public void onDeleteClick(int position) {
                photoList.remove(position);
            }
        });
        customerDialog.show();
        customerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                customerDialog = null;
                photoList.clear();
            }
        });

    }

    private void setDetailData(SiteExploreBean data) {
        data.setProjectReportType(detailData.getProjectReportType());
        detailData = data;

        binding.tvProjectName.setText(data.getSendGoodsNumber());
        binding.labelProjectCode.setText("项目名称：");
        binding.tvProjectCode.setText(data.getProjectName());
        binding.labelCheckName.setText("项目编号：");
        binding.tvCheckName.setText(data.getProjectNumber());


        //状态属性设置
        binding.tvProjectStatus.setText(data.getStatusText());
        //可见
        binding.tvProjectStatus.setVisibility(data.getStatusTextVisible() ? View.VISIBLE : View.GONE);
        binding.tvProjectStatus.setBackgroundResource(data.getStatusTextBg());
        int statusTextColor = data.getStatusTextColor();
        binding.tvProjectStatus.setTextColor(getActivity().getResources().getColor(statusTextColor, null));


        binding.tvCompanyName.setText(data.getCustomerName());
        binding.tvTime.setText(data.getCreateTime());
        binding.tvCompanyLocation.setText(data.getAcceptAddress());

        binding.clGoodsList.setVisibility(detailData.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                ? View.GONE : View.VISIBLE);

        List<CheckGoodsBean> checkGoodsListData = data.getCheckTransportProjectListVO();
        binding.tvTotalGoods.setText("共计货物：" + (checkGoodsListData != null ? checkGoodsListData.size() : 0) + "件");

        listData.clear();
        listData.addAll(checkGoodsListData);
        adapter.refreshData();

        binding.tvActivateBtn.setText(data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                ? "确认到货" : data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_CHECK_ING ? "完成清点" : "");
        binding.tvActivateBtn.setVisibility(data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                || data.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_CHECK_ING ? View.VISIBLE : View.GONE);
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
        LogUtils.showLog("setPhotoList=" + photoList.size());
        customerDialog.setPhotoList(dataList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            activity = (WorkCheckGoodsActivity) getActivity();
        }
    }
}
