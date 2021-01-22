package com.guyuan.dear.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Message;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import com.guyuan.dear.R;
import com.guyuan.dear.base.activity.BaseNoTabActivity;
import com.guyuan.dear.databinding.ActivityBaseNoTabBinding;
import com.guyuan.dear.test.bean.Question;
import com.guyuan.dear.test.bean.TestQuestionBean;
import com.guyuan.dear.test.data.TestViewModel;
import com.guyuan.dear.test.fragment.QuestionItemFragment;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.HandlerUtils;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ThreadPoolFactory;
import com.guyuan.dear.utils.dbUtil.DBHelper;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @description:
 * @author: 许建宁
 * @since: 2021/1/21 15:40
 * @company: 固远（深圳）信息技术有限公司
 */
@AndroidEntryPoint
public class ExamActivity extends BaseNoTabActivity<ActivityBaseNoTabBinding, TestViewModel> implements HandlerUtils.OnReceiveMessageListener {
    private static final int MESSAGE_LIST = 0x123;
    private HandlerUtils.HandlerHolder holder;
    private int count;
    private int current;
    private boolean wrongMode;//标志变量，判断是否进入错题模式


    public static void start(Context context, String title) {
        Intent intent = new Intent(context, ExamActivity.class);
        intent.putExtra(ConstantValue.KEY_TITLE, title);
        context.startActivity(intent);
    }
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exam);

//        count = list.size();
//        current = 0;
//        wrongMode = false;//默认情况
//
//        final TextView tv_question = findViewById(R.id.tv_question);
//        final AppCompatRadioButton[] radioButtons = new AppCompatRadioButton[4];
//        radioButtons[0] = findViewById(R.id.rb_a);
//        radioButtons[1] = findViewById(R.id.rb_b);
//        radioButtons[2] = findViewById(R.id.rb_c);
//        radioButtons[3] = findViewById(R.id.rb_d);
//        for (int i = 0; i < 4; i++) {
//            switchRadioButton(radioButtons[i], false);
//        }
//        TextView btn_previous = findViewById(R.id.tv_previous);
//        TextView btn_next = findViewById(R.id.tv_next);
//        final TextView tv_parse = findViewById(R.id.tv_parse);
//        final RadioGroup radioGroup = findViewById(R.id.rg_select);
    //为控件赋值
//        Question q = list.get(0);
//        tv_question.setText(q.question);
//        tv_parse.setText(q.parse);
//        radioButtons[0].setText(q.answerA);
//        radioButtons[1].setText(q.answerB);
//        radioButtons[2].setText(q.answerC);
//        radioButtons[3].setText(q.answerD);

//        btn_next.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                if (current < count - 1) {//若当前题目不为最后一题，点击next按钮跳转到下一题；否则不响应
//                    current++;
//                    //更新题目
//                    Question q = list.get(current);
//                    tv_question.setText(q.question);
//                    radioButtons[0].setText(q.answerA);
//                    radioButtons[1].setText(q.answerB);
//                    radioButtons[2].setText(q.answerC);
//                    radioButtons[3].setText(q.answerD);
//                    tv_parse.setText(q.parse);
//
//                    //若之前已经选择过，则应记录选择
//                    radioGroup.clearCheck();
//                    if (q.selectedAnswer != -1) {
//                        radioButtons[q.selectedAnswer].setChecked(true);
//                    }
//
//                }
//                //错题模式的最后一题
//                else if (current == count - 1 && wrongMode == true) {
//                    new AlertDialog.Builder(ExamActivity.this)
//                            .setTitle("提示")
//                            .setMessage("已经到达最后一题，是否退出？")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    ExamActivity.this.finish();
//                                }
//                            })
//                            .setNegativeButton("取消", null)
//                            .show();
//
//                } else {
//                    //当前题目为最后一题时，告知用户作答正确的数量和作答错误的数量，并询问用户是否要查看错题
//                    final List<Integer> wrongList = checkAnswer(list);
//                    //作对所有题目
//                    if (wrongList.size() == 0) {
//                        new AlertDialog.Builder(ExamActivity.this)
//                                .setTitle("提示")
//                                .setMessage("恭喜你全部回答正确！")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        ExamActivity.this.finish();
//                                    }
//                                }).show();
//
//                    } else
//                        new AlertDialog.Builder(ExamActivity.this)
//                                .setTitle("提示")
//                                .setMessage("您答对了" + (list.size() - wrongList.size()) +
//                                        "道题目；答错了" + wrongList.size() + "道题目。")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int which) {
//
//                                        //判断进入错题模式
//                                        wrongMode = true;
//                                        List<Question> newList = new ArrayList<Question>();
//                                        //将错误题目复制到newList中
//                                        for (int i = 0; i < wrongList.size(); i++) {
//                                            newList.add(list.get(wrongList.get(i)));
//                                        }
//                                        //将原来的list清空
//                                        list.clear();
//                                        //将错误题目添加到原来的list中
//                                        for (int i = 0; i < newList.size(); i++) {
//                                            list.add(newList.get(i));
//                                        }
//                                        current = 0;
//                                        count = list.size();
//                                        //更新显示时的内容
//                                        Question q = list.get(current);
//                                        tv_question.setText(q.question);
//                                        radioButtons[0].setText(q.answerA);
//                                        radioButtons[1].setText(q.answerB);
//                                        radioButtons[2].setText(q.answerC);
//                                        radioButtons[3].setText(q.answerD);
//                                        tv_parse.setText(q.parse);
//                                        //显示解析
//                                        tv_parse.setVisibility(View.VISIBLE);
//                                    }
//                                })
//                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int which) {
//                                        //点击取消时，关闭当前activity
//                                        ExamActivity.this.finish();
//                                    }
//                                }).show();
//                }
//            }
//        });
//        btn_previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (current > 0)//若当前题目不为第一题，点击previous按钮跳转到上一题；否则不响应
//                {
//                    current--;
//                    Question q = list.get(current);
//                    tv_question.setText(q.question);
//                    radioButtons[0].setText(q.answerA);
//                    radioButtons[1].setText(q.answerB);
//                    radioButtons[2].setText(q.answerC);
//                    radioButtons[3].setText(q.answerD);
//                    tv_parse.setText(q.parse);
//
//
//                    //若之前已经选择过，则应记录选择
//                    radioGroup.clearCheck();
//                    if (q.selectedAnswer != -1) {
//                        radioButtons[q.selectedAnswer].setChecked(true);
//                    }
//
//                }
//
//            }
//        });
    //选择选项时更新选择
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                for (int i = 0; i < 4; i++) {
//                    if (radioButtons[i].isChecked()) {
//                        list.get(current).selectedAnswer = i;
//                        switchRadioButton(radioButtons[i], true);
//                    } else {
//                        switchRadioButton(radioButtons[i], false);
//                    }
//                }
//
//            }
//        });
//    }


    private void initDataView(ArrayList<Question> obj) {

    }

    private void initListener() {

    }

    /**
     * 切换radioButton状态
     *
     * @param button    组件
     * @param isChecked 选中
     */
    private void switchRadioButton(AppCompatRadioButton button, boolean isChecked) {
        //定义底部标签图片大小和位置
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable = getResources().getDrawable(isChecked ?
                R.drawable.bg_work_project_rb_selector : R.drawable.bg_work_project_rb_unselector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        button.setCompoundDrawables(drawable, null, null, null);
    }

    /*
判断用户作答是否正确，并将作答错误题目的下标生成list,返回给调用者
 */
    private List<Integer> checkAnswer(List<Question> list) {
        List<Integer> wrongList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).answer != list.get(i).selectedAnswer) {
                wrongList.add(i);
            }
        }
        return wrongList;
    }


    @Override
    protected void initData() {
        setTitleCenter(getIntent().getStringExtra(ConstantValue.KEY_TITLE));
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

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_LIST:
                ArrayList<TestQuestionBean> dataList = (ArrayList<TestQuestionBean>) msg.obj;
                LogUtils.showLog("fragmentList..size=" + dataList.size());
                List<Fragment> fragmentList = new ArrayList<>();
                for (int position = 0; position < dataList.size(); position++) {
                    fragmentList.add(QuestionItemFragment.newInstance(dataList, position));
                }
                setFragmentList(fragmentList);
                break;
        }
    }


}
