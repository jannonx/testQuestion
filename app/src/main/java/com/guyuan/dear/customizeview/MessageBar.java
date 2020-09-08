package com.guyuan.dear.customizeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.guyuan.dear.R;

import java.util.List;

/**
 * created by tl
 * created at 2020/6/10
 * 普通消息View
 */
public class MessageBar extends LinearLayout {

    public static final int WORK = 3;
    public static final int OFFICE = 0;

    private LinearLayout toolbar_message_ll;
    private TextView toolbar_message_title_tv;
    private TextView toolbar_message_content_tv;
    private TextView unread_dot;
    private int label;

    public MessageBar(Context context) {
        this(context, null);
    }

    public MessageBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.message_bar, this);
        initView();
    }

    private void initView() {
        toolbar_message_ll = findViewById(R.id.toolbar_message_ll);
        toolbar_message_title_tv = findViewById(R.id.toolbar_message_title_tv);
        toolbar_message_content_tv = findViewById(R.id.toolbar_message_content_tv);
        unread_dot = findViewById(R.id.unread_dot);

        toolbar_message_ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            //    MessageActivity.start(getContext(), label);
            }
        });
    }

    public void setLabel(int label) {
        this.label = label;
    }

//    public void setMessageBar(int unReadNumber, List<MessageBean> messageBeanList) {
//        if (unReadNumber > 0 && messageBeanList != null && messageBeanList.size() > 0) {
//            MessageBean messageBean = messageBeanList.get(0);
//            toolbar_message_title_tv.setText(messageBean.getMsgTitle());
//            toolbar_message_content_tv.setText(messageBean.getMsgContent());
//            unread_dot.setVisibility(VISIBLE);
//            unread_dot.setText(unReadNumber + "");
//        } else {
//            clearMessage();
//        }
//
//    }

    //处理推送消息
//    public void handlePush(MessageBean messageBean) {
//        toolbar_message_title_tv.setText(messageBean.getMsgTitle());
//        toolbar_message_content_tv.setText(messageBean.getMsgContent());
//        unread_dot.setVisibility(VISIBLE);
//        int number = 0;
//        try {
//            number = Integer.parseInt(unread_dot.getText().toString());
//        } catch (Exception e) {
//            number = 0;
//        }
//
//        unread_dot.setText(++number + "");
//    }

    private void clearMessage() {
        toolbar_message_title_tv.setText("");
        toolbar_message_content_tv.setText("");
        unread_dot.setVisibility(View.GONE);
    }

}
