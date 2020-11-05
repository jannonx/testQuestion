package com.guyuan.dear.focus.hr.view.pickStaffs;

import android.view.View;
import android.widget.CompoundButton;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.focus.hr.adapter.PickStaffsExpListAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.focus.hr.bean.PickStaffsExpParentBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/3 12:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsViewModel extends BaseViewModel {
    private MutableLiveData<List<PickStaffBean>> allStaffs = new MutableLiveData<>(new ArrayList<>());
    private List<StaffBean> preSelectedStaffs = new ArrayList<>();
    private List<StaffBean> hiddenStaffs = new ArrayList<>();
    private MutableLiveData<List<PickStaffBean>> historyStaffs = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<PickStaffsExpParentBean>> grpBeans = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Integer> selectCount = new MutableLiveData<>();
    private MutableLiveData<PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener> onClickHistoryStaff = new MutableLiveData<>();
    private MutableLiveData<PickStaffsExpListAdapter.ItemCallback> onToggleStaff = new MutableLiveData<>();
    private MutableLiveData<CompoundButton.OnCheckedChangeListener> onToggleSelectAllHistoryStaffs = new MutableLiveData<>();
    private MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();
    private int maxSelectCount = 10;

    public MutableLiveData<View.OnClickListener> getOnClickSubmit() {
        return onClickSubmit;
    }

    public void setOnClickSubmit(View.OnClickListener onClickSubmit) {
        this.onClickSubmit.setValue(onClickSubmit);
    }

    public MutableLiveData<CompoundButton.OnCheckedChangeListener> getOnToggleSelectAllHistoryStaffs() {
        return onToggleSelectAllHistoryStaffs;
    }

    public void setOnToggleSelectAllHistoryStaffs(CompoundButton.OnCheckedChangeListener onToggleSelectAllHistoryStaffs) {
        this.onToggleSelectAllHistoryStaffs.setValue(onToggleSelectAllHistoryStaffs);
    }

    public MutableLiveData<PickStaffsExpListAdapter.ItemCallback> getOnToggleStaff() {
        return onToggleStaff;
    }

    public void setOnToggleStaff(PickStaffsExpListAdapter.ItemCallback onToggleStaff) {
        this.onToggleStaff.setValue(onToggleStaff);
    }

    public MutableLiveData<PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener> getOnClickHistoryStaff() {
        return onClickHistoryStaff;
    }

    public void setOnClickHistoryStaff(PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener onClickHistoryStaff) {
        this.onClickHistoryStaff.setValue(onClickHistoryStaff);
    }

    public List<StaffBean> getPreSelectedStaffs() {
        return preSelectedStaffs;
    }

    public List<StaffBean> getHiddenStaffs() {
        return hiddenStaffs;
    }

    public MutableLiveData<List<PickStaffBean>> getHistoryStaffs() {
        return historyStaffs;
    }

    public MutableLiveData<Integer> getSelectCount() {
        return selectCount;
    }

    public MutableLiveData<List<PickStaffsExpParentBean>> getGrpBeans() {
        return grpBeans;
    }

    public void init(ArrayList<StaffBean> preSelected, ArrayList<StaffBean> hiddenStaffs, int maxSelect) {
        this.maxSelectCount = maxSelect;
        this.selectCount.setValue(preSelected.size());
        this.preSelectedStaffs.addAll(preSelected);
        this.hiddenStaffs.addAll(hiddenStaffs);
        loadAllStaffsFromLocal();
    }

    private String zhNum = "一二三四五六七八九十";

    private void loadAllStaffsFromLocal() {
        List<PickStaffBean> staffList = allStaffs.getValue();

        //生产一部
        for (int i = 0; i < 10; i++) {
            PickStaffBean bean = new PickStaffBean();
            bean.setDept("生产一部");
            bean.setName("陈" + zhNum.charAt(i) + "平");
            bean.setId(bean.getName());
            staffList.add(bean);
        }
        //生产二部
        for (int i = 0; i < 10; i++) {
            PickStaffBean bean = new PickStaffBean();
            bean.setDept("生产二部");
            bean.setName("黄" + zhNum.charAt(i));
            bean.setId(bean.getName());
            staffList.add(bean);
        }
        //销售部
        for (int i = 0; i < 10; i++) {
            PickStaffBean bean = new PickStaffBean();
            bean.setDept("销售部");
            bean.setName("赵" + zhNum.charAt(i));
            bean.setId(bean.getName());
            staffList.add(bean);
        }
        //行政部
        for (int i = 0; i < 10; i++) {
            PickStaffBean bean = new PickStaffBean();
            bean.setDept("行政部");
            bean.setName("张" + zhNum.charAt(i));
            bean.setId(bean.getName());
            staffList.add(bean);
        }
        //工程部
        for (int i = 0; i < 10; i++) {
            PickStaffBean bean = new PickStaffBean();
            bean.setDept("工程部");
            bean.setName("李" + zhNum.charAt(i));
            bean.setId(bean.getName());
            staffList.add(bean);
        }
        //判断是否已经被选了
        for (PickStaffBean bean : staffList) {
            if (preSelectedStaffs.contains(bean)) {
                bean.setPick(true);
            }
        }
        //判断是否需要屏蔽
        ListIterator<PickStaffBean> iterator = staffList.listIterator();
        while (iterator.hasNext()) {
            PickStaffBean next = iterator.next();
            if (hiddenStaffs.contains(next)) {
                iterator.remove();
            }
        }
        allStaffs.postValue(staffList);

        //分组
        groupStaffByDept();

        //造假数据-历史选择
        for (int i = 0; i < 10; i++) {
            PickStaffBean temp = staffList.get(i);
            historyStaffs.getValue().add(temp);
        }
        historyStaffs.postValue(historyStaffs.getValue());

    }

    private void groupStaffByDept() {
        List<PickStaffBean> motherList = allStaffs.getValue();
        List<PickStaffsExpParentBean> grpList = grpBeans.getValue();
        for (PickStaffBean staffBean : motherList) {
            boolean isFoundDept = false;
            for (PickStaffsExpParentBean grp : grpList) {
                if (grp.getDept().equals(staffBean.getDept())) {
                    isFoundDept = true;
                    grp.getStaffs().add(staffBean);
                    grp.setStaffTotal(grp.getStaffs().size());
                    break;
                }
            }
            if (!isFoundDept) {
                PickStaffsExpParentBean newGrp = new PickStaffsExpParentBean();
                newGrp.setDept(staffBean.getDept());
                newGrp.setStaffs(new ArrayList<>());
                newGrp.getStaffs().add(staffBean);
                newGrp.setStaffTotal(newGrp.getStaffs().size());
                grpList.add(newGrp);
            }
        }
        grpBeans.postValue(grpList);

    }


    public void updateSelectCount() {
        int count = 0;
        List<PickStaffBean> value = allStaffs.getValue();
        for (PickStaffBean bean : value) {
            if (bean.isPick()) {
                count++;
            }
        }
        selectCount.setValue(count);
    }

    public void selectAllHistoryStaffs(boolean isToSelect) {
        List<PickStaffBean> value = historyStaffs.getValue();
        if (value != null) {
            for (PickStaffBean bean : value) {
                bean.setPick(isToSelect);
            }
        }
        updateSelectCount();
    }

    public ArrayList<StaffBean> getSelectedStaffs() {
        ArrayList<StaffBean> result = new ArrayList<>();
        for (PickStaffBean bean : allStaffs.getValue()) {
            StaffBean staffBean = new StaffBean();
            staffBean.setName(bean.getName());
            staffBean.setDept(bean.getDept());
            staffBean.setId(bean.getId());
            staffBean.setImgUrl(bean.getImgUrl());
            result.add(staffBean);
        }
        return result;
    }
}
