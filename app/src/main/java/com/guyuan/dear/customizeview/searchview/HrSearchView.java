package com.guyuan.dear.customizeview.searchview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.SearchView;

import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 专门用来搜索人员的search view，带下拉提示栏，不需要跳到新界面
 *
 * @author 廖华凯
 * @since 2020/7/6 11:43
 **/
public class HrSearchView extends SearchView {
    private HrCursorAdapter mHrCursorAdapter;
    private SelectStaffCallback mSelectStaffCallback;
    private Disposable mDisposable;
    private StaffBean mSelectedStaff;
    /**
     * 需要隐藏起来的人员的ID
     */
    private List<Long> hiddenList = new ArrayList<>();


    public HrSearchView(Context context) {
        this(context, null);
    }

    public HrSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HrSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setIconifiedByDefault(false);
        setIconified(false);
        setSubmitButtonEnabled(false);
        setQueryHint("请输入员工姓名");

        mHrCursorAdapter = new HrCursorAdapter(context);
        setSuggestionsAdapter(mHrCursorAdapter);


        setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (mSelectStaffCallback != null) {
                    if (mSelectedStaff != null && mSelectedStaff.getName().equals(query)) {
                        mSelectStaffCallback.onStaffSelected(mSelectedStaff);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    return false;
                }
                newText = "%" + newText + "%";
                mHrCursorAdapter.updateSuggestionsByKeyWordName(newText, hiddenList);
                return false;
            }
        });

        setOnSuggestionListener(new OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                mSelectedStaff = mHrCursorAdapter.getStaffByItemPosition(position);
                setQuery(mSelectedStaff.getName(), true);
                return false;
            }
        });
    }


    public interface SelectStaffCallback {
        void onStaffSelected(StaffBean staff);
    }

    public void setSelectStaffCallback(SelectStaffCallback selectStaffCallback) {
        mSelectStaffCallback = selectStaffCallback;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public void addHiddenStaffs(ArrayList<StaffBean> staffs) {
        for (StaffBean bean : staffs) {
            if (!hiddenList.contains(bean.getId())) {
                hiddenList.add(bean.getId());
            }
        }
    }
}
