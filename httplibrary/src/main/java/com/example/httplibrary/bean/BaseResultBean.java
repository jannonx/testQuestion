package com.example.httplibrary.bean;


/**
 * @description: 所有请求结果的bean
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
public class BaseResultBean {
    private int code;

    private String msg;

    public int getResult() {
        return code;
    }

    public void setResult(int result) {
        this.code = result;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

}
