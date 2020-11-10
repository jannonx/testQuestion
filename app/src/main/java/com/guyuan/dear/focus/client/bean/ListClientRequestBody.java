package com.guyuan.dear.focus.client.bean;

/**
 * @description: 客户模块--列表请求体(参数配置)
 * @author: jannonx
 * @since: 2020/9/10 11:28
 * @company: 固远（深圳）信息技术有限公司
 */
public class ListClientRequestBody {
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
         * 标识id
         */
        private long id;
        /**
         * 客户名称 or 手机号码
         */
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
