package com.guyuan.dear.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.fullScreenShowFile.FullScreenShowActivity;
import com.guyuan.dear.customizeview.flowlayout.FlowLayout;
import com.guyuan.dear.customizeview.flowlayout.TagAdapter;
import com.guyuan.dear.utils.GlideUtils;
import com.guyuan.dear.utils.StringUtils;

import java.util.List;

/**
 * @description: 图片列表adapter
 * @author:Jannonx
 * @date: 2020/6/4 10:10
 */
public class TagPhotoAdapter extends TagAdapter<String> {
    private Context mContext;
    private boolean isDeleteFlag = true;

    public TagPhotoAdapter(Context context, List<String> dataList, boolean isFlag) {
        this(context, dataList);
        isDeleteFlag = isFlag;
    }

    public TagPhotoAdapter(Context context, List<String> dataList) {
        this(dataList);
        mContext = context;
    }

    private TagPhotoAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public View getView(FlowLayout parent, int position, String photoUrl) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_picture,
            parent, false);

        ImageView photoIv = view.findViewById(R.id.pic_iv);
        ImageView deleteIv = view.findViewById(R.id.delete_iv);
        deleteIv.setVisibility(isDeleteFlag ? View.VISIBLE : View.GONE);
        GlideUtils.getInstance().loadUrlImage(photoIv, photoUrl);
        photoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenShowActivity.start(mContext, StringUtils.splicePhotoUrl(getTagDataList()),position);
            }
        });

        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagDatas.remove(position);
                notifyDataChanged();
            }
        });
        return view;
    }


    @Override
    public List<String> getTagDataList() {
        return super.getTagDataList();
    }
}
