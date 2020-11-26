package com.guyuan.dear.office.clockIn.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.net.resultBeans.NetClockInConfig;
import com.guyuan.dear.office.clockIn.repo.ClockInRepo;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.LogUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Locale;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 10:34
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInViewModel extends BaseDearViewModel {
    private ClockInRepo repo = new ClockInRepo();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> dept = new MutableLiveData<>();
    public MutableLiveData<String> imgUrl = new MutableLiveData<>();
    public MutableLiveData<Long> companyStartWorkTime = new MutableLiveData<>();
    public MutableLiveData<Long> companyOffWorkTime = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPunchStartWork = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isPunchOffWork = new MutableLiveData<>(false);
    public MutableLiveData<Long> myClockInTime = new MutableLiveData<>();
    public MutableLiveData<Long> myPunchOffTime = new MutableLiveData<>();
    public MutableLiveData<Long> currentTime = new MutableLiveData<>();
    public MutableLiveData<Boolean> isInClockInArea = new MutableLiveData<>(false);
    public MutableLiveData<String> currentLocation = new MutableLiveData<>();
    public MutableLiveData<Integer> currentAttendanceState = new MutableLiveData<>();
    public MutableLiveData<Boolean> isHideRefreshLabel = new MutableLiveData<>(true);
    public static final int ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA = 0;
    public static final int ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA = 1;
    public static final int ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA = 2;
    public static final int ATTENDANCE_STATE_CLOCKED_IN_OUT_SIDE_WORK_AREA = 3;
    public static final int ATTENDANCE_STATE_OFF_WORK_OUT_SIDE_WORK_AREA = 4;
    public static final int ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA = 5;
    private NetClockInConfig.TclockGpsConfigBean comGpsConfig;

    public MutableLiveData<View.OnClickListener> onClickPunch = new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onClickRetryGetLocation = new MutableLiveData<>(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startPositioning();
        }
    });


    public void initViews() {

        LogUtils.showLog(sHA1(DearApplication.getInstance()));

        //显示用户基础信息
        LoginBean me = repo.getMyInfo();
        if (me == null) {
            return;
        }
        name.postValue(me.getUserInfo().getName());
        imgUrl.postValue(me.getUserInfo().getImgUrl());
        dept.postValue(me.getUserInfo().getDeptIdName());
        //更新打卡时间配置和用户打卡状态
        repo.getClockInConfig(new BaseNetCallback<NetClockInConfig>() {
            @Override
            protected void handleResult(NetClockInConfig result) {
                comGpsConfig = result.getTclockGpsConfig();
                updateUiByConfig(result);
                //获取定位
                startPositioning();
            }
        });
        //保持当前时间的更新
        startTimer();
    }

    /**
     * 更新打卡状态UI
     * @param config
     */
    private void updateUiByConfig(NetClockInConfig config) {

        NetClockInConfig.TcompanyWorkTimeBean comWorkTime = config.getTcompanyWorkTime();
        NetClockInConfig.TuserWorkTimeInfoVoBean myWorkTime = config.getTuserWorkTimeInfoVo();

        String startTime = comWorkTime.getStartTime();
        if (!TextUtils.isEmpty(startTime)) {
            Date date = CalenderUtils.getInstance().parseStandardHourMinSecFormat(startTime);
            companyStartWorkTime.postValue(date.getTime());
        }
        String endTime = comWorkTime.getEndTime();
        if (!TextUtils.isEmpty(endTime)) {
            Date date = CalenderUtils.getInstance().parseStandardHourMinSecFormat(endTime);
            companyOffWorkTime.postValue(date.getTime());
        }

        String myStartTime = myWorkTime.getAmStartTime();
        if (!TextUtils.isEmpty(myStartTime)) {
            Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(myStartTime);
            myClockInTime.postValue(date.getTime());
            isPunchStartWork.postValue(true);
        } else {
            isPunchStartWork.postValue(false);
        }
        String myEndTime = myWorkTime.getPmEndTime();
        if (!TextUtils.isEmpty(myEndTime)) {
            Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(myEndTime);
            myPunchOffTime.postValue(date.getTime());
            isPunchOffWork.postValue(true);
        } else {
            isPunchOffWork.postValue(false);
        }

    }


    /**
     * 开始定位
     */
    private void startPositioning() {
        isHideRefreshLabel.postValue(true);
        isShowLoading.postValue(true);
        repo.startPositioning(mapLocationListener);
    }

    private AMapLocationListener mapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (loc != null) {
                isShowLoading.postValue(false);
                int errorCode = loc.getErrorCode();
                //错误码为0时表示获取定位成功
                if (errorCode == 0) {
                    isHideRefreshLabel.postValue(true);
                    updateUiByCurrentLoc(loc);
                } else {
                    isHideRefreshLabel.postValue(false);
                    showToast(repo.getMapError(errorCode));
                    repo.stopPositioning();
                }
            }
        }
    };

    /**
     * 根据当前位置判定打卡界面的打卡类型
     * @param loc
     */
    private void updateUiByCurrentLoc(AMapLocation loc) {
        currentLocation.postValue(loc.getAddress());
        DPoint comLoc = new DPoint();
        comLoc.setLatitude(comGpsConfig.getGpsLatitude());
        comLoc.setLongitude(comGpsConfig.getGpsLongitude());
        DPoint myLoc = new DPoint();
        myLoc.setLatitude(loc.getLatitude());
        myLoc.setLongitude(loc.getLongitude());
        float distance = CoordinateConverter.calculateLineDistance(comLoc, myLoc);
        if(distance<comGpsConfig.getDistance()){
            isInClockInArea.postValue(true);
        }else {
            isInClockInArea.postValue(false);
        }

//        public static final int ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA = 0;
//        public static final int ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA = 1;
//        public static final int ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA = 2;
//        public static final int ATTENDANCE_STATE_CLOCKED_IN_NOT_IN_WORK_AREA = 3;
//        public static final int ATTENDANCE_STATE_OFF_WORK_NOT_IN_WORK_AREA = 4;
//        public static final int ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA = 5;

        if(isInClockInArea.getValue()){

            if(isPunchOffWork.getValue()){
                currentAttendanceState.postValue(ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA);
            }else {
                if(isPunchStartWork.getValue()){
                    currentAttendanceState.postValue(ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA);
                }else {
                    currentAttendanceState.postValue(ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA);
                }
            }

        }else {

            if(isPunchOffWork.getValue()){
                currentAttendanceState.postValue(ATTENDANCE_STATE_OFF_WORK_OUT_SIDE_WORK_AREA);
            }else {
                if(isPunchStartWork.getValue()){
                    currentAttendanceState.postValue(ATTENDANCE_STATE_CLOCKED_IN_OUT_SIDE_WORK_AREA);
                }else {
                    currentAttendanceState.postValue(ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA);
                }
            }
        }

    }

    /**
     * 显示当前时间
     */
    private void startTimer() {
        Disposable disposable = repo.showCurrentTime(new ClockInRepo.TimerInterface() {
            @Override
            public void onTimeUpdate(long currentMills) {
                ClockInViewModel.this.currentTime.postValue(currentMills);
            }
        });
        addSubscription(disposable);
    }

    public void submit() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止定位，释放资源
        repo.stopPositioning();
    }

    public static String sHA1(Context context){
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
