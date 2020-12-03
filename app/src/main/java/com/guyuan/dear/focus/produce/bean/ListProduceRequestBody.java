package com.guyuan.dear.focus.produce.bean;

/**
 * @description: 生产模块--列表请求体(参数配置)
 * @author: 许建宁
 * @since: 2020/11/9 10:22
 * @company: 固远（深圳）信息技术有限公司
 */
public class ListProduceRequestBody {
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
        private String name;
        //生产状态
        private Integer status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
