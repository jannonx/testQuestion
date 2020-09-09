package com.guyuan.dear.customizeview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.guyuan.dear.R;
import com.guyuan.dear.base.app.DearApplication;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.AutoScrollAdapter;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.AutoScrollRecyclerView;
import com.guyuan.dear.customizeview.autoscrollrecyclerview.MessageBean;
import com.guyuan.dear.service.BackService;
import com.guyuan.dear.utils.CommonUtils;
import com.guyuan.dear.utils.ConstantValue;


import java.util.ArrayList;
import java.util.List;

/**
 * created by tl
 * created at 2020/6/10
 * 智能管控控件
 */
public class SmartControlView extends ConstraintLayout {

    AppCompatImageView control_image;
    AutoScrollRecyclerView control_bar;

    private static final int SMART_CONTROL_NORMAL = 0;//正常
    private static final int SMART_CONTROL_EXCEPT = 1;//异常
    private static final int SMART_CONTROL_SERIOUS = 2;//严重

    private AutoScrollAdapter autoScrollAdapter;

    public SmartControlView(Context context) {
        this(context, null);
    }

    public SmartControlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.control_bar, this);
        initView();
        DearApplication.getInstance().startBackService(BackService.NOT_HANDLE_CONTROL_MESSAGE, null);
    }

    private void initView() {
        control_image = findViewById(R.id.control_image);
        control_bar = findViewById(R.id.control_bar);
        List<MessageBean> messageBeanList = new ArrayList<>();
        messageBeanList.add(getNormalMessageBean());
        setControl_bar(messageBeanList);
    }

    //设置智能管控栏数据(接口返回数据)
    public void setControl_bar(List<MessageBean> messageBeans) {
        if (autoScrollAdapter == null) {//初始化
            initViewData(messageBeans);
        } else {
            if (messageBeans == null || messageBeans.size() == 0) {    //无异常消息显示正常消息
                List<MessageBean> messageBeanList = new ArrayList<>();
                messageBeanList.add(getNormalMessageBean());
                autoScrollAdapter.setDatas(messageBeanList);
            } else {
                autoScrollAdapter.setDatas(messageBeans);
            }

            //设置机器人图片
            MessageBean messageBean = autoScrollAdapter.getDatas().get(0);
            int label = messageBean.getLabel();
            setHeadGif(label);

            setScroll();
        }

    }


    //处理推送的智能管控消息
    public void handlePush(MessageBean messageBean) {
        if (messageBean == null) {
            return;
        }
        int isCloseAll = messageBean.getIsCloseAll();//是否关闭该类异常消息
        int isClose = messageBean.getIsClose();//该条异常是否已处理
        int label = messageBean.getLabel();
        List<MessageBean> messageBeanList = autoScrollAdapter.getDatas();
        int position = -1;//获取现有的异常列表是否推送的相同类型bean的位置
        for (int i = 0; i < messageBeanList.size(); i++) {
            MessageBean bean = messageBeanList.get(i);
            if (bean.getUrl().equals(messageBean.getUrl())) {
                position = i;
            }

            if (bean.getLabel() == 0) {       //移除正常消息
                messageBeanList.remove(bean);
            }
        }

        if (position == -1 && isClose != 1) {//异常列表没有推送的异常,并且该异常是未处理的需要加入
            messageBeanList.add(messageBean);
        }

        if (position != -1) {
            MessageBean positionBean = messageBeanList.get(position);//异常列表中存在的与推送相同类型的bean
            if (isCloseAll == 1) {//需要关闭该类异常消息
                messageBeanList.remove(positionBean);//移除该bean
            }
            if (isClose != 1 && positionBean.getLabel() < label) {//如果存在的异常bean等级低于推送的异常bean并且该bean是未处理的状态则加入异常列表
                messageBeanList.remove(positionBean);//移除该bean
                messageBeanList.add(messageBean);//添加高等级该类型异常消息
            }
        }

        //若无异常消息设置正常消息
        if (messageBeanList.size() == 0) {
            messageBeanList.add(getNormalMessageBean());
        }

        autoScrollAdapter.notifyDataSetChanged();

        setScroll();
    }


    private void setScroll() {
        //根据消息条数设置列表是否滚动
        if (autoScrollAdapter.getDatas().size() > 1) {
            control_bar.startScroll();
        } else {
            control_bar.stopScroll();
        }
    }

    //获取无异常时显示的消息
    private MessageBean getNormalMessageBean() {
        MessageBean messageBean = new MessageBean();
        messageBean.setLabel(0);
        messageBean.setUrlWarnContent("公司经营管理正常");
        messageBean.setUrl(AutoScrollAdapter.NORMAL);
        return messageBean;
    }

    //初始化view
    private void initViewData(List<MessageBean> messageBeans) {
        setHeadGif(0);//默认设置正常图片
        if (messageBeans.size() == 0) {
            messageBeans.add(getNormalMessageBean());
        }
        autoScrollAdapter = new AutoScrollAdapter(getContext(), messageBeans);
        autoScrollAdapter.setListener(new AutoScrollAdapter.AutoScrollAdapterListener() {
            @Override
            public void onClick(String url, String urlName) {
                if (url != null && urlName != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantValue.KEY_TITLE, urlName);
                    bundle.putInt(ConstantValue.KEY_EXCEPTION, ConstantValue.STATE_EXCEPTION);
                    if (CommonUtils.checkFocusAction(url)) {
                        startOtherActivityByAction(url, bundle);
                    } else {
                        Toast.makeText(getContext(), "跳转协议错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        control_bar.setDuration(2000);
        control_bar.setLayoutManager(new LinearLayoutManager(getContext()));
        control_bar.setScrollListener(new AutoScrollRecyclerView.OnScrollListener() {
            @Override
            public void afterScroll(int position) {
                MessageBean currentMessageBean = autoScrollAdapter.getMessageBeanByPosition(position);
                int label = currentMessageBean.getLabel();
                setHeadGif(label);
            }
        });
        control_bar.setAdapter(autoScrollAdapter);
    }


    private void setHeadGif(int label) {
        int gifID = SMART_CONTROL_NORMAL == label ?
                R.mipmap.ic_smart_control_normal :
                SMART_CONTROL_EXCEPT == label ? R.mipmap.ic_smart_control_except :
                        R.mipmap.ic_smart_control_serious;
        Glide.with(getContext())
                .load(gifID)
                .into(control_image);
    }

    private void startOtherActivityByAction(String url, Bundle bundle) {
        Intent dataIntent = new Intent();
        StringBuilder stringBuilder = new StringBuilder();
    //    stringBuilder.append(BuildConfig.FLAVOR);
        stringBuilder.append("_");
        stringBuilder.append(url);
        dataIntent.setAction(stringBuilder.toString());
        if (bundle != null) {
            dataIntent.putExtras(bundle);
        }
        getContext().startActivity(dataIntent);
    }
}
