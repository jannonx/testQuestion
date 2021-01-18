package com.guyuan.dear.work.qc.views.productQc;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentProductQcBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProductBatchInfo;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.views.home.QcHomeActivity;
import com.guyuan.dear.work.qc.views.home.QcHomeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.disposables.Disposable;

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
        //网络获取项目列表
        viewModel.onClickSelectProject.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable disposable = getViewModel().getProjectListFromNet();
                addDisposable(disposable);

            }
        });
        //获取成功后弹出窗口
        viewModel.projectList.observe(getViewLifecycleOwner(), new Observer<List<BaseProjectBean>>() {
            @Override
            public void onChanged(List<BaseProjectBean> baseProjectBeans) {
                if (!baseProjectBeans.isEmpty()) {
                    showDialogSelectProjects();
                } else if (getViewModel().shouldShowToastNoData.get()) {
                    showToastTip("服务器找不到可选项目。");
                }
            }
        });

        //网络获取产品列表
        viewModel.onClickSelectBatch.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseProjectBean value = getViewModel().selectedProject.getValue();
                if (value == null) {
                    showToastTip("请先选择项目");
                    return;
                }
                addDisposable(getViewModel().getProductListByProjectId());
            }
        });

        //获取后弹出窗口
        viewModel.productBatchList.observe(getViewLifecycleOwner(), new Observer<List<BaseProductBatchInfo>>() {
            @Override
            public void onChanged(List<BaseProductBatchInfo> baseProductBatchInfos) {
                if (!baseProductBatchInfos.isEmpty()) {
                    showDialogSelectBatch();
                } else if (getViewModel().shouldShowToastNoData.get()) {
                    showToastTip("当前合同下没有成品数据。");
                }
            }
        });

        //网络获取质检方式
        viewModel.onClickSelectQcApproach.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDisposable(getViewModel().getQcApproachesFromNet());
            }
        });
        //获取后弹出窗口
        viewModel.qcApproachList.observe(getViewLifecycleOwner(), new Observer<List<BaseQcApproachBean>>() {
            @Override
            public void onChanged(List<BaseQcApproachBean> baseQcApproachBeans) {
                if (!baseQcApproachBeans.isEmpty()) {
                    if(getViewModel().selectedProductBatch.getValue()==null){
                        showToastTip("请先选择出厂编号。");
                        return;
                    }
                    showDialogSelectQcApproach();
                }
            }
        });

        getViewDataBinding().fragmentProductQcCbxJudgeConditionsByNationStandard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> list = getViewModel().getJudgeConditions();
                //2 表示国家标准
                if (isChecked) {
                    if (!list.contains(SubmitQcReportBody.JUDGE_CONDITION_NATIONAL_STANDARD)) {
                        list.add(SubmitQcReportBody.JUDGE_CONDITION_NATIONAL_STANDARD);
                    }
                } else {
                    list.remove(Integer.valueOf(SubmitQcReportBody.JUDGE_CONDITION_NATIONAL_STANDARD));
                }
            }
        });

        getViewDataBinding().fragmentProductQcCbxJudgeConditionsByScheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> list = getViewModel().getJudgeConditions();
                //1 表示设计图样
                if (isChecked) {
                    if (!list.contains(SubmitQcReportBody.JUDGE_CONDITION_BLUE_PRINT_SCHEME)) {
                        list.add(SubmitQcReportBody.JUDGE_CONDITION_BLUE_PRINT_SCHEME);
                    }
                } else {
                    list.remove(Integer.valueOf(SubmitQcReportBody.JUDGE_CONDITION_BLUE_PRINT_SCHEME));
                }
            }
        });

        //选择qc人员
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

        //选择报告结果：通过/不通过
        viewModel.onClickSelectResult.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSelectResult();
            }
        });

        //选择是否需要审批
        viewModel.onClickSelectIfNeedVerify.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToggleNeedVerify();
            }
        });

        //选择审批人员
        viewModel.onClickAddVerifiers.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> checkers = getViewModel().qcCheckers.getValue();
                ArrayList<StaffBean> verifiers = getViewModel().verifiers.getValue();
                ArrayList<StaffBean> hiddenStaffs = new ArrayList<>();
                StaffBean me = new StaffBean();
                me.setId(CommonUtils.getCurrentUserId());
                hiddenStaffs.add(me);
                PickStaffsActivity.startForResult(
                        ProductQcFragment.this,
                        REQUEST_CODE_PICK_VERIFIERS,
                        "请选择审核人员",
                        verifiers,
                        hiddenStaffs,
                        checkers,
                        10
                );

            }
        });


        //点击提交报告
        viewModel.onClickSubmit.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().submitReport(new ProductQcViewModel.SubmitCallback() {
                    @Override
                    public void onSubmit(boolean success) {
                        if (success) {
                            //跳转到我的申请详情并刷新
                            FragmentActivity activity = getActivity();
                            if (activity instanceof QcHomeActivity) {
                                QcHomeViewModel vm = ((QcHomeActivity) activity).getViewModel();
                                vm.refreshMyApplyList.postValue(true);
                            }
                        }
                    }
                });
            }
        });

    }

    private void showDialogSelectResult() {
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
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
                if (item == 1) {
                    return "合格";
                }
                return "异常";
            }
        };
        dialog.show();
    }

    private void showDialogSelectQcApproach() {
        List<BaseQcApproachBean> approaches = getViewModel().qcApproachList.getValue();
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
                        getViewModel().updateSelectedQcApproach(bean);
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
        List<BaseProjectBean> list = getViewModel().projectList.getValue();
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
                        if(bean.getStatus()==1){
                            showToastTip("该合同已经暂停，不能被选中。");
                            return;
                        }
                        getViewModel().updateSelectedProject(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(BaseProjectBean item) {
                String projectName = item.getProjectName();
                int status = item.getStatus();
                if(status == 1){
                    projectName = projectName+"(暂停中)";
                }
                return projectName;
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
        List<BaseProductBatchInfo> batchInfos = getViewModel().productBatchList.getValue();
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
                        getViewModel().updateSelectedBatchInfo(bean);
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
