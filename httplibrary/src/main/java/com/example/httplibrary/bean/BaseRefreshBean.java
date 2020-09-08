package com.example.httplibrary.bean;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/5/8 17:02
 */
public class BaseRefreshBean {
  private int pageNum;
  private int pageSize;
  private int totalPages;
  private int totalSize;

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public int getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(int totalSize) {
    this.totalSize = totalSize;
  }
}
