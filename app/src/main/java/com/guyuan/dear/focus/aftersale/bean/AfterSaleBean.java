package com.guyuan.dear.focus.aftersale.bean;

import com.guyuan.dear.R;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 许建宁
 * @description :
 * @since: 2020/11/19 14:01
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AfterSaleBean implements Serializable {

    /**
     * 编号
     */
    private long id;
    /**
     * 关联项目的Id
     */
    private String projectId;
    /**
     * 关联项目的名称
     */
    private String projectName;
    /**
     * 项目编号
     * 编号
     */
    private String projectCode;
    /**
     * 客户名称
     */
    private String consumerName;
    /**
     * 施工地址
     */
    private String constructionLocaltion;
    /**
     * 验收状态 0.待验收 1.验收合格 2.验收不合格 3.验收中
     */
    private int status;
    /**
     * 审核状态 0.待审核 2.审核通过
     */
    private int checkStatus;
    /**
     * 客户反馈备注
     */
    private String remark;
    /**
     * 验收图片
     */
    private String img;
    /**
     * 标题
     */
    private String title;

    /**
     * 排查人员id
     */
    private long checkMan;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 所属阶段
     */
    private SaleSectionType sectionType;
    /**
     * 排查状态
     */
    private SaleCheckType checkType;
    /**
     * 验收状态
     */
    private SaleAcceptedType acceptedType;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 审核人员id
     */
    private long examineMan;
    /**
     * 审核人员名字
     */
    private String examineManName;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 删除标记
     */
    private int delFlag;

    /**
     * 子问题列表
     */
    private List<AfterSaleQuestionBean> saleIssueSubList;


    public SaleSectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SaleSectionType sectionType) {
        this.sectionType = sectionType;
    }

    public SaleCheckType getCheckType() {
        return SaleCheckType.toType(status);
    }

    public void setCheckType(SaleCheckType checkType) {
        this.checkType = checkType;
    }

    public SaleAcceptedType getAcceptedType() {
        return SaleAcceptedType.toType(status);
    }

    public void setAcceptedType(SaleAcceptedType acceptedType) {
        this.acceptedType = acceptedType;
    }

    /**
     * 状态文本颜色
     *
     * @return
     */
    public int getStatusTextColor() {
        if (getSectionType() == null) {
            return R.color.color_orange_FF6010;
        }
        switch (getSectionType()) {
            case TYPE_SECTION_CHECK:
                return getCheckType().getTextColor();
            case TYPE_SECTION_ACCEPT:
                return getAcceptedType().getTextColor();
            default:
                return R.color.transparent;
        }
    }

    /**
     * 状态文本背景颜色
     *
     * @return
     */
    public int getStatusTextBg() {
        if (getSectionType() == null) {
            return R.drawable.bg_orange_ffece3_corner_2;
        }
        switch (getSectionType()) {
            case TYPE_SECTION_CHECK:
                return getCheckType().getTextBgColor();
            case TYPE_SECTION_ACCEPT:
                return getAcceptedType().getTextBgColor();
            default:
                return R.drawable.bg_blue_0aad33_corner_4;
        }
    }

    /**
     * 状态文本文字
     *
     * @return
     */
    public String getStatusText() {
        if (getSectionType() == null) {
            return "未设置类型";
        }
        switch (getSectionType()) {
            case TYPE_SECTION_CHECK:
                return getCheckType().getDes();
            case TYPE_SECTION_ACCEPT:
                return getAcceptedType().getDes();

        }
        return "";
    }

    public String getExamineManName() {
        return examineManName;
    }

    public void setExamineManName(String examineManName) {
        this.examineManName = examineManName;
    }

    public List<AfterSaleQuestionBean> getSaleIssueSubList() {
        return saleIssueSubList;
    }

    public void setSaleIssueSubList(List<AfterSaleQuestionBean> saleIssueSubList) {
        this.saleIssueSubList = saleIssueSubList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConstructionLocaltion() {
        return constructionLocaltion;
    }

    public void setConstructionLocaltion(String constructionLocaltion) {
        this.constructionLocaltion = constructionLocaltion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCheckMan() {
        return checkMan;
    }

    public void setCheckMan(long checkMan) {
        this.checkMan = checkMan;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public long getExamineMan() {
        return examineMan;
    }

    public void setExamineMan(long examineMan) {
        this.examineMan = examineMan;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}