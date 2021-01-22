package com.jannonx.electric.utils.dbUtil;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jannonx.electric.test.bean.ItemQuestionBean;
import com.jannonx.electric.test.bean.ItemQuestionType;
import com.jannonx.electric.test.bean.TestQuestionBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: Jannonx
 * @since: 2021/1/21 21:28
 */

public class DBHelper {
    private SQLiteDatabase database = null;
    private static DBHelper instance = null;

    public static synchronized DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    //在构造函数中打开指定数据库，并把它的引用指向db
    public DBHelper() {
        database = SQLiteDatabase.openDatabase(DBConstant.DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * 获取数据库中的问题
     *
     * @return 选择题集合
     */
    public List<TestQuestionBean> getQuestionList() {
        List<TestQuestionBean> questionList = new ArrayList<>();
        //   Cursor是结果集游标，用于对结果集进行随机访问,其实Cursor与JDBC中的ResultSet作用很相似。
        //   rawQuery()方法的第一个参数为select语句；第二个参数为select语句中占位符参数的值，
        //   如果select语句没有使用占位符，该参数可以设置为null。
        Cursor cursor = database.rawQuery("select * from test", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();//将cursor移动到第一个光标上
            int count = cursor.getCount();
            //将cursor中的每一条记录生成一个question对象，并将该question对象添加到list中
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                TestQuestionBean testQuestionBean = new TestQuestionBean();
                List<ItemQuestionBean> itemList = new ArrayList<>();
                testQuestionBean.setId(cursor.getInt(cursor.getColumnIndex(DBConstant.COLUMN_ID)));
                testQuestionBean.setTitle(cursor.getString(cursor.getColumnIndex(DBConstant.COLUMN_TITLE)));
                ItemQuestionBean itemA = new ItemQuestionBean();
                itemA.setItemType(ItemQuestionType.TYPE_A);
                itemA.setContent(cursor.getString(cursor.getColumnIndex(DBConstant.COLUMN_ITEM_A)));
                itemList.add(new ItemQuestionBean(ItemQuestionType.TYPE_A,
                        cursor.getString(cursor.getColumnIndex(DBConstant.COLUMN_ITEM_A))));
                itemList.add(new ItemQuestionBean(ItemQuestionType.TYPE_B,
                        cursor.getString(cursor.getColumnIndex(DBConstant.COLUMN_ITEM_B))));
                itemList.add(new ItemQuestionBean(ItemQuestionType.TYPE_C,
                        cursor.getString(cursor.getColumnIndex(DBConstant.COLUMN_ITEM_C))));
                itemList.add(new ItemQuestionBean(ItemQuestionType.TYPE_D,
                        cursor.getString(cursor.getColumnIndex(DBConstant.COLUMN_ITEM_D))));
                testQuestionBean.setItemList(itemList);
                //正确选项
                testQuestionBean.setRightItem(ItemQuestionType.toType(
                        cursor.getInt(cursor.getColumnIndex(DBConstant.COLUMN_ANSWER))));
                questionList.add(testQuestionBean);
            }
        }
        return questionList;
    }

}
