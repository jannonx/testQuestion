package com.guyuan.dear.work;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.customizeview.MessageBar;
import com.guyuan.dear.databinding.FragmentWorkBinding;
import com.guyuan.dear.home.data.MainViewModel;
import com.guyuan.dear.login.data.ChildrenBean;
import com.guyuan.dear.message.data.bean.MessageUnreadBean;
import com.guyuan.dear.message.ui.MessageActivity;
import com.guyuan.dear.message.ui.MessageFragment;
import com.guyuan.dear.scan.ScanActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.NetworkUtils;
import com.guyuan.dear.work.aftersale.activity.WorkAfterSaleActivity;
import com.guyuan.dear.work.assess.ui.WorkAssessActivity;
import com.guyuan.dear.work.client.activity.WorkClientActivity;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeActivity;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeActivity;
import com.guyuan.dear.work.goodssign.ui.GoodsSignActivity;
import com.guyuan.dear.work.matterapply.ui.MatterApplyActivity;
import com.guyuan.dear.work.produce.activity.WorkProduceActivity;
import com.guyuan.dear.work.projectsite.activity.WorkProjectSiteActivity;
import com.guyuan.dear.work.qc.views.home.QcHomeActivity;

import java.util.ArrayList;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 14:24
 * @company : 固远（深圳）信息技术有限公司
 **/
public class WorkFragment extends BaseListFragment<ChildrenBean, FragmentWorkBinding, MainViewModel> {

    public static final String TAG = "WorkFragment";

    public static WorkFragment newInstance(String title, ArrayList<ChildrenBean> menuList) {

        Bundle args = new Bundle();
        args.putString(ConstantValue.KEY_TITLE, title);
        args.putParcelableArrayList(ConstantValue.KEY_MENU, menuList);
        WorkFragment fragment = new WorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString(ConstantValue.KEY_TITLE,
                    getContext().getString(R.string.default_title_process_control));
            binding.homeWorkTitleTv.setText(title);
            binding.workMessageBar.setLabel(MessageBar.WORK);
            ArrayList<ChildrenBean> menuList =
                    arguments.getParcelableArrayList(ConstantValue.KEY_MENU);
            //hidden过滤，不显示
            ArrayList<ChildrenBean> temMenuList = new ArrayList<>();
            for (ChildrenBean childrenBean : menuList) {
                if (childrenBean.getHidden() == 1) temMenuList.add(childrenBean);
            }
            menuList.clear();
            menuList.addAll(temMenuList);
            temMenuList.clear();

            BaseMenuAdapter baseMenuAdapter = new BaseMenuAdapter(getContext(), menuList,
                    R.layout.item_fragment_menu);
            BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(baseMenuAdapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (NetworkUtils.isNetworkAvailable(getContext())) {
                        String url = menuList.get(position).getUrl();
                        String title = menuList.get(position).getTitle();
                        Bundle cBundle = new Bundle();
                        cBundle.putString(ConstantValue.KEY_TITLE, title);

                        switch (url) {
                            case ConstantValue.WORK_SECURITY://安全巡查

                                break;

                            case ConstantValue.WORK_DEVICE://设备维护

                                break;

                            case ConstantValue.WORK_CUSTOMER://客户跟进
                                WorkClientActivity.start(getContext(), title);
                                break;

                            case ConstantValue.WORK_ASSESS://工程评审

                                break;

                            case ConstantValue.WORK_PRODUCE://生产计划
                                WorkProduceActivity.start(getContext(), title);
                                break;

                            case ConstantValue.WORK_PURCHASE://采购计划

                                break;

                            case ConstantValue.WORK_PROJECT_SITE://工程现场
                                ///
                                WorkProjectSiteActivity.start(getContext(), title);
                                break;


                            case ConstantValue.WORK_AFTER_SERVICE://售后服务
                                WorkAfterSaleActivity.start(getContext(), title);
                                break;


                            case ConstantValue.WORK_MEETING://会议预约

                                break;

                            case ConstantValue.WORK_APPROVE://审批处理

                                break;

                            case ConstantValue.CONTRACT_PAUSE://合同暂停
                                showToastTip("正在研发中...");
                                //ContractPauseHomeActivity.start(getContext(), title);
                                break;

                            case ConstantValue.CONTRACT_RESTART://合同重启
                                showToastTip("正在研发中...");
                                //ContractRestartHomeActivity.start(getContext(), title);
                                break;

                            case ConstantValue.WORK_CONTRACT_ASSESS://合同评审
                                WorkAssessActivity.start(getContext(), title);
                                break;
                            case ConstantValue.WORK_QC://质检
                                QcHomeActivity.start(getContext(), title);
                                break;

                            case ConstantValue.WORK_GOODS_SIGN://货物签收
                                GoodsSignActivity.start(getContext(), title);
                                break;

                            case ConstantValue.WORK_MATTER_APPLY://物料申请
                                MatterApplyActivity.start(getContext(), title);
                                break;
                            default:

                                break;
                        }

                    } else {
                        showToastTip(ConstantValue.NO_INTERNET);
                    }
                }
            });

            GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
            binding.baseRecycleView.setLayoutManager(manager);
            binding.baseRecycleView.setAdapter(adapter);

        }

        binding.homeBarCl.homeQrIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanActivity.starter(getContext(), "");
            }
        });
        MessageBar messageBar = getActivity().findViewById(R.id.work_message_bar);


        binding.workMessageBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageActivity.start(getContext(), "消息", MessageFragment.MESSAGE_COMMON);
            }
        });

    //    setMessage();
    }


    public void setMessage() {
        if (viewModel != null) {
            viewModel.getMessageListMLD().observe(this, new Observer<MessageUnreadBean>() {
                @Override
                public void onChanged(MessageUnreadBean unreadBean) {
                    binding.workMessageBar.setMessageBar(unreadBean.getNumber(), unreadBean.getNewMessage());
                }
            });
            //定时获取消息
            viewModel.getLastUnReadMessage(MessageFragment.MESSAGE_COMMON);
        }

    }


    @Override
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

    }

    @Override
    protected boolean isPullEnable() {
        return false;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
