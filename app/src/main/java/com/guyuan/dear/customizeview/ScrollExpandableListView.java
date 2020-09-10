package com.guyuan.dear.customizeview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * created by tl
 * created at 2020/4/16
 * 用于scrollView中
 */
public class ScrollExpandableListView extends ExpandableListView {

  public ScrollExpandableListView(Context context) {
    super(context);
  }

  public ScrollExpandableListView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ScrollExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec,
      int heightMeasureSpec) { // TODO Auto-generated method stub
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }
}
