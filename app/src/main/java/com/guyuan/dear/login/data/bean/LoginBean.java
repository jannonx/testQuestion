package com.guyuan.dear.login.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @description: 登陆数据
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 * @company: 固远（深圳）信息技术有限公司
 */
public class LoginBean implements Parcelable {

    private int id;
    private String createBy;
    private String createTime;
    private String lastUpdateBy;
    private long lastUpdateTime;
    private int userId;
    private String token;
    private long expireTime;
    private UserInfoBean userInfo;
    private List<AppMenusBean> appMenus;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.createBy);
        dest.writeString(this.createTime);
        dest.writeString(this.lastUpdateBy);
        dest.writeLong(this.lastUpdateTime);
        dest.writeInt(this.userId);
        dest.writeString(this.token);
        dest.writeLong(this.expireTime);
        dest.writeParcelable(this.userInfo, flags);
        dest.writeTypedList(this.appMenus);
    }

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.id = in.readInt();
        this.createBy = in.readString();
        this.createTime = in.readString();
        this.lastUpdateBy = in.readString();
        this.lastUpdateTime = in.readLong();
        this.userId = in.readInt();
        this.token = in.readString();
        this.expireTime = in.readLong();
        this.userInfo = in.readParcelable(UserInfoBean.class.getClassLoader());
        this.appMenus = in.createTypedArrayList(AppMenusBean.CREATOR);
    }

    public static final Parcelable.Creator<LoginBean> CREATOR = new Parcelable.Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<AppMenusBean> getAppMenus() {
        return appMenus;
    }

    public void setAppMenus(List<AppMenusBean> appMenus) {
        this.appMenus = appMenus;
    }
}
