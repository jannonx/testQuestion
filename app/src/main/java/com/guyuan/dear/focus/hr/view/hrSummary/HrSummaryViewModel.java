package com.guyuan.dear.focus.hr.view.hrSummary;

import android.util.SparseArray;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.hr.bean.HrStatusGroup;
import com.guyuan.dear.focus.hr.bean.HrSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 廖华凯
 * @description: 人员概况主界面的视图模型
 * @since: 2020/9/18 11:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class HrSummaryViewModel extends BaseViewModel {
    public static final int TAB_ID_ALL_STAFF = 1;
    public static final int TAB_ID_CONSTRUCTION_SITE_STAFF = 2;
    /**
     * 0为显示公司总人员概况；1为显示公司外勤人员概况
     */
    private int mode;
    private MutableLiveData<Integer> staffTotal = new MutableLiveData<>();
    private MutableLiveData<List<HrStatusGroup>> listData = new MutableLiveData<>();
    private SparseArray<Object> cacheData = new SparseArray<>();

    public int getMode() {
        return mode;
    }

    /**
     * 切换显示类型。
     * @param mode  0为显示公司总人员概况；1为显示公司外勤人员概况
     */
    public void setMode(int mode) {
        this.mode = mode;
        HrSummary value = (HrSummary) cacheData.get(mode);
        if (value == null) {
            if (mode == TAB_ID_ALL_STAFF) {
                value = createAllStaffOverView();
            } else {
                value = createConstructionSiteStaffOverView();
            }
            cacheData.put(mode, value);
        }
        listData.postValue(value.getGroupList());
        staffTotal.postValue(value.getStaffTotal());
    }

    /**
     * 生成外勤人员的模拟数据，显示UI
     * @return
     */
    private HrSummary createConstructionSiteStaffOverView() {
        HrSummary overView = new HrSummary();
        List<HrStatusGroup> list = new ArrayList<HrStatusGroup>();
        HrStatusGroup group1 = new HrStatusGroup();
        group1.setName("正常人员");
        group1.setStaffTotal(18);
        group1.setGrpType(HrStatusGroup.GRP_TYPE_NORMAL);
        HrStatusGroup group2 = new HrStatusGroup();
        group2.setName("缺席人员");
        group2.setStaffTotal(1);
        group2.setGrpType(HrStatusGroup.GRP_TYPE_ABSENT);
        HrStatusGroup group3 = new HrStatusGroup();
        group3.setName("迟到人员");
        group3.setStaffTotal(0);
        group3.setGrpType(HrStatusGroup.GRP_TYPE_LATE);
        HrStatusGroup group4 = new HrStatusGroup();
        group4.setName("早退人员");
        group4.setStaffTotal(0);
        group4.setGrpType(HrStatusGroup.GRP_TYPE_LEAVE_EARLY);
        list.add(group1);
        list.add(group2);
        list.add(group3);
        list.add(group4);
        overView.setGroupList(list);
        return overView;
    }

    /**
     * 生成公司人员的出勤数据，更新UI
     * @return
     */
    private HrSummary createAllStaffOverView() {
        HrSummary overView = new HrSummary();
        List<HrStatusGroup> list = new ArrayList<HrStatusGroup>();
        HrStatusGroup group1 = new HrStatusGroup();
        group1.setName("正常人员");
        group1.setStaffTotal(100);
        group1.setGrpType(HrStatusGroup.GRP_TYPE_NORMAL);
        HrStatusGroup group2 = new HrStatusGroup();
        group2.setName("缺席人员");
        group2.setStaffTotal(10);
        group2.setGrpType(HrStatusGroup.GRP_TYPE_ABSENT);
        HrStatusGroup group3 = new HrStatusGroup();
        group3.setName("迟到人员");
        group3.setStaffTotal(2);
        group3.setGrpType(HrStatusGroup.GRP_TYPE_LATE);
        HrStatusGroup group4 = new HrStatusGroup();
        group4.setName("早退人员");
        group4.setStaffTotal(1);
        group4.setGrpType(HrStatusGroup.GRP_TYPE_LEAVE_EARLY);
        list.add(group1);
        list.add(group2);
        list.add(group3);
        list.add(group4);
        overView.setGroupList(list);
        return overView;
    }

    public MutableLiveData<Integer> getStaffTotal() {
        return staffTotal;
    }

    public MutableLiveData<List<HrStatusGroup>> getListData() {
        return listData;
    }
}
