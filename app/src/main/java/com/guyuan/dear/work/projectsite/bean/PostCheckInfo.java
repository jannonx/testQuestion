package com.guyuan.dear.work.projectsite.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 工程现场--检查提交信息
 * @author: 许建宁
 * @since: 2020/11/21 22:03
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostCheckInfo implements Serializable {

    /**
     * checkDetailParamsList : [{"id":0,"status":0}]
     * checkRemark :
     * checkUrl : []
     * id : 0
     * isException : 0
     * sign : 0
     */

    private String checkRemark;
    private long id;
    private int isException;
    private int sign;
    private List<CheckDetailParamsListBean> checkDetailParamsList;
    private List<String> checkUrl;

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public List<CheckDetailParamsListBean> getCheckDetailParamsList() {
        return checkDetailParamsList;
    }

    public void setCheckDetailParamsList(List<CheckDetailParamsListBean> checkDetailParamsList) {
        this.checkDetailParamsList = checkDetailParamsList;
    }

    public List<String> getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(List<String> checkUrl) {
        this.checkUrl = checkUrl;
    }

    public static class CheckDetailParamsListBean {
        /**
         * id : 0
         * status : 0
         */

        private long id;
        private int status;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
