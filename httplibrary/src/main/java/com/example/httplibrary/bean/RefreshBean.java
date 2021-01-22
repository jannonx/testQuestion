package com.example.httplibrary.bean;

import java.util.List;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public class RefreshBean<T> extends BaseRefreshBean {
  private List<T> content;

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }
}
