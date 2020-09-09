package com.guyuan.dear.analyse;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.BaseMenuAdapter;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentAnalyseBinding;
import com.guyuan.dear.login.data.LoginBean;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.NetworkUtils;
import java.util.ArrayList;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/8 10:55
 * @company : 固远（深圳）信息技术有限公司
 **/
public class AnalyseFragment extends BaseListFragment<LoginBean.AppMenusBean.ChildrenBean, FragmentAnalyseBinding> {
    public static final String TAG = "AnalyseFragment";

    public static AnalyseFragment newInstance(String title,
                                              ArrayList<LoginBean.AppMenusBean.ChildrenBean> menuList) {

        Bundle args = new Bundle();
        args.putString(ConstantValue.KEY_TITLE, title);
        args.putParcelableArrayList(ConstantValue.KEY_MENU, menuList);
        AnalyseFragment fragment = new AnalyseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_analyse;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null&&getContext()!=null) {
            String title = arguments.getString(ConstantValue.KEY_TITLE,
                    getContext().getString(R.string.default_title_smart_analysis));
            binding.analyseTitleTv.setText(title);
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
                    if ( NetworkUtils.isNetworkAvailable(getContext())) {
                        LoginBean.AppMenusBean.ChildrenBean childrenBean = menuList.get(position);
                        String url = childrenBean.getUrl();
                        String title = childrenBean.getTitle();

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

}
