package com.guyuan.dear.work.assess.data.bean;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/6 12:06
 * @company : 固远（深圳）信息技术有限公司
 **/

public class CustomerBean {


    /**
     * allCustomerVOS : [{"contractNum":"","id":0}]
     * cusName :
     * id : 0
     */

    private String cusName;       //客户名称
    private int id;               //id
    /**
     * contractNum :
     * id : 0
     */

    private List<AllCustomerVOSBean> allCustomerVOS;

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AllCustomerVOSBean> getAllCustomerVOS() {
        return allCustomerVOS;
    }

    public void setAllCustomerVOS(List<AllCustomerVOSBean> allCustomerVOS) {
        this.allCustomerVOS = allCustomerVOS;
    }

    public static class AllCustomerVOSBean {
        private String contractNum;       //客户合同编号
        private int id;                   //id

        public String getContractNum() {
            return contractNum;
        }

        public void setContractNum(String contractNum) {
            this.contractNum = contractNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}