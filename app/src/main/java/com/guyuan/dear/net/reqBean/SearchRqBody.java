package com.guyuan.dear.net.reqBean;

import com.example.httplibrary.bean.BasePageReqBean;

import java.util.List;
import java.util.Map;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/19 12:13
 * @company: 固远（深圳）信息技术有限公司
 **/
public class SearchRqBody extends BasePageReqBean {
    private Map<String,String> filters;

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    /**
     * 合同变更申请查询类型：1.所有 2.用户自己申请的
     */
    private int findType;
    /**
     * 合同变更操作类型 1.暂停 2.重启
     */
    private int type;

    public int getFindType() {
        return findType;
    }

    public void setFindType(int findType) {
        this.findType = findType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
