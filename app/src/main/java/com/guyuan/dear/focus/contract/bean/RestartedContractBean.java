package com.guyuan.dear.focus.contract.bean;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/27 16:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class RestartedContractBean extends BaseContractBean{
    private String restartCause;
    private String restartStateTag;

    public String getRestartCause() {
        return restartCause;
    }

    public void setRestartCause(String restartCause) {
        this.restartCause = restartCause;
    }

    public String getRestartStateTag() {
        return restartStateTag;
    }

    public void setRestartStateTag(String restartStateTag) {
        this.restartStateTag = restartStateTag;
    }
}
