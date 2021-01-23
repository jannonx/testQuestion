package com.jannonx.electric.view.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jannonx.electric.R;
import com.jannonx.electric.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by zhy on 15/9/10.
 * 标签
 */
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataChangedListener {
  private static final String TAG = "TagFlowLayout";
  private static final String KEY_CHOOSE_POS = "key_choose_pos";
  private static final String KEY_DEFAULT = "key_default";
  private TagAdapter mTagAdapter;
  private boolean mAutoSelectEffect = true;
  private boolean enableCancel=true;//是否能取消选择
  private int mSelectedMax = -1;//-1为不限制数量
  private MotionEvent mMotionEvent;
  private Set<Integer> mSelectedView = new HashSet<>();
  private Set<Integer> mNoEnable = new HashSet<>();
  private OnSelectListener mOnSelectListener;
  private OnTagClickListener mOnTagClickListener;

  public TagFlowLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout);
    mAutoSelectEffect = ta.getBoolean(R.styleable.TagFlowLayout_auto_select_effect, true);
    enableCancel=ta.getBoolean(R.styleable.TagFlowLayout_enableCancel, true);
    mSelectedMax = ta.getInt(R.styleable.TagFlowLayout_max_select, -1);
    ta.recycle();

    if (mAutoSelectEffect) {
      setClickable(true);
    }
  }

  public TagFlowLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TagFlowLayout(Context context) {
    this(context, null);
  }

  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public List<String> getSelected() {
    List<String> selecetedList = new ArrayList<>();
    for (Integer i : getSelectedList()) {
      List<String> datas = mTagAdapter.mTagDatas;
      if (i < datas.size()) {
        selecetedList.add(datas.get(i));
      }
    }
    return selecetedList;
  }

  public void clearSelected() {
    mSelectedView.clear();
    for (int i = 0; i < getChildCount(); i++) {
      TagView tagView = (TagView) getChildAt(i);
      if (tagView != null) {
        tagView.setChecked(false);
      }
    }
  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int cCount = getChildCount();

    for (int i = 0; i < cCount; i++) {
      TagView tagView = (TagView) getChildAt(i);
      if (tagView != null) {
        if (tagView.getVisibility() == View.GONE) {
          continue;
        }
        if (tagView.getTagView().getVisibility() == View.GONE) {
          tagView.setVisibility(View.GONE);
        }
      }
    }
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public void setOnSelectListener(OnSelectListener onSelectListener) {
    mOnSelectListener = onSelectListener;
    if (mOnSelectListener != null) {
      setClickable(true);
    }
  }

  public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
    mOnTagClickListener = onTagClickListener;
    if (onTagClickListener != null) {
      setClickable(true);
    }
  }

  private int tagWidth = LayoutParams.WRAP_CONTENT;//标签宽度，默认为自适应
  private int count = 0;//每行显示n个标签
  private int leftTagMargin = 0;//tab间距
  private int rightTagMargin = 0;//tab间距
  private int topTagMargin = 0;//tab间距
  private int bottomTagMargin = 0;//tab间距

  /**
   * 设置间距
   */
  public void setTagMargin(int leftTagMargin,int topTagMargin,int rightTagMargin,int bottomTagMargin){
    this.leftTagMargin = leftTagMargin;
    this.topTagMargin = topTagMargin;
    this.rightTagMargin = rightTagMargin;
    this.bottomTagMargin = bottomTagMargin;
  }

  /**
   * 设置每行标签显示个数
   * @param count
   */
  public void setTagCount(int count){
    this.count = count;
  }

  public int getTagCount(){
    return count;
  }

  private void changeAdapter() {
    removeAllViews();
    TagAdapter adapter = mTagAdapter;
    TagView tagViewContainer;
    HashSet preCheckedList = mTagAdapter.getPreCheckedList();
    HashSet preNoEnableList = mTagAdapter.getNoEnableList();

    for (int i = 0; i < adapter.getCount(); i++) {
      View tagView = adapter.getView(this, i, adapter.getItem(i));

      tagViewContainer = new TagView(getContext());
      tagView.setDuplicateParentStateEnabled(true);
      int[] size = CommonUtils.getScreenSize(getContext());
      int screenWidth = size[0];
      if(count > 0)
        tagWidth = (screenWidth - dip2px(getContext(),30 + 66 + count * rightTagMargin * 2)) / count;//30为左右边距之和，66为对话框阴影部分宽度，5为标签的间隔
      MarginLayoutParams lp = new MarginLayoutParams(tagWidth, LayoutParams.WRAP_CONTENT);
      lp.setMargins(dip2px(getContext(), leftTagMargin),
          dip2px(getContext(), topTagMargin),
          dip2px(getContext(), rightTagMargin),
          dip2px(getContext(), bottomTagMargin));
      tagViewContainer.setLayoutParams(lp);
      tagViewContainer.addView(tagView);
      addView(tagViewContainer);

      if (preCheckedList.contains(i)) {
        tagViewContainer.setChecked(true);
      }

      if (mTagAdapter.setSelected(i, adapter.getItem(i))) {
        mSelectedView.add(i);
        tagViewContainer.setChecked(true);
      }

      if (preNoEnableList.contains(i)) {
        tagViewContainer.setEnabled(false);
      }

      if (mTagAdapter.setNoEnable(i, adapter.getItem(i))) {
        mNoEnable.add(i);
        tagViewContainer.setEnabled(false);
      }
    }
    mSelectedView.addAll(preCheckedList);
    mNoEnable.addAll(preNoEnableList);
  }


  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      mMotionEvent = MotionEvent.obtain(event);
    }
    return super.onTouchEvent(event);
  }

  @Override
  public boolean performClick() {
    if (mMotionEvent == null) {
      return super.performClick();
    }

    int x = (int) mMotionEvent.getX();
    int y = (int) mMotionEvent.getY();
    mMotionEvent = null;

    TagView child = findChild(x, y);
    int pos = findPosByView(child);
    if (child != null) {
      if (!mNoEnable.contains(pos)) {   //若是已经不能编辑,则不予许编辑
        doSelect(child, pos);
      }
      if (mOnTagClickListener != null) {
        return mOnTagClickListener.onTagClick(child.getTagView(), pos, this);
      }
    }
    return true;
  }


  public void setMaxSelectCount(int count) {
    if (mSelectedView.size() > count) {
      Log.w(TAG, "you has already select more than " + count + " views , so it will be clear .");
      mSelectedView.clear();
    }
    mSelectedMax = count;
  }

  public Set<Integer> getSelectedList() {
    return new HashSet<Integer>(mSelectedView);
  }

  private void doSelect(TagView child, int position) {
    if (mAutoSelectEffect) {
      if (!child.isChecked()) {
        //处理max_select=1的情况
        if (mSelectedMax == 1 && mSelectedView.size() == 1) {
          Iterator<Integer> iterator = mSelectedView.iterator();
          Integer preIndex = iterator.next();
          TagView pre = (TagView) getChildAt(preIndex);
          if (pre != null) {
            pre.setChecked(false);
          }
          child.setChecked(true);         //选中变色
          mSelectedView.remove(preIndex);
          mSelectedView.add(position);
        } else {
          if (mSelectedMax > 0 && mSelectedView.size() >= mSelectedMax) {
            return;
          }
          child.setChecked(true);        //选中变色
          mSelectedView.add(position);
        }
      } else if(enableCancel){
        child.setChecked(false);
        mSelectedView.remove(position);
      }
      if (mOnSelectListener != null) {
        mOnSelectListener.onSelected(new HashSet<Integer>(mSelectedView));
      }
    }
  }

  public TagAdapter getAdapter() {
    return mTagAdapter;
  }

  public void setAdapter(TagAdapter adapter) {
    mTagAdapter = adapter;
    mTagAdapter.setOnDataChangedListener(this);
    mSelectedView.clear();
    changeAdapter();

  }

  @Override
  protected Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());

    String selectPos = "";
    if (mSelectedView.size() > 0) {
      for (int key : mSelectedView) {
        selectPos += key + "|";
      }
      selectPos = selectPos.substring(0, selectPos.length() - 1);
    }
    bundle.putString(KEY_CHOOSE_POS, selectPos);
    return bundle;
  }

  @Override
  protected void onRestoreInstanceState(Parcelable state) {
    if (state instanceof Bundle) {
      Bundle bundle = (Bundle) state;
      String mSelectPos = bundle.getString(KEY_CHOOSE_POS);
      if (!TextUtils.isEmpty(mSelectPos)) {
        String[] split = mSelectPos.split("\\|");
        for (String pos : split) {
          int index = Integer.parseInt(pos);
          mSelectedView.add(index);

          TagView tagView = (TagView) getChildAt(index);
          if (tagView != null) {
            tagView.setChecked(true);
          }
        }

      }
      super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
      return;
    }
    super.onRestoreInstanceState(state);
  }

  private int findPosByView(View child) {
    final int cCount = getChildCount();
    for (int i = 0; i < cCount; i++) {
      View v = getChildAt(i);
      if (v == child) {
        return i;
      }
    }
    return -1;
  }

  private TagView findChild(int x, int y) {
    final int cCount = getChildCount();
    for (int i = 0; i < cCount; i++) {
      TagView v = (TagView) getChildAt(i);
      if (v.getVisibility() == View.GONE) {
        continue;
      }
      Rect outRect = new Rect();
      v.getHitRect(outRect);
      if (outRect.contains(x, y)) {
        return v;
      }
    }
    return null;
  }

  //设置初始选中的item
  public void setSelcetedItem(int position) {
    TagView child = (TagView) getChildAt(position);
    int pos = findPosByView(child);
    if (child != null) {
      if (!mNoEnable.contains(pos)) {   //若是已经不能编辑,则不予许编辑
        doSelect(child, pos);
//        if(mOnSelectListener!=null){
//          mOnSelectListener.onSelected(mSelectedView);
//        }
      }
    }
  }

  @Override
  public void onChanged() {
    mSelectedView.clear();
    changeAdapter();
  }

  public interface OnSelectListener {
    void onSelected(Set<Integer> selectPosSet);
  }

  public interface OnTagClickListener {
    boolean onTagClick(View view, int position, FlowLayout parent);
  }
}
