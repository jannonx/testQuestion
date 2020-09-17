package com.guyuan.dear.base.fragment;

import androidx.databinding.ViewDataBinding;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.utils.CalenderUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:14
 * @company: 固远（深圳）信息技术有限公司
 */
public abstract class BaseListSearchFragment<T, VB extends ViewDataBinding> extends BaseDataBindingFragment<VB> {
    public final int LOAD_MORE = 0X0100;
    public final int REFRESH = 0X0101;
    protected List<T> listData = new ArrayList<T>();
    protected BaseRecyclerViewAdapter adapter;
    protected final int PAGE_SIZE = 30;//一页拉取的数据条数
    protected final int FIRST_PAGE = 1;
    protected int currentPage = FIRST_PAGE;//当前页数
    protected int currentType = REFRESH;


    protected Date[] dates = new Date[2];
    protected CalenderUtils calenderUtils;
    protected long depId = -1;
    protected int sParentId = 0;
    protected long factoryId = -1;
    protected int sChildId = -1;
    protected List<SimpleTabBean> mDepartmentDataList = new ArrayList<>();
//    private SimpleTabRecyclerViewDialog.OnSelectItemClickListener depTabClickListener;

} 