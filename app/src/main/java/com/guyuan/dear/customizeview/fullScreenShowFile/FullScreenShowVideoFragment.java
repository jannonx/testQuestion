package com.guyuan.dear.customizeview.fullScreenShowFile;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFullScreenVideoBinding;

import java.util.HashMap;

import static android.media.MediaMetadataRetriever.OPTION_CLOSEST_SYNC;

/**
 * created by tl
 * created at 2020/6/17
 */
public class FullScreenShowVideoFragment extends BaseDataBindingFragment<FragmentFullScreenVideoBinding, BaseViewModel> {

    public static final String TAG = "FullScreenShowVideoFrag";
    public static final String URL = "url";
    private long currentTime;//当前视频播放位置
    private MediaController mediaController;
    private boolean isPrepared = false; //是否已经准备好播放资源
    private String url;

    public static FullScreenShowVideoFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(URL, url);
        FullScreenShowVideoFragment fragment = new FullScreenShowVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_full_screen_video;
    }

    private void setUrl() {
        if (getArguments() != null) {
            url = getArguments().getString(URL);
            if (url.contains("storage")) {
                binding.fullScreenVv.setVideoPath(url);
            } else {
                showLoadingWithStatus(childFragmentManager, "视频加载中...");
                binding.fullScreenVv.setVideoURI(Uri.parse(url));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseVideo();
    }


    @Override
    protected void initialization() {
        setUrl();
        //创建MediaController对象
        mediaController = new MediaController(getContext());
        mediaController.setAnchorView(binding.fullScreenVv);
        //VideoView与MediaController建立关联
        binding.fullScreenVv.setMediaController(mediaController);

        //视频装载监听
        binding.fullScreenVv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                hideLoading();
                isPrepared = true;
                setVideoPic();
                binding.firstPicFl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.firstPicFl.setVisibility(View.GONE);
                        binding.fullScreenVv.start();
                    }
                });
            }
        });


        //视频缓冲监听
        binding.fullScreenVv.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                switch (i) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        showLoadingWithStatus(childFragmentManager, "缓冲中...");
                        break;

                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        hideLoading();
                        break;
                }

                return false;
            }
        });

        //视频错误监听
        binding.fullScreenVv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                hideLoading();
                return false;
            }
        });
    }

    public void playVideo() {
        if (binding.fullScreenVv != null) {
            binding.firstPicFl.setVisibility(View.GONE);
            binding.fullScreenVv.requestFocus();
            binding.fullScreenVv.start();
        }
    }


    public void pauseVideo() {
        if (binding.fullScreenVv != null && isPrepared) {
            binding.fullScreenVv.pause();
            currentTime = binding.fullScreenVv.getCurrentPosition() * 1000;
            setVideoPic();
        }
    }


    //设置视频封面
    private void setVideoPic() {
        binding.firstPicFl.setVisibility(View.VISIBLE);
        if (url.contains("storage")) {
            binding.firstPicIv.setImageBitmap(getLocalVideoBitmap(url, currentTime));
        } else {
            binding.firstPicIv.setImageBitmap(getNetVideoBitmap(url, currentTime));
        }
    }

    /**
     * 获取网络视频帧画面
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl, long time) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得当前时间帧图片
            bitmap = retriever.getFrameAtTime(time, OPTION_CLOSEST_SYNC);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    /**
     * 获取本地视频的帧画面
     *
     * @param localPath
     * @return
     */
    public static Bitmap getLocalVideoBitmap(String localPath, long time) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(localPath);
            //获得当前时间帧图片
            bitmap = retriever.getFrameAtTime(time, OPTION_CLOSEST_SYNC);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
