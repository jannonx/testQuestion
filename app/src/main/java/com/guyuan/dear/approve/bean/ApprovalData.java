package com.guyuan.dear.approve.bean;


/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApprovalData {
    /**
     * id : 195
     * allId : 86
     * sort : 1
     * description : null
     * approveBy : 44
     * status : 1
     * approvalTime : null
     * remarks : 可以的
     * delFlag : null
     * tApproveAll : {"id":86,"arType":1,"status":1,"copys":null,"createBy":47,"createTime":1577151113000,"description":"测试","delFlag":0,"sLeaveTime":1577232000000,"eLeaveTime":1577404799000,"setOut":null,"objective":null,"colleague":null,"time":0,"demandPost":null,"demandNum":0,"existingNum":0,"money":null,"enclosure":null,"rbType":"2","payObj":null,"opening":null,"account":null,"cashier":null,"applyId":null,"charge":null,"file":null,"details":null,"department":null,"attribute2":null,"attribute3":null,"attribute4":null,"attribute5":null,"user":{"id":47,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"name":"杨奕雄","password":"5a59c50ac679013c8f6e970097e465f26233d34554d947e0e30839d91be00897","salt":"4d05b9b6d30f47aeb901","email":"system@qq.com","mobile":"13889700023","status":1,"deptId":null,"deptName":null,"delFlag":null,"roleNames":null,"staffId":null,"code":null,"realName":null,"identity":null,"factoryId":null,"workshopId":null,"lineId":null,"pointId":null,"attachmentId":null,"userRoles":[]},"sleaveTime":1577232000000,"eleaveTime":1577404799000}
     * user : {"id":44,"createBy":null,"createTime":null,"lastUpdateBy":null,"lastUpdateTime":null,"name":"hk","password":"9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d","salt":"YzcmCZNvbXocrsz9dm8e","email":null,"mobile":null,"status":1,"deptId":null,"deptName":null,"delFlag":null,"roleNames":null,"staffId":null,"code":null,"realName":null,"identity":null,"factoryId":null,"workshopId":null,"lineId":null,"pointId":null,"attachmentId":null,"userRoles":[]}
     */

    private long id;
    private long allId;
    private int sort;
    private String description;
    private long approveBy;
    private int status;
    private String approvalTime;
    private String remarks;
    private int delFlag;
    private ApplyBean tapproveAll;
    private ApproveUserBean user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAllId() {
        return allId;
    }

    public void setAllId(long allId) {
        this.allId = allId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(long approveBy) {
        this.approveBy = approveBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public ApplyBean gettApproveAll() {
        return tapproveAll;
    }

    public void settApproveAll(ApplyBean tApproveAll) {
        this.tapproveAll = tApproveAll;
    }

    public ApproveUserBean getUser() {
        return user;
    }

    public void setUser(ApproveUserBean user) {
        this.user = user;
    }
}
