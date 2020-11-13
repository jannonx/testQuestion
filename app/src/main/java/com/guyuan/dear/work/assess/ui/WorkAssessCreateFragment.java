package com.guyuan.dear.work.assess.ui;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.editListView.EditListViewBean;
import com.guyuan.dear.databinding.FragmentWorkAssessCreateBinding;
import com.guyuan.dear.dialog.SimpleRecyclerViewDialog;
import com.guyuan.dear.utils.AlertDialogUtils;
import com.guyuan.dear.utils.CalenderUtils;
import com.guyuan.dear.utils.DateUtil;
import com.guyuan.dear.work.assess.data.WorkAssessViewModel;
import com.guyuan.dear.work.assess.data.bean.CustomerBean;
import com.guyuan.dear.work.assess.data.bean.MeetingRoomBean;
import com.guyuan.dear.work.assess.data.bean.RoomBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/11/5 15:33
 * @company : 固远（深圳）信息技术有限公司
 **/

public class WorkAssessCreateFragment extends BaseDataBindingFragment<FragmentWorkAssessCreateBinding, WorkAssessViewModel> {

    public static final String TAG = "WorkAssessCreateFragment";
    private SimpleRecyclerViewDialog customerDialog;
    private SimpleRecyclerViewDialog contractDialog;
    private final int DEFAULT = -1;
    private int customerID = DEFAULT;  //当前用户id
    private int contractID = DEFAULT;  //当前合同id
    private int roomID = DEFAULT;      //当前会议室id

    public static WorkAssessCreateFragment newInstance() {

        Bundle args = new Bundle();

        WorkAssessCreateFragment fragment = new WorkAssessCreateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_assess_create;
    }

    @Override
    protected void initialization() {
        if (viewModel != null) {
            viewModel.getCustomerList();
            viewModel.getMeetingRoomList();
        }
        setElv();
    }

    private void setElv() {
        binding.workAssessElv.setEditable(true);
        binding.workAssessElv.setFragmentManager(childFragmentManager);
    }

    private void setListener() {

    }


    public void setCustomer(List<CustomerBean> customerMSGList) {
        //获取所有客户名列表
        List<String> customerList = new ArrayList<>();
        for (CustomerBean bean : customerMSGList) {
            customerList.add(bean.getCusName());
        }

        binding.customCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleRecyclerViewDialog.show(getContext(), customerList,
                        new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
                            @Override
                            public void onItemClick(String bean, int position) {
                                customerID = customerMSGList.get(position).getId();
                                binding.customNameTv.setText(bean);
                            }
                        });
            }
        });

        binding.contractCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerID != DEFAULT) {
                    for (CustomerBean bean : customerMSGList) {
                        if (bean.getId() == customerID) {
                            List<CustomerBean.AllCustomerVOSBean> contractList = bean.getAllCustomerVOS();
                            List<String> contractNumberList = new ArrayList<>();
                            if (contractList != null && contractList.size() > 0) {
                                for (CustomerBean.AllCustomerVOSBean contractBean : contractList) {
                                    contractNumberList.add(contractBean.getContractNum());
                                }
                                SimpleRecyclerViewDialog.show(getContext(), contractNumberList,
                                        new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
                                            @Override
                                            public void onItemClick(String bean, int position) {
                                                contractID = contractList.get(position).getId();
                                                binding.contractChooseTv.setText(bean);
                                            }
                                        });

                            } else {
                                showToastTip("该客户暂无合同");
                            }
                        }
                    }
                } else {
                    showToastTip("请先选择客户");
                }
            }
        });
    }


    public void setMeetingRoom(MeetingRoomBean list) {
        List<RoomBean> roomList = list.getContent();
        List<String> roomNameList = new ArrayList<>();
        binding.roomCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roomList != null && roomList.size() > 0) {
                    for (RoomBean bean : roomList) {
                        roomNameList.add(bean.getMeetingRoomName());
                    }

                    SimpleRecyclerViewDialog.show(getContext(), roomNameList,
                            new SimpleRecyclerViewDialog.OnSelectItemClickListener() {
                                @Override
                                public void onItemClick(String bean, int position) {
                                    roomID = roomList.get(position).getId();
                                    binding.roomNameTv.setText(bean);
                                }
                            });
                } else {
                    showToastTip("暂无会议室");
                }
            }
        });
    }

    //设置开始时间
    private void setStartTime() {
        binding.startTimeCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogUtils.pickTime(getChildFragmentManager(), "请选择开始时间",
                        System.currentTimeMillis(), Type.YEAR_MONTH_DAY, new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

                            }
                        });
            }
        });
    }
}