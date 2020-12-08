package com.guyuan.dear.office.clockIn.view;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.guyuan.dear.base.fragment.BaseDearViewModel;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.net.reqBean.ClockInRqBody;
import com.guyuan.dear.net.resultBeans.NetClockInConfig;
import com.guyuan.dear.office.clockIn.repo.ClockInRepo;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.Date;

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

    public MutableLiveData<View.OnClickListener> onClickPunch = new MutableLiveData<View.OnClickListener>(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addSubscription(clockIn());
        }
    });
    public MutableLiveData<View.OnClickListener> onClickRetryGetLocation = new MutableLiveData<>(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startPositioning();
        }
    });
    private DPoint myLoc;


    public void initViews() {

//        LogUtils.showLog(sHA1(DearApplication.getInstance()));

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
                if(comGpsConfig==null){
                    showToast("服务器返回的公司定位信息为null，请联系后台人员。");
                    return;
                }
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
     *
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
            isPunchStartWork.setValue(true);
        } else {
            isPunchStartWork.setValue(false);
        }
        String myEndTime = myWorkTime.getPmEndTime();
        if (!TextUtils.isEmpty(myEndTime)) {
            Date date = CalenderUtils.getInstance().parseSmartFactoryDateStringFormat(myEndTime);
            myPunchOffTime.postValue(date.getTime());
            isPunchOffWork.setValue(true);
        } else {
            isPunchOffWork.setValue(false);
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
     *
     * @param loc
     */
    private void updateUiByCurrentLoc(AMapLocation loc) {
        currentLocation.postValue(loc.getAddress());
        DPoint comLoc = new DPoint();
        comLoc.setLatitude(comGpsConfig.getGpsLatitude());
        comLoc.setLongitude(comGpsConfig.getGpsLongitude());
        myLoc = new DPoint();
        myLoc.setLatitude(loc.getLatitude());
        myLoc.setLongitude(loc.getLongitude());
        float distance = CoordinateConverter.calculateLineDistance(comLoc, myLoc);
        if (distance <= comGpsConfig.getDistance()) {
            isInClockInArea.postValue(true);
        } else {
            isInClockInArea.postValue(false);
        }

        updateCurrentState();

    }

    private void updateCurrentState() {

        if (isInClockInArea.getValue()) {

            if (isPunchOffWork.getValue()) {
                //已经签退，且在打卡范围
                currentAttendanceState.postValue(ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA);
            } else {
                if (isPunchStartWork.getValue()) {
                    //已经签到，且在打卡范围
                    currentAttendanceState.postValue(ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA);
                } else {
                    //还没签到，但在打卡范围
                    currentAttendanceState.postValue(ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA);
                }
            }

        } else {

            if (isPunchOffWork.getValue()) {
                //已经签退，且不在打卡范围
                currentAttendanceState.postValue(ATTENDANCE_STATE_OFF_WORK_OUT_SIDE_WORK_AREA);
            } else {
                if (isPunchStartWork.getValue()) {
                    //已经签到，但不在打卡范围
                    currentAttendanceState.postValue(ATTENDANCE_STATE_CLOCKED_IN_OUT_SIDE_WORK_AREA);
                } else {
                    //还没签到，且不在打卡范围
                    currentAttendanceState.postValue(ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA);
                }
            }
        }
    }

    /**
     * 显示当前时间
     */
    private void startTimer() {
        Disposable disposable = repo.startTimer(new ClockInRepo.TimerInterface() {
            @Override
            public void onTimeUpdate(long currentMills) {
                ClockInViewModel.this.currentTime.postValue(currentMills);
            }
        });
        addSubscription(disposable);
    }

    public Disposable clockIn() {
        if (myLoc == null) {
            showToast("定位未成功，还不能打卡。");
            return null;
        }
        Integer value = currentAttendanceState.getValue();
        switch (value) {
            case ATTENDANCE_STATE_NOT_PUNCHED_IN_WORK_AREA:
                return repo.clockIn(ClockInRqBody.CLOCK_IN_TYPE_CLOCK_IN_INSIDE_COMPANY_AREA,
                        myLoc.getLatitude(),
                        myLoc.getLongitude(),
                        new BaseNetCallback<Boolean>() {
                            @Override
                            protected void handleResult(Boolean result) {
                                showToast("签到成功");
                                updateUiAfterClockIn();

                            }
                        });
            case ATTENDANCE_STATE_NOT_PUNCHED_OUT_SIDE_WORK_AREA:
                return repo.clockIn(ClockInRqBody.CLOCK_IN_TYPE_CLOCK_IN_OUTSIDE_COMPANY_AREA,
                        myLoc.getLatitude(),
                        myLoc.getLongitude(),
                        new BaseNetCallback<Boolean>() {
                            @Override
                            protected void handleResult(Boolean result) {
                                showToast("户外签到成功");
                                updateUiAfterClockIn();
                            }
                        });
            case ATTENDANCE_STATE_CLOCKED_IN_IN_WORK_AREA:
            case ATTENDANCE_STATE_OFF_WORK_IN_WORK_AREA:
                return repo.clockIn(ClockInRqBody.CLOCK_IN_TYPE_CHECK_OUT_INSIDE_COMPANY_AREA,
                        myLoc.getLatitude(),
                        myLoc.getLongitude(),
                        new BaseNetCallback<Boolean>() {
                            @Override
                            protected void handleResult(Boolean result) {
                                showToast("签退成功");
                                updateUiAfterClockIn();
                            }
                        });
            case ATTENDANCE_STATE_CLOCKED_IN_OUT_SIDE_WORK_AREA:
            case ATTENDANCE_STATE_OFF_WORK_OUT_SIDE_WORK_AREA:
                return repo.clockIn(ClockInRqBody.CLOCK_IN_TYPE_CHECK_OUT_OUTSIDE_COMPANY_AREA,
                        myLoc.getLatitude(),
                        myLoc.getLongitude(),
                        new BaseNetCallback<Boolean>() {
                            @Override
                            protected void handleResult(Boolean result) {
                                showToast("户外签退成功");
                                updateUiAfterClockIn();
                            }
                        });
            default:
                break;
        }
        return null;
    }

    private void updateUiAfterClockIn() {
        repo.getClockInConfig(new BaseNetCallback<NetClockInConfig>() {
            @Override
            protected void handleResult(NetClockInConfig result) {
                comGpsConfig = result.getTclockGpsConfig();
                updateUiByConfig(result);
                updateCurrentState();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止定位，释放资源
        repo.stopPositioning();
    }


}
