package com.guyuan.dear.approve.bean;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @description: 掌上办公--申请POST-BODY
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApplyBean implements Serializable {

    private int id;
    /**
     * 审批类型:
     * 1.请假2.出差3.外出4.加班5.招聘6.离职
     * 7.报销8.付款9.备用金10.用印11.通用审批12.采购
     */
    private int arType;
    private int status;
    private String copys;
    private String createBy;
    private String createTime;
    private String description;
    private String delFlag;
    private String setOut;
    private String objective;
    private String colleague;
    private int time;
    private String demandPost;
    private int demandNum;
    private int existingNum;
    private double money;
    private String enclosure;
    private int rbType;
    private String payObj;
    private String opening;
    private String account;
    private String cashier;
    private String applyId;
    private String charge;
    private String file;
    private String details;
    private String department;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private ApproveUserBean user;
    private String sleaveTime;
    private String eleaveTime;
    /**
     * 审批人
     */
    private ArrayList<Long> users;
    /**
     * 抄送人
     */
    private ArrayList<Long> copy;
    /**
     * 流程ID，服务器不会返回，需要本地设置
     */
    private long sessionId;
    private String content;
    private String remarks;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArType() {
        return arType;
    }

    public ApplyType getApplyType() {
        return ApplyType.toType(arType);
    }

    public void setArType(int arType) {
        this.arType = arType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCopys() {
        return copys;
    }

    public void setCopys(String copys) {
        this.copys = copys;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSetOut() {
        return setOut;
    }

    public void setSetOut(String setOut) {
        this.setOut = setOut;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getColleague() {
        return colleague;
    }

    public void setColleague(String colleague) {
        this.colleague = colleague;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDemandPost() {
        return demandPost;
    }

    public void setDemandPost(String demandPost) {
        this.demandPost = demandPost;
    }

    public int getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(int demandNum) {
        this.demandNum = demandNum;
    }

    public int getExistingNum() {
        return existingNum;
    }

    public void setExistingNum(int existingNum) {
        this.existingNum = existingNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public int getRbType() {
        return rbType;
    }

    public void setRbType(int rbType) {
        this.rbType = rbType;
    }

    public String getPayObj() {
        return payObj;
    }

    public void setPayObj(String payObj) {
        this.payObj = payObj;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public ApproveUserBean getUser() {
        return user;
    }

    public void setUser(ApproveUserBean user) {
        this.user = user;
    }

    public String getSleaveTime() {
        return sleaveTime;
    }

    public void setSleaveTime(String sLeaveTime) {
        this.sleaveTime = sLeaveTime;
    }

    public String getEleaveTime() {
        return eleaveTime;
    }

    public void setEleaveTime(String eLeaveTime) {
        this.eleaveTime = eLeaveTime;
    }

    public ArrayList<Long> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Long> users) {
        this.users = users;
    }

    public ArrayList<Long> getCopy() {
        return copy;
    }

    public void setCopy(ArrayList<Long> copy) {
        this.copy = copy;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getStatusDescription() {
        return ApplyStatusType.toText(status);
    }

    public String getVacationType() {
        return LeaveType.toText(rbType);
    }


    public String getApplyTypeStr() {
        return ApplyType.toText(arType);
    }


    public String getPayType() {
        return PayType.toText(rbType);

    }

}

