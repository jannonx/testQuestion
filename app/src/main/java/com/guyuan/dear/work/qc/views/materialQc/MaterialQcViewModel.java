package com.guyuan.dear.work.qc.views.materialQc;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.beans.MaterialInfo;
import com.guyuan.dear.work.qc.repo.MaterialQcRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 11:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcViewModel extends BaseDearViewModel {
    /**
     * 数据源
     */
    private MutableLiveData<ArrayList<StaffBean>> qcCheckers = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<ArrayList<StaffBean>> verifiers = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<BaseProjectBean>> projectList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<BaseQcApproachBean>> qcApproachList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<MaterialInfo>> materialList = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<String> sampleSize = new MutableLiveData<>("0");
    private List<Integer> judgeConditions = new ArrayList<>();
    /**
     * 1 通过 2 不通过
     */
    private MutableLiveData<Integer> reportResult = new MutableLiveData<>(2);
    private MutableLiveData<String> comments = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNeedVerify = new MutableLiveData<>(false);
    private MutableLiveData<MaterialInfo> selectedMaterial = new MutableLiveData<>();
    private MutableLiveData<BaseProjectBean> selectedProject = new MutableLiveData<>();
    private MutableLiveData<BaseQcApproachBean> selectedQcApproach = new MutableLiveData<>();


    /**
     * 点击事件：选择项目
     */
    public MutableLiveData<View.OnClickListener> onClickSelectProject = new MutableLiveData<>();
    /**
     * 点击事件：选择材料名字
     */
    public MutableLiveData<View.OnClickListener> onClickSelectMaterial = new MutableLiveData<>();

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

    private MaterialQcRepo repo = new MaterialQcRepo();

    public MutableLiveData<ArrayList<StaffBean>> getQcCheckers() {
        return qcCheckers;
    }

    public MutableLiveData<ArrayList<StaffBean>> getVerifiers() {
        return verifiers;
    }

    public MutableLiveData<List<BaseProjectBean>> getProjectList() {
        return projectList;
    }

    public MutableLiveData<List<BaseQcApproachBean>> getQcApproachList() {
        return qcApproachList;
    }

    public MutableLiveData<List<MaterialInfo>> getMaterialList() {
        return materialList;
    }

    public MutableLiveData<String> getSampleSize() {
        return sampleSize;
    }

    public MutableLiveData<Integer> getReportResult() {
        return reportResult;
    }

    public MutableLiveData<String> getComments() {
        return comments;
    }

    public MutableLiveData<Boolean> getIsNeedVerify() {
        return isNeedVerify;
    }

    public MutableLiveData<MaterialInfo> getSelectedMaterial() {
        return selectedMaterial;
    }

    public MutableLiveData<BaseProjectBean> getSelectedProject() {
        return selectedProject;
    }

    public MutableLiveData<BaseQcApproachBean> getSelectedQcApproach() {
        return selectedQcApproach;
    }

    public Disposable loadMaterialListFromNet(int projectId) {
        return repo.loadMaterialInfoFromNet(projectId, new BaseNetCallback<List<MaterialInfo>>() {
            @Override
            protected void handleResult(List<MaterialInfo> result) {
                materialList.getValue().clear();
                materialList.getValue().addAll(result);
                materialList.postValue(materialList.getValue());
            }
        });
    }


    public Disposable getProjectListFromNet() {
        return repo.getProjectListFromNet(new BaseNetCallback<List<BaseProjectBean>>() {
            @Override
            protected void handleResult(List<BaseProjectBean> result) {
                projectList.getValue().clear();
                projectList.getValue().addAll(result);
                projectList.postValue(projectList.getValue());
            }
        });
    }


    public Disposable getQcApproachesFromNet() {
        return repo.getQcApproaches(new BaseNetCallback<List<BaseQcApproachBean>>() {
            @Override
            protected void handleResult(List<BaseQcApproachBean> result) {
                qcApproachList.getValue().clear();
                qcApproachList.getValue().addAll(result);
                qcApproachList.postValue(qcApproachList.getValue());
            }
        });
    }

    public void updateProjectInfo(BaseProjectBean projectBean) {
        this.selectedMaterial.postValue(null);
        this.selectedProject.postValue(projectBean);
    }


    public void updateQcApproach(BaseQcApproachBean approachBean) {
        this.selectedQcApproach.postValue(approachBean);
    }


    public void updateSelectedMaterial(MaterialInfo materialInfo) {
        this.selectedMaterial.postValue(materialInfo);
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

    public List<Integer> getJudgeConditions() {
        return judgeConditions;
    }

    public void submitApply() {
        SubmitQcReportBody body = new SubmitQcReportBody();
        BaseProjectBean projectBean = getSelectedProject().getValue();
        if (projectBean == null) {
            showToast("项目不能为空.");
            return;
        } else {
            body.setProjectId(projectBean.getId());
        }
        MaterialInfo material = getSelectedMaterial().getValue();
        if (material == null) {
            showToast("请选择材料");
            return;
        } else {
            body.setSubCodeId(material.getId());
        }
        if (isNeedVerify.getValue()) {
            body.setApproveFlag(1);
        } else {
            body.setApproveFlag(2);
        }
        if (isNeedVerify.getValue()) {
            ArrayList<StaffBean> approvers = getVerifiers().getValue();
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

        ArrayList<StaffBean> qcCheckers = getQcCheckers().getValue();
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

        body.setProductType(1);

        if (judgeConditions.isEmpty()) {
            showToast("请选择判定条件。");
            return;
        } else {
            body.setQualityCondition(judgeConditions);
        }

        String sampleSize = getSampleSize().getValue();
        if (TextUtils.isEmpty(sampleSize)||"0".equals(sampleSize)) {
            showToast("请输入抽检数");
            return;
        } else {
            body.setQualityNum(Integer.valueOf(sampleSize));
        }

        String comment = getComments().getValue();
        if (TextUtils.isEmpty(comment)) {
            showToast("请输入原因描述");
            return;
        } else {
            body.setQualityRemark(comment);
        }

        body.setQualityResult(getReportResult().getValue());
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
        repo.submitQcReport(body, new BaseNetCallback<Integer>() {
            @Override
            protected void handleResult(Integer result) {
                if (result > 0) {
                    showToast("提交成功。");
                    resetAllViews();
                } else {
                    showToast("提交失败。");
                }
            }
        });
    }

    private void resetAllViews() {
        qcCheckers.postValue(new ArrayList<>());
        verifiers.postValue(new ArrayList<>());
        projectList.postValue(new ArrayList<>());
        qcApproachList.postValue(new ArrayList<>());
        materialList.postValue(new ArrayList<>());
        sampleSize.postValue("0");
        judgeConditions.clear();
        reportResult.postValue(2);
        comments.postValue("");
        selectedMaterial.postValue(null);
        selectedProject.postValue(null);
        selectedQcApproach.postValue(null);
        isNeedVerify.postValue(false);
    }
}
