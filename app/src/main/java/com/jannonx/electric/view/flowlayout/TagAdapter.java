package com.jannonx.electric.view.flowlayout;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class TagAdapter<T> {
    protected List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;
    private HashSet<Integer> mCheckedPosList = new HashSet<>();
    private HashSet<Integer> mNoEnablePosList = new HashSet<>();

    public TagAdapter(List<T> datas) {
        mTagDatas = datas;
    }

    public TagAdapter(T[] datas) {
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }

    void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }


    public void setSelectedList(int... poses) {
        Set<Integer> set = new HashSet<>();
        for (int pos : poses) {
            set.add(pos);
        }
        setSelectedList(set);
    }

    public void setSelectedList(Set<Integer> set) {
        mCheckedPosList.clear();
        if (set != null) {
            mCheckedPosList.addAll(set);
        }
        notifyDataChanged();
    }

    public void setNoEnablePosList(int... poses) {
        Set<Integer> set = new HashSet<>();
        for (int pos : poses) {
            set.add(pos);
        }
        setNoEnablePosList(set);
    }

    /**
     * 按顺序排列时,位置之前的都不可编辑
     *
     * @param postion (不可编辑的位置,包含其本身)
     */
    public void setNoEnablePosListFromPosition(int postion) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= postion; i++) {
            set.add(i);
        }
        setNoEnablePosList(set);
    }

    public void setNoEnablePosList(Set<Integer> enablePosList) {
        mNoEnablePosList.clear();
        if (enablePosList != null && enablePosList.size() > 0) {
            mNoEnablePosList.addAll(enablePosList);
        }
        notifyDataChanged();
    }

    HashSet<Integer> getPreCheckedList() {
        return mCheckedPosList;
    }

    HashSet<Integer> getNoEnableList() {
        return mNoEnablePosList;
    }

    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged() {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

    public boolean setSelected(int position, T t) {
        return false;
    }

    public boolean setNoEnable(int postion, T t) {
        return false;
    }

    public void setNewDataList(List<T> newDataList) {
        mTagDatas.clear();
        mTagDatas.addAll(newDataList);
        notifyDataChanged();
    }

    public void clearDataList() {
        mTagDatas.clear();
        notifyDataChanged();
    }

    interface OnDataChangedListener {
        void onChanged();
    }

    public List<T> getTagDataList() {
        return mTagDatas;
    }
}