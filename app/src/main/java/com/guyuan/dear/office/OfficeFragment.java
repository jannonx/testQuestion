package com.guyuan.dear.office;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.data.BaseViewModel;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.customizeview.MessageBar;
import com.guyuan.dear.databinding.FragmentOfficeBinding;
import com.guyuan.dear.login.data.ChildrenBean;
import com.guyuan.dear.office.approval.ui.ApprovalActivity;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.NetworkUtils;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 14:18
 * @company : 固远（深圳）信息技术有限公司
 **/
public class OfficeFragment extends BaseListFragment<ChildrenBean, FragmentOfficeBinding, BaseViewModel> {

    public static final String TAG = "OfficeFragment";

    public static OfficeFragment newInstance(String title, ArrayList<ChildrenBean> menuList) {

        Bundle args = new Bundle();
        args.putString(ConstantValue.KEY_TITLE, title);
        args.putParcelableArrayList(ConstantValue.KEY_MENU, menuList);
        OfficeFragment fragment = new OfficeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_office;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String title = arguments.getString(ConstantValue.KEY_TITLE,
                    getContext().getString(R.string.default_title_mobile_office));
            binding.officeTitleTv.setText(title);
            ArrayList<ChildrenBean> menuList =
                    getArguments().getParcelableArrayList(ConstantValue.KEY_MENU);
            binding.officeMessageBar.setLabel(MessageBar.OFFICE);
            //hidden过滤，不显示
            ArrayList<ChildrenBean> temMenuList = new ArrayList<>();
            for (ChildrenBean childrenBean : menuList) {
                if (childrenBean.getHidden() == 1) {
                    temMenuList.add(childrenBean);
                }
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
                        switch (url) {
                            case ConstantValue.OFFICE_HANDLE_ASSESS://审批处理
                                ApprovalActivity.start(getContext(), title);
                                break;

                            case ConstantValue.OFFICE_WORK_REPORT://工作报告

                                break;

                            case ConstantValue.OFFICE_LEAVE://请假申请

                                break;

                            case ConstantValue.OFFICE_SIGN://考勤打卡

                                break;
                        }
                    } else {
                        showToastTip(ConstantValue.NO_INTERNET);
                    }
                }
            });
            binding.baseRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            binding.baseRecycleView.setAdapter(adapter);
        }

        binding.homeBarLl.homeQrIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    QRActivity.start("", getContext());
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
