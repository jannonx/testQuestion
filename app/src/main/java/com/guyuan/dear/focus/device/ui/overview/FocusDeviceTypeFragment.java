package com.guyuan.dear.focus.device.ui.overview;

import android.os.Bundle;

import com.guyuan.dear.R;
import com.guyuan.dear.base.fragment.BaseListFragment;
import com.guyuan.dear.databinding.FragmentDeviceTypeBinding;
import com.guyuan.dear.focus.device.data.beans.EquipmentBean;
import com.guyuan.dear.utils.ConstantValue;

import java.util.List;

/**
 * @author : tl
 * @description :
 * @since: 2020/9/21 14:52
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusDeviceTypeFragment extends BaseListFragment<List<EquipmentBean>, FragmentDeviceTypeBinding> {

    public static final String TAG = "FocusDeviceOverviewFragment";
    public static final String ID = "id";
    public static final String TYPE = "type";

    public static final int TOTAL = 0x000;
    public static final int OPEN = 0x001;
    public static final int STOP = 0x002;
    public static final int MAINTAIN = 0x003;
    public static final int REPAIR = 0x004;
    public static final int EXCEPTION = 0x005;


    public static FocusDeviceTypeFragment newInstance(String title, long id, int type) {
        
        Bundle args = new Bundle();
        args.putLong(ID, id);
        args.putString(ConstantValue.KEY_TITLE, title);
        args.putInt(TYPE, type);
        FocusDeviceTypeFragment fragment = new FocusDeviceTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutID() {
        return R.layout.fragment_device_type;
    }

    @Override
    protected void initView() {

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
