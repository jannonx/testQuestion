package com.guyuan.dear.work.projectsite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.base.fragment.BaseListSearchFragment;
import com.guyuan.dear.databinding.FragmentListBinding;
import com.guyuan.dear.databinding.FragmentWorkCheckGoodImgBinding;
import com.guyuan.dear.focus.projectsite.data.FocusProjectSiteViewModel;
import com.guyuan.dear.work.projectsite.adapter.SiteExplorationAdapter;
import com.guyuan.dear.work.projectsite.data.WorkProjectSiteViewModel;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;

/**
 * @description: 我的工作--工程现场--现场勘查
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class SiteExplorationFragment extends BaseDataBindingFragment<FragmentWorkCheckGoodImgBinding, WorkProjectSiteViewModel> {

    public static final String TAG = CheckGoodsFragment.class.getSimpleName();

    public static CheckGoodsFragment newInstance() {
        Bundle args = new Bundle();
        CheckGoodsFragment fragment = new CheckGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_check_good_img;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
