package com.guyuan.dear.home;


import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.databinding.ActivityMainBinding;
import com.guyuan.dear.home.data.MainViewModel;
import com.guyuan.dear.service.BackTaskType;
import com.guyuan.dear.test.ExamActivity;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: APP主页面
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 * @company: 固远（深圳）信息技术有限公司
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
                ExamActivity.start(MainActivity.this,"试题一");
            }
        });

    }


}