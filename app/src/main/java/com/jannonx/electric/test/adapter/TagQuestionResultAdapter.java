package com.jannonx.electric.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.jannonx.electric.R;
import com.jannonx.electric.test.bean.ItemQuestionResultType;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.view.flowlayout.FlowLayout;
import com.jannonx.electric.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @description: 员工列表adapter
 * @author:Jannonx
 * @date: 2020/6/4 10:10
 */
public class TagQuestionResultAdapter extends TagAdapter<TestQuestionBean> {
    private Context mContext;
    private boolean isDeleteFlag = true;


    public TagQuestionResultAdapter(Context context, List<TestQuestionBean> dataList) {
        this(dataList);
        mContext = context;
    }

    private TagQuestionResultAdapter(List<TestQuestionBean> dataList) {
        super(dataList);
    }

    @Override
    public View getView(FlowLayout parent, int position, TestQuestionBean bean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_queston_tag,
                parent, false);
        AppCompatTextView tagIndex = view.findViewById(R.id.tv_tag_index);
        FrameLayout tagRoot = view.findViewById(R.id.fl_tag_root);
        tagIndex.setBackground(ResourcesCompat.getDrawable(mContext.getResources(),
                ItemQuestionResultType.TYPE_RIGHT == bean.getResultType() ? R.drawable.bg_green_52d091_round :
                        R.drawable.bg_orange_f36c4e_round, null));
        tagIndex.setText(String.valueOf(position + 1));
        tagRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


    @Override
    public ArrayList<TestQuestionBean> getTagDataList() {
        return (ArrayList<TestQuestionBean>) super.getTagDataList();
    }
}
