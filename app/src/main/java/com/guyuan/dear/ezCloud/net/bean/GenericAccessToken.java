package com.guyuan.dear.ezCloud.net.bean;

import com.videogo.openapi.bean.EZAccessToken;

/**
 * @author 廖华凯
 * @since 2019/11/19 11:40
 **/
public class GenericAccessToken {
    /**
     * accessToken : at.7jrcjmna8qnqg8d3dgnzs87m4v2dme3l-32enpqgusd-1jvdfe4-uxo15ik0s
     * expireTime : 1470810222045
     */

    private String accessToken;
    private long expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public EZAccessToken toSdkAccessToken(){
        EZAccessToken token = new EZAccessToken();
        token.setAccessToken(getAccessToken());
        token.setExpire(getExpireTime());
        return token;
    }

    @Override
    public String toString() {
        return "GenericAccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
