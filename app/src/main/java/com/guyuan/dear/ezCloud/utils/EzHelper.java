package com.guyuan.dear.ezCloud.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceRecordFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

/**
 * 萤石云SDK使用教程
 * https://open.ys7.com/doc/zh/book/index/address.html
 *
 * @author 廖华凯
 * @since 2020/3/2 10:53
 **/
public class EzHelper {
    public Matrix fixDisplay(int videoWidth, int videoHeight, int viewWidth, int viewHeight, int scaleMode) {
        Matrix matrix = new Matrix();

        float vdr = videoWidth * 1.0f / videoHeight;
        float vr = viewWidth * 1.0f / viewHeight;
        float scaleFactor = 1.f;
        //center crop效果
        switch (scaleMode) {
            case 1:
                //center crop
                //1，因为默认为fix xy效果，这里要先还原成原比例
                matrix.postScale(videoWidth * 1.0f / viewWidth, videoHeight * 1.0f / viewHeight);
                if (vr < vdr) {
                    scaleFactor = viewHeight * 1.0f / videoHeight;
                    videoWidth = (int) (videoHeight * scaleFactor + 0.5f);
                    videoHeight = viewHeight;
                } else if (vr > vdr) {
                    scaleFactor = viewWidth * 1.0f / videoWidth;
                    videoHeight = (int) (videoHeight * scaleFactor + 0.5f);
                    videoWidth = viewWidth;
                }
                //2,拉伸
                matrix.postScale(scaleFactor, scaleFactor);
                //3，居中
                matrix.postTranslate(-(videoWidth - viewWidth) * 1.0f / 2, -(videoHeight - viewHeight) * 1.0f / 2);
                break;
            case 2:
                //center inside
                //1，因为默认为fix xy效果，这里要先还原成原比例
                matrix.postScale(videoWidth * 1.0f / viewWidth, videoHeight * 1.0f / viewHeight);
                if (vr > vdr) {
                    scaleFactor = viewHeight * 1.0f / videoHeight;
                    videoWidth = (int) (videoWidth * scaleFactor + 0.5f);
                    videoHeight = viewHeight;
                } else if (vr < vdr) {
                    scaleFactor = viewWidth * 1.0f / videoWidth;
                    videoHeight = (int) (videoHeight * scaleFactor + 0.5f);
                    videoWidth = viewWidth;
                }
                //2,拉伸
                matrix.postScale(scaleFactor, scaleFactor);
                //3，居中
                matrix.postTranslate(-(videoWidth - viewWidth) * 1.0f / 2, -(videoHeight - viewHeight) * 1.0f / 2);
                break;
            default:
                //默认fix xy
                break;
        }
        return matrix;
    }

    boolean startRecordVideo(EZPlayer ezPlayer) {
        File tempFile = getTempVideoFile();
        if (tempFile.exists()) {
            tempFile.delete();
        }
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ezPlayer.startLocalRecordWithFile(tempFile.getAbsolutePath());
    }

    private File getTempVideoFile() {
        Context context = DearApplication.getInstance().getApplicationContext();
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_MOVIES), "tempVideo.mp4");
    }

    private Uri genDestVideoUri() {
        ContentValues contentValues = new ContentValues();
        String name = "萤石云录像" + CalenderUtils.getInstance().toSmartFactoryDateStringFormat(System.currentTimeMillis());
        contentValues.put(MediaStore.Video.Media.DISPLAY_NAME, name);
        contentValues.put(MediaStore.Video.Media.DESCRIPTION, name);
        contentValues.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        Context context = DearApplication.getInstance().getApplicationContext();
        return context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
    }


    boolean stopRecordVideo(EZPlayer ezPlayer) {
        if (!ezPlayer.stopLocalRecord()) {
            return false;
        }
        File tempFile = getTempVideoFile();
        if (!tempFile.exists()) {
            return false;
        }
        Uri uri = genDestVideoUri();
        Context context = DearApplication.getInstance().getApplicationContext();
        try {
            OutputStream fOut = context.getContentResolver().openOutputStream(uri);
            InputStream fIn = new FileInputStream(tempFile);
            copyFile(fIn, fOut);
            if (fOut != null) {
                fOut.close();
            }
            fIn.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    boolean capturePicture(EZPlayer ezPlayer) {
        Context context = DearApplication.getInstance().getApplicationContext();
        Uri destPath = genDestPicUri();
        try {
            OutputStream fOut = context.getContentResolver().openOutputStream(destPath);
            File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "tempPic.jpeg");
            if (tempFile.exists()) {
                tempFile.delete();
            }
            tempFile.createNewFile();
            ezPlayer.capturePicture(tempFile.getAbsolutePath());
            InputStream fin = new FileInputStream(tempFile);
            copyFile(fin, fOut);
            if (fOut != null) {
                fOut.close();
            }
            fin.close();
            tempFile.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Uri genDestPicUri() {
        ContentValues contentValues = new ContentValues();
        String name = "萤石云图片" + CalenderUtils.getInstance().toSmartFactoryDateStringFormat(System.currentTimeMillis());
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name);
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, name);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Context context = DearApplication.getInstance().getApplicationContext();
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    private void copyFile(InputStream from, OutputStream to) {
        byte[] tempBytes = new byte[1024];
        int len = -1;
        try {
            while ((len = from.read(tempBytes)) > 0) {
                to.write(tempBytes, 0, len);
            }
        } catch (IOException e) {
            LogUtils.showLog(e.getMessage());
        }
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * 获取网络回播资源的起始时间和终止时间；萤石云提供的对外接口给出的时间不准确，需要通过
     * 反射获取真实的时间。
     * @param file
     * @return
     */
    public long[] getStartTimeAndEndTime(EZDeviceRecordFile file) {
        long[] times = new long[2];
        try {
            Class<?> cls = Class.forName("com.videogo.openapi.bean.EZDeviceRecordFile");
            Field fBegin = cls.getDeclaredField("begin");
            Field fEnd = cls.getDeclaredField("end");
            fBegin.setAccessible(true);
            fEnd.setAccessible(true);
            String begin = (String) fBegin.get(file);
            String end = (String) fEnd.get(file);
            long startTime = simpleDateFormat.parse(begin).getTime();
            long endTime = simpleDateFormat.parse(end).getTime();
            times[0] = startTime;
            times[1] = endTime;
        } catch (Exception e) {
            times[0] = 0;
            times[1] = 0;
        }
        return times;
    }

}
