package com.guyuan.dear.db.entities;

import com.guyuan.dear.db.DaoSession;
import com.guyuan.dear.db.UserEntityDao;
import com.guyuan.dear.db.UserGroupEntityDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * @author 廖华凯
 * @since 2020/3/24 17:54
 **/
@Entity
public class UserGroupEntity {
  @Id(autoincrement = true)
  private Long _id;
  private String title;
  private long createTime;
  private long dataOwnerId;
  @ToMany
  @JoinEntity(entity = GroupJoinUserEntity.class,sourceProperty = "groupId",targetProperty = "userId")
  private List<UserEntity> users;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 1258509073)
  private transient UserGroupEntityDao myDao;
  @Generated(hash = 1722998527)
  public UserGroupEntity(Long _id, String title, long createTime, long dataOwnerId) {
      this._id = _id;
      this.title = title;
      this.createTime = createTime;
      this.dataOwnerId = dataOwnerId;
  }
  @Generated(hash = 1091244916)
  public UserGroupEntity() {
  }
  public Long get_id() {
      return this._id;
  }
  public void set_id(Long _id) {
      this._id = _id;
  }
  public String getTitle() {
      return this.title;
  }
  public void setTitle(String title) {
      this.title = title;
  }
  public long getCreateTime() {
      return this.createTime;
  }
  public void setCreateTime(long createTime) {
      this.createTime = createTime;
  }
  public long getDataOwnerId() {
      return this.dataOwnerId;
  }
  public void setDataOwnerId(long dataOwnerId) {
      this.dataOwnerId = dataOwnerId;
  }
  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 234312931)
  public List<UserEntity> getUsers() {
      if (users == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          UserEntityDao targetDao = daoSession.getUserEntityDao();
          List<UserEntity> usersNew = targetDao._queryUserGroupEntity_Users(_id);
          synchronized (this) {
              if (users == null) {
                  users = usersNew;
              }
          }
      }
      return users;
  }
  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 1027274768)
  public synchronized void resetUsers() {
      users = null;
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
  @Generated(hash = 1302530516)
  public void __setDaoSession(DaoSession daoSession) {
      this.daoSession = daoSession;
      myDao = daoSession != null ? daoSession.getUserGroupEntityDao() : null;
  }
 
}
