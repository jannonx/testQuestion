package com.guyuan.dear.focus.qc.views.qcSearchList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseActionBarActivity;
import com.guyuan.dear.R;
import com.guyuan.dear.utils.ConstantValue;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/4 10:31
 * @company: 固远（深圳）信息技术有限公司
 **/
public class QcSearchListActivity extends BaseActionBarActivity {

    public static final int SEARCH_TYPE_ALL = 1;
    public static final int SEARCH_TYPE_ALL_PASS_REPORTS = 2;
    public static final int SEARCH_TYPE_ALL_REJECTED_REPORTS = 3;
    public static final int SEARCH_TYPE_ALL_MY_REPORTS = 4;


    /**
     * @param context
     * @param title
     * @param keyWords   产品名称、代号、出厂编号，字符长度255以内
     * @param searchType {@link QcSearchListActivity#SEARCH_TYPE_ALL}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_PASS_REPORTS}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_REJECTED_REPORTS}
     *                   {@link QcSearchListActivity#SEARCH_TYPE_ALL_MY_REPORTS}
     */
    public static void start(Context context, String title, String keyWords, int searchType) {
        Intent starter = new Intent(context, QcSearchListActivity.class);
        starter.putExtra(ConstantValue.KEY_TITLE, title);
        starter.putExtra(ConstantValue.KEY_KEY_WORD, keyWords);
        starter.putExtra(ConstantValue.KEY_SEARCH_TYPE, searchType);
        context.startActivity(starter);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra(ConstantValue.KEY_TITLE);
        setTitleCenter(title);

        String keyWords = intent.getStringExtra(ConstantValue.KEY_KEY_WORD);
        int searchType = intent.getIntExtra(ConstantValue.KEY_SEARCH_TYPE, SEARCH_TYPE_ALL);

        QcSearchListFragment fragment = QcSearchListFragment.getInstance(keyWords, searchType);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_qc_search_report_list_fragment_container, fragment)
                .commit();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_qc_search_report_list;
    }
}
