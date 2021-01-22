package com.example.httplibrary.bean;


/**
 * @description: 封装的结果对象
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public class ResultBean<T> extends BaseResultBean {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
