package com.guyuan.dear.db.entities;

import com.guyuan.dear.db.DaoSession;
import com.guyuan.dear.db.MeetingEntityDao;
import com.guyuan.dear.db.UserEntityDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @author 廖华凯
 * @since 2020/3/20 12:19
 **/
@Entity()
public class MeetingEntity {
  private long holderId;
  private String meetingTitle;
  private long createTime;
  @NotNull
  @Id
  private long meetingId;
  @Index(unique = true)
  private long roomId;
  private boolean isClosed;
  private int flag;
  private long dataOwnerId;
  private long meetingTime;
  @Transient
  public static final int FLAG_DEFAULT = 0;
  @Transient
  public static final int FLAG_LEAVE_BUT_NOT_OVER = 2;
  @Transient
  public static final int FLAG_LEAVE_AND_OVER = 4;
  @Transient
  public static final int FLAG_ATTENDING = 1;
  @Transient
  public static final int FLAG_FUTURE_APPOINTED =3;
  @ToOne(joinProperty = "holderId")
  private UserEntity holder;
  @ToMany
  @JoinEntity(entity = MeetingsJoinUsersEntity.class, sourceProperty = "meetingId", targetProperty = "userId")
  private List<UserEntity> attendants;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 2145444332)
  private transient MeetingEntityDao myDao;
  @Generated(hash = 309622935)
  private transient Long holder__resolvedKey;

  @Generated(hash = 1710219773)
  public MeetingEntity(long holderId, String meetingTitle, long createTime, long meetingId, long roomId,
          boolean isClosed, int flag, long dataOwnerId, long meetingTime) {
      this.holderId = holderId;
      this.meetingTitle = meetingTitle;
      this.createTime = createTime;
      this.meetingId = meetingId;
      this.roomId = roomId;
      this.isClosed = isClosed;
      this.flag = flag;
      this.dataOwnerId = dataOwnerId;
      this.meetingTime = meetingTime;
  }

  @Generated(hash = 998686073)
  public MeetingEntity() {
  }

  public long getHolderId() {
    return this.holderId;
  }

  public void setHolderId(long holderId) {
    this.holderId = holderId;
  }

  public String getMeetingTitle() {
    return this.meetingTitle;
  }

  public void setMeetingTitle(String meetingTitle) {
    this.meetingTitle = meetingTitle;
  }

  public long getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public long getMeetingId() {
    return this.meetingId;
  }

  public void setMeetingId(long meetingId) {
    this.meetingId = meetingId;
  }

  public long getRoomId() {
    return this.roomId;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public boolean getIsClosed() {
    return this.isClosed;
  }

  public void setIsClosed(boolean isClosed) {
    this.isClosed = isClosed;
  }

  public int getFlag() {
    return this.flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 1102559702)
  public List<UserEntity> getAttendants() {
      if (attendants == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          UserEntityDao targetDao = daoSession.getUserEntityDao();
          List<UserEntity> attendantsNew = targetDao._queryMeetingEntity_Attendants(meetingId);
          synchronized (this) {
              if (attendants == null) {
                  attendants = attendantsNew;
              }
          }
      }
      return attendants;
  }

  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 1454586410)
  public synchronized void resetAttendants() {
    attendants = null;
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 128553479)
  public void delete() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 1942392019)
  public void refresh() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 713229351)
  public void update() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 1422872239)
  public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getMeetingEntityDao() : null;
  }

  /** To-one relationship, resolved on first access. */
  @Generated(hash = 1554271957)
  public UserEntity getHolder() {
    long __key = this.holderId;
    if (holder__resolvedKey == null || !holder__resolvedKey.equals(__key)) {
      final DaoSession daoSession = this.daoSession;
      if (daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      UserEntityDao targetDao = daoSession.getUserEntityDao();
      UserEntity holderNew = targetDao.load(__key);
      synchronized (this) {
        holder = holderNew;
        holder__resolvedKey = __key;
      }
    }
    return holder;
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 1416998006)
  public void setHolder(@NotNull UserEntity holder) {
    if (holder == null) {
      throw new DaoException(
          "To-one property 'holderId' has not-null constraint; cannot set to-one to null");
    }
    synchronized (this) {
      this.holder = holder;
      holderId = holder.getId();
      holder__resolvedKey = holderId;
    }
  }

  public long getDataOwnerId() {
      return this.dataOwnerId;
  }

  public void setDataOwnerId(long dataOwnerId) {
      this.dataOwnerId = dataOwnerId;
  }

  public long getMeetingTime() {
      return this.meetingTime;
  }

  public void setMeetingTime(long meetingTime) {
      this.meetingTime = meetingTime;
  }

  @Override public String toString() {
    return "MeetingEntity{" +
        "holderId=" + holderId +
        ", meetingTitle='" + meetingTitle + '\'' +
        ", createTime=" + createTime +
        ", meetingId=" + meetingId +
        ", roomId=" + roomId +
        ", isClosed=" + isClosed +
        ", flag=" + flag +
        ", dataOwnerId=" + dataOwnerId +
        ", meetingTime=" + meetingTime +
        ", holder=" + holder +
        ", attendants=" + attendants +
        ", daoSession=" + daoSession +
        ", myDao=" + myDao +
        ", holder__resolvedKey=" + holder__resolvedKey +
        '}';
  }
}
