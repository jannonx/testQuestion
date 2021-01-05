package com.guyuan.dear.focus.contract.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/13 17:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgKnot {
    private String name;
    private long knotId;
    private int status;
    private String warning;
    public static final int KNOT_STATUS_DEFAULT=0;
    public static final int KNOT_STATUS_PROCESSING=1;
    public static final int KNOT_STATUS_FINISHED=2;

    public ContractPrgKnot() {
    }

    public ContractPrgKnot(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKnotId() {
        return knotId;
    }

    public void setKnotId(long knotId) {
        this.knotId = knotId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc(){
        String desc="";
        switch (status){
            case KNOT_STATUS_DEFAULT:
                desc="未开始";
                break;
            case KNOT_STATUS_PROCESSING:
                desc="进行中";
                break;
            case KNOT_STATUS_FINISHED:
                desc="已完成";
                break;
            default:
                break;
        }
        return desc;
    }
}
