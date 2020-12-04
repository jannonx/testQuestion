package com.guyuan.dear.focus.aftersale.bean;

import java.io.Serializable;

/**
 * @description: 售后服务模块--列表请求体(参数配置)
 * @author: 许建宁
 * @since: 2020/11/25 19:05
 * @company: 固远（深圳）信息技术有限公司
 */
public class ListSaleRequestBody implements Serializable {
    /**
     * 列表过滤体
     */
    private FiltersBean filters;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 每页数据尺寸
     */
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
         * 审核状态
         */
        private int status;
        /**
         * 用户Id
         */
        private Long userId;
        /**
         * 搜索name
         */
        private String name;


        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
