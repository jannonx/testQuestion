package com.guyuan.dear.focus.purchase.data.bean;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/13 16:34
 * @company : 固远（深圳）信息技术有限公司
 **/

public class PurchaseRecordBean {

    /**
     * createName :
     * createTime :
     * deptName :
     * operateNum : 0
     * reason :
     * type : 0
     */

    private String createName;      //创建人名称
    private String createTime;      //创建时间
    private String deptName;        //创建人部门名称
    private int operateNum;         //操作数量
    private String reason;          //退货原因
    private int type;               //退货类型:1.退货，2.换货

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getOperateNum() {
        return operateNum;
    }

    public void setOperateNum(int operateNum) {
        this.operateNum = operateNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}