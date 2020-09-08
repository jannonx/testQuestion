package tl.com.easy_recycleview_library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

  private final SparseArray<View> viewSparseArray;
  private final Context context;

  public BaseRecyclerViewHolder(Context context, View itemView) {
    super(itemView);
    viewSparseArray = new SparseArray<>();
    this.context = context;
  }

  public <T extends View> T getView(int viewId) {
    return retrieveView(viewId);
  }

  @SuppressWarnings("unchecked")
  private  <T extends View> T retrieveView(int viewId) {
    View view = viewSparseArray.get(viewId);
    if (view == null) {
      view = itemView.findViewById(viewId);
      viewSparseArray.put(viewId, view);
    }
    return (T) view;
  }

  /**
   * Will set the text of a TextView.
   *
   * @param viewId The view id.
   * @param value  The text to put in the text view.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setText(int viewId, String value) {
    TextView view = retrieveView(viewId);
    view.setText(value);
    return this;
  }

  public BaseRecyclerViewHolder setText(int viewId, int resId) {
    TextView view = retrieveView(viewId);
    view.setText(resId);
    return this;
  }

  public BaseRecyclerViewHolder setText(int viewId, CharSequence value) {
    TextView view = retrieveView(viewId);
    view.setText(value);
    return this;
  }



  /**
   * Will set the image of an ImageView from a resource id.
   *
   * @param viewId     The view id.
   * @param imageResId The image resource id.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setImageResource(int viewId, int imageResId) {
    ImageView view = retrieveView(viewId);
    view.setImageResource(imageResId);
    return this;
  }

  /**
   * 设置图像地址
   *
   * @param viewId (viewId)
   * @param url    (图片url)
   * @return 自身
   */
  public BaseRecyclerViewHolder setImageUrl(int viewId, String url) {
    ImageView view = retrieveView(viewId);
    Glide.with(context).load(url).into(view);
    return this;
  }

  public BaseRecyclerViewHolder setImageUrl(int viewId, String url, int errRes) {
    ImageView view = retrieveView(viewId);
    Glide.with(context)
        .load(url)
        .error(errRes)
        .into(view);
    return this;
  }

  /**
   * 默认图
   *
   * @param viewId   (加载的viewid)
   * @param url      (图片地址)
   * @param errRes   (resId)
   * @param placeRes (resId)
   * @return current
   */
  public BaseRecyclerViewHolder setImageUrl(int viewId, String url, int errRes, int placeRes) {
    ImageView view = retrieveView(viewId);
    Glide.with(context)
        .load(url)
        .error(errRes)
        .placeholder(placeRes)
        .into(view);
    return this;
  }

  /**
   * 默认图
   *
   * @param viewId (加载的viewid)
   * @param url    (图片地址)
   * @param err    (resId)
   * @param place  (resId)
   * @return current
   */
  public BaseRecyclerViewHolder setImageUrl(int viewId, String url, Drawable err, Drawable place) {
    ImageView view = retrieveView(viewId);
    Glide.with(context)
        .load(url)
        .error(err)
        .placeholder(place)
        .centerCrop()
        .dontAnimate()
        .into(view);
    return this;
  }

  /**
   * Will set background color of a view.
   *
   * @param viewId The view id.
   * @param color  A color, not a resource id.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setBackgroundColor(int viewId, int color) {
    View view = retrieveView(viewId);
    view.setBackgroundColor(color);
    return this;
  }

  /**
   * Will set background of a view.
   *
   * @param viewId        The view id.
   * @param backgroundRes A resource to use as a background.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setBackgroundRes(int viewId, int backgroundRes) {
    View view = retrieveView(viewId);
    view.setBackgroundResource(backgroundRes);
    return this;
  }



  /**
   * Will set text color of a TextView.
   *
   * @param viewId    The view id.
   * @param textColor The text color (not a resource id).
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setTextColor(int viewId, int textColor) {
    TextView view = retrieveView(viewId);
    view.setTextColor(textColor);
    return this;
  }

  /**
   * Will set text color of a TextView.
   *
   * @param viewId       The view id.
   * @param textColorRes The text color resource id.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setTextColorRes(int viewId, int textColorRes) {
    TextView view = retrieveView(viewId);
    view.setTextColor(ContextCompat.getColor(context, textColorRes));
    return this;
  }

  /**
   * Will set the image of an ImageView from a drawable.
   *
   * @param viewId   The view id.
   * @param drawable The image drawable.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
    ImageView view = retrieveView(viewId);
    view.setImageDrawable(drawable);
    return this;
  }

  /**
   * Add an action to set the image of an image view. Can be called multiple times.
   */
  public BaseRecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
    ImageView view = retrieveView(viewId);
    view.setImageBitmap(bitmap);
    return this;
  }

  /**
   * Add an action to set the alpha of a view. Can be called multiple times.
   * Alpha between 0-1.
   */
  public BaseRecyclerViewHolder setAlpha(int viewId, float value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      retrieveView(viewId).setAlpha(value);
    } else {
      // Pre-honeycomb hack to set Alpha value
      AlphaAnimation alpha = new AlphaAnimation(value, value);
      alpha.setDuration(0);
      alpha.setFillAfter(true);
      retrieveView(viewId).startAnimation(alpha);
    }
    return this;
  }

  /**
   * Set a view visibility to VISIBLE (true) or GONE (false).
   *
   * @param viewId  The view id.
   * @param visible True for VISIBLE, false for GONE.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setVisible(int viewId, boolean visible) {
    View view = retrieveView(viewId);
    view.setVisibility(visible ? View.VISIBLE : View.GONE);
    return this;
  }

  /**
   * Set a view visibility to VISIBLE (true) or GONE (false).
   *
   * @param viewId  The view id.
   * @param visible True for VISIBLE, false for GONE.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setVisibleViewGrpup(int viewId, boolean visible) {
    ViewGroup viewGroup = retrieveView(viewId);
    viewGroup.setVisibility(visible ? View.VISIBLE : View.GONE);
    return this;
  }

  /**
   * Add links into a TextView.
   *
   * @param viewId The id of the TextView to linkify.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder linkify(int viewId) {
    TextView view = retrieveView(viewId);
    Linkify.addLinks(view, Linkify.ALL);
    return this;
  }

  /**
   * Apply the typeface to the given viewId, and enable subpixel rendering.
   */
  public BaseRecyclerViewHolder setTypeface(int viewId, Typeface typeface) {
    TextView view = retrieveView(viewId);
    view.setTypeface(typeface);
    view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    return this;
  }

  /**
   * Apply the typeface to all the given viewIds, and enable subpixel rendering.
   */
  public BaseRecyclerViewHolder setTypeface(Typeface typeface, int... viewIds) {
    for (int viewId : viewIds) {
      TextView view = retrieveView(viewId);
      view.setTypeface(typeface);
      view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
    return this;
  }

  /**
   * Sets the progress of a ProgressBar.
   *
   * @param viewId   The view id.
   * @param progress The progress.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setProgress(int viewId, int progress) {
    ProgressBar view = retrieveView(viewId);
    view.setProgress(progress);
    return this;
  }

  /**
   * Sets the progress and max of a ProgressBar.
   *
   * @param viewId   The view id.
   * @param progress The progress.
   * @param max      The max value of a ProgressBar.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setProgress(int viewId, int progress, int max) {
    ProgressBar view = retrieveView(viewId);
    view.setMax(max);
    view.setProgress(progress);
    return this;
  }

  /**
   * Sets the range of a ProgressBar to 0...max.
   *
   * @param viewId The view id.
   * @param max    The max value of a ProgressBar.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setMax(int viewId, int max) {
    ProgressBar view = retrieveView(viewId);
    view.setMax(max);
    return this;
  }

  /**
   * Sets the rating (the number of stars filled) of a RatingBar.
   *
   * @param viewId The view id.
   * @param rating The rating.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setRating(int viewId, float rating) {
    RatingBar view = retrieveView(viewId);
    view.setRating(rating);
    return this;
  }

  /**
   * Sets the rating (the number of stars filled) and max of a RatingBar.
   *
   * @param viewId The view id.
   * @param rating The rating.
   * @param max    The range of the RatingBar to 0...max.
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setRating(int viewId, float rating, int max) {
    RatingBar view = retrieveView(viewId);
    view.setMax(max);
    view.setRating(rating);
    return this;
  }

  /**
   * Sets the on click listener of the view.
   *
   * @param viewId   The view id.
   * @param listener The on click listener;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
    View view = retrieveView(viewId);
    view.setOnClickListener(listener);
    return this;
  }

  /**
   * Sets the on touch listener of the view.
   *
   * @param viewId   The view id.
   * @param listener The on touch listener;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
    View view = retrieveView(viewId);
    view.setOnTouchListener(listener);
    return this;
  }

  /**
   * Sets the on long click listener of the view.
   *
   * @param viewId   The view id.
   * @param listener The on long click listener;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
    View view = retrieveView(viewId);
    view.setOnLongClickListener(listener);
    return this;
  }

  /**
   * Sets the listview or gridview's item click listener of the view
   *
   * @param viewId   The view id.
   * @param listener The item on click listener;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
    AdapterView view = retrieveView(viewId);
    view.setOnItemClickListener(listener);
    return this;
  }

  /**
   * Sets the listview or gridview's item long click listener of the view
   *
   * @param viewId   The view id.
   * @param listener The item long click listener;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
    AdapterView view = retrieveView(viewId);
    view.setOnItemLongClickListener(listener);
    return this;
  }

  /**
   * Sets the listview or gridview's item selected click listener of the view
   *
   * @param viewId   The view id.
   * @param listener The item selected click listener;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
    AdapterView view = retrieveView(viewId);
    view.setOnItemSelectedListener(listener);
    return this;
  }

  /**
   * Sets the tag of the view.
   *
   * @param viewId The view id.
   * @param tag    The tag;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setTag(int viewId, Object tag) {
    View view = retrieveView(viewId);
    view.setTag(tag);
    return this;
  }

  /**
   * Sets the tag of the view.
   *
   * @param viewId The view id.
   * @param key    The key of tag;
   * @param tag    The tag;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setTag(int viewId, int key, Object tag) {
    View view = retrieveView(viewId);
    view.setTag(key, tag);
    return this;
  }

  /**
   * Sets the checked status of a checkable.
   *
   * @param viewId  The view id.
   * @param checked The checked status;
   * @return The SpaViewHolder for chaining.
   */
  public BaseRecyclerViewHolder setChecked(int viewId, boolean checked) {
    Checkable view = retrieveView(viewId);
    view.setChecked(checked);
    return this;
  }

}
