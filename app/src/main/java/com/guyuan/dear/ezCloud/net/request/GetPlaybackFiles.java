package com.guyuan.dear.ezCloud.net.request;

/**
 * @author 廖华凯
 * @since 2020/5/8 17:36
 **/
public class GetPlaybackFiles {
  private String accessToken;
  private String deviceSerial;
  private int channelNo;
  private long startTime;
  private long endTime;
  private int recType;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getDeviceSerial() {
    return deviceSerial;
  }

  public void setDeviceSerial(String deviceSerial) {
    this.deviceSerial = deviceSerial;
  }

  public int getChannelNo() {
    return channelNo;
  }

  public void setChannelNo(int channelNo) {
    this.channelNo = channelNo;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }

  public int getRecType() {
    return recType;
  }

  public void setRecType(int recType) {
    this.recType = recType;
  }
}
