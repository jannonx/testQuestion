package com.guyuan.dear.work.qc.views.productQc;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentProductQcBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 我的工作-质检-首页
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/16 10:46
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ProductQcFragment extends BaseMvvmFragment<FragmentProductQcBinding, ProductQcViewModel> {

    private static final int REQUEST_CODE_PICK_QC_CHECKERS = 123;
    private static final int REQUEST_CODE_PICK_VERIFIERS = 321;

    public static ProductQcFragment getInstance() {
        return new ProductQcFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_product_qc_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ProductQcViewModel viewModel = getViewModel();
        viewModel.onClickAddQcCheckers.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> checkers = getViewModel().qcCheckers.getValue();
                ArrayList<StaffBean> verifiers = getViewModel().verifiers.getValue();
                PickStaffsActivity.startForResult(
                        ProductQcFragment.this,
                        REQUEST_CODE_PICK_QC_CHECKERS,
                        "请选择质检人员",
                        checkers,
                        null,
                        verifiers,
                        10
                );


            }
        });

        viewModel.onClickAddVerifiers.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> checkers = getViewModel().qcCheckers.getValue();
                ArrayList<StaffBean> verifiers = getViewModel().verifiers.getValue();
                PickStaffsActivity.startForResult(
                        ProductQcFragment.this,
                        REQUEST_CODE_PICK_VERIFIERS,
                        "请选择审核人员",
                        verifiers,
                        null,
                        checkers,
                        10
                );

            }
        });

        viewModel.onClickSelectBatch.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectBatch();
            }
        });

        viewModel.onClickSelectIfNeedVerify.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToggleNeedVerify();

            }
        });

        viewModel.onClickSelectProject.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectProjects();

            }
        });

        viewModel.onClickSelectQcApproach.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectQcApproach();

            }
        });

        viewModel.onClickSelectResult.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectResult();

            }
        });

        viewModel.onClickSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                showToastTip("提交成功。");

            }
        });

    }

    private void showDialogSelectResult() {
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(1);
                add(0);
            }
        };
        SelectionDialog<Integer> dialog = new SelectionDialog<Integer>(getContext()) {
            @Override
            public List<Integer> setListData() {
                return list;
            }

            @Override
            public OnSelectItemClickListener<Integer> setOnItemClick() {
                return new OnSelectItemClickListener<Integer>() {
                    @Override
                    public void onItemClick(Integer bean, int position) {
                        getViewModel().updateQcResult(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(Integer item) {
                if (item > 0) {
                    return "合格";
                }
                return "异常";
            }
        };
        dialog.show();
    }

    private void showDialogSelectQcApproach() {
        List<BaseQcApproachBean> approaches = getViewModel().getQcApproaches();
        SelectionDialog<BaseQcApproachBean> dialog = new SelectionDialog<BaseQcApproachBean>(getContext()) {
            @Override
            public List<BaseQcApproachBean> setListData() {
                return approaches;
            }

            @Override
            public OnSelectItemClickListener<BaseQcApproachBean> setOnItemClick() {
                return new OnSelectItemClickListener<BaseQcApproachBean>() {
                    @Override
                    public void onItemClick(BaseQcApproachBean bean, int position) {
                        getViewModel().updateQcApproach(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(BaseQcApproachBean item) {
                return item.getApproachName();
            }
        };
        dialog.show();
    }

    private void showDialogSelectProjects() {
        List<BaseProjectBean> list = getViewModel().getProjectListFromNet();
        SelectionDialog<BaseProjectBean> dialog = new SelectionDialog<BaseProjectBean>(getContext()) {
            @Override
            public List<BaseProjectBean> setListData() {
                return list;
            }

            @Override
            public OnSelectItemClickListener<BaseProjectBean> setOnItemClick() {
                return new OnSelectItemClickListener<BaseProjectBean>() {
                    @Override
                    public void onItemClick(BaseProjectBean bean, int position) {
                        getViewModel().updateProjectInfo(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(BaseProjectBean item) {
                return item.getProjectName();
            }
        };
        dialog.show();
    }

    private void showDialogToggleNeedVerify() {
        List<Boolean> selections = new ArrayList<Boolean>() {
            {
                add(true);
                add(false);
            }
        };
        SelectionDialog<Boolean> dialog = new SelectionDialog<Boolean>(getContext()) {
            @Override
            public List<Boolean> setListData() {
                return selections;
            }

            @Override
            public OnSelectItemClickListener<Boolean> setOnItemClick() {
                return new OnSelectItemClickListener<Boolean>() {
                    @Override
                    public void onItemClick(Boolean bean, int position) {
                        getViewModel().updateIsVerify(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(Boolean item) {
                if (item) {
                    return "是";
                }
                return "否";
            }
        };
        dialog.show();

    }

    private void showDialogSelectBatch() {
        List<BaseProductBatchInfo> batchInfos = getViewModel().loadBatchInfoListFromNet();
        SelectionDialog<BaseProductBatchInfo> dialog = new SelectionDialog<BaseProductBatchInfo>(
                getContext()) {
            @Override
            public List<BaseProductBatchInfo> setListData() {
                return batchInfos;
            }

            @Override
            public OnSelectItemClickListener<BaseProductBatchInfo> setOnItemClick() {
                return new OnSelectItemClickListener<BaseProductBatchInfo>() {
                    @Override
                    public void onItemClick(BaseProductBatchInfo bean, int position) {
                        getViewModel().updateBatchInfo(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(BaseProductBatchInfo item) {
                return item.getBatchId();
            }
        };
        dialog.show();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_product_qc;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //选择质检人员
                case REQUEST_CODE_PICK_QC_CHECKERS: {
                    ArrayList<StaffBean> beans = data.getParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS);
                    if (beans != null) {
                        getViewModel().updateQcCheckers(beans);
                    }
                }
                break;
                //选择审核人员
                case REQUEST_CODE_PICK_VERIFIERS: {
                    ArrayList<StaffBean> beans = data.getParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS);
                    if (beans != null) {
                        getViewModel().updateVerifiers(beans);
                    }
                }
                break;
                default:
                    break;
            }
        }
    }
}
