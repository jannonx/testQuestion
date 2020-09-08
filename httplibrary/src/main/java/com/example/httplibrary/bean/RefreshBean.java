package com.example.httplibrary.bean;

import java.util.List;

/**
 * @description:
 * @author:Jannonx
 * @date: 2020/5/8 17:04
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
