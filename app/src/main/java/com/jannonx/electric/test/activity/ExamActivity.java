package com.jannonx.electric.test.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.View;

import com.jannonx.electric.base.activity.BaseNoTabActivity;
import com.jannonx.electric.databinding.ActivityBaseNoTabBinding;
import com.jannonx.electric.test.bean.ExamConditionBean;
import com.jannonx.electric.test.bean.ExamFunctionType;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.test.data.TestViewModel;
import com.jannonx.electric.test.fragment.QuestionItemFragment;
import com.jannonx.electric.test.fragment.QuestionResultFragment;
import com.jannonx.electric.utils.CommonUtils;
import com.jannonx.electric.utils.ConstantValue;
import com.jannonx.electric.utils.HandlerUtils;
import com.jannonx.electric.utils.LogUtils;
import com.jannonx.electric.utils.ThreadPoolFactory;
import com.jannonx.electric.utils.dbUtil.DBHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 做测试题页面
 * @author: Jannonx
 * @since: 2021/1/21 15:40
 */
@AndroidEntryPoint
public class ExamActivity extends BaseNoTabActivity<ActivityBaseNoTabBinding, TestViewModel> implements HandlerUtils.OnReceiveMessageListener {
    private static final int MESSAGE_LIST = 0x123;
    private HandlerUtils.HandlerHolder holder;
    private ArrayList<TestQuestionBean> dataList;
    private Intent intent;


    public static void start(Context context, String title) {
        start(context, title, null);
    }

    public static void start(Context context, String title, ExamConditionBean conditionBean) {
        start(context, title, conditionBean, null);
    }

    public static void start(Context context, String title, ExamConditionBean conditionBean, ArrayList<TestQuestionBean> dataList) {
        Intent intent = new Intent(context, ExamActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        intent.putExtra(ConstantValue.KEY_CONDITION, conditionBean);
        intent.putExtra(ConstantValue.KEY_CONTENT, dataList);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        setTitleCenter(intent.getStringExtra(ConstantValue.KEY_TITLE));
        conditionBean = (ExamConditionBean) intent.getSerializableExtra(ConstantValue.KEY_CONDITION);
        if (conditionBean != null) {
            switch (conditionBean.getFunctionType()) {
                case TYPE_EXAM:
                    queryTestData();
                    break;
                case TYPE_PARSE:
                    parseTestList();
                    break;
                default:
            }
        }

    }


    /**
     * 开始答题模式，加载试题
     */
    private void queryTestData() {
        //清空所选数据
        CommonUtils.getSelectMap().clear();
        holder = new HandlerUtils.HandlerHolder(this);
        ThreadPoolFactory.getFactory().getDefaultThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                List<TestQuestionBean> questionList = DBHelper.getInstance().getQuestionList();
                Message message = new Message();
                message.what = MESSAGE_LIST;
                message.obj = questionList;
                holder.sendMessage(message);
            }
        });
    }

    /**
     * 答案解析
     */
    private void parseTestList() {
        ArrayList<TestQuestionBean> parseList = (ArrayList<TestQuestionBean>) intent.getSerializableExtra(ConstantValue.KEY_CONTENT);
        List<Fragment> fragmentList = new ArrayList<>();
        for (int position = 0; position < parseList.size(); position++) {
            fragmentList.add(QuestionResultFragment.newInstance(parseList, position));
        }
        setFragmentList(fragmentList, conditionBean.getBrowsePosition());
    }


    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_LIST:
                dataList = (ArrayList<TestQuestionBean>) msg.obj;
                LogUtils.showLog("fragmentList..size=" + dataList.size());
                List<Fragment> fragmentList = new ArrayList<>();
                for (int position = 0; position < dataList.size(); position++) {
                    fragmentList.add(QuestionItemFragment.newInstance(dataList, position));
                }
                setFragmentList(fragmentList, 0);
                break;
        }
    }


    @Override
    protected void initListener() {
        super.initListener();
        tvNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamResultActivity.start(ExamActivity.this, dataList);
                finish();
            }
        });

    }
}
