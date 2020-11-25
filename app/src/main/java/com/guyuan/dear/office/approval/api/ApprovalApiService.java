package com.guyuan.dear.office.approval.api;

import com.guyuan.dear.base.activity.BaseTabActivity;
import com.guyuan.dear.base.api.BaseApiService;
import com.guyuan.dear.databinding.ActivityBaseTabBinding;
import com.guyuan.dear.office.approval.data.ApprovalViewModel;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 11:45
 * @company : 固远（深圳）信息技术有限公司
 **/

public interface ApprovalApiService extends BaseApiService {

    String APPROVAL_LIST = BASE + "projectplan/findApprList";

    String APPROVAL_COMMIT = BASE + "projectplan/ratifySubPlan";

    
}