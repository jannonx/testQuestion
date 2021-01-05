package com.guyuan.dear.umeng.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户在新设备登录后，服务器会发出推送，包含以下内容，如果本机收到推送后发现和本机的用户token不一致，就会退出当前登录。
 * @author 廖华凯
 * @since 2020/6/3 10:09
 **/
public class PushLogout implements Parcelable {
  private String accessToken;
  private long userId;

  public PushLogout() {
  }

  protected PushLogout(Parcel in) {
    accessToken = in.readString();
    userId = in.readLong();
  }

  public static final Creator<PushLogout> CREATOR = new Creator<PushLogout>() {
    @Override
    public PushLogout createFromParcel(Parcel in) {
      return new PushLogout(in);
    }

    @Override
    public PushLogout[] newArray(int size) {
      return new PushLogout[size];
    }
  };

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(accessToken);
    dest.writeLong(userId);
  }
}
