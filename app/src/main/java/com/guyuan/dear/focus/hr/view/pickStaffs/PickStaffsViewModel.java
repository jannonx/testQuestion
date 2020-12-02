package com.guyuan.dear.focus.hr.view.pickStaffs;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.customizeview.searchview.HrSearchView;
import com.guyuan.dear.db.DearDbManager;
import com.guyuan.dear.db.entities.DeptEntity;
import com.guyuan.dear.db.entities.StaffAndDepts;
import com.guyuan.dear.db.entities.StaffDeptCrosRef;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.db.entities.StaffSelectHistoryEntity;
import com.guyuan.dear.focus.hr.adapter.PickStaffsExpListAdapter;
import com.guyuan.dear.focus.hr.adapter.PickStaffsHistoryStaffsAdapter;
import com.guyuan.dear.focus.hr.bean.PickStaffBean;
import com.guyuan.dear.focus.hr.bean.PickStaffsExpParentBean;
import com.guyuan.dear.utils.BeanMapper;
import com.guyuan.dear.work.contractPause.beans.DeptBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: 廖华凯
 * @description: 选人界面的视图模型
 * @since: 2020/11/3 12:01
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PickStaffsViewModel extends BaseViewModel {
    /**
     * 表示所有人，作为本界面的基础数据源，其他数据都从这里获取，以及同步。
     */
    private MutableLiveData<List<PickStaffBean>> allStaffs = new MutableLiveData<>(new ArrayList<>());
    /**
     * 已经选的人，进入界面时需要提供
     */
    private ArrayList<StaffBean> preSelectedStaffs = new ArrayList<>();
    /**
     * 隐藏起来的人，进入界面时需要提供
     */
    private ArrayList<StaffBean> hiddenStaffs = new ArrayList<>();
    /**
     * 能看到，但不能操作的人，进入界面时需要提供
     */
    private ArrayList<StaffBean> disabledStaffs = new ArrayList<>();
    /**
     * 历史被选过人的列表，用来显示方便用户重新选择。
     */
    private MutableLiveData<List<PickStaffBean>> historyStaffs = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<PickStaffsExpParentBean>> grpBeans = new MutableLiveData<>(new ArrayList<>());
    /**
     * 当前选择人数
     */
    private MutableLiveData<Integer> selectCount = new MutableLiveData<>();
    /**
     * 点击事件：点击历史选人头像的回调
     */
    private MutableLiveData<PickStaffsHistoryStaffsAdapter.PickStaffsHistoryItemClickListener> onClickHistoryStaff = new MutableLiveData<>();
    /**
     * 点击事件：点击人员，切换选择状态的回调
     */
    private MutableLiveData<PickStaffsExpListAdapter.ItemCallback> onToggleStaff = new MutableLiveData<>();
    /**
     * 点击事件：点击历史选人列表右上角“选择全部”的回调
     */
    private MutableLiveData<CompoundButton.OnCheckedChangeListener> onToggleSelectAllHistoryStaffs = new MutableLiveData<>();
    public MutableLiveData<HrSearchView.SelectStaffCallback> onSelectSearchStaff = new MutableLiveData<>();
    /**
     * 点击事件：点击提交
     */
    private MutableLiveData<View.OnClickListener> onClickSubmit = new MutableLiveData<>();
    /**
     * 最大可选人数
     */
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

    /**
     * 初始化视图模型
     *
     * @param preSelected  默认进来就显示被选择了的人员
     * @param hiddenStaffs 需要隐藏起来的人员
     * @param disabled     能显示，但不能操作的人员
     * @param maxSelect    最大选择人数
     */
    public void init(@Nullable ArrayList<StaffBean> preSelected,
                     @Nullable ArrayList<StaffBean> hiddenStaffs,
                     @Nullable ArrayList<StaffBean> disabled,
                     int maxSelect) {
        this.maxSelectCount = maxSelect;
        if (preSelected != null) {
            this.selectCount.setValue(preSelected.size());
            this.preSelectedStaffs.addAll(preSelected);
        }
        if (hiddenStaffs != null) {
            this.hiddenStaffs.addAll(hiddenStaffs);
        }
        if (disabled != null) {
            this.disabledStaffs.addAll(disabled);
        }

        loadAllStaffsFromLocal();
    }


    /**
     * 从本地数据库加载全部人员，并按照部门分组
     */
    private void loadAllStaffsFromLocal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StaffAndDepts> staffAndDepts = new ArrayList<>();
                List<StaffEntity> staffEntities = DearDbManager.getInstance().getDataBase().getStaffDao().loadAll();
                List<StaffDeptCrosRef> crosRefs = DearDbManager.getInstance().getDataBase().getStaffDeptCroRefDao().loadAll();
                for (StaffEntity entity : staffEntities) {
                    StaffAndDepts bean = new StaffAndDepts();
                    bean.staffEntity = entity;
                    bean.deptEntities = new ArrayList<>();
                    for (StaffDeptCrosRef ref : crosRefs) {
                        if (entity._id == ref._id) {
                            long deptId = ref.deptId;
                            DeptEntity deptEntity = DearDbManager.getInstance().getDataBase().getDeptDao().findById(deptId);
                            bean.deptEntities.add(deptEntity);
                        }
                    }
                    bean.deptEntities.sort(comparator);
                    staffAndDepts.add(bean);
                }

                observer.onChanged(staffAndDepts);
            }
        }).start();

    }

    private Comparator<DeptEntity> comparator = new Comparator<DeptEntity>() {
        @Override
        public int compare(DeptEntity o1, DeptEntity o2) {
            return o2.level - o1.level;
        }
    };

    private Observer<List<StaffAndDepts>> observer = new Observer<List<StaffAndDepts>>() {

        List<PickStaffBean> staffList = allStaffs.getValue();

        @Override
        public void onChanged(List<StaffAndDepts> staffAndDepts) {
            for (StaffAndDepts entity : staffAndDepts) {
                StaffBean staffBean = BeanMapper.entityToStaffBean(entity);
                PickStaffBean pickStaffBean = BeanMapper.StaffBeanToPickStaffBean(staffBean);
                staffList.add(pickStaffBean);
            }
            //判断是否已经被选了
            for (PickStaffBean bean : staffList) {
                for (StaffBean preSelect : preSelectedStaffs) {
                    if (preSelect.getId().equals(bean.getId())) {
                        bean.setPick(true);
                        bean.setPickTime(System.currentTimeMillis());
                        break;
                    }
                }
            }
            //判断是否需要屏蔽
            ListIterator<PickStaffBean> iterator = staffList.listIterator();
            while (iterator.hasNext()) {
                PickStaffBean next = iterator.next();
                for (StaffBean hiddenStaff : hiddenStaffs) {
                    if (hiddenStaff.getId().equals(next.getId())) {
                        iterator.remove();
                        break;
                    }
                }
            }
            //判断是否需要只能显示，不能操作
            for (PickStaffBean bean : staffList) {
                for (StaffBean disabled : disabledStaffs) {
                    if (disabled.getId().equals(bean.getId())) {
                        bean.setDisabled(true);
                        break;
                    }
                }
            }


            allStaffs.postValue(staffList);

            //分组
            groupStaffByDept();

            //历史选择
            List<StaffSelectHistoryEntity> recent = DearDbManager.getInstance()
                    .getDataBase().getStaffSelectHistoryDao().loadRecentByDate(10);
            for (StaffSelectHistoryEntity entity : recent) {
                for (PickStaffBean bean : staffList) {
                    if (bean.getId() == entity.staffId) {
                        historyStaffs.getValue().add(bean);
                        break;
                    }
                }
            }
            historyStaffs.postValue(historyStaffs.getValue());
        }
    };

    /**
     * 遍历人员列表，并按部门分组，显示在二级列表中
     */
    private void groupStaffByDept() {
        List<PickStaffBean> motherList = allStaffs.getValue();
        List<PickStaffsExpParentBean> grpList = grpBeans.getValue();
        for (PickStaffBean staffBean : motherList) {
            boolean isFoundDept = false;
            List<DeptBean> depts = staffBean.getDepts();
            if (depts == null || depts.isEmpty()) {
                continue;
            }
            String deptName = depts.get(0).getDeptName();
            for (PickStaffsExpParentBean grp : grpList) {
                if (grp.getDept().equals(deptName)) {
                    isFoundDept = true;
                    grp.getStaffs().add(staffBean);
                    grp.setStaffTotal(grp.getStaffs().size());
                    break;
                }
            }
            if (!isFoundDept) {
                PickStaffsExpParentBean newGrp = new PickStaffsExpParentBean();
                newGrp.setDept(deptName);
                newGrp.setStaffs(new ArrayList<>());
                newGrp.getStaffs().add(staffBean);
                newGrp.setStaffTotal(newGrp.getStaffs().size());
                grpList.add(newGrp);
            }
        }
        grpBeans.postValue(grpList);

    }

    /**
     * 重新检查一遍人员列表，计算、更新当前被选的人数的数值。
     */
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

    /**
     * 全部选择/反选历史选择人员
     *
     * @param isToSelect 是否要全选或者反选全部历史选择人员。
     */
    public void selectAllHistoryStaffs(boolean isToSelect) {
        List<PickStaffBean> value = historyStaffs.getValue();
        if (value != null) {
            for (PickStaffBean bean : value) {
                boolean isSkip = false;
                for (StaffBean staff : disabledStaffs) {
                    if (staff.getId().equals(bean.getId())) {
                        isSkip = true;
                        break;
                    }
                }
                if (isSkip) {
                    continue;
                }
                bean.setPick(isToSelect);
                if (isToSelect) {
                    bean.setPickTime(System.currentTimeMillis());
                }
            }
        }
        updateSelectCount();
    }

    /**
     * 遍历人员列表，获取最新的被选人列表
     *
     * @return
     */
    public ArrayList<StaffBean> getSelectedStaffs() {
        ArrayList<StaffBean> result = new ArrayList<>();
        List<PickStaffBean> value = allStaffs.getValue();
        if (value == null) {
            return result;
        }
        //重新排序，按人员被选的时间先后排序，保证返回的顺序跟当初选择的顺序一致
        value.sort(new Comparator<PickStaffBean>() {
            @Override
            public int compare(PickStaffBean o1, PickStaffBean o2) {
                return (int) (o1.getPickTime() - o2.getPickTime());
            }
        });
        for (PickStaffBean bean : value) {
            if (bean.isPick()) {
                StaffBean staffBean = new StaffBean();
                staffBean.setName(bean.getName());
                staffBean.setDepts(bean.getDepts());
                staffBean.setId(bean.getId());
                staffBean.setImgUrl(bean.getImgUrl());
                result.add(staffBean);
            }
        }

        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 保存最新的被选人到数据库中，下次进来选人界面将在“历史选择”中显示这些人员。
     *
     * @param staffs 本次被选的人员列表
     */
    public void saveStaffSelectHistoryToLocal(List<StaffBean> staffs) {
        DearDbManager.getInstance().updateStaffSelectHistory(staffs);
    }
}
