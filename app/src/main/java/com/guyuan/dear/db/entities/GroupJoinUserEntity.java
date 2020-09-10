package com.guyuan.dear.db.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author 廖华凯
 * @since 2020/3/24 17:56
 **/
@Entity
public class GroupJoinUserEntity {
  @Id(autoincrement = true)
  private Long _id;
  private long userId;
  private long groupId;
  @Generated(hash = 263085879)
  public GroupJoinUserEntity(Long _id, long userId, long groupId) {
      this._id = _id;
      this.userId = userId;
      this.groupId = groupId;
  }
  @Generated(hash = 2134388354)
  public GroupJoinUserEntity() {
  }
  public Long get_id() {
      return this._id;
  }
  public void set_id(Long _id) {
      this._id = _id;
  }
  public long getUserId() {
      return this.userId;
  }
  public void setUserId(long userId) {
      this.userId = userId;
  }
  public long getGroupId() {
      return this.groupId;
  }
  public void setGroupId(long groupId) {
      this.groupId = groupId;
  }

}
