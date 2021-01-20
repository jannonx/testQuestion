package com.guyuan.dear.work.projectsite.bean;

/**
 * @description: 确认弹框监听
 * @author: 许建宁
 * @since: 2020/12/5 11:14
 * @company: 固远（深圳）信息技术有限公司
 */
public interface OnConfirmDialogListener {
    void onPickImageClick();

    void onCommitCheckGoodsInfo(PostCheckInfo data);

    void onCommitInstallationDebugInfo(PostInstallationDebugInfo data);

    void onCommitCustomerAcceptanceInfo(PostCustomerAcceptanceInfo data);

    void onCommitSiteExplorationInfo(PostSiteExploreInfo data);

    void onCommitCheckSafeInfo(PostSiteExploreInfo data);

    /**
     * 删除图片position监听
     *
     * @param position 下标值
     */
    void onDeleteClick(int position);
}
