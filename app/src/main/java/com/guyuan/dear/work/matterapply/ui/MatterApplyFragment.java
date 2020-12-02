package com.guyuan.dear.work.matterapply.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.AddSendListItemDecorator;
import com.guyuan.dear.databinding.FragmentWorkMatterApplyBinding;
import com.guyuan.dear.dialog.SelectionDialog;
import com.guyuan.dear.focus.hr.view.pickStaffs.PickStaffsActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.adapters.AddSendListAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.contractPause.bindingAdapters.ContractApplyBindingAdapter;
import com.guyuan.dear.work.matterapply.data.MatterApplyViewModel;
import com.guyuan.dear.work.matterapply.data.bean.MatterMaterialBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterProductBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterProjectBean;
import com.guyuan.dear.work.matterapply.data.bean.MatterTypeBean;
import com.sun.jna.platform.win32.Winspool;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

import static android.app.Activity.RESULT_OK;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/23 19:20
 * @company : 固远（深圳）信息技术有限公司
 **/

public class MatterApplyFragment extends BaseDataBindingFragment<FragmentWorkMatterApplyBinding,
        MatterApplyViewModel> {

    public static final String TAG = "ApplyFragment";
    private static final String CHOOSE = "请选择";
    public static final int CLEAR_ALL = 1;   //全部清空
    public static final int CLEAR_TYPE = 2;    //清空生产类型
    public static final int CLEAR_PRODUCT = 3; //清空产品
    public static final int CLEAR_MATERIAL = 4;//清空材料
    public static final int CLEAR_ID = -999;
    public static final int REQUEST_CODE = 0x001;

    private int currentProjectID = CLEAR_ID;
    private int currentTypeID = CLEAR_ID;
    private int currentProductID = CLEAR_ID;
    private int currentMaterialID = CLEAR_ID;
    private int currentPersonID = CLEAR_ID;
    private List<MatterProjectBean> currentProjectList = new ArrayList<>();
    private List<MatterTypeBean> currentTypeList = new ArrayList<>();
    private List<MatterProductBean> currentProductList = new ArrayList<>();
    private List<MatterMaterialBean> currentMaterialList = new ArrayList<>();
    private BaseRecyclerViewAdapter adapter;
    private ArrayList<StaffBean> currentPersonList = new ArrayList<>();

    public static MatterApplyFragment newInstance() {

        Bundle args = new Bundle();

        MatterApplyFragment fragment = new MatterApplyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_matter_apply;
    }

    @Override
    protected void initialization() {
        setProjectObserver();
        setTypeObserver();
        setProductObserver();
        setMaterialObserver();


        binding.matterApplyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickStaffsActivity.startForResult(MatterApplyFragment.this,
                        REQUEST_CODE, "请选择质检人", null,
                        null, null, 1);
            }
        });

        binding.matterCommitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = 0;
                String inputNumber = binding.matterApplyEt.getText().toString();
                if (!TextUtils.isEmpty(inputNumber)) {
                    number = Integer.valueOf(inputNumber);
                }
                if (currentPersonID != CLEAR_ID && currentMaterialID != CLEAR_ID && number > 0
                        && currentProductID != CLEAR_ID && currentProjectID != CLEAR_ID
                        && currentTypeID != CLEAR_ID) {
                    viewModel.applyMatter(currentPersonID, 0, currentMaterialID, number,
                            currentProductID, currentProjectID, currentTypeID);
                } else {
                    showToastTip("请填写完整数据");
                }

            }
        });

        binding.matterApplyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    s.replace(0, 1, "");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<StaffBean> list = data.getParcelableArrayListExtra(ConstantValue.KEY_SELECTED_STAFFS);
                currentPersonList.clear();
                currentPersonList.addAll(list);
                if (currentPersonList.size() > 0) {
                    StaffBean staffBean = list.get(0);
                    currentPersonID = staffBean.getId().intValue();
                    ContractApplyBindingAdapter.setPauseContractSendList(binding.matterApplyRv, list);
                }
            }
        }
    }

    //设置项目列表
    private void setProjectObserver() {

        viewModel.getMatterProjectListMLD().observe(this, new Observer<List<MatterProjectBean>>() {
            @Override
            public void onChanged(List<MatterProjectBean> matterProjectBeans) {
                currentProjectList.clear();
                currentProjectList.addAll(matterProjectBeans);
                if (currentProjectList.size() > 0) {
                    showProjectDialog();
                } else {
                    showToastTip("暂无项目");
                }
            }
        });


        binding.matterApplyProjectNameLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentProjectList.size() == 0) {
                    viewModel.getMatterProjectList();
                } else {
                    showProjectDialog();
                }
            }
        });
    }


    private void setTypeObserver() {
        viewModel.getMatterTypeListMLD().observe(this, new Observer<List<MatterTypeBean>>() {
            @Override
            public void onChanged(List<MatterTypeBean> matterTypeBeans) {
                currentTypeList.clear();
                currentTypeList.addAll(matterTypeBeans);
                if (currentTypeList.size() > 0) {
                    showTypeDialog();
                } else {
                    showToastTip("暂无生产类型");
                }
            }
        });

        binding.matterApplyTypeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTypeList.size() == 0) {
                    viewModel.getMatterTypeList();
                } else {
                    showTypeDialog();
                }
            }
        });
    }


    private void setProductObserver() {
        viewModel.getMatterProductListMLD().observe(this, new Observer<List<MatterProductBean>>() {
            @Override
            public void onChanged(List<MatterProductBean> matterProductBeans) {
                currentProductList.clear();
                currentProductList.addAll(matterProductBeans);
                if (currentProductList.size() > 0) {
                    showProductDialog();
                } else {
                    showToastTip("暂无产品");
                }
            }
        });

        binding.matterApplyProductLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentProductList.size() == 0) {
                    if (currentProjectID == CLEAR_ID) {
                        showToastTip("请选择项目名称");
                        return;
                    }

                    if (currentTypeID == CLEAR_ID) {
                        showToastTip("请选择生产类型");
                        return;
                    }

                    viewModel.getMatterProductList(currentProjectID, currentTypeID);

                } else {
                    showProductDialog();
                }
            }
        });
    }

    private void setMaterialObserver() {
        viewModel.getMatterMaterialListMLD().observe(this, new Observer<List<MatterMaterialBean>>() {
            @Override
            public void onChanged(List<MatterMaterialBean> matterMaterialBeans) {
                currentMaterialList.clear();
                currentMaterialList.addAll(matterMaterialBeans);
                if (currentMaterialList.size() > 0) {
                    showMaterialDialog();
                } else {
                    showToastTip("暂无材料");
                }
            }
        });

        binding.matterApplyMaterialLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentMaterialList.size() == 0) {
                    if (currentProductID == CLEAR_ID) {
                        showToastTip("请选择产品名称");
                        return;
                    }
                    viewModel.getMaterialList(currentProductID);
                } else {
                    showMaterialDialog();
                }
            }
        });
    }


    //显示项目弹窗
    private void showProjectDialog() {
        SelectionDialog<MatterProjectBean> dialog = new SelectionDialog<MatterProjectBean>(getContext()) {
            @Override
            public List<MatterProjectBean> setListData() {
                return currentProjectList;
            }

            @Override
            public OnSelectItemClickListener<MatterProjectBean> setOnItemClick() {
                return new OnSelectItemClickListener<MatterProjectBean>() {
                    @Override
                    public void onItemClick(MatterProjectBean bean, int position) {
                        currentProjectID = bean.getId();
                        setSelectText(binding.matterApplyProjectNameTv, bean.getProjectName());
                        binding.matterApplyProjectCodeTv.setText(bean.getProjectCode());
                        clearData(CLEAR_TYPE);
                    }
                };
            }

            @Override
            public String getItemLabel(MatterProjectBean item) {
                return item.getProjectName();
            }
        };
        dialog.show();
    }


    //显示类型弹窗
    private void showTypeDialog() {
        SelectionDialog<MatterTypeBean> dialog = new SelectionDialog<MatterTypeBean>(getContext()) {
            @Override
            public List<MatterTypeBean> setListData() {
                return currentTypeList;
            }

            @Override
            public OnSelectItemClickListener<MatterTypeBean> setOnItemClick() {
                return new OnSelectItemClickListener<MatterTypeBean>() {
                    @Override
                    public void onItemClick(MatterTypeBean bean, int position) {
                        currentTypeID = Integer.parseInt(bean.getKey());
                        setSelectText(binding.matterApplyProduceTypeTv, bean.getValue());
                        clearData(CLEAR_PRODUCT);
                    }
                };
            }

            @Override
            public String getItemLabel(MatterTypeBean item) {
                return item.getValue();
            }
        };
        dialog.show();
    }

    //显示产品弹窗
    private void showProductDialog() {
        SelectionDialog<MatterProductBean> dialog = new SelectionDialog<MatterProductBean>(getContext()) {
            @Override
            public List<MatterProductBean> setListData() {
                return currentProductList;
            }

            @Override
            public OnSelectItemClickListener<MatterProductBean> setOnItemClick() {
                return new OnSelectItemClickListener<MatterProductBean>() {
                    @Override
                    public void onItemClick(MatterProductBean bean, int position) {
                        currentProductID = bean.getId();
                        setSelectText(binding.matterApplyProductNameTv, bean.getName());
                        clearData(CLEAR_MATERIAL);
                    }
                };
            }

            @Override
            public String getItemLabel(MatterProductBean item) {
                return item.getName();
            }
        };
        dialog.show();
    }

    //显示材料弹窗
    private void showMaterialDialog() {
        SelectionDialog<MatterMaterialBean> dialog = new SelectionDialog<MatterMaterialBean>(getContext()) {
            @Override
            public List<MatterMaterialBean> setListData() {
                return currentMaterialList;
            }

            @Override
            public OnSelectItemClickListener<MatterMaterialBean> setOnItemClick() {
                return new OnSelectItemClickListener<MatterMaterialBean>() {
                    @Override
                    public void onItemClick(MatterMaterialBean bean, int position) {
                        currentMaterialID = bean.getId();
                        setSelectText(binding.matterApplySpecTv, bean.getName());
                        setMaterialData(bean);
                    }
                };
            }

            @Override
            public String getItemLabel(MatterMaterialBean item) {
                return item.getName();
            }
        };
        dialog.show();
    }


    //清空数据
    public void clearData(int type) {
        switch (type) {
            case CLEAR_ALL:
                currentProjectID = CLEAR_ID;
                binding.matterApplyProjectCodeTv.setText("");
                setSelectText(binding.matterApplyProjectNameTv, CHOOSE);
                binding.matterApplyEt.setText("");
                currentPersonID = CLEAR_ID;
                currentPersonList.clear();
                binding.matterApplyRv.getAdapter().notifyDataSetChanged();
            case CLEAR_TYPE:
                currentTypeID = CLEAR_ID;
                currentTypeList.clear();
                setSelectText(binding.matterApplyProduceTypeTv, CHOOSE);
            case CLEAR_PRODUCT:
                currentProductID = CLEAR_ID;
                currentProductList.clear();
                setSelectText(binding.matterApplyProductNameTv, CHOOSE);
            case CLEAR_MATERIAL:
                currentMaterialID = CLEAR_ID;
                currentMaterialList.clear();
                setSelectText(binding.matterApplySpecTv, CHOOSE);
                setMaterialData(null);
                break;
        }
    }


    //设置请选择或选中的内容
    private void setSelectText(TextView tv, String content) {
        if (CHOOSE.equals(content)) {
            tv.setTextColor(getResources().getColor(R.color.color_grey_999999));
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_black_333333));
        }
        tv.setText(content);
    }

    //设置材料数据
    private void setMaterialData(MatterMaterialBean bean) {
        if (bean != null) {
            binding.matterApplyMaterialNameTv.setText(bean.getName());
            binding.matterApplyMaterialNumberTv.setText(bean.getPurchaseNum() + bean.getUnit());
            binding.matterApplyMaterialCodeTv.setText(String.format(getString(R.string.code), bean.getCode()));
            binding.matterApplyMaterialQualityTv.setText(String.format(getString(R.string.quality), bean.getModelCode()));
            binding.matterApplyMaterialRemarkTv.setText(String.format(getString(R.string.remark), bean.getRemark()));
        } else {
            binding.matterApplyMaterialNameTv.setText("");
            binding.matterApplyMaterialNumberTv.setText("");
            binding.matterApplyMaterialCodeTv.setText("");
            binding.matterApplyMaterialQualityTv.setText("");
            binding.matterApplyMaterialRemarkTv.setText("");
        }
    }
}