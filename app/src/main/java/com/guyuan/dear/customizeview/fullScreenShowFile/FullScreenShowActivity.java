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
    private List<String> urlList;
    private List<Fragment> fragments;
    private int startPosition;

    public static void start(Context context, List<String> urlList) {
        Intent starter = new Intent(context, FullScreenShowActivity.class);
        starter.putStringArrayListExtra(URL, new ArrayList<>(urlList));
        context.startActivity(starter);
    }


    public static void start(Context context, List<String> urlList, int position) {
        Intent starter = new Intent(context, FullScreenShowActivity.class);
        starter.putStringArrayListExtra(URL, new ArrayList<>(urlList));
        starter.putExtra(POSITION, position);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        List<String> urls = getIntent().getStringArrayListExtra(URL);
        startPosition = getIntent().getIntExtra(POSITION, 0);
        if (urls != null&&urls.size()>0) {
            urlList = urls;
            binding.pageTv.setText(startPosition + 1 + "/" + urlList.size());

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
            binding.fullScreenVp.setOffscreenPageLimit(2);
            binding.fullScreenVp.setAdapter(fileAdapter);
            startPosition = Math.min(startPosition, fragments.size() - 1);
            binding.fullScreenVp.setCurrentItem(startPosition);
        }


        binding.fullScreenVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.pageTv.setText(position + 1 + "/" + urlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.backIv.setOnClickListener(new View.OnClickListener() {
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
