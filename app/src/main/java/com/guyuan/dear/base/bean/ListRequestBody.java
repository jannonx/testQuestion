package com.guyuan.dear.base.bean;

import com.sun.jna.platform.win32.Winspool;

/**
 * @description: 列表请求配置参数
 * @author: tl
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class ListRequestBody {
    private FiltersBean filters;
    private int pageNum;
    private int pageSize;

    public FiltersBean getFilters() {
        return filters;
    }

    public void setFilters(FiltersBean filters) {
        this.filters = filters;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static class FiltersBean {
        /**
         * type : 1
         */

        private String type;
        private String cusName;
        private int orderType;
        private String orderCode;
        private int id;
        private String startTime;
        private String endTime;
        private int securityId;
        private int securityType;
        private int status;
        private String queryParams;
        private String name;
        private String mouthDate;
        private int returnType;
        private int productType;
        private int listType;
        private int transportStatus;  //10:运输中，20：已送达
        private String projectName;  //
        private String productName;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getTransportStatus() {
            return transportStatus;
        }

        public void setTransportStatus(int transportStatus) {
            this.transportStatus = transportStatus;
        }

        public int getListType() {
            return listType;
        }

        public void setListType(int listType) {
            this.listType = listType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getCusName() {
            return cusName;
        }

        public void setCusName(String cusName) {
            this.cusName = cusName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSecurityId() {
            return securityId;
        }

        public void setSecurityId(int securityId) {
            this.securityId = securityId;
        }

        public int getSecurityType() {
            return securityType;
        }

        public void setSecurityType(int securityType) {
            this.securityType = securityType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getQueryParams() {
            return queryParams;
        }

        public void setQueryParams(String queryParams) {
            this.queryParams = queryParams;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMouthDate() {
            return mouthDate;
        }

        public void setMouthDate(String mouthDate) {
            this.mouthDate = mouthDate;
        }

        public int getReturnType() {
            return returnType;
        }

        public void setReturnType(int returnType) {
            this.returnType = returnType;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }
    }
}
