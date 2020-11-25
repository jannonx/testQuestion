package com.guyuan.dear.office.approval.hilt;

import com.guyuan.dear.base.api.BaseModule;
import com.guyuan.dear.office.approval.api.ApprovalApiService;
import com.guyuan.dear.office.approval.data.ApprovalRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/25 12:03
 * @company : 固远（深圳）信息技术有限公司
 **/
@Module
@InstallIn(ActivityComponent.class)
public class ApprovalModule extends BaseModule {

    @Provides
    public ApprovalApiService providesApprovalApiService() {
        return retrofit.create(ApprovalApiService.class);
    }

    @Provides
    public ApprovalRepository providesApprovalRepository(ApprovalApiService approvalApiService) {
        return new ApprovalRepository(approvalApiService);
    }
}