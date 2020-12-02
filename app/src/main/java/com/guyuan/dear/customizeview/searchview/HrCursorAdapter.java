package com.guyuan.dear.customizeview.searchview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cursoradapter.widget.CursorAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.db.DearDbManager;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.work.contractPause.beans.DeptBean;
import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 廖华凯
 * @since 2020/7/6 11:57
 **/
public class HrCursorAdapter extends CursorAdapter {

    public HrCursorAdapter(Context context) {
        super(context, null, 0);
    }

    public Disposable updateSuggestionsByKeyWordNameAsync(String keyWord) {
        return Observable.create(new ObservableOnSubscribe<Cursor>() {
            @Override
            public void subscribe(ObservableEmitter<Cursor> emitter) throws Exception {
                Cursor cursor = DearDbManager.getInstance().getDataBase().getStaffDao().getStaffsByName(keyWord);
                emitter.onNext(cursor);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        swapCursor(cursor);
                    }
                });
    }

    public void updateSuggestionsByKeyWordName(String keyWord) {
        Cursor cursor = DearDbManager.getInstance().getDataBase().getStaffDao().getStaffsByName(keyWord);
        swapCursor(cursor);
    }

//    @Override
//    public Cursor swapCursor(Cursor newCursor) {
//        if (newCursor == mCursor) {
//            return null;
//        }
//        Cursor oldCursor = mCursor;
//        if (oldCursor != null) {
//            if (mChangeObserver != null) oldCursor.unregisterContentObserver(mChangeObserver);
//            if (mDataSetObserver != null) oldCursor.unregisterDataSetObserver(mDataSetObserver);
//        }
//        this.mCursor = newCursor;
//        if (newCursor != null) {
//            if (mChangeObserver != null) newCursor.registerContentObserver(mChangeObserver);
//            if (mDataSetObserver != null) newCursor.registerDataSetObserver(mDataSetObserver);
//            mRowIDColumn = newCursor.getColumnIndexOrThrow("_id");
//            mDataValid = true;
//            // notify the observers about the new cursor
//            notifyDataSetChanged();
//        } else {
//            mRowIDColumn = -1;
//            mDataValid = false;
//            // notify the observers about the lack of a data set
//            notifyDataSetInvalidated();
//        }
//        return oldCursor;
//    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_hr_drop_down_suggestion_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);
        rootView.setTag(viewHolder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        StaffBean bean = new StaffBean(cursor);
        viewHolder.tvName.setText(bean.getName());
        viewHolder.tvWorkId.setText("工号:" + bean.getWorkId());
        GlideUtils.getInstance().loadUserCircleImageFromGuYuanServer(viewHolder.ivIcon, bean.getImgUrl());
        StringBuilder sb = new StringBuilder();
        List<DeptBean> depts = bean.getDepts();
        Iterator<DeptBean> iterator = depts.iterator();
        while (iterator.hasNext()) {
            DeptBean next = iterator.next();
            sb.append(next.getDeptName());
            if (iterator.hasNext()) {
                sb.append("/");
            }
        }
        viewHolder.tvLocation.setText(sb.toString());
    }

    public StaffBean getStaffByItemPosition(int position) {
        Cursor cursor = (Cursor) getItem(position);
        return new StaffBean(cursor);
    }

    private class ViewHolder {
        private AppCompatTextView tvName;
        private AppCompatTextView tvLocation;
        private AppCompatImageView ivIcon;
        private AppCompatTextView tvWorkId;

        public ViewHolder(View convertView) {
            tvName = convertView.findViewById(R.id.item_hr_drop_down_suggestion_list_tv_name);
            tvLocation = convertView.findViewById(R.id.item_hr_drop_down_suggestion_list_tv_location);
            tvWorkId = convertView.findViewById(R.id.item_hr_drop_down_suggestion_list_tv_work_id);
            ivIcon = convertView.findViewById(R.id.item_hr_drop_down_suggestion_list_iv_icon);
        }
    }
}
