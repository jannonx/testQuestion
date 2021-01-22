package com.guyuan.dear;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.guyuan.dear.databinding.ActivityBaseNoTabBindingImpl;
import com.guyuan.dear.databinding.ActivityBaseTabBindingImpl;
import com.guyuan.dear.databinding.ActivityExamBindingImpl;
import com.guyuan.dear.databinding.ActivityMainBindingImpl;
import com.guyuan.dear.databinding.EmptyBindingImpl;
import com.guyuan.dear.databinding.FragmentListBindingImpl;
import com.guyuan.dear.databinding.FragmentQuestionItemBindingImpl;
import com.guyuan.dear.databinding.HomeBarBindingImpl;
import com.guyuan.dear.databinding.ItemAnswerQuestionBindingImpl;
import com.guyuan.dear.databinding.LayoutHomeBarBindingImpl;
import com.guyuan.dear.databinding.LayoutSearchBarBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;

@Generated("Android Data Binding")
public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYBASENOTAB = 1;

  private static final int LAYOUT_ACTIVITYBASETAB = 2;

  private static final int LAYOUT_ACTIVITYEXAM = 3;

  private static final int LAYOUT_ACTIVITYMAIN = 4;

  private static final int LAYOUT_EMPTY = 5;

  private static final int LAYOUT_FRAGMENTLIST = 6;

  private static final int LAYOUT_FRAGMENTQUESTIONITEM = 7;

  private static final int LAYOUT_HOMEBAR = 8;

  private static final int LAYOUT_ITEMANSWERQUESTION = 9;

  private static final int LAYOUT_LAYOUTHOMEBAR = 10;

  private static final int LAYOUT_LAYOUTSEARCHBAR = 11;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(11);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.activity_base_no_tab, LAYOUT_ACTIVITYBASENOTAB);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.activity_base_tab, LAYOUT_ACTIVITYBASETAB);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.activity_exam, LAYOUT_ACTIVITYEXAM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.empty, LAYOUT_EMPTY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.fragment_list, LAYOUT_FRAGMENTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.fragment_question_item, LAYOUT_FRAGMENTQUESTIONITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.home_bar, LAYOUT_HOMEBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.item_answer_question, LAYOUT_ITEMANSWERQUESTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.layout_home_bar, LAYOUT_LAYOUTHOMEBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.guyuan.dear.R.layout.layout_search_bar, LAYOUT_LAYOUTSEARCHBAR);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYBASENOTAB: {
          if ("layout/activity_base_no_tab_0".equals(tag)) {
            return new ActivityBaseNoTabBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_base_no_tab is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYBASETAB: {
          if ("layout/activity_base_tab_0".equals(tag)) {
            return new ActivityBaseTabBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_base_tab is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEXAM: {
          if ("layout/activity_exam_0".equals(tag)) {
            return new ActivityExamBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_exam is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_EMPTY: {
          if ("layout/empty_0".equals(tag)) {
            return new EmptyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for empty is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLIST: {
          if ("layout/fragment_list_0".equals(tag)) {
            return new FragmentListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_list is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTQUESTIONITEM: {
          if ("layout/fragment_question_item_0".equals(tag)) {
            return new FragmentQuestionItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_question_item is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEBAR: {
          if ("layout/home_bar_0".equals(tag)) {
            return new HomeBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_bar is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMANSWERQUESTION: {
          if ("layout/item_answer_question_0".equals(tag)) {
            return new ItemAnswerQuestionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_answer_question is invalid. Received: " + tag);
        }
        case  LAYOUT_LAYOUTHOMEBAR: {
          if ("layout/layout_home_bar_0".equals(tag)) {
            return new LayoutHomeBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for layout_home_bar is invalid. Received: " + tag);
        }
        case  LAYOUT_LAYOUTSEARCHBAR: {
          if ("layout/layout_search_bar_0".equals(tag)) {
            return new LayoutSearchBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for layout_search_bar is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(2);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new com.example.mvvmlibrary.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(11);

    static {
      sKeys.put("layout/activity_base_no_tab_0", com.guyuan.dear.R.layout.activity_base_no_tab);
      sKeys.put("layout/activity_base_tab_0", com.guyuan.dear.R.layout.activity_base_tab);
      sKeys.put("layout/activity_exam_0", com.guyuan.dear.R.layout.activity_exam);
      sKeys.put("layout/activity_main_0", com.guyuan.dear.R.layout.activity_main);
      sKeys.put("layout/empty_0", com.guyuan.dear.R.layout.empty);
      sKeys.put("layout/fragment_list_0", com.guyuan.dear.R.layout.fragment_list);
      sKeys.put("layout/fragment_question_item_0", com.guyuan.dear.R.layout.fragment_question_item);
      sKeys.put("layout/home_bar_0", com.guyuan.dear.R.layout.home_bar);
      sKeys.put("layout/item_answer_question_0", com.guyuan.dear.R.layout.item_answer_question);
      sKeys.put("layout/layout_home_bar_0", com.guyuan.dear.R.layout.layout_home_bar);
      sKeys.put("layout/layout_search_bar_0", com.guyuan.dear.R.layout.layout_search_bar);
    }
  }
}
