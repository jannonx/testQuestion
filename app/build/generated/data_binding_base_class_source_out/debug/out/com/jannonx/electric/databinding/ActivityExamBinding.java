// Generated by data binding compiler. Do not edit!
package com.jannonx.electric.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.jannonx.electric.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityExamBinding extends ViewDataBinding {
  @NonNull
  public final LayoutHomeBarBinding llHomeBar;

  @NonNull
  public final AppCompatRadioButton rbA;

  @NonNull
  public final AppCompatRadioButton rbB;

  @NonNull
  public final AppCompatRadioButton rbC;

  @NonNull
  public final AppCompatRadioButton rbD;

  @NonNull
  public final RadioGroup rgSelect;

  @NonNull
  public final AppCompatTextView tvNext;

  @NonNull
  public final AppCompatTextView tvParse;

  @NonNull
  public final AppCompatTextView tvPrevious;

  @NonNull
  public final AppCompatTextView tvQuestion;

  protected ActivityExamBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LayoutHomeBarBinding llHomeBar, AppCompatRadioButton rbA, AppCompatRadioButton rbB,
      AppCompatRadioButton rbC, AppCompatRadioButton rbD, RadioGroup rgSelect,
      AppCompatTextView tvNext, AppCompatTextView tvParse, AppCompatTextView tvPrevious,
      AppCompatTextView tvQuestion) {
    super(_bindingComponent, _root, _localFieldCount);
    this.llHomeBar = llHomeBar;
    setContainedBinding(this.llHomeBar);
    this.rbA = rbA;
    this.rbB = rbB;
    this.rbC = rbC;
    this.rbD = rbD;
    this.rgSelect = rgSelect;
    this.tvNext = tvNext;
    this.tvParse = tvParse;
    this.tvPrevious = tvPrevious;
    this.tvQuestion = tvQuestion;
  }

  @NonNull
  public static ActivityExamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_exam, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityExamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityExamBinding>inflateInternal(inflater, R.layout.activity_exam, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityExamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_exam, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityExamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityExamBinding>inflateInternal(inflater, R.layout.activity_exam, null, false, component);
  }

  public static ActivityExamBinding bind(@NonNull View view) {
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
  public static ActivityExamBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityExamBinding)bind(component, view, R.layout.activity_exam);
  }
}