package com.guyuan.dear.db.entities;

import com.guyuan.dear.db.DaoSession;
import com.guyuan.dear.db.MeetingEntityDao;
import com.guyuan.dear.db.UserEntityDao;
import com.guyuan.dear.db.UserGroupEntityDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * @author 廖华凯
 * @since 2020/3/20 18:21
 **/
@Entity
public class UserEntity {
  @Id
  @NotNull
  private long id;
  @NotNull
  private String name;
  private String imgUrl;
  @ToMany
  @JoinEntity(entity = MeetingsJoinUsersEntity.class,sourceProperty = "userId",targetProperty = "meetingId")
  private List<MeetingEntity> meetings;
  @ToMany
  @JoinEntity(entity = GroupJoinUserEntity.class,sourceProperty = "userId",targetProperty = "groupId")
  private List<UserGroupEntity> userGroupEntities;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 1814575071)
  private transient UserEntityDao myDao;
  @Generated(hash = 1719340000)
  public UserEntity(long id, @NotNull String name, String imgUrl) {
      this.id = id;
      this.name = name;
      this.imgUrl = imgUrl;
  }
  @Generated(hash = 1433178141)
  public UserEntity() {
  }
  public long getId() {
      return this.id;
  }
  public void setId(long id) {
      this.id = id;
  }
  public String getName() {
      return this.name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public String getImgUrl() {
      return this.imgUrl;
  }
  public void setImgUrl(String imgUrl) {
      this.imgUrl = imgUrl;
  }
  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 1586610745)
  public List<MeetingEntity> getMeetings() {
      if (meetings == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          MeetingEntityDao targetDao = daoSession.getMeetingEntityDao();
          List<MeetingEntity> meetingsNew = targetDao._queryUserEntity_Meetings(id);
          synchronized (this) {
              if (meetings == null) {
                  meetings = meetingsNew;
              }
          }
      }
      return meetings;
  }
  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 495885775)
  public synchronized void resetMeetings() {
      meetings = null;
  }
  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 622412881)
  public List<UserGroupEntity> getUserGroupEntities() {
      if (userGroupEntities == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          UserGroupEntityDao targetDao = daoSession.getUserGroupEntityDao();
          List<UserGroupEntity> userGroupEntitiesNew = targetDao._queryUserEntity_UserGroupEntities(id);
          synchronized (this) {
              if (userGroupEntities == null) {
                  userGroupEntities = userGroupEntitiesNew;
              }
          }
      }
      return userGroupEntities;
  }
  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 593213444)
  public synchronized void resetUserGroupEntities() {
      userGroupEntities = null;
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
  @Generated(hash = 287999134)
  public void __setDaoSession(DaoSession daoSession) {
      this.daoSession = daoSession;
      myDao = daoSession != null ? daoSession.getUserEntityDao() : null;
  }

}
