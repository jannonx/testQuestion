package com.guyuan.dear.mine.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.BuildConfig;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentAboutUsBinding;
import com.guyuan.dear.mine.data.MineViewModel;
import com.tencent.bugly.beta.Beta;

/**
 * @description: 我的--关于我们
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class AboutUsFragment extends BaseDataBindingFragment<FragmentAboutUsBinding, MineViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static AboutUsFragment newInstance() {
        Bundle args = new Bundle();
        AboutUsFragment fragment = new AboutUsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_about_us;
    }

    @Override
    protected void initialization() {
        binding.tvVersion.setText("版本号：" + BuildConfig.BUILD_TYPE + "-" + BuildConfig.VERSION_NAME);
        binding.updateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.checkUpgrade();
            }
        });
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
