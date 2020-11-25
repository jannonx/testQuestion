package com.guyuan.dear.work.qc.repo;

import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsViewModel;
import com.guyuan.dear.net.DearNetHelper;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 17:34
 * @company: 固远（深圳）信息技术有限公司
 **/
public class BaseQcRepo {
    public Disposable getProjectListFromNet(DearNetHelper.NetCallback<List<BaseProjectBean>> callback){
//        List<BaseProjectBean> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            BaseProjectBean bean = new BaseProjectBean();
//            bean.setProjectName("DEAR-PRJ-00"+(i+1));
//            bean.setProjectId("DEAR-PRJ-00"+(i+1));
//            list.add(bean);
//        }
//        return list;
        return DearNetHelper.getInstance().getBaseProjectList(callback);
    }

    public Disposable getQcApproaches(DearNetHelper.NetCallback<List<BaseQcApproachBean>> callback){
//        List<BaseQcApproachBean> list = new ArrayList<>();
//        BaseQcApproachBean a1 = new BaseQcApproachBean();
//        a1.setApproachName("全检");
//        a1.setApproachId("0");
//        BaseQcApproachBean a2 = new BaseQcApproachBean();
//        a2.setApproachName("抽检");
//        a2.setApproachId("1");
//        list.add(a1);
//        list.add(a2);
//        return list;
        return DearNetHelper.getInstance().getQcApproaches(callback);
    }

    public Disposable submitQcReport(SubmitQcReportBody body, DearNetHelper.NetCallback<Integer> callback){
        return DearNetHelper.getInstance().submitQcReport(body,callback);
    }

}
