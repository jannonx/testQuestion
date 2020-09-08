package com.guyuan.dear.utils;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 字符串处理工具
 * @author:Jannonx
 * @date: 2020/6/2 10:14
 */
public class StringUtils {
    private static final String PHOTO_SPLIT_CHAR = ",";
    private static final String FILE_SPLIT_CHAR = "\\.";

    public static boolean isTextEmpty(EditText editView) {
        if (editView == null) return true;
        return isTextEmpty(editView.getText().toString());
    }

    public static boolean isTextEmpty(TextView editView) {
        if (editView == null) return true;
        return isTextEmpty(editView.getText().toString());
    }


    /**
     * 字符串非空判断
     */
    public static boolean isTextEmpty(String text) {
        return TextUtils.isEmpty(text);
    }


    /**
     * 拼接图片路径
     */
    public static String splicePhotoUrl(List<String> dataList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            stringBuilder.append(i == dataList.size() - 1 ? dataList.get(i) :
                dataList.get(i) + PHOTO_SPLIT_CHAR);
        }
        return stringBuilder.toString();
    }

    /**
     * 分割图片路径
     */
    public static List<String> splitPhotoUrl(String photoUrl) {
        if (TextUtils.isEmpty(photoUrl)) return null;
        if (!photoUrl.contains(PHOTO_SPLIT_CHAR)) return new ArrayList<String>() {{
            add(photoUrl);
        }};
        String[] split = photoUrl.split(PHOTO_SPLIT_CHAR);
        return Arrays.asList(split);
    }

    //拼接文件id和文件后缀
    public static String montage(String id, String url) {
        String[] split = url.split(FILE_SPLIT_CHAR);
        if (split.length > 1) {
            return id + "." + split[1];
        } else {
            return id;
        }
    }

    //去掉后缀
    public static String dropSuffix(String url) {
        String[] split = url.split(FILE_SPLIT_CHAR);
        if (split.length > 0) {
            return split[0];
        } else {
            return url;
        }
    }


    public static boolean isContainChinese(TextView editView) {
        if (editView == null) return true;
        return isContainChinese(editView.getText().toString());
    }


    /**
     * 字符串是否包含中文
     *
     * @param str 待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5" +
            "|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    //导致TextView异常换行的原因：安卓默认数字、字母不能为第一行以后每行的开头字符，因为数字、字母为半角字符
    //所以我们只需要将半角字符转换为全角字符即可，方法如下
    public static String toFullWidthChar(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


}
