package com.guyuan.dear.customizeview.itemDecorator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.utils.DimensionUtils;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/13 16:04
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgKnotItemDecorator extends RecyclerView.ItemDecoration {

    private static final float DIVIDER_WIDTH_IN_DP = 50;
    private static final float DIVIDER_HEIGHT_IN_DP = 1;
    private Paint mPaint;

    public ContractPrgKnotItemDecorator() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        int index = parent.getChildAdapterPosition(view);
//        RecyclerView.LayoutManager manager = parent.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            int spanCount = ((GridLayoutManager) manager).getSpanCount();
//            if(index % spanCount == 0){
//                //最左边的item
//                //右边留空间画横线
//                outRect.set(0,0, (int) DimensionUtils.dp2px(DIVIDER_WIDTH_IN_DP),0);
//            }else if(index % spanCount == (spanCount-1)) {
//                //最右边的item
//                //啥也不用干
//            }else {
//                //中间的item
//                //右边留空间画横线
//                outRect.set(0,0, (int) DimensionUtils.dp2px(DIVIDER_WIDTH_IN_DP),0);
//            }
//        }
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager && ((GridLayoutManager) manager).getOrientation() == RecyclerView.VERTICAL) {
            int childCount = parent.getChildCount();
            int spanCount = ((GridLayoutManager) manager).getSpanCount();
            for (int i = 0; i < childCount; i++) {
                if (i == childCount - 1) {
                    continue;
                }
                ViewGroup startChild = (ViewGroup) parent.getChildAt(i);
                ViewGroup endChild = (ViewGroup) parent.getChildAt(i + 1);
                View sphereStart = startChild.getChildAt(0);
                int index = parent.getChildAdapterPosition(startChild);
                int rowIndex = index / spanCount;
                if (index % spanCount != (spanCount - 1)) {
                    int left = startChild.getLeft() + startChild.getWidth() / 2;
                    int top = rowIndex * startChild.getHeight() + (int) (sphereStart.getTop() + sphereStart.getHeight() / 2 - DimensionUtils.dp2px(DIVIDER_HEIGHT_IN_DP) / 2);
                    int right = endChild.getLeft() + endChild.getWidth() / 2;
                    int bottom = (int) (top + DimensionUtils.dp2px(DIVIDER_HEIGHT_IN_DP));
                    c.drawRect(left, top, right, bottom, mPaint);
                }
            }
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }


}
