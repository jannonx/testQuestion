//package com.jannonx.electric.test.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Message;
//
//import androidx.fragment.app.Fragment;
//
//import com.jannonx.electric.base.activity.BaseNoTabActivity;
//import com.jannonx.electric.databinding.ActivityBaseNoTabBinding;
//import com.jannonx.electric.test.bean.TestQuestionBean;
//import com.jannonx.electric.test.data.TestViewModel;
//import com.jannonx.electric.test.fragment.QuestionItemFragment;
//import com.jannonx.electric.utils.CommonUtils;
//import com.jannonx.electric.utils.ConstantValue;
//import com.jannonx.electric.utils.HandlerUtils;
//import com.jannonx.electric.utils.LogUtils;
//import com.jannonx.electric.utils.ThreadPoolFactory;
//import com.jannonx.electric.utils.dbUtil.DBHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dagger.hilt.android.AndroidEntryPoint;
//
///**
// * @description: 做测试题页面
// * @author: Jannonx
// * @since: 2021/1/21 15:40
// */
//@AndroidEntryPoint
//public class ExamActivity extends BaseNoTabActivity<ActivityBaseNoTabBinding, TestViewModel> implements HandlerUtils.OnReceiveMessageListener {
//    private static final int MESSAGE_LIST = 0x123;
//    private HandlerUtils.HandlerHolder holder;
//
//    public static void start(Context context, String title) {
//        Intent intent = new Intent(context, ExamActivity.class);
//        intent.putExtra(ConstantValue.KEY_TITLE, title);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected void initData() {
//        setTitleCenter(getIntent().getStringExtra(ConstantValue.KEY_TITLE));
//        //清空所选数据
//        CommonUtils.getSelectMap().clear();
//        holder = new HandlerUtils.HandlerHolder(this);
//        ThreadPoolFactory.getFactory().getDefaultThreadPool().execute(new Runnable() {
//            @Override
//            public void run() {
//                List<TestQuestionBean> questionList = DBHelper.getInstance().getQuestionList();
//                Message message = new Message();
//                message.what = MESSAGE_LIST;
//                message.obj = questionList;
//                holder.sendMessage(message);
//            }
//        });
//    }
//
//    @Override
//    public void handlerMessage(Message msg) {
//        switch (msg.what) {
//            case MESSAGE_LIST:
//                ArrayList<TestQuestionBean> dataList = (ArrayList<TestQuestionBean>) msg.obj;
//                LogUtils.showLog("fragmentList..size=" + dataList.size());
//                List<Fragment> fragmentList = new ArrayList<>();
//                for (int position = 0; position < dataList.size(); position++) {
//                    fragmentList.add(QuestionItemFragment.newInstance(dataList, position));
//                }
//                setFragmentList(fragmentList);
//                break;
//        }
//    }
//
//
//}
