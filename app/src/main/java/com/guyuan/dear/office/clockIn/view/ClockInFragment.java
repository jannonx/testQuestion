package com.guyuan.dear.office.clockIn.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentClockInBinding;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/26 10:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClockInFragment extends BaseMvvmFragment<FragmentClockInBinding, ClockInViewModel> {

    public static ClockInFragment getInstance() {
        return new ClockInFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return com.guyuan.dear.BR.fragment_clock_in_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        getViewModel().initViews();
    }

    @Override
    protected void initListeners() {
        getViewModel().currentAttendanceState.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //状态发生改变，通知其他fragment（如用户考勤日历）更新考勤信息
                FragmentActivity activity = getActivity();
                if(activity instanceof ClockInActivity){
                    ((ClockInActivity) activity).updateAttendanceCalendar();
                }
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_clock_in;
    }
}
