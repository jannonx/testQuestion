package com.guyuan.dear.db.entities;

import com.guyuan.dear.db.DaoSession;
import com.guyuan.dear.db.LocationEntityDao;
import com.guyuan.dear.db.StaffEntityDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * @author 廖华凯
 * @since 2020/6/15 18:05
 **/
@Entity
public class LocationEntity {
  @Id
  private Long id;
  private String name;
  private int level;
  private int sortOrder;
  public transient static final int LEVEL_TYPE_COMPANY=1;
  public transient static final int LEVEL_TYPE_FACTORY=2;
  public transient static final int LEVEL_TYPE_BUILDING=3;
  public transient static final int LEVEL_TYPE_FLOOR=4;
  public transient static final int LEVEL_TYPE_DEPARTMENT=5;
  public transient static final int LEVEL_TYPE_LINE=6;
  @ToMany
  @JoinEntity(entity = StaffsJoinLocationsEntity.class,sourceProperty = "locationId",targetProperty = "staffId")
  private List<StaffEntity> staffs;
  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 2022235245)
  private transient LocationEntityDao myDao;
  @Generated(hash = 214076052)
  public LocationEntity(Long id, String name, int level, int sortOrder) {
      this.id = id;
      this.name = name;
      this.level = level;
      this.sortOrder = sortOrder;
  }
  @Generated(hash = 1723987110)
  public LocationEntity() {
  }
  public Long getId() {
      return this.id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getName() {
      return this.name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public int getLevel() {
      return this.level;
  }
  public void setLevel(int level) {
      this.level = level;
  }
  public int getSortOrder() {
      return this.sortOrder;
  }
  public void setSortOrder(int sortOrder) {
      this.sortOrder = sortOrder;
  }
  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 158022547)
  public List<StaffEntity> getStaffs() {
      if (staffs == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          StaffEntityDao targetDao = daoSession.getStaffEntityDao();
          List<StaffEntity> staffsNew = targetDao._queryLocationEntity_Staffs(id);
          synchronized (this) {
              if (staffs == null) {
                  staffs = staffsNew;
              }
          }
      }
      return staffs;
  }
  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 249257169)
  public synchronized void resetStaffs() {
      staffs = null;
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
  @Generated(hash = 923553686)
  public void __setDaoSession(DaoSession daoSession) {
      this.daoSession = daoSession;
      myDao = daoSession != null ? daoSession.getLocationEntityDao() : null;
  }




}
