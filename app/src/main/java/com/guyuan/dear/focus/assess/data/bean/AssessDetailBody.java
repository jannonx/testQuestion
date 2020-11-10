package com.guyuan.dear.focus.assess.data.bean;

/**
 * @author : tl
 * @description :我的关注-评审详情body
 * @since: 2020/11/2 16:17
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessDetailBody {
    private String contractNumber;
    private int id;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}