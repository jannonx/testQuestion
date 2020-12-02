package com.guyuan.dear.focus.projectsite.bean;

import android.text.TextUtils;

import com.guyuan.dear.R;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 我的关注--现场勘查--数据载体
 * @author: Jannonx
 * @since: 2020/11/19 11:01
 * @company: 固远（深圳）信息技术有限公司
 */

public class SiteExploreBean implements Serializable {

    /**
     * 主键ID
     */
    private long id;
    /**
     * 是否满足条件
     */
    private int satisfyFlag;

    private FunctionModuleType moduleType;
    /**
     * 类型
     */
    private int auditFormType;
    private ProjectReportType projectReportType;
    /**
     * 客户名称
     */
    private String cusName;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 现场勘查/安全排查:
     * 状态（10:待操作(待勘察等...)、20:执行中（勘查中...）、30:完成（勘查完成...）、40:其他）
     * -------------------
     */
    private int status;
    /**
     * 评审时间
     */
    private String createTime;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目编号
     */
    private String projectNum;
    /**
     * 执行人名称
     */
    private String name;
    /**
     * 完成时间
     */
    private String finishTime;
    /**
     * 最新回复意见
     */
    private String idea;
    //================清点货物
    /**
     * 收货地址
     */
    private String acceptAddress;
    /**
     * 清点人员名称
     */
    private String checkName;
    /**
     * 清点货物：
     * 清点状态(10：待清点，20：清点中，30：完成清点)
     * 客户验收：
     * 验收状态，0:待验收，10合格，20不合格
     */
    private int checkStatus;
    /**
     * 是否异常，0正常，1异常
     */
    private int isException;
    /**
     * 清点时间
     */
    private String checkTime;
    /**
     * 最新回复意见
     */
    private String sendGoodsNumber;
    /**
     * 运输状态(10:运输中，20：已送达)
     */
    private int transportStatus;
    /**
     * 清点备注
     */
    private String checkRemark;
    //================安装调试

    /**
     * 施工地址
     */
    private String address;
    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 项目名称
     */
    private String projectNumber;

    /**
     * 计划施工调试截止时间
     */
    private String debugEndTime;
    /**
     * 计划施工调试开始时间
     */
    private String debugStartTime;


    /**
     * 现场监工人员
     */
    private String personLiableName;

    //================客户验收

    /**
     * 合同编号
     */
    private String contractNum;

    //==============详情

    /**
     * 附件
     */
    private String imgUrl;
    /**
     * 附件
     */
    private String checkUrl;
    /**
     * 问题描述
     */
    private String auditItemExplain;

    /**
     * 内容描述集
     */
    private List<ProjectSiteStatusBean> answerVOList;
    /**
     * 回复意见集
     */
    private List<ProjectSiteOpinionBean> psAuditItemVOList;

    /**
     * 货物清单
     */
    private List<CheckGoodsBean> appCheckTransportProjectListVOList;
    /**
     * 安装调试/客户验收
     */
    private List<InstallDebugBean> appInstallDebugItemVOList;


    public String getDebugEndTime() {
        return debugEndTime;
    }

    public void setDebugEndTime(String debugEndTime) {
        this.debugEndTime = debugEndTime;
    }

    public String getDebugStartTime() {
        return debugStartTime;
    }

    public void setDebugStartTime(String debugStartTime) {
        this.debugStartTime = debugStartTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public int getSatisfyFlag() {
        return satisfyFlag;
    }

    public FunctionModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(FunctionModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public SiteProjectSatisfyType getSiteProjectSatisfyType() {
        return SiteProjectSatisfyType.toType(this);
    }

    public CheckSafeSatisfyType getCheckSafeSatisfyType() {
        return CheckSafeSatisfyType.toType(this);
    }

    public InstallDebugSatisfyType getInstallDebugSatisfyType() {
        LogUtils.showLog("status=" + status);
        return InstallDebugSatisfyType.toType(status);
    }

    public CheckGoodsSatisfyType getCheckGoodsSatisfyType() {
        return CheckGoodsSatisfyType.toType(this);
    }

    public CustomerAcceptanceSatisfyType getCustomerAcceptanceSatisfyType() {
        return CustomerAcceptanceSatisfyType.toType(this);
    }

    public void setSatisfyFlag(int satisfyFlag) {
        this.satisfyFlag = satisfyFlag;
    }

    public boolean getStatusTextVisible() {
        switch (getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                return getSiteProjectSatisfyType().isJudgingCondition();
            case TYPE_CHECK_SAFE:
                return getCheckSafeSatisfyType().isJudgingCondition();
            case TYPE_INSTALLATION_DEBUG:
                return getInstallDebugSatisfyType().isJudgingCondition();
            case TYPE_CUSTOMER_ACCEPTANCE:
                return getCustomerAcceptanceSatisfyType().isJudgingCondition();
            case TYPE_CHECK_GOODS:
                return getCheckGoodsSatisfyType().isJudgingCondition();
            default:
                return true;
        }
    }

    public int getStatusTextColor() {
        switch (getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                return getSiteProjectSatisfyType().getTextColor();
            case TYPE_CHECK_GOODS:
                return getCheckGoodsSatisfyType().getTextColor();
            case TYPE_CHECK_SAFE:
                return getCheckSafeSatisfyType().getTextColor();

            case TYPE_INSTALLATION_DEBUG:
                return getInstallDebugSatisfyType().getTextColor();
            case TYPE_CUSTOMER_ACCEPTANCE:
                return getCustomerAcceptanceSatisfyType().getTextColor();
            default:
                return R.color.transparent;
        }
    }

    public int getStatusTextBg() {
        switch (getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                return getSiteProjectSatisfyType().getTextBgColor();
            case TYPE_CHECK_SAFE:
                return getCheckSafeSatisfyType().getTextBgColor();
            case TYPE_CHECK_GOODS:
                return getCheckGoodsSatisfyType().getTextBgColor();
            case TYPE_CUSTOMER_ACCEPTANCE:
                return getCustomerAcceptanceSatisfyType().getTextBgColor();
            case TYPE_INSTALLATION_DEBUG:
                return getInstallDebugSatisfyType().getTextBgColor();
            default:
                return R.drawable.bg_blue_0aad33_corner_4;
        }
    }

    public String getStatusText() {
        switch (getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                return getSiteProjectSatisfyType().getDes();
            case TYPE_CHECK_SAFE:
                return getCheckSafeSatisfyType().getDes();
            case TYPE_CHECK_GOODS:
                return getCheckGoodsSatisfyType().getDes();
            case TYPE_INSTALLATION_DEBUG:
                return getInstallDebugSatisfyType().getDes();
            case TYPE_CUSTOMER_ACCEPTANCE:
                return getCustomerAcceptanceSatisfyType().getDes();
        }

        return "";
    }


    public int getProjectReportTabArray() {
        switch (getProjectReportType()) {
            case TYPE_SITE_EXPLORATION:
                return R.array.focus_site_explore;
            case TYPE_CHECK_SAFE:
                return R.array.focus_check_safe;
            case TYPE_INSTALLATION_DEBUG:
                return R.array.focus_install_debug;
            case TYPE_CUSTOMER_ACCEPTANCE:
                return R.array.focus_customer_acceptance;
            case TYPE_CHECK_GOODS:
                return R.array.focus_check_good;
            default:
                return 0;
        }
    }

    public int getAuditFormType() {
        return auditFormType;
    }

    public void setAuditFormType(int auditFormType) {
        this.auditFormType = auditFormType;
    }

    //    public ProjectReportType getProjectReportType() {
//        return ProjectReportType.toType(auditFormType);
//    }
    public ProjectReportType getProjectReportType() {
        return projectReportType;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public void setProjectReportType(ProjectReportType projectReportType) {
        this.projectReportType = projectReportType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getSendGoodsNumber() {
        return sendGoodsNumber;
    }

    public void setSendGoodsNumber(String sendGoodsNumber) {
        this.sendGoodsNumber = sendGoodsNumber;
    }

    public int getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(int transportStatus) {
        this.transportStatus = transportStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<String> getImgUrlList() {
        //s-imgUrl、g-checkUrl、c-imgUrl、cu-checkUrl
        return StringUtils.splitPhotoUrl(TextUtils.isEmpty(imgUrl) ? checkUrl : imgUrl);
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuditItemExplain() {
        return auditItemExplain;
    }

    public void setAuditItemExplain(String auditItemExplain) {
        this.auditItemExplain = auditItemExplain;
    }

    public List<ProjectSiteStatusBean> getAnswerVOList() {
        return answerVOList;
    }

    public void setAnswerVOList(List<ProjectSiteStatusBean> answerVOList) {
        this.answerVOList = answerVOList;
    }

    public List<ProjectSiteOpinionBean> getPsAuditItemVOList() {
        return psAuditItemVOList;
    }

    public void setPsAuditItemVOList(List<ProjectSiteOpinionBean> psAuditItemVOList) {
        this.psAuditItemVOList = psAuditItemVOList;
    }

    public List<CheckGoodsBean> getCheckTransportProjectListVO() {
        return appCheckTransportProjectListVOList;
    }

    public void setCheckTransportProjectListVO(List<CheckGoodsBean> checkTransportProjectListVO) {
        this.appCheckTransportProjectListVOList = checkTransportProjectListVO;
    }

    public List<InstallDebugBean> getAppInstallDebugItemVOList() {
        return appInstallDebugItemVOList;
    }

    public void setAppInstallDebugItemVOList(List<InstallDebugBean> appInstallDebugItemVOList) {
        this.appInstallDebugItemVOList = appInstallDebugItemVOList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPersonLiableName() {
        return personLiableName;
    }

    public void setPersonLiableName(String personLiableName) {
        this.personLiableName = personLiableName;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }
}