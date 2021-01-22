// Generated by data binding compiler. Do not edit!
package com.jannonx.electric.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jannonx.electric.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class LayoutSearchBarBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout clSearchBar;

  @NonNull
  public final AppCompatEditText etSearch;

  @NonNull
  public final AppCompatImageView ivClear;

  @NonNull
  public final AppCompatImageView ivSearch;

  @NonNull
  public final RelativeLayout rlSearchBar;

  @NonNull
  public final AppCompatCheckedTextView tvSearchBtn;

  protected LayoutSearchBarBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout clSearchBar, AppCompatEditText etSearch, AppCompatImageView ivClear,
      AppCompatImageView ivSearch, RelativeLayout rlSearchBar,
      AppCompatCheckedTextView tvSearchBtn) {
    super(_bindingComponent, _root, _localFieldCount);
    this.clSearchBar = clSearchBar;
    this.etSearch = etSearch;
    this.ivClear = ivClear;
    this.ivSearch = ivSearch;
    this.rlSearchBar = rlSearchBar;
    this.tvSearchBtn = tvSearchBtn;
  }

  @NonNull
  public static LayoutSearchBarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_search_bar, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static LayoutSearchBarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<LayoutSearchBarBinding>inflateInternal(inflater, R.layout.layout_search_bar, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutSearchBarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.layout_search_bar, null, false, component)
   */
  @NonNull
  @Deprecated
  public static LayoutSearchBarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<LayoutSearchBarBinding>inflateInternal(inflater, R.layout.layout_search_bar, null, false, component);
  }

  public static LayoutSearchBarBinding bind(@NonNull View view) {
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
  public static LayoutSearchBarBinding bind(@NonNull View view, @Nullable Object component) {
    return (LayoutSearchBarBinding)bind(component, view, R.layout.layout_search_bar);
  }
}
