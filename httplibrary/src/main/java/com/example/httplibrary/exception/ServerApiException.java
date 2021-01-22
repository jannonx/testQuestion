package com.example.httplibrary.exception;


/**
 * @description: 服务器异常(和服务器协议的错误)
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public final class ServerApiException extends Exception {
    private final int code;

    private final String errMsg;

    public ServerApiException(int code, String message) {
//        super("Server Api Code " + code + " " + message);
        super(message);
        this.code = code;
        this.errMsg = message;
    }

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
