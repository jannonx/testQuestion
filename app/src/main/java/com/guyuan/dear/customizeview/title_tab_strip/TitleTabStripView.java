package com.guyuan.dear.customizeview.title_tab_strip;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.guyuan.dear.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：文字下有横线，点击文字横线跟着移动，并更新被监听的内容
 * 首次使用在：我的关注-财务首页-收入-按时间段统计收入柱状图中
 *
 * @author 廖华凯
 * @since 2020/4/3 10:32
 **/
public class TitleTabStripView extends FrameLayout {
  private List<Rect> mIndicatorPos = new ArrayList<>();
  private LinearLayoutCompat mRootView;
  private LinearLayoutCompat mTabViewContainer;
  private TabIndicatorView mIndicator;
  private List<View> mTabViews = new ArrayList<>();
  private List<TabContent> mTabContents = new ArrayList<>();
  private TabListener mTabListener;

  public TitleTabStripView(Context context) {
    this(context, null);
  }

  public TitleTabStripView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TitleTabStripView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
    mRootView = new LinearLayoutCompat(context);
    mRootView.setOrientation(LinearLayoutCompat.VERTICAL);
    addView(mRootView, params);
    mTabViewContainer = new LinearLayoutCompat(context);
    mTabViewContainer.setOrientation(LinearLayoutCompat.HORIZONTAL);
    mRootView.addView(mTabViewContainer, params);
    mIndicator = new TabIndicatorView(getContext());
    LinearLayoutCompat.LayoutParams params1 = new LinearLayoutCompat.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    mRootView.addView(mIndicator, params1);
  }

  /**
   * 需要在这里初始化按键，否则不会显示内容。
   */
  public void setTabContents(List<TabContent> contents) {
    post(new Runnable() {
      @Override
      public void run() {
        mTabContents.clear();
        mTabContents.addAll(contents);
        mIndicatorPos.clear();
        int width = getWidth() / mTabContents.size();
        for (int i = 0; i < mTabContents.size(); i++) {
          AppCompatTextView tv = (AppCompatTextView) LayoutInflater
              .from(getContext())
              .inflate(R.layout.widget_title_tab_strip_item, null, false);
          tv.setText(mTabContents.get(i).getTabTitle());
          LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(
              width,
              ViewGroup.LayoutParams.WRAP_CONTENT
          );
          mTabViewContainer.addView(tv, params);
          Rect rect = new Rect();
          rect.left = (int) (i * width + width * 0.3f);
          rect.top = 0;
          rect.right = (int) ((i + 1) * width - width * 0.3f);
          rect.bottom = mIndicator.getHeight();
          mIndicatorPos.add(rect);

          mTabViews.add(tv);
          tv.setTag(i);
          tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              setCurrentSelected((Integer) tv.getTag());
            }
          });
        }
      }
    });
  }

  /**
   * 设置当前点选标题
   *
   * @param index
   */
  public void setCurrentSelected(int index) {
    post(new Runnable() {
      @Override
      public void run() {
        if (mIndicatorPos.isEmpty() || mIndicatorPos.size() < index - 1) {
          return;
        }
        mIndicator.animateTranslation(mIndicator.getIndicatorRect(), mIndicatorPos.get(index));
        for (int i = 0; i < mTabViews.size(); i++) {
          AppCompatTextView temp = (AppCompatTextView) mTabViews.get(i);
          if (i == index) {
            temp.setSelected(true);
            temp.setTextAppearance(getContext(), R.style.text_style_tab_item_selected);
          } else {
            temp.setSelected(false);
            temp.setTextAppearance(getContext(), R.style.text_style_tab_item_normal);
          }
        }
        if (mTabListener != null) {
          mTabListener.onTabChange(TitleTabStripView.this, mTabContents.get(index));
        }
      }
    });
  }

  /**
   * 用户点击切换标题时的监听器
   */
  public interface TabListener {
    /**
     * 用户点击切换标题时的回调
     *
     * @param view       当前控件，当多个同样类型的{@link TitleTabStripView}在同一个界面时，分辨是那个触发回调。
     * @param tabContent 按键对应的内容
     */
    void onTabChange(View view, TabContent tabContent);
  }

  public void setTabListener(TabListener tabListener) {
    this.mTabListener = tabListener;
  }
}
