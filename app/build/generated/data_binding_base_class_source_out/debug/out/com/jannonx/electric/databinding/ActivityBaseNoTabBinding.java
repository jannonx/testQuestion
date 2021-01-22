// Generated by data binding compiler. Do not edit!
package com.jannonx.electric.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.example.mvvmlibrary.databinding.ToolbarBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.jannonx.electric.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityBaseNoTabBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout ablTitle;

  @NonNull
  public final ViewPager2 baseVp;

  @NonNull
  public final ToolbarBinding layoutToolbar;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final AppCompatTextView tvNextStep;

  protected ActivityBaseNoTabBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout ablTitle, ViewPager2 baseVp, ToolbarBinding layoutToolbar,
      ProgressBar progressBar, AppCompatTextView tvNextStep) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ablTitle = ablTitle;
    this.baseVp = baseVp;
    this.layoutToolbar = layoutToolbar;
    setContainedBinding(this.layoutToolbar);
    this.progressBar = progressBar;
    this.tvNextStep = tvNextStep;
  }

  @NonNull
  public static ActivityBaseNoTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_base_no_tab, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityBaseNoTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityBaseNoTabBinding>inflateInternal(inflater, R.layout.activity_base_no_tab, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityBaseNoTabBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_base_no_tab, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityBaseNoTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityBaseNoTabBinding>inflateInternal(inflater, R.layout.activity_base_no_tab, null, false, component);
  }

  public static ActivityBaseNoTabBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityBaseNoTabBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityBaseNoTabBinding)bind(component, view, R.layout.activity_base_no_tab);
  }
}
