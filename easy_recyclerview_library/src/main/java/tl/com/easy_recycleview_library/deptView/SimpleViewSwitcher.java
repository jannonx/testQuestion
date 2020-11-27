package tl.com.easy_recycleview_library.deptView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 加载过渡动画容器
 */

public class SimpleViewSwitcher extends ViewGroup {

  public SimpleViewSwitcher(Context context) {
    super(context);
  }

  public SimpleViewSwitcher(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SimpleViewSwitcher(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int childCount = this.getChildCount();
    int maxHeight = 0;
    int maxWidth = 0;
    for (int i = 0; i < childCount; i++) {
      View child = this.getChildAt(i);
      this.measureChild(child, widthMeasureSpec, heightMeasureSpec);//测量子view的宽高
      int cw = child.getMeasuredWidth();
      // int ch = child.getMeasuredHeight();
      maxWidth = child.getMeasuredWidth();
      maxHeight = child.getMeasuredHeight();
    }
    setMeasuredDimension(maxWidth, maxHeight);//设置控件宽高
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

    final int count = getChildCount();

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != View.GONE) {
        child.layout(0, 0, r - l, b - t);

      }
    }
  }

  public void setView(View view) {
    if (this.getChildCount() != 0) {
      this.removeViewAt(0);
    }
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(64, 64);
    this.addView(view);
  }

}