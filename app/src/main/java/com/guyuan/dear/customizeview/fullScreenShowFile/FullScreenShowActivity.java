package com.guyuan.dear.customizeview.fullScreenShowFile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.util.MediaFileUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.ActivityImageviewBinding;
import com.guyuan.dear.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tl
 * created at 2020/4/14
 */
public class FullScreenShowActivity extends BaseNoToolbarActivity<ActivityImageviewBinding, BaseViewModel> {

    private static final String URL = "url";
    private static final String POSITION = "position";
    private ViewPager full_screen_vp = binding.fullScreenVp;
    private ImageView back_iv = binding.backIv;
    private TextView page_tv = binding.pageTv;
    private List<String> urlList;
    private List<Fragment> fragments;
    private int startPosition;

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, FullScreenShowActivity.class);
        starter.putExtra(URL, url);
        context.startActivity(starter);
    }


    public static void start(Context context, String url, int position) {
        Intent starter = new Intent(context, FullScreenShowActivity.class);
        starter.putExtra(URL, url);
        starter.putExtra(POSITION, position);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        String urls = getIntent().getStringExtra(URL);
        startPosition = getIntent().getIntExtra(POSITION, 0);
        if (urls != null) {
            urlList = StringUtils.splitPhotoUrl(urls);
            page_tv.setText(startPosition + 1 + "/" + urlList.size());

            for (String url : urlList) {
                if (MediaFileUtils.isImageFileType(url) || !url.contains(".")) {
                    FullScreenImageFragment imageFragment = FullScreenImageFragment.newInstance(url);
                    fragments.add(imageFragment);
                } else if (MediaFileUtils.isVideoFileType(url)) {
                    FullScreenShowVideoFragment videoFragment = FullScreenShowVideoFragment.newInstance(url);
                    fragments.add(videoFragment);
                }
            }


            FullScreenFileAdapter fileAdapter = new FullScreenFileAdapter(getSupportFragmentManager(),
                    fragments, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            full_screen_vp.setOffscreenPageLimit(2);
            full_screen_vp.setAdapter(fileAdapter);
            startPosition = Math.min(startPosition, fragments.size() - 1);
            full_screen_vp.setCurrentItem(startPosition);
        }


        full_screen_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page_tv.setText(position + 1 + "/" + urlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.activity_imageview;
    }
}
