// Generated by data binding compiler. Do not edit!
package com.jannonx.electric.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jannonx.electric.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LayoutHomeBarBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatImageView homeLogoTv;

  @NonNull
  public final AppCompatTextView tvTitle;

  protected LayoutHomeBarBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatImageView homeLogoTv, AppCompatTextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.homeLogoTv = homeLogoTv;
    this.tvTitle = tvTitle;
  }

  @NonNull
  public static LayoutHomeBarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_home_bar, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LayoutHomeBarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LayoutHomeBarBinding>inflateInternal(inflater, R.layout.layout_home_bar, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutHomeBarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_home_bar, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LayoutHomeBarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LayoutHomeBarBinding>inflateInternal(inflater, R.layout.layout_home_bar, null, false, component);
  }

  public static LayoutHomeBarBinding bind(@NonNull View view) {
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
  public static LayoutHomeBarBinding bind(@NonNull View view, @Nullable Object component) {
    return (LayoutHomeBarBinding)bind(component, view, R.layout.layout_home_bar);
  }
}