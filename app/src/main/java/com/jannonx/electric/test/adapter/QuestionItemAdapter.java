package com.jannonx.electric.test.adapter;

import android.content.Context;

import com.jannonx.electric.R;
import com.jannonx.electric.test.bean.ExamConditionBean;
import com.jannonx.electric.test.bean.ExamFunctionType;
import com.jannonx.electric.test.bean.ItemQuestionBean;
import com.jannonx.electric.test.bean.ItemQuestionResultType;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.utils.GlideUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerAdapter;
import tl.com.easy_recycleview_library.adapter.BaseRecyclerViewHolder;

/**
 * @author : Jannonx
 * @description :
 * @since: 2020/11/19 14:11
 **/

public class QuestionItemAdapter extends BaseRecyclerAdapter<ItemQuestionBean> {
    private ExamConditionBean conditionBean;
    private TestQuestionBean testQuestionBean;
    private ExamFunctionType examFunctionType;

    public QuestionItemAdapter(Context context, @NonNull ExamConditionBean examConditionBean, int layoutID) {
        super(context, examConditionBean.getTestQuestionBean().getItemList(), layoutID);
        this.conditionBean = examConditionBean;
        this.testQuestionBean = examConditionBean.getTestQuestionBean();
        this.examFunctionType = examConditionBean.getFunctionType();
    }

    public QuestionItemAdapter(Context context, @NonNull List<ItemQuestionBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    public QuestionItemAdapter(Context context, @NonNull List<ItemQuestionBean> listData, int layoutID, ExamFunctionType type) {
        super(context, listData, layoutID);
        this.examFunctionType = type;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, ItemQuestionBean item, int position) {
        ConstraintLayout rootView = holder.getView(R.id.cl_item_root);
        AppCompatImageView ivSelectResult = holder.getView(R.id.iv_select_result);
        holder.setText(R.id.tv_item, item.getItemType().getSign());
        holder.setText(R.id.tv_content, item.getContent());
//        LogUtils.showLog("getSelectIndex==" + item.getSelectIndex() + "position=" + position);
        if (examFunctionType == null) {
            rootView.setBackground(ResourcesCompat.getDrawable(context.getResources(),
                    item.getSelectIndex() == position ? R.drawable.bg_gray_f8f8f8_stroke_33333_corner_6 :
                            R.drawable.bg_gray_f8f8f8_corner_6, null));

        } else {
            int showIvRes = testQuestionBean.getResultType().getCode() == position ? R.mipmap.ic_right_white :
                    ItemQuestionResultType.TYPE_WRONG == testQuestionBean.getResultType()
                            ? R.mipmap.ic_wrong_orange : R.mipmap.ic_empty_select;
            int showBgRes = testQuestionBean.getResultType().getCode() == position ? R.drawable.bg_green_52d091_corner_6 :
                    ItemQuestionResultType.TYPE_WRONG == testQuestionBean.getResultType()
                            ? R.drawable.bg_orange_f36c4e_corner_6 : R.drawable.bg_gray_f8f8f8_corner_6;
            GlideUtils.getInstance().loadLocalImage(ivSelectResult, showIvRes);
            rootView.setBackground(ResourcesCompat.getDrawable(context.getResources(), showBgRes, null));
        }


    }
}