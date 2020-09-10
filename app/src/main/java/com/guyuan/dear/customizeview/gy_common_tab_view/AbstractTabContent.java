package com.guyuan.dear.customizeview.gy_common_tab_view;


import com.guyuan.dear.customizeview.title_tab_strip.TabContent;

/**
 * @author 廖华凯
 * @since 2020/7/13 14:29
 **/
public abstract class AbstractTabContent implements TabContent {
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
