package com.guyuan.dear.focus.hr.repos;

import com.guyuan.dear.db.DearDbManager;
import com.guyuan.dear.db.entities.StaffEntity;
import com.guyuan.dear.focus.hr.bean.ChildDept;
import com.guyuan.dear.focus.hr.bean.ParentDept;
import com.guyuan.dear.focus.hr.bean.StaffWorkStatusInfo;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.resultBeans.NetIdAndStatusMapping;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: 廖华凯
 * Date: 2020/11/29
 * Project: Dear
 * Description:
 */
public class HrTreeRepo {

    public Disposable getStaffIdAndAttendStatusMappings(DearNetHelper.NetCallback<List<NetIdAndStatusMapping>> callback) {
        return DearNetHelper.getInstance().getStaffIdAndAttendStatusMapping(callback);
    }

    public Disposable sortStaffsByDept(List<NetIdAndStatusMapping> statusMappings, DearNetHelper.NetCallback<List<ParentDept>> callback) {
        return Observable.create(
                new ObservableOnSubscribe<List<NetIdAndStatusMapping>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<NetIdAndStatusMapping>> emitter) throws Exception {
                        emitter.onNext(statusMappings);
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<List<NetIdAndStatusMapping>, List<ParentDept>>() {
                    @Override
                    public List<ParentDept> apply(List<NetIdAndStatusMapping> netIdAndStatusMappings) throws Exception {
                        List<StaffEntity> staffEntities = DearDbManager.getInstance().getDataBase().getStaffDao().loadAll();
                        List<StaffBean> staffBeans = new ArrayList<>();
                        for (StaffEntity entity : staffEntities) {
                            staffBeans.add(new StaffBean(entity));
                        }
                        List<StaffWorkStatusInfo> workStatusList = new ArrayList<>();
                        for (StaffBean staffBean : staffBeans) {
                            long id = staffBean.getId();
                            for (NetIdAndStatusMapping mapping : netIdAndStatusMappings) {
                                if (mapping.getId() == id) {
                                    StaffWorkStatusInfo status = new StaffWorkStatusInfo(staffBean);
                                    status.setCurrentStatus(mapping.getStatus());
                                    workStatusList.add(status);
                                    break;
                                }
                            }
                        }
                        return groupStaffsByDept(workStatusList);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ParentDept>>() {
                    @Override
                    public void accept(List<ParentDept> parentDepts) throws Exception {
                        callback.onGetResult(parentDepts);
                    }
                });

    }

    private List<ParentDept> groupStaffsByDept(List<StaffWorkStatusInfo> staffBeans) {
        List<ParentDept> result = new ArrayList<>();
        for (StaffWorkStatusInfo staff : staffBeans) {
            //遍历是否已经存在一级部门
            int lv1DeptId = staff.getLv1DeptId();
            int lv2DeptId = staff.getLv2DeptId();
            ParentDept foundParentDept = null;
            for (ParentDept parentDept : result) {
                long parentDeptId = parentDept.getId();
                if (lv1DeptId == parentDeptId) {
                    foundParentDept = parentDept;
                    break;
                }
            }
            //如果一级部门存在
            if (foundParentDept != null) {
                List<ChildDept> childDeptList = foundParentDept.getChildDeptList();
                //查找一级部门里的二级部门
                ChildDept foundChildDept = null;
                for (ChildDept childDept : childDeptList) {
                    if (childDept.getId() == lv2DeptId) {
                        foundChildDept = childDept;
                        break;
                    }
                }
                if (foundChildDept == null) {
                    //如果二级部门不存在，新建二级部门
                    foundChildDept = new ChildDept();
                    foundChildDept.setStaffs(new ArrayList<>());
                    foundChildDept.setDeptName(staff.getLv2DeptName());
                    foundChildDept.setId(staff.getLv2DeptId());
                    foundParentDept.getChildDeptList().add(foundChildDept);
                }
                //把员工放进二级部门
                foundChildDept.getStaffs().add(staff);
            } else {
                //如果一级部门不存在，新建一级部门以及底层二级部门
                ParentDept parentDept = new ParentDept();
                parentDept.setDeptName(staff.getLv1DeptName());
                parentDept.setId(staff.getLv1DeptId());
                parentDept.setChildDeptList(new ArrayList<>());

                ChildDept childDept = new ChildDept();
                childDept.setStaffs(new ArrayList<>());
                childDept.setDeptName(staff.getLv2DeptName());
                childDept.setId(staff.getLv2DeptId());
                childDept.getStaffs().add(staff);

                parentDept.getChildDeptList().add(childDept);

                result.add(parentDept);

            }

        }
        return result;
    }

}
