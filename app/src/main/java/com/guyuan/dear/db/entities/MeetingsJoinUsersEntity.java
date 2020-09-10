package com.guyuan.dear.db.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author 廖华凯
 * @since 2020/3/20 18:43
 **/
@Entity
public class MeetingsJoinUsersEntity {
  @Id(autoincrement = true)
  private Long _id;
  private long meetingId;
  private long userId;
  @Generated(hash = 187049504)
  public MeetingsJoinUsersEntity(Long _id, long meetingId, long userId) {
      this._id = _id;
      this.meetingId = meetingId;
      this.userId = userId;
  }
  @Generated(hash = 770011517)
  public MeetingsJoinUsersEntity() {
  }
  public Long get_id() {
      return this._id;
  }
  public void set_id(Long _id) {
      this._id = _id;
  }
  public long getMeetingId() {
      return this.meetingId;
  }
  public void setMeetingId(long meetingId) {
      this.meetingId = meetingId;
  }
  public long getUserId() {
      return this.userId;
  }
  public void setUserId(long userId) {
      this.userId = userId;
  }

}
