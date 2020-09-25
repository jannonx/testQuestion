package com.guyuan.dear.focus.security.data.beans;

import java.util.List;

/**
 * Created by TL
 * on 2019/11/30
 */
public class DangerNumberBean {

  /**
   * type_id : 0
   * num : 0
   * name : null
   * img_url : null
   * list : [{"type_id":6,"num":1,"name":"易燃区域","img_url":"157597099494411251","list":null,
   * "sumList":null},{"type_id":7,"num":3,"name":"易爆区域","img_url":"157597105878477381",
   * "list":null,"sumList":null},{"type_id":9,"num":3,"name":"易腐蚀区域",
   * "img_url":"157597108815679403","list":null,"sumList":null},{"type_id":10,"num":2,
   * "name":"当心打滑","img_url":"157656425711450844","list":null,"sumList":null},{"type_id":11,
   * "num":2,"name":"当心触电","img_url":"157656437990361330","list":null,"sumList":null},{"type_id
   * ":12,"num":1,"name":"当心坠落","img_url":"157656440126869118","list":null,"sumList":null},{
   * "type_id":13,"num":1,"name":"当心吊物","img_url":"157656441691120714","list":null,"sumList":null
   * },{"type_id":14,"num":1,"name":"当心机械伤人","img_url":"157656444697768986","list":null,
   * "sumList":null},{"type_id":15,"num":2,"name":"当心有毒气体","img_url":"157663491012830142",
   * "list":null,"sumList":null},{"type_id":16,"num":2,"name":"当心煤气",
   * "img_url":"157742762538732162","list":null,"sumList":null}]
   * sumList : [{"type_id":-2,"num":10,"name":"危险点类型数量","img_url":null,"list":null,"sumList":null
   * },{"type_id":-1,"num":11,"name":"危险点类型总数","img_url":null,"list":null,"sumList":null},{
   * "type_id":0,"num":18,"name":"危险点总数","img_url":null,"list":null,"sumList":null}]
   */

  private int type_id;
  private int num;
  private Object name;
  private Object img_url;
  private List<ListBean> list;
  private List<SumListBean> sumList;

  public int getType_id() {
    return type_id;
  }

  public void setType_id(int type_id) {
    this.type_id = type_id;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public Object getName() {
    return name;
  }

  public void setName(Object name) {
    this.name = name;
  }

  public Object getImg_url() {
    return img_url;
  }

  public void setImg_url(Object img_url) {
    this.img_url = img_url;
  }

  public List<ListBean> getList() {
    return list;
  }

  public void setList(List<ListBean> list) {
    this.list = list;
  }

  public List<SumListBean> getSumList() {
    return sumList;
  }

  public void setSumList(List<SumListBean> sumList) {
    this.sumList = sumList;
  }

  public static class ListBean {
    /**
     * type_id : 6
     * num : 1
     * name : 易燃区域
     * img_url : 157597099494411251
     * list : null
     * sumList : null
     */

    private int typeId;
    private int num;
    private String name;
    private String imgUrl;
    private Object list;
    private Object sumList;

    public int getType_id() {
      return typeId;
    }

    public void setType_id(int type_id) {
      this.typeId = type_id;
    }

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getImg_url() {
      return imgUrl;
    }

    public void setImg_url(String img_url) {
      this.imgUrl = img_url;
    }

    public Object getList() {
      return list;
    }

    public void setList(Object list) {
      this.list = list;
    }

    public Object getSumList() {
      return sumList;
    }

    public void setSumList(Object sumList) {
      this.sumList = sumList;
    }
  }

  public static class SumListBean {
    /**
     * type_id : -2
     * num : 10
     * name : 危险点类型数量
     * img_url : null
     * list : null
     * sumList : null
     */

    private int typeId;
    private int num;
    private String name;
    private Object imgUrl;
    private Object list;
    private Object sumList;

    public int getType_id() {
      return typeId;
    }

    public void setType_id(int type_id) {
      this.typeId = type_id;
    }

    public int getNum() {
      return num;
    }

    public void setNum(int num) {
      this.num = num;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Object getImg_url() {
      return imgUrl;
    }

    public void setImg_url(Object img_url) {
      this.imgUrl = img_url;
    }

    public Object getList() {
      return list;
    }

    public void setList(Object list) {
      this.list = list;
    }

    public Object getSumList() {
      return sumList;
    }

    public void setSumList(Object sumList) {
      this.sumList = sumList;
    }
  }
}
