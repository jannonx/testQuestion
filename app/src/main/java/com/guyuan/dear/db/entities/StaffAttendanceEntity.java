package com.guyuan.dear.db.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * @author 廖华凯
 * @since 2020/6/15 17:44
 **/
@Entity
public class StaffAttendanceEntity {
  @Id(autoincrement = true)
  private Long _id;
  private Long staffId;
  private String date;
  private int type;
  @Transient
  public static final int TYPE_LATE = 2;
  @Transient
  public static final int TYPE_ON_LEAVE = 3;
  @Transient
  public static final int TYPE_ABSENT = 1;
  @Generated(hash = 906274364)
  public StaffAttendanceEntity(Long _id, Long staffId, String date, int type) {
      this._id = _id;
      this.staffId = staffId;
      this.date = date;
      this.type = type;
  }
  @Generated(hash = 260298681)
  public StaffAttendanceEntity() {
  }
  public Long get_id() {
      return this._id;
  }
  public void set_id(Long _id) {
      this._id = _id;
  }
  public Long getStaffId() {
      return this.staffId;
  }
  public void setStaffId(Long staffId) {
      this.staffId = staffId;
  }
  public String getDate() {
      return this.date;
  }
  public void setDate(String date) {
      this.date = date;
  }
  public int getType() {
      return this.type;
  }
  public void setType(int type) {
      this.type = type;
  }

}
