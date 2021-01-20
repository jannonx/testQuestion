package com.guyuan.dear.work.qc.views.materialQc;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMaterialQcBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.beans.MaterialInfo;
import com.guyuan.dear.work.qc.views.home.QcHomeActivity;
import com.guyuan.dear.work.qc.views.home.QcHomeViewModel;
import com.guyuan.dear.work.qc.views.productQc.ProductQcViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/17 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MaterialQcFragment extends BaseMvvmFragment<FragmentMaterialQcBinding, MaterialQcViewModel> {

    private static final int REQUEST_CODE_PICK_QC_CHECKERS = 123;
    private static final int REQUEST_CODE_PICK_VERIFIERS = 321;

    public static MaterialQcFragment getInstance() {
        return new MaterialQcFragment();
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_material_qc_vm;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        MaterialQcViewModel viewModel = getViewModel();
        viewModel.onClickAddQcCheckers.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StaffBean> checkers = getViewModel().getQcCheckers().getValue();
                ArrayList<StaffBean> verifiers = getViewModel().getVerifiers().getValue();
                PickStaffsActivity.startForResult(
                        MaterialQcFragment.this,
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
                ArrayList<StaffBean> checkers = getViewModel().getQcCheckers().getValue();
                ArrayList<StaffBean> verifiers = getViewModel().getVerifiers().getValue();
                ArrayList<StaffBean> hiddenStaffs = new ArrayList<>();
                StaffBean me = new StaffBean();
                me.setId(CommonUtils.getCurrentUserId());
                hiddenStaffs.add(me);
                PickStaffsActivity.startForResult(
                        MaterialQcFragment.this,
                        REQUEST_CODE_PICK_VERIFIERS,
                        "请选择审核人员",
                        verifiers,
                        hiddenStaffs,
                        checkers,
                        10
                );

            }
        });

        viewModel.onClickSelectMaterial.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseProjectBean projectBean = getViewModel().getSelectedProject().getValue();
                if (projectBean == null) {
                    showToastTip("请先选择项目。");
                    return;
                }
                addDisposable(getViewModel().loadMaterialListFromNet(projectBean.getId()));
            }
        });

        viewModel.getMaterialList().observe(getViewLifecycleOwner(), new Observer<List<MaterialInfo>>() {
            @Override
            public void onChanged(List<MaterialInfo> materialInfos) {
                if (!materialInfos.isEmpty()) {
                    showDialogSelectMaterial();
                } else if (getViewModel().shouldShowToastNoData.get()) {
                    showToastTip("当前合同下没有原材料数据。");
                }
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
                addDisposable(getViewModel().getProjectListFromNet());
            }
        });
        getViewModel().getProjectList().observe(getViewLifecycleOwner(), new Observer<List<BaseProjectBean>>() {
            @Override
            public void onChanged(List<BaseProjectBean> baseProjectBeans) {
                if (!baseProjectBeans.isEmpty()) {
                    showDialogSelectProjects();
                } else if (getViewModel().shouldShowToastNoData.get()) {
                    showToastTip("服务器找不到可选项目。");
                }
            }
        });

        viewModel.onClickSelectQcApproach.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getViewModel().getSelectedMaterial().getValue() == null) {
                    showToastTip("请先选择原材料。");
                    return;
                }
                addDisposable(getViewModel().getQcApproachesFromNet());

            }
        });
        viewModel.getQcApproachList().observe(getViewLifecycleOwner(), new Observer<List<BaseQcApproachBean>>() {
            @Override
            public void onChanged(List<BaseQcApproachBean> baseQcApproachBeans) {
                if (!baseQcApproachBeans.isEmpty()) {
                    showDialogSelectQcApproach();
                }
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
                getViewModel().submitApply(new ProductQcViewModel.SubmitCallback() {
                    @Override
                    public void onSubmit(boolean success) {
                        if (success) {
                            getViewDataBinding().fragmentMaterialQcScrollerView.fullScroll(View.FOCUS_UP);
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


    }


    private void showDialogSelectMaterial() {
        List<MaterialInfo> list = getViewModel().getMaterialList().getValue();
        SelectionDialog<MaterialInfo> dialog = new SelectionDialog<MaterialInfo>(getContext()) {
            @Override
            public List<MaterialInfo> setListData() {
                return list;
            }

            @Override
            public OnSelectItemClickListener<MaterialInfo> setOnItemClick() {
                return new OnSelectItemClickListener<MaterialInfo>() {
                    @Override
                    public void onItemClick(MaterialInfo bean, int position) {
                        getViewModel().updateSelectedMaterial(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(MaterialInfo item) {
                return item.getMaterialName();
            }
        };
        dialog.show();
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
        List<BaseQcApproachBean> approaches = getViewModel().getQcApproachList().getValue();
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
        List<BaseProjectBean> list = getViewModel().getProjectList().getValue();
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
                        if (bean.getStatus() == 1) {
                            showToastTip("该合同已经暂停，不能被选中。");
                            return;
                        }
                        getViewModel().updateProjectInfo(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(BaseProjectBean item) {
                String projectName = item.getProjectName();
                int status = item.getStatus();
                if (status == 1) {
                    projectName = projectName + "(暂停中)";
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


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_material_qc;
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
