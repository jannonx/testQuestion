package com.guyuan.dear.welcome;

import android.Manifest;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.mvvmlibrary.base.activity.BaseActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.home.MainActivity;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.login.ui.LoginActivity;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/9 14:32
 * @company : 固远（深圳）信息技术有限公司
 **/
public class WelcomeActivity extends BaseActivity {

    private boolean isPlayVideo = true;
    private TextView jump_tv;
    private VideoView welcome_vv;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setFullScreen();
        if (isPlayVideo) {
            setVideo();
            DearApplication.getInstance().saveCacheData(ConstantValue.FIRST_OPEN, false);
        }
        checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        //        ,Manifest.permission.RECORD_AUDIO
        );
    }



    @Override
    public void onAllPermissionsGranted() {
        super.onAllPermissionsGranted();
        if (isPlayVideo) {
            jump_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    welcome_vv.stopPlayback();
                    updateMenuOrLogin();
                }
            });

            welcome_vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    updateMenuOrLogin();
                }
            });
        } else {
            updateMenuOrLogin();
        }
    }

    //判断登录或者更新首页菜单
    private void updateMenuOrLogin() {
        LoginBean loginBean = CommonUtils.getLoginInfo();
        if (loginBean != null && loginBean.getAppMenus() != null && loginBean.getAppMenus().size() > 0) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.putExtra(MainActivity.OPEN_TYPE, MainActivity.COMMON);
            startActivity(intent);
        } else {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        }
        finish();
    }



    private void setVideo() {
        //加载指定的视频文件
        AssetManager assetManager = this.getAssets();
        String welcomeVideoPath = "android.resource://" + getPackageName() + "/" + R.raw.handlein;
        jump_tv = findViewById(R.id.jump_tv);
        welcome_vv = findViewById(R.id.welcome_vv);
        welcome_vv.setVideoPath(welcomeVideoPath);

        ////创建MediaController对象
        //MediaController mediaController = new MediaController(this);
        //
        //VideoView与MediaController建立关联
        //welcome_vv.setMediaController(mediaController);

        //让VideoView获取焦点
        welcome_vv.requestFocus();

        welcome_vv.start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
