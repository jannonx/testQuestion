package com.guyuan.dear.customizeview.title_tab_strip;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.mvvmlibrary.util.LogUtils;


/**
 * 指示条，必须配合{@link TitleTabStripView}使用
 *
 * @author 廖华凯
 * @since 2020/4/3 15:41
 **/
public class TabIndicatorView extends View {

  private int viewWidth = -1;
  private int viewHeight = 8;
  private int color = Color.parseColor("#FF108EE9");
  private Paint paint;
  private Rect indicatorRect;
  private ValueAnimator animator;

  public TabIndicatorView(Context context) {
    this(context, null);
  }

  public TabIndicatorView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TabIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(color);
    indicatorRect = new Rect(0, 0, 0, 0);
  }

  public void setColor(int color) {
    this.color = color;
  }

  public void animateTranslation(Rect from, Rect to) {
    if (animator != null && animator.isRunning()) {
      return;
    }
    animator = new ValueAnimator();
    animator.setDuration(200);
    animator.setObjectValues(from, to);
    animator.setInterpolator(new LinearInterpolator());
    animator.setEvaluator(new TypeEvaluator() {
      @Override public Object evaluate(float fraction, Object startValue, Object endValue) {
        Rect current = (Rect) startValue;
        current.left += (int) ((((Rect) endValue).left - ((Rect) startValue).left) * fraction);
        current.right += (int) ((((Rect) endValue).right - ((Rect) startValue).right) * fraction);
        current.top += (int) ((((Rect) endValue).top - ((Rect) startValue).top) * fraction);
        current.bottom +=
            (int) ((((Rect) endValue).bottom - ((Rect) startValue).bottom) * fraction);
        return current;
      }
    });
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        indicatorRect = (Rect) animation.getAnimatedValue();
        postInvalidate();
      }
    });
    animator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        animator.removeAllUpdateListeners();
        animation.removeListener(this);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
    animator.start();
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    ViewParent parent = getParent();
    if (parent instanceof ViewGroup) {
      viewWidth = ((ViewGroup) parent).getWidth();
      LogUtils.showLog("viewWidth="+viewWidth);
      postInvalidate();
    }
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (animator != null && animator.isRunning()) {
      animator.cancel();
    }
  }

  public Rect getIndicatorRect() {
    return indicatorRect;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (viewWidth > 0) {
      super.onMeasure(MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY),
          MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY));
    } else {
      super.onMeasure(widthMeasureSpec,
          MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY));
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawRect(indicatorRect, paint);
  }
}
