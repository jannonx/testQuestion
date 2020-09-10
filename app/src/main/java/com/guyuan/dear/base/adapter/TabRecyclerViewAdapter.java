package com.guyuan.dear.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;

import java.util.List;


/**
 * @description:
 * @author:Jannonx
 * @date: 2020/7/6 16:35
 */


public class TabRecyclerViewAdapter extends RecyclerView.Adapter<TabRecyclerViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<SimpleTabBean> mDatas;

    public TabRecyclerViewAdapter(Context context, List<SimpleTabBean> datats) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_tab_recycler_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.title = (AppCompatTextView) view.findViewById(R.id.tv_title);
        viewHolder.rootView = (FrameLayout) view.findViewById(R.id.root_view);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        SimpleTabBean simpleTabBean = mDatas.get(position);
        viewHolder.title.setText(simpleTabBean.getTitle());
        viewHolder.title.setSelected(simpleTabBean.isSelected());
//        LogUtils.showLog("position=" + position + ",,,select=" + simpleTabBean.isSelected());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(simpleTabBean, position);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        AppCompatTextView title;
        FrameLayout rootView;

    }

    public interface OnItemClickListener {
        void onItemClick(SimpleTabBean item, int position);
    }

    private OnItemClickListener clickListener;


    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}

