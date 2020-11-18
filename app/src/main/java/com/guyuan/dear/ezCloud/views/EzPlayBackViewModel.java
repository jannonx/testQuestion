package com.guyuan.dear.ezCloud.views;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.ezCloud.repo.EzRepo;
import com.guyuan.dear.utils.ToastUtils;
import com.videogo.openapi.bean.EZDeviceRecordFile;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 16:06
 * @company: 固远（深圳）信息技术有限公司
 **/
public class EzPlayBackViewModel extends BaseViewModel {
    private EzRepo repo = new EzRepo();
    /**
     * 播放进度
     */
    public MutableLiveData<Integer> playProgress = new MutableLiveData<>(0);
    /**
     * 播放列表
     */
    public MutableLiveData<List<EZDeviceRecordFile>> playBackFiles = new MutableLiveData<>(new ArrayList<>());
    /**
     * 是否显示加载历史列表
     */
    public MutableLiveData<Boolean> isLoadingPlaybackFiles = new MutableLiveData<>(true);
    /**
     * 是否显示加载视频页面中
     */
    public MutableLiveData<Boolean> isPlayerLoading = new MutableLiveData<>(true);
    public MutableLiveData<OnItemClickListener> onClickPlaybackListItem = new MutableLiveData<>();


    /**
     * 加载摄像头SD卡上的录像
     * @param serialNumber
     * @param channelId
     * @param from
     * @param to
     */
    public void updatePlaybackFiles(String serialNumber, int channelId, long from, long to) {
        isLoadingPlaybackFiles.postValue(true);
        repo.getPlayBackListFromLocalFile(serialNumber, channelId, from, to, new EzRepo.GetLocalPlayBackFilesCallback() {
            @Override
            public void onGetLocalFiles(List<EZDeviceRecordFile> list) {
                playBackFiles.postValue(list);
                isLoadingPlaybackFiles.postValue(false);
            }

            @Override
            public void onFailure(Exception error) {
                isLoadingPlaybackFiles.postValue(false);
                ToastUtils.showShort(DearApplication.getInstance(),error.getMessage());

            }
        });

    }
}
