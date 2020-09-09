package com.guyuan.dear.approve.bean;


/**
 * @description: 申请模块，列表请求配置
 * @author: Jannonx
 * @since: 2020/9/9 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
public class ApproveListBody {

    /**
     * pageSize : 0
     * pageNum : 10
     * columnFilters : {"type":{"name":"type","value":"1"}}
     */

    private int pageSize;
    private int pageNum;
    private ColumnFiltersBean filters;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public ColumnFiltersBean getColumnFilters() {
        return filters;
    }

    public void setColumnFilters(ColumnFiltersBean columnFilters) {
        this.filters = columnFilters;
    }

    public static class ColumnFiltersBean {

        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }


    }
}
