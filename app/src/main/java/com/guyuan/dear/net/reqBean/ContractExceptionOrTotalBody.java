package com.guyuan.dear.net.reqBean;

import com.guyuan.dear.base.bean.ListRequestBody;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/1 11:24
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ContractExceptionOrTotalBody {
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
        private String name;
        private int type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}