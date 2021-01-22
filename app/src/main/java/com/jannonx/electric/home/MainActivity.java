package com.jannonx.electric.home;


import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActivity;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
//@AndroidEntryPoint
public class MainActivity  extends BaseActivity{
    @Override
    protected int getLayoutID() {
        return 0;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


//        extends BaseNoToolbarActivity<ActivityMainBinding, MainViewModel> {
//
//
//    @Override
//    protected int getLayoutID() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    protected void initFragment(Bundle savedInstanceState) {
//        checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//        );
//        DearApplication.getInstance().startBackService(BackTaskType.TYPE_WR_DB, null);
//        binding.tvSelect.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                ExamActivity.start(MainActivity.this,"试题一");
//            }
//        });
//
//    }
//

}