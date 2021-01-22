package com.jannonx.electric.test.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.jannonx.electric.R;
import com.jannonx.electric.databinding.ActvityExamResultBinding;
import com.jannonx.electric.test.adapter.TagQuestionResultAdapter;
import com.jannonx.electric.test.bean.ItemQuestionType;
import com.jannonx.electric.test.bean.TestQuestionBean;
import com.jannonx.electric.test.data.TestViewModel;
import com.jannonx.electric.utils.CommonUtils;
import com.jannonx.electric.utils.ConstantValue;
import com.jannonx.electric.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description: 测试结果页
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
@AndroidEntryPoint
public class ExamResultActivity extends BaseNoToolbarActivity<ActvityExamResultBinding, TestViewModel> {

    public static void start(Context context, ArrayList<TestQuestionBean> dataList) {
        Intent intent = new Intent(context, ExamResultActivity.class);
        intent.putExtra(ConstantValue.KEY_CONTENT, dataList);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.actvity_exam_result;
    }


    @Override
    protected void initFragment(Bundle savedInstanceState) {
        ArrayList<TestQuestionBean> questionList = (ArrayList<TestQuestionBean>) getIntent().getSerializableExtra(ConstantValue.KEY_CONTENT);

        ArrayList<TestQuestionBean> dealList = new ArrayList<>();
        HashMap<Integer, Integer> selectMap = CommonUtils.getSelectMap();
        LogUtils.showLog("mapSize="+selectMap.size());
        for (int ik = 0; ik < questionList.size(); ik++) {
            LogUtils.showLog("位置ik="+ik);

            TestQuestionBean innerBean = new TestQuestionBean();
            TestQuestionBean testQuestionBean = questionList.get(ik);
            LogUtils.showLog("tile="+testQuestionBean.getTitle());
            innerBean.setId(testQuestionBean.getId());
            innerBean.setTitle(testQuestionBean.getTitle());
            innerBean.setParse(testQuestionBean.getParse());
            LogUtils.showLog("right="+testQuestionBean.getRightItem().getDes());
            LogUtils.showLog("containsKey="+(selectMap.containsKey(ik)));
            if (selectMap.containsKey(ik)) {
                //map存储的是item的index值，从0开始；abcd，对应的值是从1开始
                innerBean.setSelectItem(ItemQuestionType.toType((selectMap.get(ik)+1)));
                LogUtils.showLog("selectMap="+(ItemQuestionType.toType((selectMap.get(ik)+1)).getDes()));
                LogUtils.showLog("result="+innerBean.getResultType().getDes());
            }

            dealList.add(innerBean);
            LogUtils.showLog("---------------");
        }

        TagQuestionResultAdapter staffAdapter = new TagQuestionResultAdapter(this, dealList);
        binding.tflQuestionResult.setAdapter(staffAdapter);
    }
}