package com.guyuan.dear.work.qc.views.materialQc;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMaterialQcBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.net.reqBean.SubmitQcReportBody;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.qc.beans.BaseProjectBean;
import com.guyuan.dear.work.qc.beans.BaseQcApproachBean;
import com.guyuan.dear.work.qc.beans.MaterialInfo;

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
                PickStaffsActivity.startForResult(
                        MaterialQcFragment.this,
                        REQUEST_CODE_PICK_VERIFIERS,
                        "请选择审核人员",
                        verifiers,
                        null,
                        checkers,
                        10
                );

            }
        });

        viewModel.onClickSelectMaterial.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseProjectBean projectBean = getViewModel().getSelectedProject().getValue();
                if(projectBean ==null){
                    showToastTip("请先选择项目。");
                    return;
                }
                addDisposable(getViewModel().loadMaterialListFromNet(projectBean.getId()));
            }
        });

        viewModel.getMaterialList().observe(getViewLifecycleOwner(), new Observer<List<MaterialInfo>>() {
            @Override
            public void onChanged(List<MaterialInfo> materialInfos) {
                if(!materialInfos.isEmpty()){
                    showDialogSelectMaterial();
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
//                showDialogSelectProjects();
                addDisposable(getViewModel().getProjectListFromNet());

            }
        });
        getViewModel().getProjectList().observe(getViewLifecycleOwner(), new Observer<List<BaseProjectBean>>() {
            @Override
            public void onChanged(List<BaseProjectBean> baseProjectBeans) {
                if(!baseProjectBeans.isEmpty()){
                    showDialogSelectProjects();
                }
            }
        });

        viewModel.onClickSelectQcApproach.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDisposable(getViewModel().getQcApproachesFromNet());

            }
        });
        viewModel.getQcApproachList().observe(getViewLifecycleOwner(), new Observer<List<BaseQcApproachBean>>() {
            @Override
            public void onChanged(List<BaseQcApproachBean> baseQcApproachBeans) {
                if(!baseQcApproachBeans.isEmpty()){
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
                getViewModel().submitApply();
            }
        });

        getViewDataBinding().fragmentProductQcCbxJudgeConditionsByNationStandard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> list = getViewModel().getJudgeConditions();
                //2 表示国家标准
                if(isChecked){
                    if(!list.contains(SubmitQcReportBody.JUDGE_CONDITION_NATIONAL_STANDARD)){
                        list.add(SubmitQcReportBody.JUDGE_CONDITION_NATIONAL_STANDARD);
                    }
                }else {
                    list.remove(Integer.valueOf(SubmitQcReportBody.JUDGE_CONDITION_NATIONAL_STANDARD));
                }
            }
        });

        getViewDataBinding().fragmentProductQcCbxJudgeConditionsByScheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> list = getViewModel().getJudgeConditions();
                //1 表示设计图样
                if(isChecked){
                    if(!list.contains(SubmitQcReportBody.JUDGE_CONDITION_BLUE_PRINT_SCHEME)){
                        list.add(SubmitQcReportBody.JUDGE_CONDITION_BLUE_PRINT_SCHEME);
                    }
                }else {
                    list.remove(Integer.valueOf(SubmitQcReportBody.JUDGE_CONDITION_BLUE_PRINT_SCHEME));
                }
            }
        });

//        getViewDataBinding().fragmentProductQcEdtSampleSize.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String string = s.toString();
//                if(TextUtils.isEmpty(string)){
//                    getViewModel().getSampleSize().postValue(0);
//                }else {
//                    getViewModel().getSampleSize().postValue(Integer.valueOf(string));
//                }
//            }
//        });

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
