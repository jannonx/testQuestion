package com.guyuan.dear.ezCloud.views;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.TextureView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentEzPlaybackBinding;
import com.guyuan.dear.ezCloud.utils.EzHelper;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;
import com.videogo.errorlayer.ErrorInfo;
import com.videogo.openapi.EZConstants;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;

import io.reactivex.disposables.Disposable;

/**
 * 萤石云视频直播以及提供回看列表
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 16:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class EzPlayBackFragment extends BaseMvvmFragment<FragmentEzPlaybackBinding, EzPlayBackViewModel> {
    private String serialId;
    private int channelId;
    private EZPlayer ezPlayer;
    private EzHelper ezHelper;
    private int playState;
    private static final int PLAY_STATE_REAL_PLAYING =1;
    private static final int PLAY_STATE_PLAY_BACK=2;

    public static EzPlayBackFragment getInstance(String serialId, int channelId) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.KEY_DEVICE_SERIAL_NUMBER, serialId);
        bundle.putInt(ConstantValue.KEY_CAMERA_CHANNEL_ID, channelId);
        EzPlayBackFragment fragment = new EzPlayBackFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_ez_playback_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        serialId = bundle.getString(ConstantValue.KEY_DEVICE_SERIAL_NUMBER);
        channelId = bundle.getInt(ConstantValue.KEY_CAMERA_CHANNEL_ID);
        ezHelper = new EzHelper();

        getViewModel().updatePlaybackFiles(serialId,channelId,0,System.currentTimeMillis());
    }

    @Override
    protected void initViews() {
        TextureView textureView = getViewDataBinding().fragmentEzPlaybackTextureView;
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                ezPlayer = EZOpenSDK.getInstance().createPlayer(serialId, channelId);
                ezPlayer.setSurfaceEx(textureView.getSurfaceTexture());
                ezPlayer.setHandler(mHandler);
                ezPlayer.startRealPlay();
                playState = PLAY_STATE_REAL_PLAYING;
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if(ezPlayer!=null){
            switch (playState){
                case PLAY_STATE_PLAY_BACK:
                    ezPlayer.pausePlayback();
                    break;
                case PLAY_STATE_REAL_PLAYING:
                    ezPlayer.stopRealPlay();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ezPlayer!=null){
            switch (playState){
                case PLAY_STATE_PLAY_BACK:
                    ezPlayer.resumePlayback();
                    break;
                case PLAY_STATE_REAL_PLAYING:
                    ezPlayer.startRealPlay();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ezPlayer != null) {
            ezPlayer.closeSound();
            ezPlayer.setSurfaceEx(null);
            ezPlayer.release();
            ezPlayer = null;
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }


    @Override
    protected void initListeners() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_ez_playback;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_FAIL:
                    //tvHint.setVisibility(View.GONE);
                    //播放失败,得到失败信息
                    ErrorInfo errorinfo = (ErrorInfo) msg.obj;
                    //得到播放失败错误码
                    int code = errorinfo.errorCode;
                    //得到播放失败模块错误码
                    String codeStr = errorinfo.moduleCode;
                    //得到播放失败描述
                    String description = errorinfo.description;
                    //得到播放失败解决方方案
                    String solution = errorinfo.sulution;
                    StringBuilder sb = new StringBuilder();
                    sb.append("errorCode:").append(code)
                            .append(" moduleCode:").append(codeStr)
                            .append(" description:").append(description)
                            .append(" solution:").append(solution);
                    LogUtils.showLog(sb.toString());
                    break;
                case EZConstants.MSG_VIDEO_SIZE_CHANGED:
                    //播放成功
                    ezPlayer.openSound();
                    LogUtils.showLog("回播成功");

                    //解析出视频画面分辨率回调
                    try {
                        LogUtils.showLog("MSG_VIDEO_SIZE_CHANGED");
                        String temp = (String) msg.obj;
                        String[] strings = temp.split(":");
                        int width = Integer.parseInt(strings[0]);
                        int height = Integer.parseInt(strings[1]);
                        fixDisplay(width, height);
                    } catch (Exception e) {
                        LogUtils.showLog(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void fixDisplay(int videoWidth, int videoHeight) {
        TextureView textureView = getViewDataBinding().fragmentEzPlaybackTextureView;
        Matrix matrix = ezHelper.fixDisplay(videoWidth, videoHeight, textureView.getWidth(), textureView.getHeight(), 2);
        textureView.setTransform(matrix);
        textureView.postInvalidate();
    }
}
