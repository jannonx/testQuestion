package com.guyuan.dear.customizeview.itemDecorator;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.utils.DimensionUtils;

/**
 * 合同进度中，客户签收栏目下部的单据照片列表的item decorator
 *
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/13 17:33
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ClientAcceptanceBillsDecorator extends RecyclerView.ItemDecoration {
    private int outerLeft;
    private int outerTop;
    private int outerRight;
    private int outerBottom;
    private int gapBetween;

    public ClientAcceptanceBillsDecorator() {
        this.outerLeft = (int) DimensionUtils.dp2px(0);
        this.outerTop = (int) DimensionUtils.dp2px(0);
        this.outerRight = (int) DimensionUtils.dp2px(0);
        this.outerBottom = (int) DimensionUtils.dp2px(0);
        this.gapBetween = (int) DimensionUtils.dp2px(8);
    }

    /**
     * 设置外框以及内部item间的空间，以dp为单位。
     *
     * @param outerLeft   外框左边的空间
     * @param outerTop    外框顶部的空间
     * @param outerRight  外框右边的空间
     * @param outerBottom 外框底部的空间
     * @param gapBetween  item之间的空间
     */
    public ClientAcceptanceBillsDecorator(int outerLeft, int outerTop, int outerRight, int outerBottom, int gapBetween) {
        this.outerLeft = (int) DimensionUtils.dp2px(outerLeft);
        this.outerTop = (int) DimensionUtils.dp2px(outerTop);
        this.outerRight = (int) DimensionUtils.dp2px(outerRight);
        this.outerBottom = (int) DimensionUtils.dp2px(outerBottom);
        this.gapBetween = (int) DimensionUtils.dp2px(gapBetween);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager && ((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.VERTICAL) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int position = parent.getChildAdapterPosition(view);
            int columnIndex = position % spanCount;
            int rowIndex = position / spanCount;
            if (columnIndex == 0) {
                //每行第一个item
                outRect.set(outerLeft, outerTop + gapBetween * Math.min(rowIndex, 1), gapBetween, outerBottom);
            } else if (columnIndex == spanCount-1) {
                //每行最后一个item
                outRect.set(0, outerTop + gapBetween * Math.min(rowIndex, 1), outerRight, outerBottom);
            } else {
                //中间的item
                outRect.set(0, outerTop + gapBetween * Math.min(rowIndex, 1), gapBetween, outerBottom);
            }
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
