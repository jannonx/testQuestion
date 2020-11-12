package com.guyuan.dear.customizeview.itemDecorator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.guyuan.dear.R;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 10:42
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AddCopyListItemDecorator extends RecyclerView.ItemDecoration {
    private Paint paint;

    public AddCopyListItemDecorator() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager &&
                ((GridLayoutManager) layoutManager).getOrientation() == GridLayoutManager.VERTICAL) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                int position = parent.getChildAdapterPosition(parent.getChildAt(childCount)) & spanCount;
                if (position != 0 && position != spanCount - 1 && isInTheSameRow(i, i - 1, spanCount)) {
                    View pre = parent.getChildAt(i - 1);
                    View cur = parent.getChildAt(i);
                    drawDividerRightArrow(c, pre, cur);
                }
            }
        }
    }

    /**
     * 绘制分割图标-右箭头
     *
     * @param c   画布
     * @param pre 上一个子item
     * @param cur 当前子item
     */
    private void drawDividerRightArrow(Canvas c, View pre, View cur) {
        if (pre != null && cur != null) {
            AppCompatImageView curAvatarView = cur.findViewById(R.id.item_add_send_list_iv_user_avatar);
            AppCompatImageView preAvatarView = pre.findViewById(R.id.item_add_send_list_iv_user_avatar);
            if (curAvatarView == null || preAvatarView == null) {
                return;
            }
            Bitmap bitmap = getBitmapAddIcon(pre.getContext());
            if (bitmap != null) {
                int left = pre.getRight() + (cur.getLeft() - pre.getRight()) / 2 - bitmap.getWidth() / 2;
                int top = pre.getTop() + pre.getPaddingTop() + preAvatarView.getHeight() / 2 - bitmap.getHeight() / 2;
                c.drawBitmap(bitmap, left, top, paint);
            }
        }
    }

    /**
     * 判断gridview中俩个子item是不是在同一行。
     *
     * @param curPos    当前item在列表中的下标
     * @param prePos    上一个item在列表中的下标
     * @param spanCount gridview每行的item的个数
     * @return
     */
    private boolean isInTheSameRow(int curPos, int prePos, int spanCount) {
        return curPos / spanCount == prePos / spanCount;
    }

    /**
     * 从本地读取装饰图的资源，转换成bitmap
     *
     * @param context
     * @return
     */
    private Bitmap getBitmapAddIcon(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_svg_add_to_copy_list_16dp);
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable || drawable instanceof VectorDrawableCompat) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return bitmap;
    }
}
