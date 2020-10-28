package com.guyuan.dear.focus.contract.view.zoomView;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityZoomViewBinding;
import com.guyuan.dear.focus.contract.adapter.ZoomViewPagerAdapter;
import com.guyuan.dear.focus.contract.bean.ImageSource;
import com.guyuan.dear.utils.ConstantValue;

import java.util.ArrayList;

/**
 * 首次使用我的关注-销售-合同概况-合同详情-客户签收照片-点击放大
 * @author 廖华凯
 */
public class ZoomViewActivity extends BaseToolbarActivity<ActivityZoomViewBinding,ZoomViewViewModel> {

    private ArrayList<ImageSource> list;

    public static void start(Context context, String title,ArrayList<ImageSource> sources,int displayIndex) {
        Intent starter = new Intent(context, ZoomViewActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE,title);
        starter.putParcelableArrayListExtra(ConstantValue.KEY_IMG_SRC,sources);
        starter.putExtra(ConstantValue.KEY_DISPLAY_INDEX,displayIndex);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_zoom_view;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        list = intent.getParcelableArrayListExtra(ConstantValue.KEY_IMG_SRC);
        int displayIndex = intent.getIntExtra(ConstantValue.KEY_DISPLAY_INDEX,0);
        ZoomViewPagerAdapter adapter = new ZoomViewPagerAdapter(getSupportFragmentManager(),getLifecycle(),list);
        ViewPager2 vp = binding.activityZoomViewVp;
        vp.setOffscreenPageLimit(2);
        vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp.setAdapter(adapter);
        vp.setCurrentItem(displayIndex);
    }

    @Override
    public void viewModuleCallBack(Object o) {

    }
}