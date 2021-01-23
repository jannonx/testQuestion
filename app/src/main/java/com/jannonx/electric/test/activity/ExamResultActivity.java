package com.jannonx.electric.test.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmlibrary.base.activity.BaseNoToolbarActivity;
import com.jannonx.electric.R;
import com.jannonx.electric.databinding.ActvityExamResultBinding;
import com.jannonx.electric.test.adapter.TagQuestionResultAdapter;
import com.jannonx.electric.test.bean.ItemQuestionResultType;
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

        dealInitData(questionList);
        initListener();
    }


    /**
     * 处理数据
     */
    private void dealInitData(ArrayList<TestQuestionBean> questionList) {
        ArrayList<TestQuestionBean> dealList = new ArrayList<>();
        HashMap<Integer, Integer> selectMap = CommonUtils.getSelectMap();
        LogUtils.showLog("mapSize=" + selectMap.size());
        for (int ik = 0; ik < questionList.size(); ik++) {
            LogUtils.showLog("位置ik=" + ik);

            TestQuestionBean innerBean = new TestQuestionBean();
            TestQuestionBean testQuestionBean = questionList.get(ik);
            LogUtils.showLog("tile=" + testQuestionBean.getTitle());
            innerBean.setId(testQuestionBean.getId());
            innerBean.setTitle(testQuestionBean.getTitle());
            innerBean.setParse(testQuestionBean.getParse());
            innerBean.setRightItem(testQuestionBean.getRightItem());
            innerBean.setItemList(testQuestionBean.getItemList());
            LogUtils.showLog("right=" + testQuestionBean.getRightItem().getDes());
            LogUtils.showLog("containsKey=" + (selectMap.containsKey(ik)));
            if (selectMap.containsKey(ik)) {
                //map存储的是item的index值，从0开始；abcd，对应的值是从1开始
                innerBean.setSelectItem(ItemQuestionType.toType((selectMap.get(ik) + 1)));
                LogUtils.showLog("selectMap=" + (ItemQuestionType.toType((selectMap.get(ik) + 1)).getDes()));
                LogUtils.showLog("result=" + innerBean.getResultType().getDes());
            }
            dealList.add(innerBean);
            LogUtils.showLog("---------------");
        }


        showDealData(dealList);
    }

    /**
     * 显示处理数据
     */
    private void showDealData(ArrayList<TestQuestionBean> dealList) {
        //选对
        int rightNumber = 0;
        //选错，或者不选
        int wrongNumber = 0;

        for (int ij = 0; ij < dealList.size(); ij++) {
            TestQuestionBean testQuestionBean = dealList.get(ij);
            if (ItemQuestionResultType.TYPE_RIGHT == testQuestionBean.getResultType()) {
                rightNumber = rightNumber + 1;
            } else {
                wrongNumber = wrongNumber + 1;
            }
        }
        //选对百分比
        int rateNumber = (int) ((rightNumber * 1.0 / dealList.size()) * 100);
        String rate = "正确率" + rateNumber + "%";
        binding.tvScore.setText(String.valueOf(rateNumber));
        binding.tvRate.setText(rate);
        binding.tvRightNumber.setText(String.valueOf(rightNumber));
        binding.tvWrongNumber.setText(String.valueOf(wrongNumber));
        TagQuestionResultAdapter staffAdapter = new TagQuestionResultAdapter(this, dealList);
        binding.tflQuestionResult.setAdapter(staffAdapter);

    }

    private void initListener() {
        binding.labelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}