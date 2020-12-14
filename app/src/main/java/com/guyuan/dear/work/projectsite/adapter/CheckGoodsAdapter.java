package com.guyuan.dear.work.projectsite.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.guyuan.dear.base.adapter.BaseRecyclerAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.type.FunctionModuleType;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.LogUtils;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;

import tl.com.easy_recycleview_library.BaseRecyclerViewHolder;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CheckGoodsAdapter extends BaseRecyclerAdapter<CheckGoodsBean> {
    /**
     * 清点确认状态(-1尚未操作，0:正常,1:异常)
     */
    private static final int CHECK_RIGHT = 0;
    private static final int CHECK_WRONG = 1;
    private FunctionModuleType moduleType;


    public CheckGoodsAdapter(Context context, @NonNull List<CheckGoodsBean> listData, int layoutID) {
        super(context, listData, layoutID);
    }

    public CheckGoodsAdapter(Context context, @NonNull List<CheckGoodsBean> listData, int layoutID, FunctionModuleType type) {
        super(context, listData, layoutID);
        this.moduleType = type;
    }

    @Override
    protected void bindDataToView(BaseRecyclerViewHolder holder, CheckGoodsBean item,
                                  int position) {
        AppCompatImageView imageView = holder.getView(R.id.image_view);
        //status=-1,没有清点
        imageView.setVisibility(FunctionModuleType.TYPE_FOCUS == moduleType && item.getStatus() != -1 ? View.VISIBLE : View.GONE);
        RelativeLayout rlConfirm = holder.getView(R.id.rl_confirm);
        rlConfirm.setVisibility(FunctionModuleType.TYPE_WORK == moduleType ? View.VISIBLE : View.GONE);

        holder.setText(R.id.tv_good_name, item.getProjectName());
        holder.setText(R.id.tv_goods_code, item.getProjectCode());
        holder.setText(R.id.tv_goods_model, item.getModel());
        holder.setText(R.id.tv_remark, item.getRemark());
        holder.setText(R.id.tv_goods_number, item.getAmount() + item.getUnit());

        //我的关注
        imageView.setImageResource(item.getStatus() == CHECK_RIGHT ? R.mipmap.right : R.mipmap.wrong);

        //我的工作
        AppCompatImageView ivRightBtn = holder.getView(R.id.iv_right_btn);
        AppCompatImageView ivWrongBtn = holder.getView(R.id.iv_wrong_btn);

        if (FunctionModuleType.TYPE_WORK == moduleType) {
            item.setStatus(CHECK_RIGHT);
        }

        ivRightBtn.setImageResource(R.mipmap.right);
        ivWrongBtn.setImageResource(R.mipmap.ic_wrong_gray_unselect);

        View viewBottom = holder.getView(R.id.view_bottom);
        //处在我的工作，列表数大于2条，且最后一条，显示空白块
        viewBottom.setVisibility(FunctionModuleType.TYPE_WORK == moduleType && listData.size() > 2
                && listData.size() - 1 == position ? View.VISIBLE : View.GONE);
        ivRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setStatus(CHECK_RIGHT);
                ivRightBtn.setImageResource(R.mipmap.right);
                ivWrongBtn.setImageResource(R.mipmap.ic_wrong_gray_unselect);
            }
        });
        ivWrongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setStatus(CHECK_WRONG);
                ivRightBtn.setImageResource(R.mipmap.ic_right_gray_unselect);
                ivWrongBtn.setImageResource(R.mipmap.wrong);
            }
        });
    }
}
