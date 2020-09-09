package com.guyuan.dear.office;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.customizeview.MessageBar;
import com.guyuan.dear.databinding.FragmentOfficeBinding;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.NetworkUtils;

import java.util.ArrayList;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 14:18
 * @company : 固远（深圳）信息技术有限公司
 **/
public class OfficeFragment extends BaseListFragment<LoginBean.AppMenusBean.ChildrenBean, FragmentOfficeBinding> {

    public static final String TAG = "OfficeFragment";

    public static OfficeFragment newInstance(String title,
                                             ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList) {

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
            ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList =
                    getArguments().getParcelableArrayList(ConstantValue.KEY_MENU);
            binding.officeMessageBar.setLabel(MessageBar.OFFICE);
            //hidden过滤，不显示
            ArrayList<LoginBean.AppMenusBean.ChildrenBean> temMenuList = new ArrayList<>();
            for (LoginBean.AppMenusBean.ChildrenBean childrenBean : menuList) {
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
                    } else {
                        showToastTip(ConstantValue.NO_INTERNET);
                    }
                }
            });
            recycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            recycleView.setAdapter(adapter);
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
}
