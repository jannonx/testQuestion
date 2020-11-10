package com.guyuan.dear.focus.produce.bean;

import java.util.List;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/9 20:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProduceStateBean {


    /**
     * id : 51
     * arType : 10501
     * businessId : 1
     * status : 1
     * createBy : 1755577
     * createName : 李家辉
     * createDept : 研发部,研发一部
     * createTime : 2020-11-09 19:42:51
     * imgUrl : https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160388473927849876.png
     * remark :
     * texamineFlows : [{"id":251,"mainId":51,"sort":1,"approveBy":1755577,"status":1,"approvalTime":"2020-11-09 19:42:54","remarks":"","approveName":"李家辉","departmentName":"研发部,研发一部","imgUrl":"https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/160388473927849876.png"}]
     */

    /**
     * 主键
     */
    private long id;
    /**
     * 主表id
     */
    private long mainId;
    /**
     * 序号（第几步）默认1
     */
    private int sort;
    /**
     * 审批类型
     */
    private int arType;
    /**
     * 审批人
     */
    private long approveBy;
    /**
     * 审批时间
     */
    private String approvalTime;
    /**
     * 审批人名称
     */
    private String approveName;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 业务id
     */
    private long businessId;
    /**
     * 状态 0.审批中 1.已同意 2.已拒绝
     */
    private int status;
    /**
     * 申请人
     */
    private long createBy;
    /**
     * 申请时间
     */
    private String createName;
    /**
     * 申请人部门
     */
    private String createDept;
    /**
     * 审批时间
     */
    private String createTime;
    /**
     * 申请人头像
     */
    private String imgUrl;
    /**
     * 批注信息
     */
    private String remark;
    /**
     * 跟随状态
     */
    private List<ProduceStateBean> texamineFlows;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMainId() {
        return mainId;
    }

    public void setMainId(long mainId) {
        this.mainId = mainId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getArType() {
        return arType;
    }

    public void setArType(int arType) {
        this.arType = arType;
    }

    public long getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(long approveBy) {
        this.approveBy = approveBy;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ProduceStateBean> getTexamineFlows() {
        return texamineFlows;
    }

    public void setTexamineFlows(List<ProduceStateBean> texamineFlows) {
        this.texamineFlows = texamineFlows;
    }
}
