package com.guyuan.dear.focus.qc.views.home;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的关注-质检-首页
 * @author 廖华凯
 * @date 2020/11/11
 */
public class QcHomeActivity extends BaseTabActivity<ActivityBaseTabBinding, QcHomeViewModel> {

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, QcHomeActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(starter);
    }


    @Override
    protected List<String> getTitles() {
        return new ArrayList<String>(){
            {
                add("质检概况");
            }
        };
    }

    @Override
    protected List<Fragment> getFragments() {
        return null;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);

    }

    @Override
    protected List<Integer> setTabIconList() {
        List<Integer> tabDrawableList = new ArrayList<>();
        tabDrawableList.add(R.drawable.tab_common_icon_selector);
        return tabDrawableList;
    }
}