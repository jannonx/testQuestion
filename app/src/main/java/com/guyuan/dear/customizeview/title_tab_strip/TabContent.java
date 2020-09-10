package com.guyuan.dear.customizeview.title_tab_strip;

/**
 * {@link TitleTabStripView}中使用，提供按键名称和ID，其中名称是必须的，ID留给开发者自由发挥
 *
 * @author 廖华凯
 * @since 2020/4/7 14:42
 **/
public interface TabContent {
  String getTabTitle();

  int getTabId();

}
