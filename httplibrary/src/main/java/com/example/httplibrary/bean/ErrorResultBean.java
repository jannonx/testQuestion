package com.example.httplibrary.bean;

import io.reactivex.functions.Consumer;

/**
 * 请求失败结果的bean
 */
public abstract class ErrorResultBean extends BaseResultBean implements Consumer<Throwable> {


    private boolean isSuccess;   //成功或失败

    private String codeDesc;     //状态码描述


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    @Override
    public void accept(Throwable e) throws Exception {
        e.printStackTrace();  //打印异常信息
        ErrorBean errorBean = new ErrorBean();
        errorBean.setErrorResult(e.getMessage());
        onError(errorBean);
    }

    protected abstract void onError(ErrorBean errorBean);


    public class ErrorBean {
        private int errorCode;
        private String errorResult;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorResult() {
            return errorResult;
        }

        public void setErrorResult(String errorResult) {
            this.errorResult = errorResult;
        }
    }

}
