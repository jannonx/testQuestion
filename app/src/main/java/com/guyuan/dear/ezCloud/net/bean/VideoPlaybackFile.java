package com.guyuan.dear.ezCloud.net.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.videogo.openapi.bean.EZCloudRecordFile;

import java.util.Calendar;

/**
 * 萤石云录像回播记录
 *
 * @author 廖华凯
 * @since 2020/5/8 16:03
 **/
public class VideoPlaybackFile implements Parcelable {

  /**
   * recType : 1
   * startTime : 1588888897000
   * endTime : 1588888915000
   * deviceSerial : D80083082
   * channelNo : 1
   * localType : null
   * channelType : null
   * id : 77595736690
   * fileId : 20200508060144-D80083082-1-10002-1-1
   * ownerId : 3634707519_open
   * fileType : 1
   * fileName :
   * cloudType : 1
   * fileIndex : 20200508060144-D80083082-1-10002-1-1
   * fileSize : 2176988
   * locked : 0
   * createTime : 1588888897000
   * crypt : 13
   * keyChecksum : 7f3f37049a2ed9216037c1f7425d402b
   * videoLong : 18000
   * coverPic : https://cloud.ys7.com:8089/api/cloud?method=download&fid=20200508060144-D80083082-1-10002-2-1&session=hik%24shipin7%231%23USK%23at.b91ilg86bny4rtdr3ld4c1yh178gz1lj-9kee1168nu-1fxrjdx-ulpve8nrh
   * downloadPath : cloud.ys7.com:32723
   * type : 1
   * iStorageVersion : 1
   * videoType : 2
   */

  private int recType;
  private long startTime;
  private long endTime;
  private String deviceSerial;
  private int channelNo;
  private long id;
  private String fileId;
  private String ownerId;
  private int fileType;
  private String fileName;
  private int cloudType;
  private String fileIndex;
  private int fileSize;
  private int locked;
  private long createTime;
  private int crypt;
  private String keyChecksum;
  private int videoLong;
  private String coverPic;
  private String downloadPath;
  private int type;
  private int iStorageVersion;
  private int videoType;

  public VideoPlaybackFile() {
  }

  protected VideoPlaybackFile(Parcel in) {
    recType = in.readInt();
    startTime = in.readLong();
    endTime = in.readLong();
    deviceSerial = in.readString();
    channelNo = in.readInt();
    id = in.readLong();
    fileId = in.readString();
    ownerId = in.readString();
    fileType = in.readInt();
    fileName = in.readString();
    cloudType = in.readInt();
    fileIndex = in.readString();
    fileSize = in.readInt();
    locked = in.readInt();
    createTime = in.readLong();
    crypt = in.readInt();
    keyChecksum = in.readString();
    videoLong = in.readInt();
    coverPic = in.readString();
    downloadPath = in.readString();
    type = in.readInt();
    iStorageVersion = in.readInt();
    videoType = in.readInt();
  }

  public static final Creator<VideoPlaybackFile> CREATOR = new Creator<VideoPlaybackFile>() {
    @Override
    public VideoPlaybackFile createFromParcel(Parcel in) {
      return new VideoPlaybackFile(in);
    }

    @Override
    public VideoPlaybackFile[] newArray(int size) {
      return new VideoPlaybackFile[size];
    }
  };

  public int getRecType() {
    return recType;
  }

  public void setRecType(int recType) {
    this.recType = recType;
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public int getFileType() {
    return fileType;
  }

  public void setFileType(int fileType) {
    this.fileType = fileType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public int getCloudType() {
    return cloudType;
  }

  public void setCloudType(int cloudType) {
    this.cloudType = cloudType;
  }

  public String getFileIndex() {
    return fileIndex;
  }

  public void setFileIndex(String fileIndex) {
    this.fileIndex = fileIndex;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }

  public int getLocked() {
    return locked;
  }

  public void setLocked(int locked) {
    this.locked = locked;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public int getCrypt() {
    return crypt;
  }

  public void setCrypt(int crypt) {
    this.crypt = crypt;
  }

  public String getKeyChecksum() {
    return keyChecksum;
  }

  public void setKeyChecksum(String keyChecksum) {
    this.keyChecksum = keyChecksum;
  }

  public int getVideoLong() {
    return videoLong;
  }

  public void setVideoLong(int videoLong) {
    this.videoLong = videoLong;
  }

  public String getCoverPic() {
    return coverPic;
  }

  public void setCoverPic(String coverPic) {
    this.coverPic = coverPic;
  }

  public String getDownloadPath() {
    return downloadPath;
  }

  public void setDownloadPath(String downloadPath) {
    this.downloadPath = downloadPath;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getIStorageVersion() {
    return iStorageVersion;
  }

  public void setIStorageVersion(int iStorageVersion) {
    this.iStorageVersion = iStorageVersion;
  }

  public int getVideoType() {
    return videoType;
  }

  public void setVideoType(int videoType) {
    this.videoType = videoType;
  }

  @Override
  public String toString() {
    return "VideoPlaybackFile{" +
        "recType=" + recType +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", deviceSerial='" + deviceSerial + '\'' +
        ", channelNo=" + channelNo +
        ", id=" + id +
        ", fileId='" + fileId + '\'' +
        ", ownerId='" + ownerId + '\'' +
        ", fileType=" + fileType +
        ", fileName='" + fileName + '\'' +
        ", cloudType=" + cloudType +
        ", fileIndex='" + fileIndex + '\'' +
        ", fileSize=" + fileSize +
        ", locked=" + locked +
        ", createTime=" + createTime +
        ", crypt=" + crypt +
        ", keyChecksum='" + keyChecksum + '\'' +
        ", videoLong=" + videoLong +
        ", coverPic='" + coverPic + '\'' +
        ", downloadPath='" + downloadPath + '\'' +
        ", type=" + type +
        ", iStorageVersion=" + iStorageVersion +
        ", videoType=" + videoType +
        '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(recType);
    dest.writeLong(startTime);
    dest.writeLong(endTime);
    dest.writeString(deviceSerial);
    dest.writeInt(channelNo);
    dest.writeLong(id);
    dest.writeString(fileId);
    dest.writeString(ownerId);
    dest.writeInt(fileType);
    dest.writeString(fileName);
    dest.writeInt(cloudType);
    dest.writeString(fileIndex);
    dest.writeInt(fileSize);
    dest.writeInt(locked);
    dest.writeLong(createTime);
    dest.writeInt(crypt);
    dest.writeString(keyChecksum);
    dest.writeInt(videoLong);
    dest.writeString(coverPic);
    dest.writeString(downloadPath);
    dest.writeInt(type);
    dest.writeInt(iStorageVersion);
    dest.writeInt(videoType);
  }

  public EZCloudRecordFile toEzCloudRecordFile() {
    EZCloudRecordFile file = new EZCloudRecordFile();
    file.setCameraNo(getChannelNo());
    file.setDeviceSerial(getDeviceSerial());
    file.setCoverPic(getCoverPic());
    file.setDownloadPath(getDownloadPath());
    file.setEncryption(getKeyChecksum());
    file.setFileId(getFileId());
    file.setiStorageVersion(getIStorageVersion());
    Calendar startTime = Calendar.getInstance();
    startTime.setTimeInMillis(getStartTime());
    file.setStartTime(startTime);
    Calendar endTime = Calendar.getInstance();
    endTime.setTimeInMillis(getEndTime());
    file.setStopTime(endTime);
    file.setVideoType(getVideoType());
    return file;
  }
}
