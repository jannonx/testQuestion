package com.jannonx.electric.base.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.jannonx.electric.R;
import com.jannonx.electric.base.adapter.TabAdapter;

import java.util.List;


/**
 * @description: 没有顶部导航栏activity基类
 * @author: Jannonx
 * @since: 2021/1/21 20:57
 */
public abstract class BaseNoTabActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFileUploadActivity<V, VM> {

    protected ProgressBar progressBar;
//    protected AppCompatTextView tvIndex;
    protected ViewPager2 viewPager;

    /**
     * 起始选中位置
     */
    private int startPosition;

    /**
     * tab图标集合
     */
    private List<Integer> tabIconList;
    /**
     * tab文本集合
     */
    private List<String> titleList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_base_no_tab;
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initData();
//        titleList = getTitles();
//        List<Fragment> fragmentList = getFragments();
//        tabIconList = setTabIconList();

//        new TabLayoutHelper(this, tlBase, vpBase, fragmentList, TabLayoutHelper.COMMON)
//                .setTab()
//                .setStartPosition(startPosition)
//                .setListener(this)
//                .setCustomView();
        initListener();
    }

    private void initView() {
        progressBar = findViewById(R.id.progress_bar);
//        tvIndex = findViewById(R.id.tv_index);
        viewPager = findViewById(R.id.base_vp);
    }

    private void initListener() {

    }


//    @Override
//    public void setCustomContent(View customView, int currentPosition) {
//        ImageView iv = customView.findViewById(R.id.tab_common_iv);
//        TextView tv = customView.findViewById(R.id.tab_common_tv);
//        LogUtils.showLog("setCustomContent=" + currentPosition);
//        iv.setImageResource(tabIconList.get(currentPosition));
//        tv.setText(titleList.get(currentPosition));
//    }

//    /**
//     * 设置头部Tab文本集合
//     *
//     * @return 头部Tab文本集合
//     */
//    protected abstract List<String> getTitles();

    /**
     * 设置内容fragment集合
     *
     * @return 内容fragment集合
     */
    public void setFragmentList(List<Fragment> fragmentList) {
        viewPager.setUserInputEnabled(true);
        progressBar.setMax(100);
        progressBar.setProgress((int) ((1.0 / fragmentList.size()) * 100));
        TabAdapter tabAdapter = new TabAdapter(this, fragmentList);
        viewPager.setAdapter(tabAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                progressBar.setProgress((int) (((position + 1.0) / fragmentList.size()) * 100));
//                tvIndex.setText((position + 1) + "/" + fragmentList.size());
            }
        });
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

//    /**
//     * 设置头部Tab图标集合
//     *
//     * @return 头部Tab图标集合
//     */
//    protected abstract List<Integer> setTabIconList();


    /**
     * 设置起始位置
     *
     * @param position 位置下标
     */
    protected void setStartPosition(int position) {
        startPosition = position;
    }


    //设置tabLayout左对齐
//  protected void setTabLayoutToLeft() {
//    LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) base_tl.getLayoutParams();
//    ll.gravity = Gravity.START;
//    base_tl.setLayoutParams(ll);
//  }

}
