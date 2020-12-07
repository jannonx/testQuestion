package com.guyuan.dear.work.matterapply.data;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.api.RxJavaHelper;
import com.guyuan.dear.base.bean.ListRequestBody;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.matterapply.api.MatterApplyApiService;
import com.guyuan.dear.work.matterapply.data.bean.MatterApplyBody;
import com.guyuan.dear.work.matterapply.data.bean.MatterApplyListBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterDetailBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterMaterialBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterProductBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterProjectBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterTypeBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.http.Query;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 18:54
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyViewModel extends BaseViewModel {
    private MatterApplyApiService applyApiService;
    private MatterApplyViewModelListener listener;

    private MutableLiveData<List<MatterProjectBean>> matterProjectListMLD = new MutableLiveData<>();
    private MutableLiveData<List<MatterTypeBean>> matterTypeListMLD = new MutableLiveData<>();
    private MutableLiveData<List<MatterProductBean>> matterProductListMLD = new MutableLiveData<>();
    private MutableLiveData<List<MatterMaterialBean>> matterMaterialListMLD = new MutableLiveData<>();
    private MutableLiveData<MatterApplyListBean> matterApplyListMLD = new MutableLiveData<>();
    private MutableLiveData<MatterDetailBean> matterDetailMLD = new MutableLiveData<>();

    @ViewModelInject
    public MatterApplyViewModel(MatterApplyRepository repository) {
        applyApiService = repository.getApplyApiService();
    }


    public MutableLiveData<List<MatterProjectBean>> getMatterProjectListMLD() {
        return matterProjectListMLD;
    }

    public MutableLiveData<List<MatterTypeBean>> getMatterTypeListMLD() {
        return matterTypeListMLD;
    }

    public MutableLiveData<List<MatterProductBean>> getMatterProductListMLD() {
        return matterProductListMLD;
    }

    public MutableLiveData<List<MatterMaterialBean>> getMatterMaterialListMLD() {
        return matterMaterialListMLD;
    }

    public MutableLiveData<MatterApplyListBean> getMatterApplyListMLD() {
        return matterApplyListMLD;
    }

    public MutableLiveData<MatterDetailBean> getMatterDetailMLD() {
        return matterDetailMLD;
    }

    //获取项目列表
    public void getMatterProjectList() {
        RxJavaHelper.build(this, applyApiService.getMatterProjectList())
                .getHelper().flow(matterProjectListMLD);
    }


    //获取物料生产类型列表
    public void getMatterTypeList() {
        RxJavaHelper.build(this, applyApiService.getMatterTypeList())
                .getHelper().flow(matterTypeListMLD);
    }

    //获取产品列表
    public void getMatterProductList(int projectID, int taskType) {
        RxJavaHelper.build(this, applyApiService.getMatterProductList(projectID, taskType))
                .getHelper().flow(matterProductListMLD);
    }

    //获取原材料列表
    public void getMaterialList(int productID) {
        RxJavaHelper.build(this, applyApiService.getMaterialList(productID))
                .getHelper().flow(matterMaterialListMLD);
    }

    //申请物料
    public void applyMatter(int approveBy, int id, int materialId, int number,
                            int productId, int projectId, int taskType) {
        MatterApplyBody matterApplyBody = new MatterApplyBody();
        matterApplyBody.setApproveBy(approveBy);
        matterApplyBody.setId(id);
        matterApplyBody.setMaterialId(materialId);
        matterApplyBody.setNumber(number);
        matterApplyBody.setProductId(productId);
        matterApplyBody.setProjectId(projectId);
        matterApplyBody.setTaskType(taskType);

        RxJavaHelper.build(this, applyApiService.applyMatter(
                CommonUtils.getCommonRequestBody(matterApplyBody)))
                .success(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (listener != null) {
                            listener.applySuccess();
                        }
                    }
                })
                .getHelper().flow();
    }

    //领料申请列表
    public void getMatterApplyList(int pageIndex) {
        ListRequestBody requestBody = new ListRequestBody();
        requestBody.setPageNum(pageIndex);
        requestBody.setPageSize(ConstantValue.PAGE_SIZE);
        ListRequestBody.FiltersBean filtersBean = new ListRequestBody.FiltersBean();
        filtersBean.setListType(2);
        requestBody.setFilters(filtersBean);
        RxJavaHelper.build(this, applyApiService.getMatterApplyList(
                CommonUtils.getCommonRequestBody(requestBody)))
                .showLoading(false)
                .getHelper().flow(matterApplyListMLD);
    }

    //领料详情
    public void getMatterDetail(int requestID) {
        RxJavaHelper.build(this, applyApiService.getMatterDetail(requestID))
                .getHelper().flow(matterDetailMLD);
    }


    public void setListener(MatterApplyViewModelListener listener) {
        this.listener = listener;
    }

    public interface MatterApplyViewModelListener {
        void applySuccess();
    }
}