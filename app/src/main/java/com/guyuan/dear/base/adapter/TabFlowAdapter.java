package com.guyuan.dear.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.guyuan.dear.R;
import com.guyuan.dear.base.bean.SimpleTabBean;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;

import java.util.List;


/**
 * @description:
 * @author:Jannonx
 * @date: 2020/6/28 16:37
 */
public class TabFlowAdapter extends TagAdapter<SimpleTabBean> {
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public TabFlowAdapter(Context context, List<SimpleTabBean> dataList) {
        super(dataList);
        mContext = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, SimpleTabBean itemData) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab_flow, parent, false);
        AppCompatTextView tvName = view.findViewById(R.id.tv_name);
        tvName.setText(itemData.getTitle());
        if (itemData.isSelected()) {
            tvName.setSelected(true);
        } else {
            tvName.setSelected(false);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    if (!itemData.isSelected()) {
                        onItemClickListener.onItemClick(itemData, position);
                        for (int i = 0; i < mTagDatas.size(); i++) {
                            mTagDatas.get(i).setSelected(i == position);
                        }
                        notifyDataChanged();
                    }

                }
            }
        });
        return view;
    }

    public interface OnItemClickListener {
        void onItemClick(SimpleTabBean tabBean, int pos);
    }

    public void setOnItemClickListener(
            OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
