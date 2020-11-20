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
}
