package com.guyuan.dear.base.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;


import com.example.mvvmlibrary.base.activity.BaseToolbarActivity;
import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.example.mvvmlibrary.util.LogUtils;
import com.example.mvvmlibrary.util.MediaFileUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ImgCompressor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by TL
 * on 2020/1/17
 */
public abstract class BaseFileUploadActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseToolbarActivity<V, VM>
        implements ImgCompressor.CompressListener {

    public static final int FIRST = 0x0001;
    public static final int SECOND = 0x0002;
    public static final int THIRD = 0x0003;
    public static final int FOURTH = 0x0004;
    public static final int FIFTH = 0x0005;
    private int maxSelectImageNumber = 5;
    public static final int TYPE_UP_LOAD_IMAGE = 0x100;//上传类型：图片
    public static final int TYPE_UP_LOAD_IMAGE_VIDEO = 101;//上传类型:图片和视频

    private ArrayList<String> firstPhotoList = new ArrayList<>(), secondPhotoList =
            new ArrayList<>(),
            thirdPhotoList = new ArrayList<>(), fourthPhotoList = new ArrayList<>(),
            fifthPhotoList = new ArrayList<>();

    private ArrayList<String> firstFileList = new ArrayList<>(), secondFileList = new ArrayList<>(),
            thirdFileList = new ArrayList<>(), fourthFileList = new ArrayList<>(),
            fifthFileList = new ArrayList<>();

    protected PhotoSelectListener firstPhotoListener, secondPhotoListener, thirdPhotoListener,
            fourthPhotoListener,
            fifthPhotoListener;

    protected FileSelectListener firstFileListener, secondFileListener, thirdFileListener,
            fourthFileListener,
            fifthFileListener;

    protected int currentFileType; //当前打开的文件界面

    protected int currentPhotoType;//当前打开的相册界面

    protected int typeUpLoad;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    ArrayList<String> photoPaths =
                            data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                    if (photoPaths != null) {
                        if (currentPhotoType == FIRST && firstPhotoListener != null) {
                            firstPhotoList.clear();
                            firstPhotoList.addAll(photoPaths);
                            firstPhotoListener.onPhotoSelected(firstPhotoList);
                        } else if (currentPhotoType == SECOND && secondPhotoListener != null) {
                            secondPhotoList.clear();
                            secondPhotoList.addAll(photoPaths);
                            secondPhotoListener.onPhotoSelected(secondPhotoList);
                        } else if (currentPhotoType == THIRD && thirdPhotoListener != null) {
                            thirdPhotoList.clear();
                            thirdPhotoList.addAll(photoPaths);
                            thirdPhotoListener.onPhotoSelected(thirdPhotoList);
                        } else if (currentPhotoType == FOURTH && fourthPhotoListener != null) {
                            fourthPhotoList.clear();
                            fourthPhotoList.addAll(photoPaths);
                            fourthPhotoListener.onPhotoSelected(fourthPhotoList);
                        } else if (currentPhotoType == FIFTH && fifthPhotoListener != null) {
                            fifthPhotoList.clear();
                            fifthPhotoList.addAll(photoPaths);
                            fifthPhotoListener.onPhotoSelected(fifthPhotoList);
                        }
                    }
                }

                break;
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    ArrayList<String> filePaths =
                            data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS);
                    if (filePaths != null && filePaths.size() > 0) {
                        if (currentFileType == FIRST && firstFileListener != null) {
                            firstFileList.addAll(filePaths);
                            firstFileListener.onFileSelected(firstFileList);
                        } else if (currentFileType == SECOND && secondFileListener != null) {
                            secondFileList.addAll(filePaths);
                            secondFileListener.onFileSelected(secondFileList);
                        } else if (currentFileType == THIRD && thirdFileListener != null) {
                            thirdFileList.addAll(filePaths);
                            thirdFileListener.onFileSelected(thirdFileList);
                        } else if (currentFileType == FOURTH && fourthFileListener != null) {
                            fourthFileList.addAll(filePaths);
                            fourthFileListener.onFileSelected(fourthFileList);
                        } else if (currentFileType == FIFTH && fifthFileListener != null) {
                            fifthFileList.addAll(filePaths);
                            fifthFileListener.onFileSelected(fifthFileList);
                        }
                    }
                }
                break;
            default:
        }
    }

    @Override
    public void onAllPermissionsGranted() {
        super.onAllPermissionsGranted();
        ArrayList<String> currentPhotoList = getCurrentPhotoList(currentPhotoType);
        if (currentPhotoList != null) {
            FilePickerBuilder.getInstance().setMaxCount(maxSelectImageNumber)
                    .setSelectedFiles(currentPhotoList)
                    .enableVideoPicker(true)
                    .setActivityTheme(R.style.LibAppTheme)
                    .pickPhoto(this);
        }
    }

    //打开相册
    public void openAlbum(int type) {
        checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
        currentPhotoType = type;
    }

    //打开文件
    public void openFile(int type) {
        checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
        currentFileType = type;
        ArrayList<String> currentFileList = getCurrentFileList(currentFileType);
        if (currentFileList != null) {
            FilePickerBuilder.getInstance().setMaxCount(1)
                    .setSelectedFiles(currentFileList)
                    .setActivityTheme(R.style.LibAppTheme)
                    .pickFile(this);
        }
    }

    /**
     * 打开相册，获得单张照片
     *
     * @param type
     */
    public void pickSinglePhoto(int type) {
        maxSelectImageNumber = 1;
        checkPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
        currentFileType = type;
    }

    private ArrayList<String> getCurrentPhotoList(int photoType) {
        switch (photoType) {
            case FIRST:
                return firstPhotoListener.getSelectedMediaList();

            case SECOND:
                return secondPhotoListener.getSelectedMediaList();

            case THIRD:
                return thirdPhotoListener.getSelectedMediaList();

            case FOURTH:
                return fourthPhotoListener.getSelectedMediaList();
            case FIFTH:
                return fifthPhotoListener.getSelectedMediaList();

            default:
                return null;
        }
    }

    private ArrayList<String> getCurrentFileList(int fileType) {
        switch (fileType) {
            case FIRST:
                firstFileList.clear();
                return firstFileListener.getSelectedFileList();

            case SECOND:
                secondFileList.clear();
                return secondFileListener.getSelectedFileList();

            case THIRD:
                thirdFileList.clear();
                return thirdFileListener.getSelectedFileList();

            case FOURTH:
                fourthFileList.clear();
                return fourthFileListener.getSelectedFileList();
            case FIFTH:
                fifthFileList.clear();
                return firstFileListener.getSelectedFileList();

            default:
                return null;
        }
    }

    public void compressPhoto(List<String> paths) {
        ImgCompressor.getInstance(this)
                .withListener(this)
                .starCompressWithDefault(paths);
        showLoadingWithStatus(fragmentManager, "图片压缩中...");
    }


    //图片和视频文件集合
    List<File> photoAndVideoFileList = new ArrayList<>();

    //检查视频文件大小和压缩压缩
    public void checkPhotoAndFileUpLoad(List<String> pathList) {
        photoAndVideoFileList.clear();
        //视频集合
        List<String> videoArr = new ArrayList<>();
        //图片集合
        List<String> imageArr = new ArrayList<>();
        for (String path : pathList) {
            if (MediaFileUtils.isVideoFileType(path)) {
                videoArr.add(path);
            } else {
                imageArr.add(path);
            }
        }
        if (!videoArr.isEmpty()) {
            for (String filePath : videoArr) {
                File file = new File(filePath);
                if (file.length() >= 1024 * 1024 * 50) {
                    showToastTip("视频文件太大无法上传");
                    return;
                }
                photoAndVideoFileList.add(file);
            }
        }

        if (getFileSize(photoAndVideoFileList) >= 1024 * 1024 * 100) {
            showToastTip("视频文件太大无法上传");
            return;
        }

        LogUtils.showLog("checkPhotoAndFileUpLoad..11111");
        compressPhoto(imageArr);
    }

    //获取上传文件大小
    private long getFileSize(List<File> files) {
        long size = 0;
        for (File file : files) {
            size = +file.length();
        }

        return size;
    }


    /**
     * 文件上传
     */
    protected Map<String, RequestBody> richFilesToMaps(List<File> files) {
        Map<String, RequestBody> map = new HashMap<>();
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(
                    MediaFileUtils.isImageFileType(file.getPath()) ? "image/png" : "multipart/form-data"), file);
            map.put("file\"; filename=\"" + file.getName(), requestBody);
        }
        return map;
    }


    @Override
    public void onCompressSuccess(List<ImgCompressor.CompressResult> compressResultList) {
        hideLoading();
        LogUtils.showLog("onCompressSuccess..11111");
        for (int i = 0; i < compressResultList.size(); i++) {
            ImgCompressor.CompressResult imageOutPath = compressResultList.get(i);
            if (imageOutPath.getStatus() == ImgCompressor.CompressResult.RESULT_ERROR)//压缩失败
            {
                break;
            }
            File file = new File(imageOutPath.getOutPath());
            photoAndVideoFileList.add(file);
        }
        upLoadPicAndVideo(currentPhotoType, richFilesToMaps(photoAndVideoFileList));
    }


    public interface PhotoSelectListener {
        //获取已选中的媒体路径
        ArrayList<String> getSelectedMediaList();

        void onPhotoSelected(ArrayList<String> photoList);
    }

    public interface FileSelectListener {
        //获取已选中的文件路径
        ArrayList<String> getSelectedFileList();

        void onFileSelected(ArrayList<String> fileList);
    }

    public void setCurrentPhotoType(int type) {
        currentPhotoType = type;
    }

    public void setCurrentFileType(int type) {
        currentFileType = type;
    }

    protected void setFirstPhotoListener(PhotoSelectListener listener) {
        this.firstPhotoListener = listener;
    }

    protected void setSecondPhotoListener(PhotoSelectListener listener) {
        this.secondPhotoListener = listener;
    }

    protected void setThirdPhotoListener(PhotoSelectListener listener) {
        this.thirdPhotoListener = listener;
    }

    protected void setFourthPhotoListener(PhotoSelectListener listener) {
        this.fourthPhotoListener = listener;
    }

    protected void setFifthPhotoListener(PhotoSelectListener listener) {
        this.fifthPhotoListener = listener;
    }

    protected void setFirstFileListener(FileSelectListener listener) {
        this.firstFileListener = listener;
    }

    protected void setSecondFileListener(FileSelectListener listener) {
        this.secondFileListener = listener;
    }

    protected void setThirdFileListener(FileSelectListener listener) {
        this.thirdFileListener = listener;
    }

    protected void setFourthFileListener(FileSelectListener listener) {
        this.fourthFileListener = listener;
    }

    protected void setFifthFileListener(FileSelectListener listener) {
        this.fifthFileListener = listener;
    }


    //需要上传文件在子类重写该方法,同时上传图片和视频
    protected void upLoadPicAndVideo(int currentType, Map<String, RequestBody> fileMap) {

    }

}
