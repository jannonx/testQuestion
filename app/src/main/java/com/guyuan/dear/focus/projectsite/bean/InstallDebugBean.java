package com.guyuan.dear.focus.projectsite.bean;

import android.text.TextUtils;

import com.guyuan.dear.focus.projectsite.type.InstallDebugSatisfyType;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/20 17:33
 * @company: 固远（深圳）信息技术有限公司
 */
public class InstallDebugBean implements Serializable {
    /**
     * 主键ID
     */
    private long id;
    /**
     * 摄像头IP
     */
    private String cameraNumber;
    /**
     * 施工作业单位名称
     */
    private String customerName;
    /**
     * 计划施工调试截止时间
     */
    private String debugEndTime;
    /**
     * 计划施工调试开始时间
     */
    private String debugStartTime;

    /**
     * 最新意见
     */
    private String idea;
    /**
     * 安装调试主表ID
     */
    private long installDebugId;
    /**
     * 责任人姓名(冗余字段)
     */
    private String personLiableName;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 实际施工调试截止时间(施工周期-截止)
     */
    private String realityEndTime;
    /**
     * 实际施工调试开始时间(施工周期-开始)
     */
    private String realityStartTime;
    /**
     * 序号
     */
    private int sort;
    /**
     * 状态（10待开始，20安装中，30完成, 40暂停）
     */
    private int status;

    public InstallDebugSatisfyType getInstallDebugSatisfyType() {
        LogUtils.showLog("status=" + status);
        return InstallDebugSatisfyType.toType(status);
    }

    public boolean getStatusTextVisible() {
        return getInstallDebugSatisfyType().isJudgingCondition();

    }

    public int getStatusTextColor() {

        return getInstallDebugSatisfyType().getTextColor();
    }

    public int getStatusTextBg() {
        return getInstallDebugSatisfyType().getTextBgColor();

    }

    public String getStatusText() {
        return getInstallDebugSatisfyType().getDes();

    }

    /**
     * 没有实际开始，显示预计开始和预计完工
     */
    public String getShowTime() {
        CalenderUtils calenderUtils = CalenderUtils.getInstance();
        String debugStartTime = calenderUtils.toSmartFactoryDateFormatByFull(getDebugStartTime());
        String debugEndTime = calenderUtils.toSmartFactoryDateFormatByFull(getDebugEndTime());
        String realStartTime = calenderUtils.toSmartFactoryDateFormatByFull(getRealityStartTime());
        String realEndTime = calenderUtils.toSmartFactoryDateFormatByFull(getRealityEndTime());
        return TextUtils.isEmpty(realStartTime) ? (debugStartTime + "~" + debugEndTime) : (realStartTime + "~" + realEndTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCameraNumber() {
        return cameraNumber;
    }

    public void setCameraNumber(String cameraNumber) {
        this.cameraNumber = cameraNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public long getInstallDebugId() {
        return installDebugId;
    }

    public void setInstallDebugId(long installDebugId) {
        this.installDebugId = installDebugId;
    }

    public String getPersonLiableName() {
        return personLiableName;
    }

    public void setPersonLiableName(String personLiableName) {
        this.personLiableName = personLiableName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRealityEndTime() {
        return realityEndTime;
    }

    public void setRealityEndTime(String realityEndTime) {
        this.realityEndTime = realityEndTime;
    }

    public String getRealityStartTime() {
        return realityStartTime;
    }

    public void setRealityStartTime(String realityStartTime) {
        this.realityStartTime = realityStartTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
