package com.guyuan.dear.work;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.customizeview.MessageBar;
import com.guyuan.dear.databinding.FragmentWorkBinding;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.scan.ScanActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.NetworkUtils;
import com.guyuan.dear.work.client.activity.WorkClientActivity;

import java.util.ArrayList;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 14:24
 * @company : 固远（深圳）信息技术有限公司
 **/
public class WorkFragment extends BaseListFragment<LoginBean.AppMenusBean.ChildrenBean, FragmentWorkBinding, BaseViewModel> {

    public static final String TAG = "WorkFragment";

    public static WorkFragment newInstance(String title,
                                           ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList) {

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
            ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList =
                    arguments.getParcelableArrayList(ConstantValue.KEY_MENU);
            //hidden过滤，不显示
            ArrayList<LoginBean.AppMenusBean.ChildrenBean> temMenuList = new ArrayList<>();
            for (LoginBean.AppMenusBean.ChildrenBean childrenBean : menuList) {
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

                                break;

                            case ConstantValue.WORK_PURCHASE://采购计划

                                break;

                            case ConstantValue.WORK_PROJECT_SITE://工程现场

                                break;


                            case ConstantValue.WORK_AFTER_SERVICE://售后服务

                                break;


                            case ConstantValue.WORK_MEETING://会议预约

                                break;

                            case ConstantValue.WORK_APPROVE://审批处理

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
