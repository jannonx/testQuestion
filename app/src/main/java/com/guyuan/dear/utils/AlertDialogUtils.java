package com.guyuan.dear.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mvvmlibrary.util.LogUtils;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

import static android.app.Activity.RESULT_OK;

/**
 * @author 廖华凯
 * @since 2019/12/3 11:04
 **/
public class AlertDialogUtils {

    private static AlertDialog alertDialog;
    private static Uri imagePickerImageUri;
    private static File outputImageFile;

    private AlertDialogUtils() {
    }

    /**
     * 从页面下方弹出日期轮
     *
     * @param manager
     * @param title
     * @param minDate
     * @param maxDate
     * @param selectedDate
     * @param dialogType
     * @param callback
     */
    public static void pickTime(FragmentManager manager, String title, long minDate, long maxDate,
                                long selectedDate, Type dialogType, OnDateSetListener callback) {
        if (!CommonUtils.isFastDoubleClick()) {
            TimePickerDialog dialog = new TimePickerDialog.Builder()
                    .setCallBack(new OnDateSetListener() {
                        @Override
                        public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                            //这里修正一下日期，把时间定在该日期那天的23点59分59秒
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(millseconds);
                            calendar.set(Calendar.HOUR_OF_DAY,23);
                            calendar.set(Calendar.MINUTE,59);
                            calendar.set(Calendar.SECOND,59);
                            callback.onDateSet(timePickerView,calendar.getTimeInMillis());
                        }
                    })
                    .setCancelStringId("取消")
                    .setSureStringId("确认")
                    .setTitleStringId(title)
                    .setYearText("年")
                    .setMonthText("月")
                    .setDayText("日")
                    .setHourText("时")
                    .setMinuteText("分")
                    .setCyclic(true)
                    .setMinMillseconds(minDate)
                    .setMaxMillseconds(maxDate)
                    .setCurrentMillseconds(selectedDate)
                    .setThemeColor(DearApplication.getInstance().getBaseContext().getResources().getColor(R.color.color_actionbar))
                    .setType(dialogType)
                    .setWheelItemTextNormalColor(DearApplication.getInstance().getBaseContext().getResources().getColor(R.color.timetimepicker_default_text_color))
                    .setWheelItemTextSelectorColor(DearApplication.getInstance().getBaseContext().getResources().getColor(R.color.color_black_000000))
                    .setWheelItemTextSize(12)
                    .build();
            dialog.show(manager, title);
        }

    }

    public static void pickTime(FragmentManager manager, String title, Type dialogType,
                                OnDateSetListener callback) {
        //前后十年
        long selectDate = System.currentTimeMillis();
        long minDate = selectDate - 365L * 24 * 60 * 60 * 1000 * 10;
        long maxDate = selectDate + 365L * 24 * 60 * 60 * 1000 * 10;
        pickTime(manager, title, minDate, maxDate, selectDate, dialogType, callback);
    }


    /**
     * 设置当前选择时间
     *
     * @param manager       上下文
     * @param title         标题
     * @param newSelectTime 现在时间
     * @param dialogType    时间类型
     * @param callback      回调
     */
    public static void pickTime(FragmentManager manager, String title, long newSelectTime, Type dialogType,
                                OnDateSetListener callback) {
        //前后十年
        long selectDate = System.currentTimeMillis();
        long minDate = selectDate - 365L * 24 * 60 * 60 * 1000 * 10;
        long maxDate = selectDate + 365L * 24 * 60 * 60 * 1000 * 10;
        pickTime(manager, title, minDate, maxDate, newSelectTime, dialogType, callback);
    }

    public static void pickTime(FragmentManager manager, String title, Type dialogType, long selectedDate,
                                OnDateSetListener callback) {
        if (selectedDate <= 0) {
            selectedDate = System.currentTimeMillis();
        }
        long minDate = selectedDate - 365L * 24 * 60 * 60 * 1000 * 10;
        minDate = minDate > 0 ? minDate : 0;
        long maxDate = selectedDate + 365L * 24 * 60 * 60 * 1000 * 10;
        pickTime(manager, title, minDate, maxDate, selectedDate, dialogType, callback);

    }

    @Deprecated
    /**
     * 此方法已过期，为了风格统一，请不要再使用。
     */
    public static void pickDate(Context context, String title, DatePickerListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_date_picker, null);
        DatePicker picker = view.findViewById(R.id.dialog_date_picker_selector);
        AppCompatButton btnConfirm = view.findViewById(R.id.dialog_date_picker_btn_confirm);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                calendar.set(Calendar.YEAR, picker.getYear());
                calendar.set(Calendar.MONTH, picker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, picker.getDayOfMonth());
                listener.onGetDatePicked(calendar.getTime());
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alertDialog = builder.setCancelable(true)
                .setTitle(title)
                .setView(view)
                .create();
        alertDialog.show();
    }


    public static void pickYear(Context context, String title, YearPickerListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_year_picker, null);
        DatePicker picker = view.findViewById(R.id.dialog_date_picker_selector);
        AppCompatButton btnConfirm = view.findViewById(R.id.dialog_date_picker_btn_confirm);
        Calendar calendar = Calendar.getInstance();
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                List<Date> dateList = new ArrayList<>();
                dateList.add(DateUtil.getYearFirstDayTime(picker.getYear()));
                dateList.add(DateUtil.getYearLastDayTime(picker.getYear()));
                listener.onSelected(dateList);
            }
        });
        hideMonthAndDay(picker);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alertDialog = builder.setCancelable(true)
                .setTitle(title)
                .setView(view)
                .create();
        alertDialog.show();
    }


    public static void pickYear(Context context, String title, YearStringListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_year_picker, null);
        DatePicker picker = view.findViewById(R.id.dialog_date_picker_selector);
        AppCompatButton btnConfirm = view.findViewById(R.id.dialog_date_picker_btn_confirm);
        Calendar calendar = Calendar.getInstance();
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                listener.onSelected(String.valueOf(picker.getYear()));
            }
        });
        hideMonthAndDay(picker);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alertDialog = builder.setCancelable(true)
                .setTitle(title)
                .setView(view)
                .create();
        alertDialog.show();
    }


    public static void pickYearAndMouth(Context context, String title, YearStringListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_year_picker, null);
        DatePicker picker = view.findViewById(R.id.dialog_date_picker_selector);
        AppCompatButton btnConfirm = view.findViewById(R.id.dialog_date_picker_btn_confirm);
        Calendar calendar = Calendar.getInstance();
        picker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                int month = picker.getMonth() + 1;
                String monthStr;
                if (month < 10) {
                    monthStr = "0" + month;
                } else {
                    monthStr = String.valueOf(month);
                }

                listener.onSelected(picker.getYear() + "-" + monthStr);
            }
        });
        hideDay(picker);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alertDialog = builder.setCancelable(true)
                .setTitle(title)
                .setView(view)
                .create();
        alertDialog.show();
    }


    public interface DatePickerListener {
        void onGetDatePicked(Date date);
    }

    public interface YearPickerListener {
        void onSelected(List<Date> dateList);
    }


    public interface YearStringListener {
        void onSelected(String year);
    }

    public static void showImagePicker(Fragment fragment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
        View rootView =
                LayoutInflater.from(fragment.getContext()).inflate(R.layout.dialog_image_picker, null);
        AppCompatImageView ivCamera = rootView.findViewById(R.id.dialog_image_picker_iv_camera);
        AppCompatImageView ivGallery = rootView.findViewById(R.id.dialog_image_picker_iv_gallery);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                outputImageFile = new File(fragment.getContext().getExternalCacheDir(), "output_image.jpg");
                // 对照片的更换设置
                try {
                    // 如果上一次的照片存在，就删除
                    if (outputImageFile.exists()) {
                        outputImageFile.delete();
                    }
                    // 创建一个新的文件
                    boolean newFile = outputImageFile.createNewFile();
                    LogUtils.showLog("new image file is create:" + newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 如果Android版本大于等于7.0
                if (Build.VERSION.SDK_INT >= 24) {
                    // 将File对象转换成一个封装过的Uri对象
                    imagePickerImageUri = FileProvider.getUriForFile(fragment.getContext(), "com.gy" +
                            ".smartmanagement.cameraalbumtest.fileprovider", outputImageFile);
                } else {
                    // 将File对象转换为Uri对象，这个Uri标识着output_image.jpg这张图片的本地真实路径
                    imagePickerImageUri = Uri.fromFile(outputImageFile);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 指定图片的输出地址为imageUri
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePickerImageUri);
                fragment.startActivityForResult(intent, ConstantValue.REQUEST_CODE_TAKE_PICTURE);
            }
        });
        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                FilePickerBuilder.getInstance()
                        .setMaxCount(1)
                        .setSelectedFiles(new ArrayList<Uri>())
                        .setActivityTheme(R.style.LibAppTheme)
                        .pickPhoto(fragment);
            }
        });
        alertDialog = builder.setView(rootView)
                .setCancelable(true)
                .create();
        alertDialog.show();
    }

    public static void onActivityResultForImagePicker(int requestCode, int resultCode,
                                                      @Nullable Intent data,
                                                      ImagePickerCallback callback) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case ConstantValue.REQUEST_CODE_TAKE_PICTURE:
                callback.onGetLastTakenPicture(outputImageFile.getPath());
                break;
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (data != null) {
                    ArrayList<String> list = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                    callback.onGetSelectedGalleryPicture(list.get(0));
                }
                break;
            default:
                break;
        }
    }


    private static void hideMonthAndDay(DatePicker mDatePicker) {
        try {
            /* 处理android5.0以上的特殊情况 */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
                int monthSpnnerId = Resources.getSystem().getIdentifier("month", "id", "android");
                if (daySpinnerId != 0) {
                    View daySpinner = mDatePicker.findViewById(daySpinnerId);
                    if (daySpinner != null) {
                        daySpinner.setVisibility(View.GONE);
                    }
                }

                if (monthSpnnerId != 0) {
                    View monthSpinner = mDatePicker.findViewById(monthSpnnerId);
                    if (monthSpinner != null) {
                        monthSpinner.setVisibility(View.GONE);
                    }
                }

            } else {
                Field[] datePickerfFields = mDatePicker.getClass().getDeclaredFields();
                for (Field datePickerField : datePickerfFields) {
                    if ("mDaySpinner".equals(datePickerField.getName()) || ("mDayPicker").equals(datePickerField.getName())) {
                        datePickerField.setAccessible(true);
                        Object dayPicker = new Object();
                        try {
                            dayPicker = datePickerField.get(mDatePicker);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        ((View) dayPicker).setVisibility(View.GONE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void hideDay(DatePicker mDatePicker) {
        int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
        if (daySpinnerId != 0) {
            View daySpinner = mDatePicker.findViewById(daySpinnerId);
            if (daySpinner != null) {
                daySpinner.setVisibility(View.GONE);
            }
        }
    }

    public interface ImagePickerCallback {
        void onGetLastTakenPicture(String path);

        void onGetSelectedGalleryPicture(String path);
    }

    public static AlertDialog showLoading(Context context, String title, String msg) {
        SoftReference<Context> softReference = new SoftReference<Context>(context);
        Context softContext = softReference.get();
        View view = LayoutInflater.from(softContext).inflate(R.layout.dialog_loading, null);
        AppCompatTextView tvTitle = view.findViewById(R.id.dialog_loading_tv_title);
        AppCompatTextView tvMsg = view.findViewById(R.id.dialog_loading_tv_msg);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msg);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(softContext);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setWindowAnimations(R.style.loading_dialog_window_animation);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

}
