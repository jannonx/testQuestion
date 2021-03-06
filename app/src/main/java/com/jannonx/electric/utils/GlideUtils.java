package com.jannonx.electric.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jannonx.electric.R;
import com.jannonx.electric.base.app.DearApplication;


/**
 * @author Jannonx
 * @since 2019/12/18 19:26
 **/
public class GlideUtils {
    private static GlideUtils instance;
    public static final int NORMAL = 0x001;
    public static final int ROUND_RADIUS = 0x002;
    public static final int CIRCLE = 0x003;

    private GlideUtils() {
    }

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }





    public void loadRoundedCornersImageFromGuYuanServer(ImageView target,
                                                        String guYuanUploadedPicId) {
        String url = guYuanUploadedPicId;
        Glide.with(target)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)))
                .into(target);
    }

    public void loadImageFromGuYuanServer(ImageView target, String guYuanUploadedPicId) {
        String url = guYuanUploadedPicId;
        Glide.with(target)
                .load(url)
                .into(target);
    }

    public void loadImageFromGuYuanServer(ImageView target, String guYuanUploadedPicId,
                                          int placeHolderSrcId) {
        String url = guYuanUploadedPicId;
        Glide.with(target)
                .load(url)
                .placeholder(placeHolderSrcId)
                .into(target);
    }

    public void loadUrlImage(ImageView target, String picUrl) {
        Glide.with(target)
                .load(picUrl)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(target);
    }


    public void loadRoundUriImage(ImageView target, String picUrl, int roundingRadius) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);

        Glide.with(target).load(picUrl)
                .apply(options)
                .into(target);

    }


    public void loadImageInRound(ImageView target, String picUrl, int roundingRadius) {
        Glide.with(target).load(picUrl)
                //    .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CenterCrop(), new GlideRoundTransform(DearApplication.getInstance(), roundingRadius))
                .into(target);
    }

    public void loadLocalImage(ImageView target, int resId) {
        Glide.with(target)
                .load(resId)
                .into(target);
    }
}
