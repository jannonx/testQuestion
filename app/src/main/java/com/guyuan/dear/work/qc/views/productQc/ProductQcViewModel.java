package com.guyuan.dear.work.qc.views.productQc;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.repo.ProductQcRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 10:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcViewModel extends BaseDearViewModel {
    /**
     * 数据源
     */
    public MutableLiveData<ArrayList<StaffBean>> qcCheckers = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<ArrayList<StaffBean>> verifiers = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<BaseProjectBean>> projectList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<BaseQcApproachBean>> qcApproachList = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<BaseProductBatchInfo>> productBatchList = new MutableLiveData<>(new ArrayList<>());
    private List<Integer> judgeConditions = new ArrayList<>();

    public MutableLiveData<BaseProductBatchInfo> selectedProductBatch = new MutableLiveData<>();
    public MutableLiveData<BaseProjectBean> selectedProject = new MutableLiveData<>();
    public MutableLiveData<BaseQcApproachBean> selectedQcApproach = new MutableLiveData<>();
    public MutableLiveData<String> sampleSize = new MutableLiveData<>("0");
    public MutableLiveData<Integer> reportResult = new MutableLiveData<>();
    public MutableLiveData<String> comments = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNeedVerify = new MutableLiveData<>(false);
    /**
     * 是否要忽略post数据为空时相关提示
     */
    public AtomicBoolean shouldShowToastNoData = new AtomicBoolean(false);


    /**
     * 点击事件：选择项目
     */
    public MutableLiveData<View.OnClickListener> onClickSelectProject = new MutableLiveData<>();
    /**
     * 点击事件：选择出厂编号
     */
    public MutableLiveData<View.OnClickListener> onClickSelectBatch = new MutableLiveData<>();
    /**
     * 点击事件：选择质检方式
     */
    public MutableLiveData<View.OnClickListener> onClickSelectQcApproach = new MutableLiveData<>();
    /**
     * 点击事件：选择质检人员
     */
    public MutableLiveData<View.OnClickListener> onClickAddQcCheckers = new MutableLiveData<>();
    /**
     * 点击事件：选择判定结果
     */
    public MutableLiveData<View.OnClickListener> onClickSelectResult = new MutableLiveData<>();
    /**
     * 点击事件：选择是否需要审批
     */
    public MutableLiveData<View.OnClickListener> onClickSelectIfNeedVerify = new MutableLiveData<>();
    /**
     * 点击事件：选择审批人
     */
    public MutableLiveData<View.OnClickListener> onClickAddVerifiers = new MutableLiveData<>();
    /**
     * 点击事件：提交
     */
    public MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();

    private ProductQcRepo repo = new ProductQcRepo();
    public MutableLiveData<Boolean> isCheckAllProducts = new MutableLiveData<>(false);


    public Disposable getProjectListFromNet() {
        shouldShowToastNoData.set(true);
        return repo.getProductProjectListFromNet(new BaseNetCallback<List<BaseProjectBean>>() {
            @Override
            protected void handleResult(List<BaseProjectBean> result) {
                projectList.getValue().clear();
                projectList.getValue().addAll(result);
                projectList.postValue(projectList.getValue());
            }
        });
    }

    public Disposable getQcApproachesFromNet() {
        shouldShowToastNoData.set(true);
        return repo.getQcApproaches(new BaseNetCallback<List<BaseQcApproachBean>>() {
            @Override
            protected void handleResult(List<BaseQcApproachBean> result) {
                qcApproachList.getValue().clear();
                qcApproachList.getValue().addAll(result);
                qcApproachList.postValue(qcApproachList.getValue());
            }
        });
    }

    public List<Integer> getJudgeConditions() {
        return judgeConditions;
    }

    public void updateSelectedProject(BaseProjectBean projectBean) {
        selectedProject.postValue(projectBean);
        selectedProductBatch.postValue(null);
        sampleSize.postValue("0");
    }

    public void updateSelectedBatchInfo(BaseProductBatchInfo info) {
        selectedProductBatch.postValue(info);
        BaseQcApproachBean value = selectedQcApproach.getValue();
        if (value != null) {
            String approachId = value.getApproachId();
            //1表示全检，这时默认选择最大数量
            if ("1".equals(approachId)) {
                sampleSize.postValue(String.valueOf(info.getProducts().get(0).getQuantity()));
            }
        }
    }

    public void updateSelectedQcApproach(BaseQcApproachBean approachBean) {
        this.selectedQcApproach.postValue(approachBean);
        //1表示全检
        if (approachBean.getApproachId().equals("1")) {
            isCheckAllProducts.postValue(true);
            BaseProductBatchInfo value = selectedProductBatch.getValue();
            //强行选择最大抽检数量
            if (value != null) {
                sampleSize.postValue(String.valueOf(value.getProducts().get(0).getQuantity()));
            }
        } else {
            isCheckAllProducts.postValue(false);
        }
    }


    public void updateQcCheckers(ArrayList<StaffBean> staffs) {
        qcCheckers.postValue(staffs);
    }

    public void updateQcResult(int result) {
        reportResult.postValue(result);
    }

    public void updateIsVerify(boolean verify) {
        isNeedVerify.postValue(verify);
    }

    public void updateVerifiers(ArrayList<StaffBean> staffs) {
        verifiers.postValue(staffs);
    }


    public Disposable getProductListByProjectId() {
        int id = selectedProject.getValue().getId();
        return repo.getProductBatchListByProjectId(id, new BaseNetCallback<List<BaseProductBatchInfo>>() {
            @Override
            protected void handleResult(List<BaseProductBatchInfo> result) {
                productBatchList.postValue(result);
            }
        });
    }

    public void submitReport(SubmitCallback callback) {
        SubmitQcReportBody body = new SubmitQcReportBody();
        BaseProjectBean projectBean = selectedProject.getValue();
        if (projectBean == null) {
            showToast("项目不能为空.");
            return;
        } else {
            body.setProjectId(projectBean.getId());
        }
        BaseProductBatchInfo batchInfo = selectedProductBatch.getValue();
        if (batchInfo == null) {
            showToast("请选择出厂编号");
            return;
        } else {
            body.setSubCodeId(batchInfo.getSubmitId());
        }
        if (isNeedVerify.getValue()) {
            body.setApproveFlag(1);
        } else {
            body.setApproveFlag(2);
        }
        if (isNeedVerify.getValue()) {
            ArrayList<StaffBean> approvers = verifiers.getValue();
            List<Integer> list = new ArrayList<>();
            if (approvers.isEmpty()) {
                showToast("审批人不能为空。");
                return;
            }
            for (StaffBean bean : approvers) {
                list.add(bean.getId().intValue());
            }
            body.setApprovers(list);
        }

        ArrayList<StaffBean> qcCheckers = this.qcCheckers.getValue();
        if (qcCheckers.isEmpty()) {
            showToast("质检人员不能为空。");
            return;
        } else {
            List<Integer> list = new ArrayList<>();
            for (StaffBean checker : qcCheckers) {
                list.add(checker.getId().intValue());
            }
            body.setQualityBy(list.get(0));
        }

        body.setProductType(2);

        if (judgeConditions.isEmpty()) {
            showToast("请选择判定条件。");
            return;
        } else {
            body.setQualityCondition(judgeConditions);
        }

        String sampleSize = this.sampleSize.getValue();
        if (TextUtils.isEmpty(sampleSize) || "0".equals(sampleSize)) {
            showToast("请输入质检数量");
            return;
        } else {
            body.setQualityNum(Integer.valueOf(sampleSize));
        }

        String comment = comments.getValue();
        if (TextUtils.isEmpty(comment)) {
            showToast("请输入原因描述");
            return;
        } else {
            body.setQualityRemark(comment);
        }

        Integer reportResultValue = reportResult.getValue();
        if (reportResultValue == null) {
            showToast("请选择报告结果");
            return;
        } else {
            body.setQualityResult(reportResultValue);
        }

        BaseQcApproachBean approachBean = selectedQcApproach.getValue();
        if (approachBean == null) {
            showToast("请选择质检方式");
            return;
        } else {
            String approachId = approachBean.getApproachId();
            try {
                int type = Integer.valueOf(approachId);
                body.setQualityType(type);
            } catch (NumberFormatException e) {
                showToast("服务器返回的质检方式的参数中的key不是一个数字，请联系开发人员。");
                return;
            }
        }
        Disposable disposable = repo.submitQcReport(body, new BaseNetCallback<Integer>() {
            @Override
            protected void handleResult(Integer result) {
                if (result > 0) {
                    showToast("提交成功。");
                    resetAllViews();
                    callback.onSubmit(true);
                } else {
                    showToast("提交失败。");
                    callback.onSubmit(false);
                }
            }
        });
        addSubscription(disposable);
    }

    public interface SubmitCallback {
        void onSubmit(boolean success);
    }

    private void resetAllViews() {
        qcCheckers.postValue(new ArrayList<>());
        verifiers.postValue(new ArrayList<>());
        projectList.postValue(new ArrayList<>());
        qcApproachList.postValue(new ArrayList<>());
        productBatchList.postValue(new ArrayList<>());
        sampleSize.postValue("0");
        reportResult.postValue(null);
        comments.postValue("");
        isNeedVerify.postValue(false);
        selectedProductBatch.postValue(null);
        selectedProject.postValue(null);
        selectedQcApproach.postValue(null);
        isCheckAllProducts.postValue(false);
        shouldShowToastNoData.set(false);
    }
}
