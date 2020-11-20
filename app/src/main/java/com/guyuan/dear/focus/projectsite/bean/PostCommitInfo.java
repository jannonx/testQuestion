package com.guyuan.dear.focus.projectsite.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 我的关注-工程现场-现场勘察单-app提交操作
 * @author: 许建宁
 * @since: 2020/11/19 14:15
 * @company: 固远（深圳）信息技术有限公司
 */
public class PostCommitInfo implements Serializable {

    /**
     * 问题描述
     */
    private String auditExplain;

    /**
     * 主表ID
     */
    private long id;

    /**
     * 是否满足条件、是否安全(1:是，2:否)
     */
    private int satisfyFlag;

    /**
     * 附件
     */
    private List<String> imgUrl;

    /**
     * 评审事项明细集合
     */
    private List<PsAuditItemDetailParamsListBean> psAuditItemDetailParamsList;

    public String getAuditExplain() {
        return auditExplain;
    }

    public void setAuditExplain(String auditExplain) {
        this.auditExplain = auditExplain;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSatisfyFlag() {
        return satisfyFlag;
    }

    public void setSatisfyFlag(int satisfyFlag) {
        this.satisfyFlag = satisfyFlag;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<PsAuditItemDetailParamsListBean> getPsAuditItemDetailParamsList() {
        return psAuditItemDetailParamsList;
    }

    public void setPsAuditItemDetailParamsList(List<PsAuditItemDetailParamsListBean> psAuditItemDetailParamsList) {
        this.psAuditItemDetailParamsList = psAuditItemDetailParamsList;
    }

    public static class PsAuditItemDetailParamsListBean {


        /**
         * 事项细则ID主键
         */
        private long id;

        /**
         * 事项细则等级(如有需要可以填写)
         */
        private int level;

        /**
         * 事项细则名称
         */
        private String name;

        /**
         * 单个事项细则提交结果
         */
        private int singleItemResult;

        /**
         * 事项细则排序
         */
        private int sort;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSingleItemResult() {
            return singleItemResult;
        }

        public void setSingleItemResult(int singleItemResult) {
            this.singleItemResult = singleItemResult;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
