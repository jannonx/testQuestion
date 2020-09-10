package com.guyuan.dear.db.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author 廖华凯
 * @since 2020/6/15 18:28
 **/
@Entity
public class StaffsJoinLocationsEntity {
  @Id(autoincrement = true)
  private Long _id;
  private long staffId;
  private long locationId;

  @Generated(hash = 627680580)
  public StaffsJoinLocationsEntity(Long _id, long staffId, long locationId) {
      this._id = _id;
      this.staffId = staffId;
      this.locationId = locationId;
  }


  @Generated(hash = 978131260)
  public StaffsJoinLocationsEntity() {
  }

  public Long get_id() {
    return _id;
  }


  public long getStaffId() {
    return staffId;
  }

  public void setStaffId(long staffId) {
    this.staffId = staffId;
  }

  public long getLocationId() {
    return locationId;
  }

  public void setLocationId(long locationId) {
    this.locationId = locationId;
  }


  public void set_id(Long _id) {
      this._id = _id;
  }
}
