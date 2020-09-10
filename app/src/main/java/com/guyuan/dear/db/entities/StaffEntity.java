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
 * @since 2020/6/15 17:43
 **/
@Entity
public class StaffEntity {
  private String name;
  @Id
  private Long id;
  private String imageUrl;
  private long locationId;
  private long deptId;
  private String workId;
  @ToMany
  @JoinEntity(entity = StaffsJoinLocationsEntity.class, sourceProperty = "staffId", targetProperty = "locationId")
  private List<LocationEntity> locations;
  /**
   * Used to resolve relations
   */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;
  /**
   * Used for active entity operations.
   */
  @Generated(hash = 944745661)
  private transient StaffEntityDao myDao;

  @Generated(hash = 373192852)
  public StaffEntity(String name, Long id, String imageUrl, long locationId, long deptId, String workId) {
      this.name = name;
      this.id = id;
      this.imageUrl = imageUrl;
      this.locationId = locationId;
      this.deptId = deptId;
      this.workId = workId;
  }

  @Generated(hash = 623314536)
  public StaffEntity() {
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public long getLocationId() {
    return locationId;
  }

  public void setLocationId(long locationId) {
    this.locationId = locationId;
  }

  public long getDeptId() {
    return deptId;
  }

  public void setDeptId(long deptId) {
    this.deptId = deptId;
  }

  public String getWorkId() {
    return workId;
  }

  public void setWorkId(String workId) {
    this.workId = workId;
  }

  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 346030871)
  public List<LocationEntity> getLocations() {
    if (locations == null) {
      final DaoSession daoSession = this.daoSession;
      if (daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      LocationEntityDao targetDao = daoSession.getLocationEntityDao();
      List<LocationEntity> locationsNew = targetDao._queryStaffEntity_Locations(id);
      synchronized (this) {
        if (locations == null) {
          locations = locationsNew;
        }
      }
    }
    return locations;
  }

  /**
   * Resets a to-many relationship, making the next get call to query for a fresh result.
   */
  @Generated(hash = 1398170159)
  public synchronized void resetLocations() {
    locations = null;
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

  /**
   * called by internal mechanisms, do not call yourself.
   */
  @Generated(hash = 881921302)
  public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getStaffEntityDao() : null;
  }

}
