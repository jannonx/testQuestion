package com.guyuan.dear.customizeview.autoscrollrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

/**
 * created by tl
 * created at 2020/4/25
 * 自动滚动recycleview
 */
public class AutoScrollRecyclerView extends RecyclerView {

  private long scrollTime = 1000;//滚动间隔
  private AutoScrollTask autoScrollTask;
  private int position;
  private OnScrollListener scrollListener;

  public AutoScrollRecyclerView(@NonNull Context context) {
    super(context);
  }

  public AutoScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    autoScrollTask = new AutoScrollTask(this);
  }

  public AutoScrollRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs,
                                int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  //滚动任务
  private static class AutoScrollTask implements Runnable {

    private final WeakReference<AutoScrollRecyclerView> mReference;

    public AutoScrollTask(AutoScrollRecyclerView reference) {
      this.mReference = new WeakReference<AutoScrollRecyclerView>(reference);
    }

    @Override
    public void run() {
      AutoScrollRecyclerView recyclerView = mReference.get();
      if (recyclerView != null && recyclerView.getScrollState() == 0) {
        OnScrollListener scrollListener = recyclerView.getScrollListener();
        recyclerView.scrollToPosition(recyclerView.position++);
        if (scrollListener != null) {
          scrollListener.afterScroll(recyclerView.position);
        }
        recyclerView.postDelayed(recyclerView.autoScrollTask, recyclerView.scrollTime);
      }
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent e) {
    switch (e.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (getScrollState() != 0) {
          stopScroll();
        }
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
      case MotionEvent.ACTION_OUTSIDE:
        startScroll();
        break;
    }
    return super.onTouchEvent(e);
  }

  public void setDuration(long scrollTime) {
    this.scrollTime = scrollTime;
  }

  public void startScroll() {
    stopScroll();
    postDelayed(autoScrollTask, scrollTime);
  }

  public void stopScroll() {
    removeCallbacks(autoScrollTask);
  }

  public void setScrollListener(OnScrollListener scrollListener) {
    this.scrollListener = scrollListener;
  }

  public OnScrollListener getScrollListener() {
    return scrollListener;
  }

  public interface OnScrollListener {
    void afterScroll(int position);
  }
}
