package com.jannonx.electric.home;


import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.jannonx.electric.R;
import com.jannonx.electric.base.app.DearApplication;
import com.jannonx.electric.databinding.ActivityMainBinding;
import com.jannonx.electric.home.data.MainViewModel;
import com.jannonx.electric.service.BackTaskType;
import com.jannonx.electric.test.activity.ExamActivity;
import com.jannonx.electric.test.bean.ExamConditionBean;
import com.jannonx.electric.test.bean.ExamFunctionType;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
@AndroidEntryPoint
public class MainActivity extends BaseNoToolbarActivity<ActivityMainBinding, MainViewModel> {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        );
        DearApplication.getInstance().startBackService(BackTaskType.TYPE_WR_DB, null);
        binding.tvSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ExamActivity.start(MainActivity.this, "试题一",
                        new ExamConditionBean(0, ExamFunctionType.TYPE_EXAM));
            }
        });

    }


}